/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.rest.file;

import com.factory.deepforestrunner.entity.dto.FileDTO;
import com.factory.deepforestrunner.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static com.factory.deepforestrunner.util.CommonUtil.DATE_TIME_FORMATTER;
import static com.factory.deepforestrunner.util.CommonUtil.nvl;

/**
 * FileController data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @GetMapping
    public String getFile(
        final Model model
    ) {
        final FileDTO file = Optional.ofNullable(fileService.getFile())
            .map(f -> new FileDTO()
                .setId(f.getId())
                .setName(f.getName())
                .setCreated(nvl(f.getCreated(), date -> date.format(DATE_TIME_FORMATTER)))
            )
            .orElse(new FileDTO());

        model.addAttribute("file", file);

        return "file";
    }

    @PostMapping
    public String create(
        @RequestParam("file") MultipartFile file,
        Model model
    ) {
        if (!file.isEmpty()) {
            fileService.create(file);
        }

        return getFile(model);
    }
}
