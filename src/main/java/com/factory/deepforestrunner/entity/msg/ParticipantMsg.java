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

import java.io.Serializable;

/**
 * Participant data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 16.07.2021.
 */
public final class ParticipantMsg implements Serializable {

    public final static ParticipantMsg INSTANCE = new ParticipantMsg();

    public static final String id = "Ид";
    public static final String fio = "ФИО";
    public static final String gender = "Пол";
    public static final String birthday = "Дата";
    public static final String activities = "Активность";
    public static final String subdivision = SubdivisionMsg.name;
    public static final String number = SubdivisionMsg.number;
}