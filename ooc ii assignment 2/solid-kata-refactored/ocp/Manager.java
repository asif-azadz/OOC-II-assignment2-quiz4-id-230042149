package com.codurance.ocp;


public class Manager extends Employee {

    private final int bonus;

    public Manager(int salary, int bonus) {
        super(salary);
        this.bonus = bonus;
    }

    @Override
    public int payAmount() {
        // Managers receive their base salary plus a bonus
        return salary + bonus;
    }
}
