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
import com.factory.deepforestrunner.dao.subdivision.rowmapper.SubdivisionRowMap;
import com.factory.deepforestrunner.entity.Subdivision;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
            new SubdivisionRowMap());
    }
}
