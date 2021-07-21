/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.dao.file;

import com.factory.deepforestrunner.dao.FileDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * FileDaoImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
@Repository
@Transactional
@RequiredArgsConstructor
public class FileDaoImpl implements FileDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(
        final MultipartFile file
    ) throws IOException {
        jdbcTemplate.execute(
            String.format(
                "INSERT INTO file (name, content) VALUES ('%s', '%s')",
                StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())),
                Arrays.toString(file.getBytes())
            )
        );
    }
}
