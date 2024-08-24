package com.devsu.business.transactional.service;

import com.devsu.business.transactional.service.dto.ReportDto;

import java.util.Date;

public interface ReportService {
    ReportDto generateReport(Long customerId, Date startDate, Date endDate);
}
