package com.devsu.business.transactional.controller;

import com.devsu.business.transactional.service.ReportService;
import com.devsu.business.transactional.service.dto.ReportDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/reports")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ReportController {
    ReportService reportService;

    @GetMapping
    public ResponseEntity<ReportDto> generateReport(
            @RequestParam Long customerId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        ReportDto reportDto = reportService.generateReport(customerId, startDate, endDate);
        return ResponseEntity.ok(reportDto);
    }
}
