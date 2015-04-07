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

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/**
 *
 */
public class DefaultTestContentService implements TestContentService {
    protected TemplateChooser chooser;

    protected TestContentGenerator dateGenerator;

    protected TestContentGenerator nameGenerator;

    protected TestContentGenerator emailAddressGenerator;

    protected TestContentGenerator amountGenerator;
    
    public TestData generate() {
        final EmailNotificationTestData retval = getChooser().getTemplate().<EmailNotificationTestData>cast();
        retval.setName(getNameGenerator().<String>generate());
        retval.setMemberSince(new Random().nextInt(2015));
        retval.setEmailAddress(getEmailAddressGenerator().<String>generate(retval.getName()));
        retval.setAnnualSpend(getAmountGenerator().<BigDecimal>generate());
        return retval;
    }

    public Iterator<TestData> generate(final Integer count) {
        return new Iterator<TestData>() {
            int idx = 0;
            public boolean hasNext() {
                return idx < count;
            }

            public TestData next() {
                idx++;
                return generate();
            }
        };
    }

    @Inject
    public void setChooser(final TemplateChooser chooser) {
        this.chooser = chooser;
    }

    public TemplateChooser getChooser() {
        return this.chooser;
    }

    @Inject
    public void setNameGenerator(@Named("Name") final TestContentGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }

    public TestContentGenerator getNameGenerator() {
        return this.nameGenerator;
    }

    @Inject
    public void setDateGenerator(@Named("Date") final TestContentGenerator dateGenerator) {
        this.dateGenerator = dateGenerator;
    }

    public TestContentGenerator getDateGenerator() {
        return this.dateGenerator;
    }

    @Inject
    public void setEmailAddressGenerator(@Named("Email Address") final TestContentGenerator emailAddressGenerator) {
        this.emailAddressGenerator = emailAddressGenerator;
    }

    public TestContentGenerator getEmailAddressGenerator() {
        return this.emailAddressGenerator;
    }

    @Inject
    public void setAmountGenerator(@Named("Amount") final TestContentGenerator amountGenerator) {
        this.amountGenerator = amountGenerator;
    }

    public TestContentGenerator getAmountGenerator() {
        return this.amountGenerator;
    }
}

