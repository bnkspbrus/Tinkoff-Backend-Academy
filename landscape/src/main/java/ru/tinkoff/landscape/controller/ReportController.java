package ru.tinkoff.landscape.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.landscape.dto.Report;
import ru.tinkoff.landscape.service.ReportService;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/orders")
    public Report getOrdersReport() {
        return reportService.getOrdersReport();
    }
}
