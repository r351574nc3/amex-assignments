package com.github.r351574nc3.amex.assignment3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class QuadraticFunctionFactoryTest {
    
    @Test
    public void testStandardEquation() {
        final QuadraticFunctionFactory factory = new QuadraticFunctionFactory() {
                    public QuadraticFunction newFunction(final String equation) {
                        final double a = parseFirstCoefficient(equation.trim());
                        assertEquals(10D, a, 0.1);
                        final double b = parseSecondCoefficient(equation.trim());
                        assertEquals(5D, b, 0.1);
                        final double c = parseConstant(equation.trim());
                        assertEquals(23D, c, 0.1);
                        return new QuadraticFunction(a, b, c);
                    }

            };
        
        final QuadraticFunction quadratic = factory.newFunction("10x^2 + 5x + 23 = 0");
        
    }

    @Test
    public void testNegativeFirstCoefficient() {
        final QuadraticFunctionFactory factory = new QuadraticFunctionFactory() {
                    public QuadraticFunction newFunction(final String equation) {
                        final double a = parseFirstCoefficient(equation.trim());
                        assertEquals(-10D, a, 0.1);
                        final double b = parseSecondCoefficient(equation.trim());
                        assertEquals(5D, b, 0.1);
                        final double c = parseConstant(equation.trim());
                        assertEquals(23D, c, 0.1);
                        return new QuadraticFunction(a, b, c);
                    }

            };
        
        final QuadraticFunction quadratic = factory.newFunction("-10x^2 + 5x + 23 = 0");
        
    }

    @Test
    public void testNegativeSecondCoefficient() {
        final QuadraticFunctionFactory factory = new QuadraticFunctionFactory() {
                    public QuadraticFunction newFunction(final String equation) {
                        final double a = parseFirstCoefficient(equation.trim());
                        assertEquals(1D, a, 0.1);
                        final double b = parseSecondCoefficient(equation.trim());
                        assertEquals(-2D, b, 0.1);
                        final double c = parseConstant(equation.trim());
                        assertEquals(1D, c, 0.1);
                        return new QuadraticFunction(a, b, c);
                    }

            };
        
        final QuadraticFunction quadratic = factory.newFunction("x^2 - 2x + 1 = 0");
        
    }
}
