/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.service.subdivision;

import com.factory.deepforestrunner.dao.SubdivisionDao;
import com.factory.deepforestrunner.entity.model.Subdivision;
import com.factory.deepforestrunner.service.ParticipantService;
import com.factory.deepforestrunner.service.SubdivisionService;
import com.factory.deepforestrunner.service.participant.ParticipantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SubdivisionServiceImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
@Service
@RequiredArgsConstructor
public class SubdivisionServiceImpl implements SubdivisionService {

    private final SubdivisionDao subdivisionDao;
    private final ParticipantService participantService;

    @Override
    public List<Subdivision> list() {
        return subdivisionDao.list();
    }

    @Override
    public Subdivision get(final Long id) {
        return subdivisionDao.get(id);
    }



    @Override
    public void createAll(
        final List<Subdivision> createdSubdivisions
    ) {
        subdivisionDao.createAll(createdSubdivisions);
    }

    @Override
    public void clearAll() {
        subdivisionDao.clearAll();
    }

    @Override
    public void update(
        final Subdivision subdivision,
        final Long id
    ) {
        subdivisionDao.update(subdivision, id);
    }

    @Override
    public void create(final Subdivision subdivision) {
        subdivisionDao.create(subdivision);
    }

    @Override
    public void delete(final Long id) {
        participantService.clearSubdivision(id);
        subdivisionDao.delete(id);
    }
}
