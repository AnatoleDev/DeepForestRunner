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
import com.factory.deepforestrunner.entity.model.Participant;
import com.factory.deepforestrunner.service.ActivityServices;
import com.factory.deepforestrunner.service.ParticipantService;
import com.factory.deepforestrunner.service.RunnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final ActivityServices activityServices;
    private final RunnerService runnerService;

    @Override
    public List<Participant> list() {
        return participantDao.list();
    }

    @Override
    public void createAll(
        final List<Participant> participants
    ) {
        participantDao.createAll(participants);
        runnerService.createAll(participants);
    }

    @Override
    public void clearAll() {
        participantDao.clearAll();
    }

    @Override
    @Transactional
    public void delete(final Long participantId) {
        runnerService.deleteByParticipant(participantId);
        activityServices.deleteByParticipant(participantId);
        participantDao.delete(participantId);
    }

    @Override
    public Participant get(final Long id) {
        return participantDao.get(id);
    }

    @Override
    public void update(
        final String id,
        final Participant participant
    ) {
        participantDao.update(id, participant);
    }

    @Override
    public void clearSubdivision(final Long id) {
        participantDao.clearSubdivision(id);
    }

    @Override
    @Transactional
    public void create(final Participant participant) {
        participantDao.create(participant);
        runnerService.create(participant);
    }
}