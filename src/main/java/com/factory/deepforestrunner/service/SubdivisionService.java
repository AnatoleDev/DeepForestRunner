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

import com.factory.deepforestrunner.entity.Subdivision;

import java.util.List;

/**
 * SubdivisionService data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
public interface SubdivisionService {
    /**
     * List list.
     *
     * @return the list
     */
    List<Subdivision> list();
}
