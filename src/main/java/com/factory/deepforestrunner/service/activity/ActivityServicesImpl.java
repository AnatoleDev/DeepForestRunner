/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.service.activity;

import com.factory.deepforestrunner.dao.ActivityDao;
import com.factory.deepforestrunner.entity.Participant;
import com.factory.deepforestrunner.service.ActivityServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ActivityServicesImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
@Service
@RequiredArgsConstructor
public class ActivityServicesImpl implements ActivityServices {

    private final ActivityDao activityDao;

    @Override
    public void clearAll() {
        activityDao.clearAll();
    }

    @Override
    public void createAll(
        final List<Participant> participants,
        final Map<String, Long> partMap
    ) {
        participants.forEach(
            participant -> activityDao.createAll(
                participant.getActivities(),
                partMap.get(participant.getFio())
            )
        );

    }
}
