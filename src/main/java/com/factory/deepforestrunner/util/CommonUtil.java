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

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Function;

/**
 * CommonUtil data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 22.07.2021.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonUtil {

    /**
     * The constant DATE_FORMATTER.
     */
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * The constant DATE_TIME_FORMATTER.
     */
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    /**
     * The constant DATE_TIME_FORMATTER.
     */
    public final static DateTimeFormatter DATE_TIME_HH_MM_SS_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Nvl t.
     *
     * @param <V> the type parameter
     * @param <T> the type parameter
     * @param val the val
     * @param fun the fun
     * @return the t
     */
    public static <V, T> T nvl(
        final V val,
        final Function<V, T> fun
    ) {
        return Objects.isNull(val) ? null : fun.apply(val);
    }

    /**
     * The constant DATE_2_LOCAL_DATE.
     */
    public static final Function<Date, LocalDate> DATE_2_LOCAL_DATE =
        date -> nvl(date.getTime(), d -> new Date(d).toLocalDate());

    /**
     * The constant DATE_2_LOCAL_DATE_TIME.
     */
    public static final Function<Date, LocalDateTime> DATE_2_LOCAL_DATE_TIME =
        date -> nvl(date, d -> new Timestamp(d.getTime()).toLocalDateTime());

    /**
     * The constant LOCAL_DATE_TIME_2_TIMESTAMP.
     */
    public static final Function<LocalDateTime, Timestamp> LOCAL_DATE_TIME_2_TIMESTAMP =
        dateTime -> nvl(dateTime, Timestamp::valueOf);

    /**
     * The constant LOCAL_DATE_2_DATE.
     */
    public static final Function<LocalDate, Date> LOCAL_DATE_2_DATE =
        localDate -> nvl(localDate, Date::valueOf);


    /**
     * The constant DATE_2_LOCAL_DATE.
     */
    public static final Function<java.util.Date, LocalDate> JAVA_DATE_2_LOCAL_DATE =
        date -> nvl(date.getTime(), d -> new Date(d).toLocalDate());


//    public Date convertToDateViaInstant(LocalDate dateToConvert) {
//        return java.util.Date.from(dateToConvert.atStartOfDay()
//            .atZone(ZoneId.systemDefault())
//            .toInstant());
//    }

}
