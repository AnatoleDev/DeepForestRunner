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
import com.factory.deepforestrunner.dao.file.rowmapper.FileRowMapper;
import com.factory.deepforestrunner.entity.model.File;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

import static com.factory.deepforestrunner.util.CommonUtil.nvl;

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
                nvl(file.getOriginalFilename(), StringUtils::cleanPath),
                Arrays.toString(file.getBytes())
            )
        );
    }

    @Override
    public File getFile() {
        return jdbcTemplate.query(
            "SELECT id, name, created " +
                "FROM file ORDER BY created LIMIT 1",
            new FileRowMapper()
        )
            .stream()
            .findFirst()
            .orElse(new File());
    }

    @Override
    public void clearAll() {
        jdbcTemplate.update("DELETE FROM file");
    }
}