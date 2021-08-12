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

import com.factory.deepforestrunner.entity.dto.RunnerDTO;
import com.factory.deepforestrunner.entity.model.Participant;
import com.factory.deepforestrunner.entity.model.Runner;
import com.factory.deepforestrunner.entity.model.Subdivision;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * RunnerUtil data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 06.08.2021.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RunnerUtil {

    //    private static final LocalTime START_TIME = LocalTime.of(0, 0, 0);


    /**
     * Runner 2 dto runner dto.
     *
     * @param runner      the runner
     * @param participant the participant
     * @param subdivision the subdivision
     * @return the runner dto
     */
    public static RunnerDTO runner_2_dto(
        final Runner runner,
        final Participant participant,
        final Subdivision subdivision
    ) {
        return new RunnerDTO()
            .setId(runner.getId())
            .setSubdivision(subdivision.getName())
            .setParticipant(participant.getFio())
            .setNumber(runner.getNumber())
            .setGender(participant.getGender())
            .setStart(runner.getStart())
            .setFinish(runner.getFinish())
            .setTotal(runner.getTotal())
            .setKp(runner.getKp());
    }
}
