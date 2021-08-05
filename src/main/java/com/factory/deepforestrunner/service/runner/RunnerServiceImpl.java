/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.service.runner;

import com.factory.deepforestrunner.dao.RunnerDao;
import com.factory.deepforestrunner.entity.model.Participant;
import com.factory.deepforestrunner.entity.model.Runner;
import com.factory.deepforestrunner.service.RunnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RunnerServiceImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
@Service
@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

    private final RunnerDao runnerDao;

    @Override
    public List<Runner> list() {
        return runnerDao.list();
    }

    @Override
    public void createAll(List<Participant> participants) {
        runnerDao.createAll(participants);
    }

    @Override
    public void clearAll() {
        runnerDao.clearAll();
    }

    @Override
    public void deleteByParticipant(final Long participantId) {
        runnerDao.deleteByParticipant(participantId);
    }

    @Override
    public void create(final Participant participant) {
        runnerDao.create(participant);
    }
}
