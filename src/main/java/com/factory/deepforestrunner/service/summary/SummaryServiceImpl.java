/*
 * Copyright 2021 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package com.factory.deepforestrunner.service.summary;

import com.factory.deepforestrunner.common.Place;
import com.factory.deepforestrunner.entity.model.CommandSummary;
import com.factory.deepforestrunner.entity.model.Runner;
import com.factory.deepforestrunner.entity.model.RunnerSummary;
import com.factory.deepforestrunner.service.RunnerService;
import com.factory.deepforestrunner.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * SummaryServiceImpl data
 *
 * @author <a href="mailto:Anatoly.Glazkov@russianpost.ru">Anatoly Glazkov</a> on 13.08.2021.
 */
@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final static int THREE_BEST_RUNNER_TIME = 3;
    private final static int CENTER_PRINT = 1;
    private final static int KP_PENALTY_SEC = 3600;

    private final RunnerService runnerService;

    @Override
    public List<RunnerSummary> list() {

        final List<Runner> list = runnerService.list();

        if (list.stream().anyMatch(runner -> isNull(runner.getTotal()))) {
            return Collections.emptyList();
        }

        final Map<Long, List<RunnerSummary>> summaryGroups = list.stream()
            .map(
                runner -> new RunnerSummary()
                    .setSubdivisionId(runner.getSubdivisionId())
                    .setParticipantId(runner.getParticipantId())
                    .setTotal(runner.getTotal())
                    .setPenaltySec(runner.getKp() * KP_PENALTY_SEC)
            )
            .collect(Collectors.groupingBy(RunnerSummary::getSubdivisionId));


        final List<CommandSummary> commandTimeSummary = new ArrayList<>();
//        проверяем все группы берем лучшие результаты из все подразделний
        summaryGroups.forEach(
            (key, summaries) -> {
                if (summaries.size() >= THREE_BEST_RUNNER_TIME) {

                    final LocalTime threeBestTime = summaries.stream()
//                        увеличиваем время с учетом штрафов
                        .map(runnerSummary -> runnerSummary.getTotal().plusSeconds(runnerSummary.getPenaltySec()))
//                        сортируем по времени с учетом штрафов и их считаем
                        .sorted(Comparator.comparing(LocalTime::toSecondOfDay))
                        .limit(THREE_BEST_RUNNER_TIME)
                        .reduce((lt1, lt2) -> LocalTime.ofSecondOfDay(lt1.toSecondOfDay() + lt2.toSecondOfDay()))
                        .orElse(null);

                    commandTimeSummary.add(new CommandSummary().setSubdivisionId(key).setCommand(threeBestTime));
                }
            });
//

//        Берем сортированную мапу
        final TreeMap<LocalTime, List<CommandSummary>> sortCommandSummary = new TreeMap<>(
            commandTimeSummary.stream().collect(Collectors.groupingBy(CommandSummary::getCommand))
        );

        AtomicInteger counter = new AtomicInteger(1);

//      пробегаем по мапе берем ид подразделения, по счету увеличиваем место,
//      берем эти подразделения из группы и прописываем им лучшее время
        sortCommandSummary.forEach(
            (localTime, commandSummaries) -> {

                final Place place = Place.byInt(counter.getAndIncrement());

                commandSummaries.forEach(
                    commandSummary -> {
                        final List<RunnerSummary> runnerSummaries = summaryGroups.get(commandSummary.getSubdivisionId());

                        runnerSummaries.sort(Comparator.comparing(o -> o.getTotal().plusSeconds(o.getPenaltySec())));

                        for (int i = 0; i < THREE_BEST_RUNNER_TIME; i++) {
                            final RunnerSummary runnerSummary = runnerSummaries.get(i);
                            if (i == CENTER_PRINT) {
                                runnerSummary.setPlace(place).setCommand(commandSummary.getCommand());
                            }
                            runnerSummary.setBest(runnerSummary.getTotal().plusSeconds(runnerSummary.getPenaltySec()));
                        }
                    }
                );
            }
        );
        return summaryGroups.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }
}
