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

import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.MissingArgumentException;

import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ArffLoader;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main entry point class
 *
 * @author Leo Przybylski
 */
public class App  {
    protected static final String UNABLE_TO_LOAD_DATA_MESSAGE = "Unable to load the data file";
    protected static final String DEFAULT_MPG_DATA_FILE = "auto-mpg.data";
    protected static final String USER_DIR_KEY       = "user.dir";
    
    protected Instances trained;
    protected Instances test;
    protected LinearRegression classifier;
    
    public App() {
    }

    public void setTest(final Instances instances) {
        this.test = instances;
    }

    public Instances getTest() {
        return this.test;
    }

    public void setTrained(final Instances instances) {
        this.trained = instances;
    }

    public Instances getTrained() {
        return this.trained;
    }

    public void setClassifier(final LinearRegression linearRegression) {
        this.classifier = linearRegression;
    }

    public LinearRegression getClassifier() {
        return this.classifier;
    }

    /**
     * Loads the MPG data file from UCI
     *
     * @param {@link String} intances of path of the dataset
     * @return {@link Instances} instance containing all records of the dataset.
     */
    public Instances load(final String mpgFile) throws Exception {
        try {           
            final Instances retval = DataSource.read(mpgFile);
            retval.setClassIndex(0);
            retval.deleteStringAttributes();
            return retval;
        }
        catch (Exception e) {
            error("%s:%s", UNABLE_TO_LOAD_DATA_MESSAGE, e.getClass().getSimpleName());
            e.printStackTrace();
        }

        return null;

    }

    /**
     * Trains the model using a {@link LinearRegression} classifier.
     *
     * @throws an Exception
     */
    public void train() throws Exception {
        setClassifier(new LinearRegression());
        getClassifier().buildClassifier(getTrained());
    }

    /**
     * Tests/evaluates the trained model. This method assumes that {@link #train()} was previously called to assign a {@link LinearRegression} 
     * classifier. If it wasn't, an exception will be thrown.
     *
     * @throws Exception if train wasn't called prior.
     */
    public void test() throws Exception {
        if (getClassifier() == null) {
            throw new RuntimeException("Make sure train was run prior to this method call");
        }
        
        final Evaluation eval = new Evaluation(getTrained());
        eval.evaluateModel(getClassifier(), getTest());
        info("%s", eval.toSummaryString("Results\n\n", false));
        info("Percent of correctly classified instances: %s", eval.pctCorrect());
    }

    /**
     * Generates a predictive model based on a previously trained and evaluated model.
     *
     * @param inputName unlabeled model to load
     * @param outputName path to the file where results will be stored.
     */
    public void predict(final String inputName, final String outputName) throws Exception {
        final Instances input = load(inputName);
        final Instances labeled = new Instances(input);
        
        for (int i = 0; i < input.numInstances(); i++) {
            final Double clsLabel = getClassifier().classifyInstance(input.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
        }

        boolean isLocal = true;
        if (outputName.contains(File.separator)) {
            isLocal = false;
        }
        
        final File pwd = isLocal ? new File(System.getProperty(USER_DIR_KEY)) : new File(outputName).getParentFile();
        if (pwd.exists() && pwd.isDirectory()) {
            DataSink.write(outputName, labeled);
        }
        else {
            throw new FileNotFoundException("Cannot write to " + outputName);
        }
    }
    
    public static void main(final String ... args) {
        if (args.length < 1) {
            printUsage();
            System.exit(0);            
        }
        
        final Options options = new Options();
        options.addOption(OptionBuilder.withArgName("test")
                          .hasArg(true)
                          .isRequired(true)
                          .withDescription("Path for ARFF test data").create("t"));
        options.addOption(OptionBuilder.withArgName("output")
                          .hasArg(true)
                          .isRequired(true)
                          .withDescription("Path for ARFF output").create("o"));
        options.addOption(OptionBuilder.withArgName("input")
                          .hasArg(true)
                          .isRequired(true)
                          .withDescription("Path for ARFF input").create("i"));

        
        final CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            printUsage();
            System.exit(0);
        }

        final String outputName = cmd.getOptionValue("o"); 
        final String inputName  = cmd.getOptionValue("i"); 
        final String testName   = cmd.getOptionValue("t"); 
        
        final App app = new App();
        try {
            if (args.length > 0) {
                app.setTrained(app.load(testName));
                app.setTest(app.load(testName));
            }
        }
        catch (Exception e) {
            error("There was an exception loading training and test datasets: %s", e.getMessage());
        }
        
        try {
            app.train();
            app.test();
        }
        catch (Exception e) {
            error("There was an exception testing the model: %s", e.getMessage());
        }

        try {
            app.predict(inputName, outputName);
        }
        catch (Exception e) {
            error("There was an exception predicting MPG: %s", e.getMessage());
            e.printStackTrace();
        }
        
        System.exit(0);

    }

    public static void printUsage() {
        System.out.println(new StringBuilder()
                           .append("Usage:\n")
                           .append("    com.github.r351574nc3.amex.assignment2.App -t <test data.arff> -i <input data.arff> -o <output arff> \n\n")
                           .append("            -t <test data.arff>   : Test data used for training and testing the model.\n")
                           .append("            -i <input data.arff>  : Unlabeled dataset\n")
                           .append("            -o <output data.arff> : Path where results will be output\n")
                           .toString());
    }
}
