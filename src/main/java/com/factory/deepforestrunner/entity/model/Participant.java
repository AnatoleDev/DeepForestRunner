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

import com.factory.deepforestrunner.common.Activity;
import com.factory.deepforestrunner.common.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Participant data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 16.07.2021.
 */
@Setter
@Getter
@Accessors(chain = true)
public class Participant implements Serializable {

    private Long id;
    private String fio;
    private Gender gender;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
    private List<Activity> activities;
    private Long subdivisionId;
}