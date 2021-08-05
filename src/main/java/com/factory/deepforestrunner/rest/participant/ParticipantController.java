/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.rest.participant;

import com.factory.deepforestrunner.entity.model.Participant;
import com.factory.deepforestrunner.entity.model.Subdivision;
import com.factory.deepforestrunner.entity.msg.ParticipantMsg;
import com.factory.deepforestrunner.service.ParticipantService;
import com.factory.deepforestrunner.service.SubdivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ParticipantController data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 22.07.2021.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/participant")
public class ParticipantController {

    private final ParticipantService participantService;
    private final SubdivisionService subdivisionService;

    @GetMapping("/list")
    public String list(
        final Model model
    ) {
        model.addAttribute("participants", participantService.list());
        model.addAttribute("subdivisionMap", subdivisionService.list().stream().collect(Collectors.toMap(Subdivision::getId, Function.identity())));
        model.addAttribute("msg", ParticipantMsg.INSTANCE);
        return "participant/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(
        final Model model,
        @PathVariable final Long id
    ) {
        model.addAttribute("participant", participantService.get(id));
        model.addAttribute("subdivisionMap", subdivisionService.list().stream().collect(Collectors.toMap(Subdivision::getId, Function.identity())));
        model.addAttribute("msg", ParticipantMsg.INSTANCE);
        return "participant/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(
        @ModelAttribute final Participant participant,
        @PathVariable final String id
    ) {
        participantService.update(id, participant);
        return "redirect:/participant/list";
    }

    @GetMapping("/create")
    public String createForm(final Model model) {

        model.addAttribute("participant", new Participant());
        model.addAttribute("subdivisionMap", subdivisionService.list().stream().collect(Collectors.toMap(Subdivision::getId, Function.identity())));
        model.addAttribute("msg", ParticipantMsg.INSTANCE);
        return "participant/create";
    }

    @PostMapping("/create")
    public String create(
        @ModelAttribute final Participant participant
    ) {
        participantService.create(participant);
        return "redirect:/participant/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(
        @PathVariable final long id,
        final Model model
    ) {
        model.addAttribute("participant", participantService.get(id));
        model.addAttribute("subdivisionMap", subdivisionService.list().stream().collect(Collectors.toMap(Subdivision::getId, Function.identity())));
        model.addAttribute("msg", ParticipantMsg.INSTANCE);
        return "participant/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(
        @PathVariable final Long id
    ) {
        participantService.delete(id);
        return "redirect:/participant/list";
    }
}