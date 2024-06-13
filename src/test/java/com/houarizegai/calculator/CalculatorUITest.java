package com.houarizegai.calculator;

import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorUITest {

    private CalculatorUI calculatorUI;

    // This method runs before each test case to set up the test environment
    @BeforeEach
    void setUp() {
        calculatorUI = new CalculatorUI();  // Initialize the CalculatorUI instance before each test
    }

    // This parameterized test runs the calculation method with multiple sets of input data
    @ParameterizedTest
    @CsvSource({
        "3,5,+,8",     // Test case 1: 3 + 5 = 8
        "2,8,-,-6",    // Test case 2: 2 - 8 = -6
        "44.5,10,*,445", // Test case 3: 44.5 * 10 = 445
        "320,5,/,64",  // Test case 4: 320 / 5 = 64
        "3,5,%,3",     // Test case 5: 3 % 5 = 3
        "5,3,^,125"    // Test case 6: 5 ^ 3 = 125
    })
    void testCalculation(double firstNumber, double secondNumber, char operator, double expectedResult) {
        // Check if the calculation method produces the expected result for each set of input data
        assertEquals(expectedResult, calculatorUI.calculate(firstNumber, secondNumber, operator));
    }
}
