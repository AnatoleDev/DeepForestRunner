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

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

import static java.util.Objects.nonNull;

/**
 * RunnerServiceImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
@Service
@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

    private static final Function<Integer, LocalTime> START_TIME = number -> LocalTime.of(0, 0, 0).plusSeconds(number * 60);

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

    @Override
    public void setNumber(
        Long id,
        Integer number
    ) {
        runnerDao.setNumber(id, number, START_TIME.apply(number));
    }

    @Override
    public void setFinish(
        Long id,
        LocalTime finish
    ) {
        LocalTime totalTime = null;

        final Runner runner = runnerDao.getRunnerByid(id);

        if (nonNull(runner) && nonNull(runner.getStart()) && nonNull(finish)) {
            totalTime = finish.minusSeconds(runner.getStart().toSecondOfDay());
        }
        runnerDao.setFinish(id, finish, totalTime);
    }

    @Override
    public void setKp(
        Long id,
        Integer kp
    ) {
        runnerDao.setKp(id, kp);
    }
}
