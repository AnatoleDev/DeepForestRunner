/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.dao.runner.rowmapper;

import com.factory.deepforestrunner.entity.Runner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.factory.deepforestrunner.util.CommonUtil.DATE_2_LOCAL_DATE_TIME;
import static com.factory.deepforestrunner.util.CommonUtil.nvl;

/**
 * RunnerRowMapper data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
public class RunnerRowMapper implements RowMapper<Runner> {

    @Override
    public Runner mapRow(
        ResultSet resultSet,
        int i
    ) throws SQLException {
        return new Runner()
            .setId(resultSet.getLong("id"))
            .setSubdivisionId(nvl(resultSet.getString("subdivision_id"), Long::new))
            .setParticipantId(nvl(resultSet.getString("participant_id"), Long::new))
            .setNumber(nvl(resultSet.getString("number"), Integer::new))
            .setStart(nvl(resultSet.getDate("start"), DATE_2_LOCAL_DATE_TIME))
            .setFinish(nvl(resultSet.getDate("finish"), DATE_2_LOCAL_DATE_TIME))
            .setTotal(nvl(resultSet.getDate("total"), DATE_2_LOCAL_DATE_TIME))
            .setKp(nvl(resultSet.getString("kp"), Integer::new));
    }
}