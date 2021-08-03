/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.util;

import com.factory.deepforestrunner.common.Activity;
import com.factory.deepforestrunner.common.Gender;
import com.factory.deepforestrunner.entity.model.Participant;
import com.factory.deepforestrunner.entity.model.Subdivision;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.factory.deepforestrunner.util.CommonUtil.nvl;

/**
 * ParseUtil data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ParseUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Parse subdivision subdivision.
     *
     * @param row       the row
     * @param formatter the formatter
     * @return the subdivision
     */
    public static Subdivision parseSubdivision(
        final Row row,
        final DataFormatter formatter
    ) {
        return new Subdivision()
            .setNumber(
                Optional.ofNullable(parseCell(row, 1, formatter))
                    .map(Integer::parseInt)
                    .orElse(0))
            .setName(parseCell(row, 2, formatter))
            .setCaptain(parseCell(row, 3, formatter))
            .setPhone(parseCell(row, 4, formatter));
    }

    /**
     * Parse participant participant.
     *
     * @param row                  the row
     * @param formatter            the formatter
     * @param stringSubdivisionMap the string subdivision map
     * @return the participant
     */
    public static Participant parseParticipant(
        final Row row,
        final DataFormatter formatter,
        final Map<String, Subdivision> stringSubdivisionMap
    ) {
        return new Participant()
            .setFio(parseCell(row, 1, formatter))
            .setGender(nvl(parseCell(row, 2, formatter), Gender::byRus))
            .setActivities(nvl(parseCell(row, 3, formatter), ACTIVITIES))
            .setBirthday(
                nvl(parseCell(row, 5, formatter), strDate -> LocalDate.parse(strDate, DATE_FORMAT)))
            .setSubdivisionId(nvl(parseCell(row, 0, formatter), name -> stringSubdivisionMap.get(name).getId()));
    }

    private static final Function<String, List<Activity>> ACTIVITIES =
        str -> Arrays.stream(str.split(" "))
            .distinct()
            .map(Activity::byRus)
            .collect(Collectors.toList());


    /**
     * Parse cell string.
     *
     * @param row       the row
     * @param cellIndex the cell index
     * @param formatter the formatter
     * @return the string
     */
    public static String parseCell(
        final Row row,
        final int cellIndex,
        final DataFormatter formatter
    ) {
        final Cell cell = row.getCell(cellIndex, Row.RETURN_BLANK_AS_NULL);

        if (cell == null) {
            return null;
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

            switch (cell.getCachedFormulaResultType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case Cell.CELL_TYPE_STRING:
                    return cell.getRichStringCellValue().getString();
            }
        } else {
            return formatter.formatCellValue(cell);
        }

        return null;
    }
}