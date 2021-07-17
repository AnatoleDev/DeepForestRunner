/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.rest;

import com.factory.deepforestrunner.entity.Runner;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DeepController data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 14.07.2021.
 */
@Controller
@RequiredArgsConstructor
public class DeepController {

    private final JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String start() {
        return "redirect:/runner";
    }

    @GetMapping("/runner")
    @ResponseBody
    public String runner(
        @RequestParam(value = "name", defaultValue = "Ivan") String name
    ) {
        //Insert a record:
        final String insert = String.format("INSERT INTO runners VALUES (NULL, '%s')", name);
        jdbcTemplate.execute(insert);

        List<Runner> runners = jdbcTemplate.query("SELECT * FROM runners",
            (resultSet, rowNum) -> new Runner()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name")));

        return String.format(
            "Приветствую вас спортсмены \n %s! Вас теперь %s",
            runners.stream().map(Runner::getName).collect(Collectors.joining(", \n")),
            runners.size()

        );
    }
}