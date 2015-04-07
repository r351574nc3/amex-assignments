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
package com.github.r351574nc3.amex.assignment2;

import static com.github.r351574nc3.logging.FormattedLogger.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ArffLoader;
import weka.classifiers.functions.LinearRegression;
import java.io.File;

/**
 * 
 *
 */
public class App  {
    protected static final String UNABLE_TO_LOAD_DATA_MESSAGE = "Unable to load the data file";
    protected static final String DEFAULT_MPG_DATA_FILE = "auto-mpg.data";
    protected Instances data;
    
    public App() {
    }

    public void load(final File mpgFile) {
        
        try {
            final ArffLoader loader = new ArffLoader();
            if (mpgFile != null) {
                loader.setFile(mpgFile);
            }
            else {
                loader.setSource(getClass().getClassLoader().getResourceAsStream(DEFAULT_MPG_DATA_FILE));
            }
            
            data = loader.getStructure();
            // data.setClassIndex(data.numAttributes() - 1);            
            data.deleteStringAttributes();
            
            final LinearRegression classifier = new LinearRegression();
            classifier.setOptions(new String[] { "-do-not-check-capabilities" });
            classifier.buildClassifier(data);

            for (final Instance current : data) {
                // classifier.updateClassifier(current);
            }

        }
        catch (Exception e) {
            error("%s:%s", UNABLE_TO_LOAD_DATA_MESSAGE, e.getClass().getSimpleName());
        }
    }

    public void predict() throws Exception {
    }
    
    public static void main(final String ... args) {
        final App app = new App();
        if (args.length > 0) {
            app.load(new File(args[0]));
        }
        else {
            app.load(null);
        }

        try {
            app.predict();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
