/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.dao.participant;

import com.factory.deepforestrunner.common.Activity;
import com.factory.deepforestrunner.dao.ParticipantDao;
import com.factory.deepforestrunner.dao.participant.rowmapper.ParticipantRowMapper;
import com.factory.deepforestrunner.entity.model.Participant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.factory.deepforestrunner.util.CommonUtil.LOCAL_DATE_2_DATE;
import static com.factory.deepforestrunner.util.CommonUtil.nvl;
import static com.factory.deepforestrunner.util.SqlUtil.SQL_long;

/**
 * ParticipantDaoImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 22.07.2021.
 */
@Repository
@Transactional
@RequiredArgsConstructor
public class ParticipantDaoImpl implements ParticipantDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Participant> list() {
        return jdbcTemplate.query(
            "SELECT * FROM participant",
            new ParticipantRowMapper());
    }

    @Override
    public void createAll(
        final List<Participant> participants
    ) {
        jdbcTemplate.batchUpdate(
            "INSERT INTO participant (fio, gender, birthday, subdivision_id) VALUES(?,?,?,?) ON CONFLICT DO NOTHING",
            new BatchPreparedStatementSetter() {

                public void setValues(
                    PreparedStatement ps,
                    int i
                ) throws SQLException {
                    ps.setString(1, participants.get(i).getFio());
                    ps.setString(2, nvl(participants.get(i).getGender(), Enum::name));
                    ps.setDate(3, LOCAL_DATE_2_DATE.apply(participants.get(i).getBirthday()));
                    SQL_long(ps, 4, participants.get(i).getSubdivisionId());
                }

                public int getBatchSize() {
                    return participants.size();
                }
            });

        participants.forEach(
            this::insertActivityList
        );
    }

    @Override
    public void clearAll() {
        jdbcTemplate.update("DELETE FROM participant");
    }

    @Override
    public void create(final Participant participant) {
        jdbcTemplate.update(
            "INSERT INTO participant (fio, gender, birthday, subdivision_id) VALUES (?,?,?,?);",
            participant.getFio(),
            nvl(participant.getGender(), Enum::name),
            LOCAL_DATE_2_DATE.apply(participant.getBirthday()),
            participant.getSubdivisionId()
        );

        insertActivityList(participant);
    }

    @Override
    public Participant get(final Long id) {
        return jdbcTemplate.query(
            "SELECT * FROM participant WHERE id = ?;",
            new ParticipantRowMapper(),
            id
        ).stream()
            .findFirst()
            .orElse(new Participant());
    }

    @Override
    public void update(
        final String id,
        final Participant participant
    ) {
        jdbcTemplate.update(
            "UPDATE participant " +
                "SET fio = ?, gender = ?, birthday = ?, subdivision_id = ? " +
                "WHERE id = ?;",
            participant.getFio(),
            nvl(participant.getGender(), Enum::name),
            LOCAL_DATE_2_DATE.apply(participant.getBirthday()),
            participant.getSubdivisionId(),
            id
        );
    }

    @Override
    public void clearSubdivision(final Long id) {
        jdbcTemplate.update(
            "UPDATE participant " +
                "SET subdivision_id = NULL " +
                "WHERE subdivision_id = ?;",
            id
        );
    }

    @Override
    public void delete(final Long participantId) {
        jdbcTemplate.update(
            "DELETE FROM participant WHERE id = ?", participantId
        );
    }

    private void insertActivityList(Participant participant) {
        final List<Activity> activities = participant.getActivities();

        jdbcTemplate.batchUpdate(
            "INSERT INTO activity (participant_id, type) VALUES(" +
                "(SELECT id FROM participant WHERE fio = ? LIMIT 1),?) ON CONFLICT DO NOTHING",
            new BatchPreparedStatementSetter() {

                public void setValues(
                    PreparedStatement ps,
                    int i
                ) throws SQLException {
                    ps.setString(1, participant.getFio());
                    ps.setString(2, nvl(activities.get(i), Enum::name));
                }

                public int getBatchSize() {
                    return activities.size();
                }
            });
    }

}