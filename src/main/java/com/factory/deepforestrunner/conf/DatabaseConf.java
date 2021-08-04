/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.conf;

import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConf data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 04.08.2021.
 */
@Configuration
public class DatabaseConf {

    public static final String DB_URL = "jdbc:sqlite:database.db";
    public static final String DRIVER = "org.sqlite.JDBC";

    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName(DRIVER);
        Connection connection = null;

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection(DB_URL, config.toProperties());
        } catch (SQLException ex) {

        }
        return connection;
    }

}
