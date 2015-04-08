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

import static java.lang.Math.sqrt;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Singleton that parses strings and creates {@link QuadraticFuction} instances, so it's both a builder and a factory
 *
 * @author Leo Przybylski
 */
public class QuadraticFunctionFactory {
    protected static QuadraticFunctionFactory factory;
    
    protected QuadraticFunctionFactory() {
        
    }

    public static QuadraticFunctionFactory getInstance() {
        if (factory == null) {
            factory = new QuadraticFunctionFactory();
        }

        return factory;
    }

    protected Double parseFirstCoefficient(final String equation) {
        final Matcher matcher = Pattern.compile("([\\+\\-]?[0-9]*)x\\^2").matcher(equation);
        if (matcher.find()) {
            return matcher.group(1).length() > 0 ? Double.parseDouble(matcher.group(1)) : 1D;
        }
        return 1D;
    }

    protected Double parseSecondCoefficient(final String equation) {
        final Matcher matcher = Pattern.compile("([\\+\\-]?[\\s]?[0-9]*)x[^\\^]").matcher(equation);
        if (matcher.find()) {
            final String matched = matcher.group(1);
            
            return matcher.group(1).replaceAll(" ", "").length() > 0 ? Double.parseDouble(matcher.group(1).replaceAll(" ", "")) : 1D;
        }
        return 1D;
    }

    protected Double parseConstant(final String equation) {
        final Matcher matcher = Pattern.compile("([\\+\\-]?[\\s]?[0-9]*) = 0").matcher(equation);
        if (matcher.find()) {
            return matcher.group(1).replaceAll(" ", "").length() > 0 ? Double.parseDouble(matcher.group(1).replaceAll(" ", "")) : 0D;
        }
        return 0D;
    }

    /**
     * Takes an equation in {@link String} form and parses it using Regex into 
     * a {@link QuadraticEquation}.
     */
    public QuadraticFunction newFunction(final String equation) {
        return new QuadraticFunction(parseFirstCoefficient(equation.trim()),
                                     parseSecondCoefficient(equation.trim()),
                                     parseConstant(equation.trim()));
    }
}
