package com.mobileapplicationdev.roboproject.models;

import android.content.res.Resources;

import com.mobileapplicationdev.roboproject.R;

/**
 * This class contains all information about a robot profile
 */

public class RobotProfile {
    private String name;
    private String controlIp;
    private String debugIp;
    private int portOne;
    private int portTwo;
    private int portThree;
    private float maxAngularSpeed;
    private float maxX;
    private float maxY;
    private int id;
    private float frequency;

    /**
     * regex IP validator
     */
    private static final String REGEX =
            "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])." +
                    "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])." +
                    "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])." +
                    "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])";

    /**
     * full robot profile
     * @param name name
     * @param controlIp controlIp
     * @param debugIp debugIp
     * @param portOne portOne
     * @param portTwo portTwo
     * @param portThree portThree
     * @param maxAngularSpeed maxAngular Speed
     * @param maxX maxX
     * @param maxY maxY
     * @param frequency frequency
     */
    public RobotProfile(String name, String controlIp, String debugIp, int portOne, int portTwo, int portThree,
                        float maxAngularSpeed, float maxX, float maxY, float frequency) {
        checkName(name);
        this.name = name;

        checkIp(controlIp);
        this.controlIp = controlIp;

        checkIp(debugIp);
        this.debugIp = debugIp;

        checkPort(portOne);
        this.portOne = portOne;

        checkPort(portTwo);
        this.portTwo = portTwo;

        checkPort(portThree);
        this.portThree = portThree;

        checkMaxSpeed(maxAngularSpeed);
        this.maxAngularSpeed = maxAngularSpeed;

        checkMaxSpeed(maxX);
        this.maxX = maxX;

        checkMaxSpeed(maxY);
        this.maxY = maxY;

        checkFreq(frequency);
        this.frequency = frequency;
    }

    /**
     * standard constructor
     */
    public RobotProfile() {
    }

    /* Validator*/

    private void checkName(String name){
        name = name.trim();
        if (name.isEmpty()){
            throw new IllegalArgumentException(Resources.getSystem().getString(R.string.name_not_empty));
        }
    }

    private void checkIp(String ip) {
        ip = ip.trim();
        if (!ip.matches(REGEX)) {
            throw new IllegalArgumentException(Resources.getSystem().getString(R.string.ip_not_valid));
        }
    }

    private void checkPort(int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Port=" + port + Resources.getSystem().getString(R.string.port_not_valid));
        }
    }

    private void checkMaxSpeed(float speed){
        if (speed < 0.0){
            throw new IllegalArgumentException(Resources.getSystem().getString(R.string.wrong_speed));
        }
    }

    private void checkFreq(float freq){
        if(freq < 0.0){
            throw new IllegalArgumentException(Resources.getSystem().getString(R.string.wrong_frequency));
        }
    }

    /**
     * Getter/Setter/ToString
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkName(name);
        this.name = name;
    }

    public String getControlIp() {
        return controlIp;
    }

    public void setControlIp(String controlIp) {
        checkIp(controlIp);
        this.controlIp = controlIp;
    }

    public String getDebugIp() {
        return debugIp;
    }

    public void setDebugIp(String debugIp) {
        checkIp(debugIp);
        this.debugIp = debugIp;
    }

    public int getPortOne() {
        return portOne;
    }

    public void setPortOne(int portOne) {
        checkPort(portOne);
        this.portOne = portOne;
    }

    public int getPortTwo() {
        return portTwo;
    }

    public void setPortTwo(int portTwo) {
        checkPort(portTwo);
        this.portTwo = portTwo;
    }

    public int getPortThree() {
        return portThree;
    }

    public void setPortThree(int portThree) {
        checkPort(portThree);
        this.portThree = portThree;
    }

    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    public void setMaxAngularSpeed(float maxAngularSpeed) {
        checkMaxSpeed(maxAngularSpeed);
        this.maxAngularSpeed = maxAngularSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        checkFreq(frequency);
        this.frequency = frequency;
    }

    public float getMaxX() {
        return maxX;
    }

    public void setMaxX(float maxX) {
        checkMaxSpeed(maxX);
        this.maxX = maxX;
    }

    public float getMaxY() {
        return maxY;
    }

    public void setMaxY(float maxY) {
        checkMaxSpeed(maxY);
        this.maxY = maxY;
    }

    @Override
    public String toString() {
        return name + " id=" + id;
    }

}
