/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.dao.runner;

import com.factory.deepforestrunner.dao.RunnerDao;
import com.factory.deepforestrunner.dao.runner.rowmapper.RunnerRowMapper;
import com.factory.deepforestrunner.entity.model.Runner;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.factory.deepforestrunner.util.CommonUtil.LOCAL_DATE_TIME_2_TIMESTAMP;
import static com.factory.deepforestrunner.util.CommonUtil.nvl;
import static com.factory.deepforestrunner.util.SqlUtil.SQL_int;
import static com.factory.deepforestrunner.util.SqlUtil.SQL_long;

/**
 * RunnerDaoImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
@Repository
@Transactional
@RequiredArgsConstructor
public class RunnerDaoImpl implements RunnerDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void clearAll() {
        jdbcTemplate.update("DELETE FROM runner");
    }

    @Override
    public List<Runner> list() {
        return jdbcTemplate.query(
            "SELECT * FROM runner",
            new RunnerRowMapper());
    }

    @Override
    public void createAll(
        final List<Runner> runners
    ) {
        jdbcTemplate.batchUpdate(
            "INSERT INTO runner (subdivision_id, participant_id, number, start, finish, total, kp) " +
                "VALUES(?,?,?,?,?,?,?) ON CONFLICT DO NOTHING",
            new BatchPreparedStatementSetter() {

                public void setValues(
                    final PreparedStatement ps,
                    final int i
                ) throws SQLException {

                    SQL_long(ps, 1, runners.get(i).getSubdivisionId());
                    SQL_long(ps, 2, runners.get(i).getParticipantId());
                    SQL_int(ps, 3, runners.get(i).getNumber());

                    ps.setTimestamp(4, nvl(runners.get(i).getStart(), LOCAL_DATE_TIME_2_TIMESTAMP));
                    ps.setTimestamp(5, nvl(runners.get(i).getFinish(), LOCAL_DATE_TIME_2_TIMESTAMP));
                    ps.setTimestamp(6, nvl(runners.get(i).getTotal(), LOCAL_DATE_TIME_2_TIMESTAMP));

                    SQL_int(ps, 7, runners.get(i).getKp());
                }

                public int getBatchSize() {
                    return runners.size();
                }
            });
    }
}