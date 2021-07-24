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

import com.factory.deepforestrunner.common.Gender;
import com.factory.deepforestrunner.entity.Subdivision;
import com.factory.deepforestrunner.entity.dto.ParticipantDTO;
import com.factory.deepforestrunner.service.ParticipantService;
import com.factory.deepforestrunner.service.SubdivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.factory.deepforestrunner.util.CommonUtil.DATE_FORMATTER;
import static com.factory.deepforestrunner.util.CommonUtil.nvl;

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

    @GetMapping
    public String list(
        final Model model
    ) {
        final Map<Long, Subdivision> subdivisionMap = subdivisionService.list().stream()
            .collect(Collectors.toMap(Subdivision::getId, Function.identity()));

        final List<ParticipantDTO> participants = participantService.list().stream()
            .map(participant -> {
                final Subdivision subdivision = subdivisionMap.getOrDefault(participant.getOrgId(), new Subdivision());
                return new ParticipantDTO()
                    .setId(participant.getId())
                    .setSubdivisionName(subdivision.getName())
                    .setFio(participant.getFio())
                    .setGender(nvl(participant.getGender(), Gender::getRus))
                    .setNumber(subdivision.getNumber())
                    .setBirthday(nvl(participant.getBirthday(), date -> date.format(DATE_FORMATTER)));
            })
            .collect(Collectors.toList());

        model.addAttribute("participants", participants);

        return "participant";
    }
}
