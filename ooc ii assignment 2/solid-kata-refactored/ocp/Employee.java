package com.codurance.ocp;


public abstract class Employee {

    protected final int salary;

    protected Employee(int salary) {
        this.salary = salary;
    }

    public abstract int payAmount();
}
