/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.dao.activity;

import com.factory.deepforestrunner.common.Activity;
import com.factory.deepforestrunner.dao.ActivityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.factory.deepforestrunner.util.CommonUtil.nvl;

/**
 * ActivityDaoImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
@Repository
@Transactional
@RequiredArgsConstructor
public class ActivityDaoImpl implements ActivityDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void clearAll() {
        jdbcTemplate.update("DELETE FROM activity");
    }

    @Override
    public void createAll(
        final List<Activity> activities,
        final Long partID
    ) {
        jdbcTemplate.batchUpdate(
            "INSERT INTO activity (participant_id, type) VALUES(?,?)",
            new BatchPreparedStatementSetter() {

                public void setValues(
                    PreparedStatement ps,
                    int i
                ) throws SQLException {
                    ps.setLong(1, partID);
                    ps.setString(2, nvl(activities.get(i), Enum::name));
                }

                public int getBatchSize() {
                    return activities.size();
                }
            });
    }
}
