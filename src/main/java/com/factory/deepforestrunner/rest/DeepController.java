/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.rest;

import com.factory.deepforestrunner.entity.Customer;
import com.factory.deepforestrunner.entity.Organization;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DeepController data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 14.07.2021.
 */
@Controller
@RequiredArgsConstructor
public class DeepController {

    private final JdbcTemplate jdbcTemplate;

//    private final static String UPLOAD_DIR = "./src/main/resources/db/";

    @GetMapping("/")
    public String start(
        final Model model
    ) {
        model.addAttribute("start", "Стартовая страница запуска");
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(
        @RequestParam("file") MultipartFile file,
        Model model
    ) {
        if (file.isEmpty()) {
            return draw(model);
        }

        try {

//            final String insert = String.format(
//                "INSERT INTO files (name, content) VALUES ('%s', '%s')",
//                StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())),
//                Arrays.toString(file.getBytes())
//            );

//            jdbcTemplate.execute(insert);

            final byte[] fileBytes = file.getBytes();

            final ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(fileBytes);
//            NPOIFSFileSystem n = new NPOIFSFileSystem(file);
            final Workbook workbook = WorkbookFactory.create(arrayInputStream);
            final Sheet sheet = workbook.getSheetAt(1);
            final List<Customer> runners = new ArrayList<>();
            final DataFormatter formatter = new DataFormatter();


            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                final Row row = sheet.getRow(i);
                runners.add(parseRow(row, formatter));
            }
            arrayInputStream.close();
        } catch (IOException | InvalidFormatException ioException) {
            ioException.printStackTrace();
        }

        return draw(model);
    }

    private Customer parseRow(
        Row row,
        DataFormatter formatter
    ) {

        Map<String, String> parseError = new HashMap<>();
        final Customer customer = new Customer();

        customer.setFio(parseCell(row, 1, formatter));
        final String parseCell = parseCell(row, 2, formatter);
        final String parseCell2 = parseCell(row, 3, formatter);
//        customer.setGender(parseCell);

        return customer;

    }

//    @GetMapping("/draw")
//    public String runner(
//        final Model model
//    ) {
//
//        List<Customer> runners = jdbcTemplate.query("SELECT * FROM runners",
//            (resultSet, rowNum) -> new Customer()
//                .setId(resultSet.getLong("id"))
//                .setFio(resultSet.getString("name")));
//
//        final String format = String.format(
//            "Приветствую вас спортсмены \n %s! Вас теперь %s",
//            runners.stream().map(Customer::getFio).collect(Collectors.joining(", \n")),
//            runners.size()
//        );
//
//        model.addAttribute("runners", runners);
//        model.addAttribute("runner", new Customer());
//        model.addAttribute("text", format);
//
//        return "draw";
//    }

//    @PostMapping("/saveRunner")
//    public String saveRunner(
//        @ModelAttribute Customer runner,
//        BindingResult errors,
//        Model model
//    ) {
//        return runner(model);
//        // logic to process input data
//    }

    @GetMapping("/draw")
    public String draw(
        Model model
    ) {

        final List<Organization> organizations = jdbcTemplate.query(
            "SELECT * FROM organization ORDER BY number DESC ",
            (resultSet, rowNum) -> new Organization()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setNumber(resultSet.getInt("number"))
                .setCaptain(resultSet.getString("captain"))
                .setPhone(resultSet.getString("phone")));

        model.addAttribute("organizations", organizations);

        return "draw";
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