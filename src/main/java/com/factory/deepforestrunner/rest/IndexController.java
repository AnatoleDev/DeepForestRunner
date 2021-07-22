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

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * DeepController data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 14.07.2021.
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String start(
        final Model model
    ) {
        model.addAttribute("start", "Стартовая страница запуска");
        return "index";
    }

//    @PostMapping("/saveRunner")
//    public String saveRunner(
//        @ModelAttribute Participant runner,
//        BindingResult errors,
//        Model model
//    ) {
//        return runner(model);
//        // logic to process input data
//    }
}