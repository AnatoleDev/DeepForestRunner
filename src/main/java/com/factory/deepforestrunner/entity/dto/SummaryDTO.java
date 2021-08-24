/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.entity.dto;

import com.factory.deepforestrunner.common.Gender;
import com.factory.deepforestrunner.common.Place;
import com.factory.deepforestrunner.entity.model.Participant;
import com.factory.deepforestrunner.entity.model.RunnerSummary;
import com.factory.deepforestrunner.entity.model.Subdivision;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Map;

import static com.factory.deepforestrunner.util.CommonUtil.nvl;

/**
 * SummaryDTO data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 13.08.2021.
 */
@Setter
@Getter
@Accessors(chain = true)
public class SummaryDTO implements Serializable {

    private String subName;
    private Integer subNumber;
    private String partName;
    private String partGender;
    private LocalTime total;
    private LocalTime best;
    private LocalTime command;
    private String place;
    private Integer eBalls;

    public static SummaryDTO toDTO(
        final RunnerSummary runnerSummary,
        final Map<Long, Participant> participantMap,
        final Map<Long, Subdivision> subdivisionMap
    ) {
        final Subdivision subdivision = subdivisionMap.getOrDefault(runnerSummary.getSubdivisionId(), new Subdivision());
        final Participant participant = participantMap.getOrDefault(runnerSummary.getParticipantId(), new Participant());
        return new SummaryDTO()
            .setSubName(subdivision.getName())
            .setSubNumber(subdivision.getNumber())
            .setPartName(participant.getFio())
            .setPartGender(nvl(participant.getGender(), Gender::getRus))
            .setTotal(runnerSummary.getTotal())
            .setBest(runnerSummary.getBest())
            .setCommand(runnerSummary.getCommand())
            .setPlace(nvl(runnerSummary.getPlace(), Place::getRim))
            .setEBalls(nvl(runnerSummary.getPlace(), Place::getEBalls));
    }
}