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
package com.github.r351574nc3.amex.assignment1.model;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.Date;

/**
 * Generates {@link BigDecimal} values for test data to be used as currency.
 *
 * @author Leo Przybylski
 */
public class AmountGenerator implements TestContentGenerator {

    public AmountGenerator() {
    }

    /**
     * Randomly generates a {@link BigInteger}, converts it to a {@link BigDecimal}, then moves the decimal place over to the left by
     * 2.
     *
     * @return {@link BigDecimal} amount instance
     */
    public <T> T generate(final Object ... args) {
        return (T) new BigDecimal(new BigInteger(8, new Random())).movePointLeft(2);
    }
}
