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
 * Generates email addresses for use as test data. Email addresses are only required to have a domain and a name separated by '@'. The 
 * domain portion is required to have a top-level and a company. The name of the email address is completely open-ended. It can have
 * any character including spaces and special characters like '@'. This makes it particularly difficult to validate email addresses
 * since just about anything is allowed.
 *
 *
 */
public class EmailAddressGenerator implements TestContentGenerator {
    public EmailAddressGenerator() {
    }

    /**
     *
     */
    public <T> T generate(final Object ... args) {
        if (args.length > 0) {
            final String name = (String) args[0];
            return (T) String.format("%s@amex.com", name.replace(' ', '.'));
        }
        throw new IllegalArgumentException("This generator generates email addresses from names. Please give a name.");        
    }
}
