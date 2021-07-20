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

import com.factory.deepforestrunner.entity.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
        model.addAttribute("start", "Стартовая страница запуска");
        return "index";
    }

//    @GetMapping("/draw")
//    public String runner(
//        final Model model
//    ) {
//
//        List<Runner> runners = jdbcTemplate.query("SELECT * FROM runners",
//            (resultSet, rowNum) -> new Runner()
//                .setId(resultSet.getLong("id"))
//                .setFio(resultSet.getString("name")));
//
//        final String format = String.format(
//            "Приветствую вас спортсмены \n %s! Вас теперь %s",
//            runners.stream().map(Runner::getFio).collect(Collectors.joining(", \n")),
//            runners.size()
//        );
//
//        model.addAttribute("runners", runners);
//        model.addAttribute("runner", new Runner());
//        model.addAttribute("text", format);
//
//        return "draw";
//    }

//    @PostMapping("/saveRunner")
//    public String saveRunner(
//        @ModelAttribute Runner runner,
//        BindingResult errors,
//        Model model
//    ) {
//        return runner(model);
//        // logic to process input data
//    }

    @GetMapping("/draw")
    public String draw(
        Model model
    ) {

        final List<Organization> organizations = jdbcTemplate.query("SELECT * FROM organization ORDER BY number DESC ",
            (resultSet, rowNum) -> new Organization()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setNumber(resultSet.getInt("number"))
                .setCaptain(resultSet.getString("captain"))
                .setPhone(resultSet.getString("phone")));

        model.addAttribute("organizations", organizations);

        return "draw";
    }
}