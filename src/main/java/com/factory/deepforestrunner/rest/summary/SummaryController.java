/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.rest.summary;

import com.factory.deepforestrunner.entity.dto.SummaryDTO;
import com.factory.deepforestrunner.entity.model.Participant;
import com.factory.deepforestrunner.entity.model.Subdivision;
import com.factory.deepforestrunner.entity.msg.SummaryMsg;
import com.factory.deepforestrunner.service.ParticipantService;
import com.factory.deepforestrunner.service.SubdivisionService;
import com.factory.deepforestrunner.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * SummaryController data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 13.08.2021.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/summary")
public class SummaryController {

    private final SummaryService summaryService;
    private final SubdivisionService subdivisionService;
    private final ParticipantService participantService;

    @GetMapping("/list")
    public String list(
        final Model model
    ) {
        final Map<Long, Subdivision> subdivisionMap = subdivisionService.list().stream()
            .collect(Collectors.toMap(Subdivision::getId, Function.identity()));

        final Map<Long, Participant> participantMap = participantService.list().stream()
            .collect(Collectors.toMap(Participant::getId, Function.identity()));

        model.addAttribute("summaries", summaryService.list().stream()
            .map(runnerSummary -> SummaryDTO.toDTO(runnerSummary, participantMap, subdivisionMap))
            .collect(Collectors.toList())
        );
        model.addAttribute("msg", SummaryMsg.INSTANCE);
        return "summary/list";
    }
}