/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.dao.subdivision.rowmapper;

import com.factory.deepforestrunner.entity.model.Subdivision;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SubdivisionRowMapper data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
public class SubdivisionRowMapper implements RowMapper<Subdivision> {

    @Override
    public Subdivision mapRow(
        ResultSet resultSet,
        int i
    ) throws SQLException {
        return new Subdivision()
            .setId(resultSet.getLong("id"))
            .setName(resultSet.getString("name"))
            .setNumber(resultSet.getInt("number"))
            .setCaptain(resultSet.getString("captain"))
            .setPhone(resultSet.getString("phone"));
    }
}
