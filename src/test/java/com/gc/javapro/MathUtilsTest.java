package com.gc.javapro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    void sum() {
        MathUtils mathUtils = new MathUtils();
        assertEquals(2, mathUtils.sum(1,1));
    }
}