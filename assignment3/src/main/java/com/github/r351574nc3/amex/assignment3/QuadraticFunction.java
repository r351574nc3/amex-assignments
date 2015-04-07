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

public class QuadraticFunction implements Function<Double[]> {
    protected Double a;
    protected Double b;
    protected Double c;

    public QuadraticFunction(final Double a, final Double b, final Double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public Double[] solve() {
        final Double[] retval = new Double[2];

        // Solve for -
        retval[0] = solve(-1D);

        // Solve for +
        retval[1] = solve(1D);

        return retval;
    }

    protected Double solve(final Double sign) {
        return (((-1) * b) + (sign * sqrt((b*b) + (-4 * a * c)))) / (2 * a);
    }
}
