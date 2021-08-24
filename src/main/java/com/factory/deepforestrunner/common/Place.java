/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * Place data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 13.08.2021.
 */
@Getter
@RequiredArgsConstructor
public enum Place {

    /**
     * One place.
     */
    ONE(1, "I", 30),
    /**
     * Two place.
     */
    TWO(2, "II", 28),
    /**
     * Three place.
     */
    THREE(3, "III", 26),
    /**
     * Four place.
     */
    FOUR(4, "IV", 24),
    /**
     * Five place.
     */
    FIVE(5, "V", 22),
    /**
     * Six place.
     */
    SIX(6, "VI", 20),
    /**
     * Seven place.
     */
    SEVEN(7, "VII", 18),
    /**
     * Eight place.
     */
    EIGHT(8, "VIII", 16),
    /**
     * Nine place.
     */
    NINE(9, "IX", 14),
    /**
     * Ten place.
     */
    TEN(10, "X", 12),
    /**
     * Eleven place.
     */
    ELEVEN(11, "XI", 10),
    /**
     * Twelve place.
     */
    TWELVE(12, "XII", 8),
    /**
     * Thirteen place.
     */
    THIRTEEN(13, "XIII", 6),
    /**
     * Fourteen place.
     */
    FOURTEEN(14, "XIV", 4),
    /**
     * Fifteen place.
     */
    FIFTEEN(15, "XV", 2),
    /**
     * Sixteen place.
     */
    SIXTEEN(16, "XVI", 0);

    private final int number;
    private final String rim;
    private final int eBalls;

    /**
     * By int place.
     *
     * @param i the
     * @return the place
     */
    public static Place byInt(int i) {
        return Arrays.stream(Place.values()).filter(p -> p.getNumber() == i).findFirst().orElse(null);
    }

}
