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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;

import static java.util.Objects.nonNull;

/**
 * SqlUtil data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SqlUtil {

    @SneakyThrows
    public static void SQL_long(
        final PreparedStatement ps,
        int psCount,
        final Long val
    ) {
        if (nonNull(val)) ps.setLong(psCount, val);
    }

    @SneakyThrows
    public static void SQL_int(
        final PreparedStatement ps,
        int psCount,
        final Integer val
    ) {
        if (nonNull(val)) ps.setInt(psCount, val);
    }

}
