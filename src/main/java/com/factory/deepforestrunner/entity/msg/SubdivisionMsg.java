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
 * Subdivision data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 18.07.2021.
 */
public final class SubdivisionMsg implements Serializable {

    public final static SubdivisionMsg INSTANCE = new SubdivisionMsg();

    public static final String id = "id";
    public static final String name = "Название команды";
    public static final String number = "Жеребьевка";
    public static final String captain = "Капитан";
    public static final String phone = "Телефон";

}