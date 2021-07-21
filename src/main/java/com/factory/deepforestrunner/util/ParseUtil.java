/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.util;

import com.factory.deepforestrunner.entity.Participant;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

/**
 * ParseUtil data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 21.07.2021.
 */
public final class ParseUtil {

    private ParseUtil() {
    }


    public static Participant parseParticipant(
        Row row,
        DataFormatter formatter
    ) {

        final Participant customer = new Participant();

        customer.setFio(parseCell(row, 1, formatter));
        final String parseCell = parseCell(row, 2, formatter);
        final String parseCell2 = parseCell(row, 3, formatter);
//        customer.setGender(parseCell);

        return customer;


    }

    private static String parseCell(
        final Row row,
        final int cellIndex,
        final DataFormatter formatter
    ) {
        final Cell cell = row.getCell(cellIndex, Row.RETURN_BLANK_AS_NULL);

        if (cell == null) {
            return null;
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

            switch (cell.getCachedFormulaResultType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case Cell.CELL_TYPE_STRING:
                    return cell.getRichStringCellValue().getString();
            }
        } else {
            return formatter.formatCellValue(cell);
        }
        return null;
    }

}
