/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.dao;

import com.factory.deepforestrunner.entity.Subdivision;

import java.util.List;

/**
 * SubdivisionDao data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
public interface SubdivisionDao {
    /**
     * List list.
     *
     * @return the list
     */
    List<Subdivision> list();

    /**
     * Create all.
     *
     * @param createdSubdivisions the created subdivision
     */
    void createAll(
        final List<Subdivision> createdSubdivisions
    );

    /**
     * Clear all.
     */
    void clearAll();

    /**
     * Get subdivision.
     *
     * @param id the id
     * @return the subdivision
     */
    Subdivision get(Long id);


    /**
     * Update.
     *
     * @param subdivision the subdivision
     * @param id          the id
     */
    void update(
        Subdivision subdivision,
        Long id
    );

    /**
     * Create.
     *
     * @param subdivision the subdivision
     */
    void create(Subdivision subdivision);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);
}
