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

import com.factory.deepforestrunner.common.Activity;

import java.util.List;

/**
 * ActivityDao data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
public interface ActivityDao {
    /**
     * Clear all.
     */
    void clearAll();

    /**
     * Create all.
     *
     * @param activities the activities
     * @param partID     the part id
     */
    void createAll(
        List<Activity> activities,
        Long partID
    );

    /**
     * Delete by participant.
     *
     * @param participantId the participant id
     */
    void deleteByParticipant(Long participantId);
}
