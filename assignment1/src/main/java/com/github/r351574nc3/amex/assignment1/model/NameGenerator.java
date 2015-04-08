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
import java.util.ArrayList;
import java.util.List;

/**
 * Generates a random human name to be used within {@link TestData}
 *
 * @author Leo Przybylski
 */
public class NameGenerator implements TestContentGenerator {

    protected List<String> names;

    public NameGenerator() {
        names = new ArrayList<String>() {{
                add("Nicholas Cage");
                add("Sean Penn");
                add("Emilio Estevez");
            }};
    }

    /**
     * Uses a pseudo random number generator to select from a finite set of names and return it.
     *
     * @param args unused
     * @return a peudo-randomly selected name as a {@link String} instance
     */
    public <T> T generate(final Object ... args) {
        return (T) names.get(new Random().nextInt(names.size()));
    }
}
