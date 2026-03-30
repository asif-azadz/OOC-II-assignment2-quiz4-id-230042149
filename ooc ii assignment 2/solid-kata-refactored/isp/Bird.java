package com.codurance.isp;


public class Bird implements Flyable, Runnable {

    @Override
    public void fly() {
        System.out.print("Bird is flying");
    }

    @Override
    public void run() {
        System.out.print("Bird is running");
    }

    
}
