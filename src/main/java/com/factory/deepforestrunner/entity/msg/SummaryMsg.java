/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.entity.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * SummaryDTO data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 13.08.2021.
 */
public class SummaryMsg implements Serializable {

    public final static SummaryMsg INSTANCE = new SummaryMsg();

    public static final String subName = SubdivisionMsg.name;
    public static final String subNumber = SubdivisionMsg.number;
    public static final String partName = ParticipantMsg.fio;
    public static final String partGender = ParticipantMsg.gender;
    public static final String total = "Финиш";
    public static final String best = "Три лучших результата";
    public static final String command = "Время команды";
    public static final String place = "Место";
    public static final String eBalls = "Баллы";

}
