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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/")
    public String start(
        final Model model
    ) {
        model.addAttribute("name", "Стартовая страница запуска");
        return "index";
    }

    @GetMapping("/runner")
    public String runner(
        @RequestParam(value = "name", defaultValue = "Ivan") final String name,
        final Model model
    ) {
        //Insert a record:
//        final String insert = String.format("INSERT INTO runners VALUES (NULL, '%s')", name);
//        jdbcTemplate.execute(insert);

        List<Runner> runners = jdbcTemplate.query("SELECT * FROM runners",
            (resultSet, rowNum) -> new Runner()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name")));

        final String format = String.format(
            "Приветствую %s вас спортсмены \n %s! Вас теперь %s",
            name,
            runners.stream().map(Runner::getName).collect(Collectors.joining(", \n")),
            runners.size()
        );

        model.addAttribute("runners", runners);
        model.addAttribute("runner", new Runner());
        model.addAttribute("text", format);

        return "runner";
    }

    @PostMapping("/saveRunner")
    public String saveRunner(
        @ModelAttribute Runner runner,
        BindingResult errors,
        Model model
    ) {
        return runner(runner.getName(), model);
        // logic to process input data
    }


}