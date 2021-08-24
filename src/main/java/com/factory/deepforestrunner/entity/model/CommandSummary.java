/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.entity.model;

import com.factory.deepforestrunner.common.Place;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalTime;

/**
 * RunnerSummary data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 13.08.2021.
 */
@Getter
@Setter
@Accessors(chain = true)
public class CommandSummary {

    private Long subdivisionId;
    private Long participantId;
    private LocalTime total;
    private LocalTime best;
    private LocalTime command;
    private Place place;

//    private int kp;
    private int penaltySec;
}
