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
 * Activity data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
@Getter
@RequiredArgsConstructor
public enum Activity {
    O("Спортивное ориентирование", "С"),
    OP("Спортивное ориентирование (практика)", "СП");

    private final String messages;
    private final String rus;

    public static Activity byRus(final String text) {
        return Arrays.stream(Activity.values())
            .filter(g -> g.getRus().equals(text))
            .findFirst().orElse(null);
    }
}
