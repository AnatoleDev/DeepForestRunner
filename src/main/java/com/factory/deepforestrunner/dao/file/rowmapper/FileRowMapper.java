/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.dao.file.rowmapper;

import com.factory.deepforestrunner.entity.File;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.factory.deepforestrunner.util.CommonUtil.DATE_2_LOCAL_DATE_TIME;

/**
 * FileRowMapper data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
public class FileRowMapper implements RowMapper<File> {


    @Override
    public File mapRow(
        final ResultSet resultSet,
        final int i
    ) throws SQLException {
        return new File()
            .setId(resultSet.getLong("id"))
            .setName(resultSet.getString("name"))
            .setCreated(DATE_2_LOCAL_DATE_TIME.apply(resultSet.getDate("created")));
    }
}
