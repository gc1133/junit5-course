package com.gc.javapro;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) -- is the default one
class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeAll
    static void beforeAllInit(){
        System.out.println("This has to run before all...");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        mathUtils = new MathUtils();

        this.testInfo = testInfo;
        this.testReporter = testReporter;
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
    }

    @AfterEach
    void cleanUp(){
        System.out.println("Cleaning up...");
    }

    @Test
    @DisplayName("Testing sum method")
    void testSum() {
        Integer expected = 2;
        Integer actual = mathUtils.sum(1, 1);
        assertEquals(expected, actual, "Sum method should add the two numbers");
    }

    @Test
    @Tag("Math")
    public void testDivide(){
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(2,0), "Divide by zero throws exception");
        //assertThrows(NullPointerException.class, () -> mathUtils.divide(2,0), "Divide by zero throws exception");
    }

    @Test
    @DisplayName("TDD. This method is disabled as of now")
    @Disabled
    void testDisabled(){
        fail("This test should fail");
    }

    @Nested
    @DisplayName("add method")
    class AddTest{

        @Test
        @DisplayName("when adding positive numbers")
        void addPositiveNumbers(){
            assertEquals(3, mathUtils.sum(1,2), "Should return the right sum");
        }

        @Test
        @DisplayName("when adding negative numbers")
        void addNegativeNumbers(){
            assertEquals(-3, mathUtils.sum(-1, -2),"Should return the right sum");
        }

    }

    @Test
    @DisplayName("test multiply method")
    @Tag("Math")
    void testMultiply(){
        assertAll(
                () -> assertEquals(4,mathUtils.multiply(2,2)),
                () -> assertEquals(0,mathUtils.multiply(2,0)),
                () -> assertEquals(-2,mathUtils.multiply(-2,1))
        );

    }

    @Test
    void testOptimizedMultiply(){
        int expected = 4;
        int actual = mathUtils.multiply(2,2);

        // here failure message string is evaluated only upon failure
        assertEquals(expected, actual, () -> "should return " + expected + " but returned " + actual );
    }
}