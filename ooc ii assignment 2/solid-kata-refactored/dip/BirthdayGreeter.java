package com.codurance.dip;

import java.time.MonthDay;


public class BirthdayGreeter {

    private final EmployeeRepository employeeRepository;
    private final Clock clock;
    private final EmailSenderPort emailSender; // depends on abstraction, not concrete class

    public BirthdayGreeter(EmployeeRepository employeeRepository,
                           Clock clock,
                           EmailSenderPort emailSender) {  // injected from outside
        this.employeeRepository = employeeRepository;
        this.clock = clock;
        this.emailSender = emailSender;
    }

    public void sendGreetings() {
        MonthDay today = clock.monthDay();
        employeeRepository.findEmployeesBornOn(today)
                .stream()
                .map(this::emailFor)
                .forEach(emailSender::send); // no more "new EmailSender()" — fully decoupled
    }

    private Email emailFor(Employee employee) {
        String message = String.format("Happy birthday, dear %s!", employee.getFirstName());
        return new Email(employee.getEmail(), "Happy birthday!", message);
    }
}
