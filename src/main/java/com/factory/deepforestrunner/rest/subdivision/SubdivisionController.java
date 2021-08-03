/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.rest.subdivision;

import com.factory.deepforestrunner.entity.model.Subdivision;
import com.factory.deepforestrunner.entity.msg.SubdivisionMsg;
import com.factory.deepforestrunner.service.SubdivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SubdivisionController data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/subdivision")
public class SubdivisionController {

    private final SubdivisionService subdivisionService;

    @GetMapping("/list")
    public String list(
        final Model model
    ) {
        model.addAttribute("subdivisions", subdivisionService.list());
        model.addAttribute("msg", SubdivisionMsg.INSTANCE);
        return "subdivision/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(
        final Model model,
        @PathVariable final Long id
    ) {
        model.addAttribute("subdivision", subdivisionService.get(id));
        model.addAttribute("msg", SubdivisionMsg.INSTANCE);
        return "subdivision/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(
        @ModelAttribute final Subdivision subdivision,
        @PathVariable final Long id
    ) {
        subdivisionService.update(subdivision, id);
        return "redirect:/subdivision/list";
    }

    @GetMapping("/create")
    public String createForm(final Model model) {
        model.addAttribute("subdivision", new Subdivision());
        model.addAttribute("msg", SubdivisionMsg.INSTANCE);
        return "subdivision/create";
    }

    @PostMapping("/create")
    public String create(
        @ModelAttribute final Subdivision subdivision
    ) {
        subdivisionService.create(subdivision);
        return "redirect:/subdivision/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(
        @PathVariable final Long id,
        final Model model
    ) {
        model.addAttribute("subdivision", subdivisionService.get(id));
        model.addAttribute("msg", SubdivisionMsg.INSTANCE);
        return "subdivision/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(
        @PathVariable final Long id
    ) {
        subdivisionService.delete(id);
        return "redirect:/subdivision/list";
    }
}