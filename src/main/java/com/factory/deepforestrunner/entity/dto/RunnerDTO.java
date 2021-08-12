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
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * RunnerDTO data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 06.08.2021.
 */
@Setter
@Getter
@Accessors(chain = true)
public class RunnerDTO implements Serializable {

    private Long id;
    private String subdivision;
    private String participant;
    private Integer number;
    private Gender gender;

    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime start;
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime finish;
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime total;
    private Integer kp;

}
