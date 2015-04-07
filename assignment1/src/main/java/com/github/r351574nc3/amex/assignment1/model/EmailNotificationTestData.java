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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 */
public class EmailNotificationTestData implements TestData {
    protected String name;
    protected Integer memberSince;
    protected BigDecimal annualSpend;
    protected String emailAddress;
    protected String subject;
    protected String content;
    protected List<String> additionalFields;

    public EmailNotificationTestData() {
        this.additionalFields = new ArrayList<String>();
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMemberSince(final Integer memberSince) {
        this.memberSince = memberSince;
    }

    public Integer getMemberSince() {
        return this.memberSince;
    }

    public void setAnnualSpend(final BigDecimal annualSpend) {
        this.annualSpend = annualSpend;
    }

    public BigDecimal getAnnualSpend() {
        return this.annualSpend;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }
    
    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setAdditionalFields(final List<String> additionalFields) {
        this.additionalFields = additionalFields;
    }

    public List<String> getAdditionalFields() {
        return this.additionalFields;
    }

    public <T> T cast() {
        return (T) this;
    }

    public List<Object> asList() {
        return new ArrayList<Object>() {{
            add(getName());
            add(getMemberSince());
            add(getAnnualSpend());
            add(getEmailAddress());
            add(getSubject());
            add(getContent());
            addAll(getAdditionalFields());
        }};
    }
}
