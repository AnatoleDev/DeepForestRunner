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
import com.factory.deepforestrunner.entity.model.Participant;
import com.factory.deepforestrunner.entity.model.Runner;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import static com.factory.deepforestrunner.util.CommonUtil.DATE_TIME_HH_MM_SS_FORMATTER;
import static com.factory.deepforestrunner.util.CommonUtil.nvl;

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
            "SELECT * FROM runner r LEFT JOIN participant p ON p.id = r.participant_id",
            new RunnerRowMapper());
    }

    @Override
    public void createAll(
        final List<Participant> runners
    ) {
        jdbcTemplate.batchUpdate(
            "INSERT INTO runner (participant_id) " +
                "VALUES((SELECT id FROM participant WHERE fio = ? LIMIT 1)) ON CONFLICT DO NOTHING",
            new BatchPreparedStatementSetter() {

                public void setValues(
                    final PreparedStatement ps,
                    final int i
                ) throws SQLException {
                    ps.setString(1, runners.get(i).getFio());
                }

                public int getBatchSize() {
                    return runners.size();
                }
            });
    }

    @Override
    public void deleteByParticipant(final Long participantId) {
        jdbcTemplate.update(
            "DELETE FROM runner WHERE participant_id = ?", participantId
        );
    }

    @Override
    public void create(final Participant participant) {

        jdbcTemplate.update(
            "INSERT INTO runner (participant_id) " +
                "VALUES((SELECT id FROM participant WHERE fio = ? LIMIT 1))",
            participant.getFio()
        );
    }

    @Override
    public void setNumber(
        final Long id,
        final Integer number,
        final LocalTime start
    ) {
        jdbcTemplate.update(
            "UPDATE runner SET number = ?, start = ? WHERE id = ?;",
            number,
            start.format(DATE_TIME_HH_MM_SS_FORMATTER),
            id
        );
    }

    @Override
    public void setFinish(
        final Long id,
        final LocalTime finish,
        final LocalTime totalTime
    ) {
        jdbcTemplate.update(
            "UPDATE runner SET finish = ?, total = ?  WHERE id = ?;",
            nvl(finish, f -> f.format(DATE_TIME_HH_MM_SS_FORMATTER)),
            nvl(totalTime, t -> t.format(DATE_TIME_HH_MM_SS_FORMATTER)),
            id
        );
    }

    @Override
    public Runner getRunnerByid(final Long id) {
        return jdbcTemplate.query(
            "SELECT * FROM runner WHERE id = ?;",
            new RunnerRowMapper(),
            id).stream().findFirst().orElse(null);
    }

    @Override
    public void setKp(
        final Long id,
        final Integer kp
    ) {
        jdbcTemplate.update(
            "UPDATE runner SET kp = ? WHERE id = ?;",
            kp,
            id
        );
    }
}