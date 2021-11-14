package com.mobileapplicationdev.roboproject.models;

/**
 * Engine Enum
 */

@SuppressWarnings("unused")
public enum Engines {
    ENGINE_1(0),
    ENGINE_2(1),
    ENGINE_3(2);

    private final int engine;

    Engines(int engine) {
        this.engine = engine;
    }

    public int getEngineId() {
        return engine;
    }
}
