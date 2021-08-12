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

import com.factory.deepforestrunner.common.Gender;
import com.factory.deepforestrunner.dao.RunnerDao;
import com.factory.deepforestrunner.entity.model.Participant;
import com.factory.deepforestrunner.entity.model.Runner;
import com.factory.deepforestrunner.entity.model.Subdivision;
import com.factory.deepforestrunner.service.RunnerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * RunnerServiceImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
@Service
@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

    private static final Integer BETWEEN_NUMBER = 5;
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

    @Override
    public void initNumber(
        final List<Subdivision> subdivisions,
        final List<Participant> participants
    ) {
        final Map<Gender, List<RunnerNumber>> genderRunnerMap = getGenderRunnerMap(subdivisions, participants);

        final AtomicInteger number = new AtomicInteger(0);

        initRunnerNumber(
            genderRunnerMap.get(Gender.M).stream().collect(Collectors.groupingBy(RunnerNumber::getSubNumber)),
            number,
            number.getAndIncrement()
        );


        number.set(number.get() + BETWEEN_NUMBER);

        initRunnerNumber(
            genderRunnerMap.get(Gender.F).stream().collect(Collectors.groupingBy(RunnerNumber::getSubNumber)),
            number,
            number.get() - 1
        );

    }

    private Map<Gender, List<RunnerNumber>> getGenderRunnerMap(
        List<Subdivision> subdivisions,
        List<Participant> participants
    ) {
        final Map<Long, Subdivision> subdivisionMap = subdivisions.stream()
            .collect(Collectors.toMap(Subdivision::getId, Function.identity()));

        final Map<Long, Participant> participantMap = participants.stream()
            .collect(Collectors.toMap(Participant::getId, Function.identity()));

        return list().stream().map(runner -> {
                final Participant participant = participantMap.getOrDefault(runner.getParticipantId(), new Participant());
                final Subdivision subdivision = subdivisionMap.getOrDefault(participant.getSubdivisionId(), new Subdivision());

                return new RunnerNumber(
                    runner.getId(),
                    subdivision.getNumber(),
                    participant.getGender()
                );
            }
        ).collect(Collectors.groupingBy(RunnerNumber::getGender));
    }

    private void initRunnerNumber(
        final Map<Integer, List<RunnerNumber>> runnerNumbers,
        final AtomicInteger number,
        int lastNumber
    ) {
        int maxMaleCount = runnerNumbers.values().stream().map(List::size).max(Integer::compareTo).orElse(0);
        int maxSubMaleCount = runnerNumbers.keySet().stream().max(Integer::compareTo).orElse(0);
        for (int i = 0; i < maxMaleCount; i++) {

            for (int j = 1; j <= maxSubMaleCount; j++) {

                int finalI = i;
                Optional.ofNullable(runnerNumbers.get(j))
                    .flatMap(
                        list -> list.stream()
                            .skip(finalI)
                            .findFirst())
                    .ifPresent(
                        runnerNumber -> {
                            final int andIncrement = number.getAndIncrement();
                            runnerDao.setNumber(runnerNumber.runnerId, andIncrement, START_TIME.apply(andIncrement - lastNumber));
                        });
            }
        }
    }

    @Getter
    @RequiredArgsConstructor
    private class RunnerNumber {

        private final Long runnerId;
        private final Integer subNumber;
        private final Gender gender;
    }
}
