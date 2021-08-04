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

import com.factory.deepforestrunner.entity.model.Participant;

import java.util.List;

/**
 * ParticipantDao data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 22.07.2021.
 */
public interface ParticipantDao {
    /**
     * List list.
     *
     * @return the list
     */
    List<Participant> list();

    /**
     * Create all.
     *
     * @param createdParticipant the created participant
     */
    void createAll(
        final List<Participant> createdParticipant
    );

    /**
     * Clear all.
     */
    void clearAll();

    /**
     * Create.
     *
     * @param id the id
     */
    void create(Long id);

    /**
     * Get participant.
     *
     * @param id the id
     * @return the participant
     */
    Participant get(Long id);

    void update(
        String id,
        Participant participant
    );
}
