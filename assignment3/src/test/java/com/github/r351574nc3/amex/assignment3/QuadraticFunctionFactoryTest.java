/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Leo Przybylski
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.r351574nc3.amex.assignment3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests parsing of quadratic functions in standard form..
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

    @Test
    public void testNegativeSecondCoefficient2() {
        final QuadraticFunctionFactory factory = new QuadraticFunctionFactory() {
                    public QuadraticFunction newFunction(final String equation) {
                        final double a = parseFirstCoefficient(equation.trim());
                        assertEquals(2D, a, 0.1);
                        final double b = parseSecondCoefficient(equation.trim());
                        assertEquals(-3D, b, 0.1);
                        final double c = parseConstant(equation.trim());
                        assertEquals(-1D, c, 0.1);
                        return new QuadraticFunction(a, b, c);
                    }

            };
        
        final QuadraticFunction quadratic = factory.newFunction("2x^2 - 3x - 1 = 0");
        
    }
}
