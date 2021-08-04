/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.dao.subdivision;

import com.factory.deepforestrunner.dao.SubdivisionDao;
import com.factory.deepforestrunner.dao.subdivision.rowmapper.SubdivisionRowMapper;
import com.factory.deepforestrunner.entity.model.Subdivision;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * SubdivisionDaoImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
@Repository
@Transactional
@RequiredArgsConstructor
public class SubdivisionDaoImpl implements SubdivisionDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Subdivision> list() {
        return jdbcTemplate.query(
            "SELECT * FROM subdivision",
            new SubdivisionRowMapper());
    }

    @Override
    public void createAll(
        final List<Subdivision> createdSubdivisions
    ) {
        jdbcTemplate.batchUpdate(
            "INSERT INTO subdivision (name, number, captain, phone) VALUES(?,?,?,?) ON CONFLICT DO NOTHING;",
            new BatchPreparedStatementSetter() {

                public void setValues(
                    PreparedStatement ps,
                    int i
                ) throws SQLException {
                    ps.setString(1, createdSubdivisions.get(i).getName());
                    ps.setLong(2, createdSubdivisions.get(i).getNumber());
                    ps.setString(3, createdSubdivisions.get(i).getCaptain());
                    ps.setString(4, createdSubdivisions.get(i).getPhone());
                }

                public int getBatchSize() {
                    return createdSubdivisions.size();
                }
            });
    }

    @Override
    public void clearAll() {
        jdbcTemplate.update("DELETE FROM subdivision");
    }

    @Override
    public Subdivision get(final Long id) {
        return jdbcTemplate.query(
            "SELECT * FROM subdivision WHERE id = ?;",
            new SubdivisionRowMapper(),
            id
        ).stream()
            .findFirst()
            .orElse(new Subdivision());
    }

    @Override
    public void update(
        final Subdivision subdivision,
        final Long id
    ) {
        jdbcTemplate.update(
            "UPDATE subdivision " +
                "SET name = ?, number = ?, captain = ?, phone = ? " +
                "WHERE id = ?;",
            subdivision.getName(),
            subdivision.getNumber(),
            subdivision.getCaptain(),
            subdivision.getPhone(),
            id
        );
    }

    @Override
    public void create(final Subdivision subdivision) {
        jdbcTemplate.update(
            "INSERT INTO subdivision (name, number, captain, phone) VALUES (?,?,?,?);",
            subdivision.getName(),
            subdivision.getNumber(),
            subdivision.getCaptain(),
            subdivision.getPhone()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
            "DELETE FROM subdivision WHERE id = ?;",
            id
        );
    }
}
