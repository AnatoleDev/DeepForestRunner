/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.rest.runner.util;

import com.factory.deepforestrunner.entity.Participant;
import com.factory.deepforestrunner.entity.Runner;
import com.factory.deepforestrunner.entity.Subdivision;
import com.factory.deepforestrunner.entity.dto.RunnerDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import static com.factory.deepforestrunner.util.CommonUtil.DATE_TIME_HH_MM_SS_FORMATTER;
import static com.factory.deepforestrunner.util.CommonUtil.nvl;

/**
 * RunnerUtil data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RunnerUtil {

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
            .setGender(nvl(participant.getGender(), Enum::name))
            .setStart(nvl(runner.getStart(), date -> date.format(DATE_TIME_HH_MM_SS_FORMATTER)))
            .setFinish(nvl(runner.getFinish(), date -> date.format(DATE_TIME_HH_MM_SS_FORMATTER)))
            .setTotal(nvl(runner.getTotal(), date -> date.format(DATE_TIME_HH_MM_SS_FORMATTER)))
            .setKp(runner.getKp());
    }
}