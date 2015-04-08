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

import java.util.Random;
import java.util.Date;

/**
 * Used to when test data requires a generated {@link Date}
 * 
 *
 * @author Leo Przybylski
 */
public class DateGenerator implements TestContentGenerator {

    public DateGenerator() {
    }

    /**
     * Randomly generates a long to represent epoch time (time in milliseconds since 1970), then returns a {@link Date} instance
     * once it has a reasonable random value.
     *
     * @return {@link Date} instance between 1900 and now.
     */
    public <T> T generate(final Object ... args) {
        Long epoch = new Random().nextLong() * (new Random().nextBoolean() ? 1 : -1);
        Long epochNow = new Date().getTime();
        Long epoch1900 = -2177422016000L;

        while (epoch >= epochNow || epoch <= epoch1900) {
            epoch = new Random().nextLong() * (new Random().nextBoolean() ? 1 : -1);
        }

        return (T) new Date(epoch);
    }
}
