/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.service.participant;

import com.factory.deepforestrunner.dao.ParticipantDao;
import com.factory.deepforestrunner.entity.Participant;
import com.factory.deepforestrunner.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ParticipantServiceImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 22.07.2021.
 */
@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantDao participantDao;

    @Override
    public List<Participant> list() {
        return participantDao.list();
    }

    @Override
    public void createAll(
        final List<Participant> createdParticipant
    ) {
        participantDao.createAll(createdParticipant);
    }
}