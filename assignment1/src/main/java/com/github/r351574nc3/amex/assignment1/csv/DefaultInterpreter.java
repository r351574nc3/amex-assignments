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
package com.github.r351574nc3.amex.assignment1.csv;

import static com.github.r351574nc3.logging.FormattedLogger.*;

import com.google.inject.Inject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.github.r351574nc3.amex.assignment1.model.EmailNotificationTestData;
import com.github.r351574nc3.amex.assignment1.model.TestData;

/**
 * Default implementation that reads {@link CSVRecord} instances from a CSV file and converts them to 
 * {@link TestData} instances.
 *
 * @author Leo Przybylski
 */
public class DefaultInterpreter implements Interpreter<Hashtable> {
    protected static final String TOKEN_REGEX = "\\{PZN[0-9]+\\}";

    /**
     * Convert records to {@link TestData}.
     *
     * @return {@link Hashtable} instance which makes record lookup by name much easier. Records that belong to a given name are indexed
     * within the {@link Hashtable} instance. In case there is more than one instance, the object in the {@link Hashtable} is
     * a {@link LinkedList} which can be quickly iterated
     */
    public Hashtable interpret(final File input) throws IOException {
        final CSVParser parser = CSVParser.parse(input, Charset.defaultCharset(), CSVFormat.RFC4180.withDelimiter('|'));

        // Using a {@link Hashtable with the name field on the CSV record as the key. A lower load factor is used to give more
        // priority to the time cost for looking up values. 
        final Hashtable<String,LinkedList<TestData>> index = new Hashtable<String,LinkedList<TestData>>(2, 0.5f);
        
        for (final CSVRecord record : parser) {
            final EmailNotificationTestData data = toTestData(record);

            LinkedList<TestData> data_ls = index.get(data.getName());
            if (data_ls == null) {
                data_ls = new LinkedList<TestData>();
                index.put(data.getName(), data_ls);
            }
            data_ls.add(data);
        }

        return index;
    }

    protected EmailNotificationTestData toTestData(final CSVRecord record) {
        final EmailNotificationTestData retval = new EmailNotificationTestData();

        retval.setName(record.get(0));
        retval.setMemberSince(Integer.parseInt(record.get(1)));
        retval.setAnnualSpend(new BigDecimal(record.get(2)));
        retval.setEmailAddress(record.get(3));
        retval.setSubject(record.get(4));

        if (record.size() > 6) {
            for (int idx = 6; idx < record.size(); idx++) {
                retval.getAdditionalFields().add(record.get(idx));
            }
        }

        retval.setContent(replaceTokens(record.get(5), retval.getAdditionalFields()));

        return retval;
    }

    /**
     * Handles replacement of special <code>{PZN#}</code> tokens in the content field and replaces them with the personalizable field
     * values in order. This may be different than the expected behavior which is that personalizable fields may be duplicatable in
     * the content as well as being unordered. I realize that is the case, 
     *
     * @param template {@link String} instance containing tokens
     * @param fields are additional fields to replace tokens with.
     * @return evaluated and replaced {@link String} instance.
     */
    protected String replaceTokens(final String template, final List<String> fields) {
        final String[] fieldsArr = fields.toArray(new String[fields.size()]);
        return String.format(template.replaceAll(TOKEN_REGEX, "%s"), fieldsArr);
    }
}
