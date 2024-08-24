package com.devsu.business.transactional.service.impl;

import com.devsu.business.transactional.domain.Account;
import com.devsu.business.transactional.domain.Transaction;
import com.devsu.business.transactional.exception.NotFoundException;
import com.devsu.business.transactional.repository.AccountRepository;
import com.devsu.business.transactional.repository.TransactionRepository;
import com.devsu.business.transactional.service.ReportService;
import com.devsu.business.transactional.service.dto.AccountReportDto;
import com.devsu.business.transactional.service.dto.ReportDto;
import com.devsu.business.transactional.service.dto.TransactionDto;
import com.devsu.business.transactional.service.mapper.TransactionMapper;
import com.devsu.business.transactional.service.mapper.TransactionMapperImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    AccountRepository accountRepository;
    TransactionRepository transactionRepository;
    TransactionMapper transactionMapper;

    @Override
    public ReportDto generateReport(Long customerId, Date startDate, Date endDate) {
        List<Account> accountList = accountRepository.findByCustomerId(customerId);
        if (accountList.isEmpty()) {
            throw new NotFoundException("CUSTOMER {0} DON'T HAVE ANY ACCOUNT", customerId);
        }
        List<AccountReportDto> accountReportDtoList = new ArrayList<>();
        for (Account account : accountList) {
            BigDecimal accountBalance = BigDecimal.ZERO;

            List<Transaction> accountsTransactions = transactionRepository.findByAccountAndTransactionDateBetween(account, startDate, endDate);
            List<TransactionDto> transactionDtoList = new ArrayList<>();
            for (Transaction transaction : accountsTransactions) {
                accountBalance = accountBalance.add(transaction.getAmount());

                TransactionDto transactionDto = transactionMapper.toDto(transaction);
                transactionDtoList.add(transactionDto);
            }
            AccountReportDto accountReportDto = new AccountReportDto();
            accountReportDto.setAccountId(account.getAccountId());
            accountReportDto.setTransactions(transactionDtoList);
            accountReportDto.setBalance(accountBalance);

            accountReportDtoList.add(accountReportDto);
        }
        ReportDto reportDto = new ReportDto();
        reportDto.setAccounts(accountReportDtoList);
        return reportDto;
    }
}
