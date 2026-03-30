package com.codurance.lsp;


public abstract class Vehicle {

    private boolean engineStarted = false;

    
     //All vehicles (petrol and electric) have an engine.
    // These methods are safe to call on ANY Vehicle subclass.
     
    public void startEngine() {
        this.engineStarted = true;
    }

    public void stopEngine() {
        this.engineStarted = false;
    }

    public boolean engineIsStarted() {
        return engineStarted;
    }
}
