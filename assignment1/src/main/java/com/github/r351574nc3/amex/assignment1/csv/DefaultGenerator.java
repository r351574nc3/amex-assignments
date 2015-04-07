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

import com.github.r351574nc3.amex.assignment1.model.TestContentService;
import com.github.r351574nc3.amex.assignment1.model.TestData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class DefaultGenerator implements Generator {

    @Inject
    protected TestContentService testContentService;

    public void generate(final File output, final Integer recordCount) throws IOException {

        final CSVPrinter printer = new CSVPrinter(new FileWriter(output), CSVFormat.RFC4180.withDelimiter('|'));
        try {
            for (final Iterator<TestData> data_iter = testContentService.generate(recordCount); data_iter.hasNext();) {
                final TestData record = data_iter.next();
                info("Printing record: %s", record.asList().get(0));
                printer.printRecord(record.asList());
            }
        }
        finally {
            if (printer != null) {
                try {
                    printer.close();
                }
                catch (Exception e) {
                }
            }
        }
    }
}
