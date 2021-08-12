/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.service;

import com.factory.deepforestrunner.entity.model.Participant;
import com.factory.deepforestrunner.entity.model.Runner;

import java.time.LocalTime;
import java.util.List;

/**
 * RunnerService data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 24.07.2021.
 */
public interface RunnerService {
    /**
     * List list.
     *
     * @return the list
     */
    List<Runner> list();

    /**
     * Create all.
     *
     * @param participants the participants
     */
    void createAll(List<Participant> participants);

    /**
     * Clear all.
     */
    void clearAll();

    /**
     * Delete by participant.
     *
     * @param participantId the participant id
     */
    void deleteByParticipant(Long participantId);

    /**
     * Create.
     *
     * @param participant the participant
     */
    void create(Participant participant);

    /**
     * Sets number.
     *
     * @param id     the id
     * @param number the number
     */
    void setNumber(
        Long id,
        Integer number
    );


    /**
     * Sets finish.
     *
     * @param id     the id
     * @param finish the finish
     */
    void setFinish(
        Long id,
        LocalTime finish
    );

    /**
     * Sets kp.
     *
     * @param id the id
     * @param kp the kp
     */
    void setKp(
        Long id,
        Integer kp
    );
}
