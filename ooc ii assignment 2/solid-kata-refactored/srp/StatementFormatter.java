package com.codurance.srp;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toCollection;


public class StatementFormatter {

    private static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";
    private static final String DATE_FORMAT       = "dd/MM/yyyy";
    private static final String AMOUNT_FORMAT     = "#.00";

    
    public List<String> format(List<Transaction> transactions) {
        List<String> lines = new ArrayList<>();
        lines.add(STATEMENT_HEADER);

        // Build running balance and map each transaction to a formatted line
        final AtomicInteger balance = new AtomicInteger(0);
        transactions.stream()
                .map(t -> statementLine(t, balance.addAndGet(t.amount())))
                .collect(toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(lines::add);

        return lines;
    }

    private String statementLine(Transaction transaction, int balance) {
        return MessageFormat.format("{0} | {1} | {2}",
                formatDate(transaction.date()),
                formatAmount(transaction.amount()),
                formatAmount(balance));
    }

    private String formatDate(LocalDate date) {
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(date);
    }

    private String formatAmount(int amount) {
        DecimalFormat df = new DecimalFormat(AMOUNT_FORMAT, DecimalFormatSymbols.getInstance(Locale.UK));
        return df.format(amount);
    }
}
