package com.codurance.srp;

import java.util.List;


public class StatementPrinter {

    private final StatementFormatter formatter;
    private final Console console;

    public StatementPrinter(StatementFormatter formatter, Console console) {
        this.formatter = formatter;
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        formatter.format(transactions).forEach(console::printLine);
    }
}
