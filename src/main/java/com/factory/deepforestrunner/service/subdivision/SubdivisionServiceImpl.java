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
import com.factory.deepforestrunner.entity.Subdivision;
import com.factory.deepforestrunner.service.SubdivisionService;
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

    @Override
    public List<Subdivision> list() {
        return subdivisionDao.list();
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
}
