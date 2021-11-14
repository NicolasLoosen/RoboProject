package com.mobileapplicationdev.roboproject.models;

/**
 * Data Packet which contains all necessary control data
 */

@SuppressWarnings("unused")
public class ControlData {
    private int speed;
    private Engines engine;
    private float varI;
    private float varP;
    private float regulatorFrequency;

    private float angularVelocity;
    private float x;
    private float y;

    /**
     * standard constructor
     */
    public ControlData() {
    }

    /**
     * return the angular velocity
     * @return angularVelocity
     */
    public float getAngularVelocity() {
        return angularVelocity;
    }

    /**
     * set AngularVelocity
     * @param angularVelocity user input
     */
    public void setAngularVelocity(float angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    /**
     * get I
     * @return I
     */
    public float getVarI() {
        return varI;
    }

    /**
     * get current X
     * @return x
     */
    public float getX() {
        return x;
    }

    /**
     * set new x
     * @param x new value
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * get current Y
     * @return y
     */
    public float getY() {
        return y;
    }

    /**
     * set new Y
     * @param y new value
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Control Data as a string
     * @return control data String
     */
    @Override
    public String toString() {
        return "ControlData{" +
                "angularVelocity=" + angularVelocity +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * set I
     * @param varI I
     */
    public void setVarI(float varI) {
        this.varI = varI;
    }

    /**
     * get P
     * @return P
     */
    public float getVarP() {
        return varP;
    }

    /**
     * set P
     * @param varP new Value
     */
    public void setVarP(float varP) {
        this.varP = varP;
    }

    /**
     * get Frequency
     * @return current frequency
     */
    public float getRegulatorFrequency() {
        return regulatorFrequency;
    }

    /**
     * set Frequency
     * @param regulatorFrequency new value
     */
    public void setRegulatorFrequency(float regulatorFrequency) {
        this.regulatorFrequency = regulatorFrequency;
    }

    /**
     * get speed
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * set speed
     * @param speed new value
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * get Engine
     * @return Engine
     */
    public Engines getEngine() {
        return engine;
    }

    /**
     * set Engine
     * @param engine new Engine
     */
    public void setEngine(Engines engine) {
        this.engine = engine;
    }
}
