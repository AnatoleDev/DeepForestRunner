/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.dao.participant.rowmapper;

import com.factory.deepforestrunner.common.Gender;
import com.factory.deepforestrunner.entity.model.Participant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.factory.deepforestrunner.util.CommonUtil.nvl;

/**
 * ParticipantRowMapper data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 22.07.2021.
 */
public class ParticipantRowMapper implements RowMapper<Participant> {

    @Override
    public Participant mapRow(
        final ResultSet resultSet,
        final int i
    ) throws SQLException {
        return new Participant()
            .setId(resultSet.getLong("id"))
            .setFio(resultSet.getString("fio"))
            .setGender(nvl(resultSet.getString("gender"), Gender::valueOf))
            .setBirthday(nvl(resultSet.getDate("birthday"), Date::toLocalDate))
            .setSubdivisionId(resultSet.getLong("subdivision_id"));
    }
}
