/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.service.file;

import com.factory.deepforestrunner.dao.FileDao;
import com.factory.deepforestrunner.entity.File;
import com.factory.deepforestrunner.entity.Participant;
import com.factory.deepforestrunner.entity.Subdivision;
import com.factory.deepforestrunner.service.ActivityServices;
import com.factory.deepforestrunner.service.FileService;
import com.factory.deepforestrunner.service.ParticipantService;
import com.factory.deepforestrunner.service.RunnerService;
import com.factory.deepforestrunner.service.SubdivisionService;
import com.factory.deepforestrunner.util.ParseUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.factory.deepforestrunner.util.ParseUtil.parseCell;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

/**
 * FileServiceImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileDao fileDao;
    private final SubdivisionService subdivisionService;
    private final ParticipantService participantService;
    private final ActivityServices activityServices;
    private final RunnerService runnerService;

    @Override
    @Transactional
    public void create(
        final MultipartFile file
    ) {
        try {

            clearSystem();

            fileDao.create(file);

            final ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(file.getBytes());
            final Workbook workbook = WorkbookFactory.create(arrayInputStream);

            subdivisionService.createAll(createdSubdivision(workbook));
            participantService.createAll(createdParticipant(workbook));

            runnerService.createAll();

            arrayInputStream.close();
        } catch (IOException | InvalidFormatException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File getFile() {
        return fileDao.getFile();
    }

    private void clearSystem() {
        runnerService.clearAll();
        activityServices.clearAll();
        participantService.clearAll();
        subdivisionService.clearAll();
        fileDao.clearAll();
    }

    private List<Participant> createdParticipant(
        final Workbook workbook
    ) throws ParseException {

        final List<Participant> participants = new ArrayList<>();
        final Sheet sheet = workbook.getSheetAt(1);
        final DataFormatter formatter = new DataFormatter();
        formatter.addFormat("m/d/yy", new java.text.SimpleDateFormat("dd.MM.yyyy"));

        final Map<String, Subdivision> stringSubdivisionMap = subdivisionService.list().stream()
            .collect(Collectors.toMap(Subdivision::getName, Function.identity()));
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            final Row row = sheet.getRow(i);

            if (isNotBlank(parseCell(row, 0, formatter))) {
                participants.add(ParseUtil.parseParticipant(row, formatter, stringSubdivisionMap));
            }
        }
        return participants;
    }

    private List<Subdivision> createdSubdivision(Workbook workbook) {

        final List<Subdivision> subdivisions = new ArrayList<>();
        final Sheet sheet = workbook.getSheetAt(0);
        final DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            final Row row = sheet.getRow(i);

            if (isNotBlank(parseCell(row, 1, formatter))) {
                subdivisions.add(ParseUtil.parseSubdivision(row, formatter));
            }
        }
        return subdivisions;
    }
}