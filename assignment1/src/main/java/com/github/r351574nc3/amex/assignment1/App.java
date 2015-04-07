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
package com.github.r351574nc3.amex.assignment1;

import static com.github.r351574nc3.logging.FormattedLogger.*;

import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.MissingArgumentException;

import java.io.File;
import java.io.IOException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.github.r351574nc3.amex.assignment1.csv.DefaultGenerator;
import com.github.r351574nc3.amex.assignment1.csv.DefaultInterpreter;
import com.github.r351574nc3.amex.assignment1.csv.Generator;
import com.github.r351574nc3.amex.assignment1.csv.Interpreter;


public class App  {

    /**
     * Main body executed with the CLI option -i is used
     *
     * @param input {@link File} instance to be read
     */
    public void run(final File input) {
        try {
            final Interpreter interpreter = new DefaultInterpreter();
            interpreter.interpret(input);
        }
        catch (IOException ioe) {
            error("Error reading CSV Input: %s", ioe.getMessage());
        }
    }
    
    /**
     * Main body executed with the CLI option -o is used
     *
     * @param output {@link File} instance of CSV to be generated
     * @param count is the number of records to add to the file
     */
    public void run(final File output, final Integer count) {
        final Injector injector = Guice.createInjector(new Assignment1Module());
        final Generator generator = injector.getInstance(DefaultGenerator.class);

        try {           
            generator.generate(output, count);
        }
        catch (IOException ioe) {
            error("Error generating CSV output: %s", ioe.getMessage());
        }
    }
    
    public static void main(final String ... args) {
        if (args.length < 1) {
            printUsage();
            System.exit(0);            
        }
        
        final Options options = new Options();
        options.addOption(OptionBuilder.withArgName("output")
                          .hasArg(true)
                          .withDescription("Path for CSV output").create("o"));
        options.addOption(OptionBuilder.withArgName("input")
                          .hasArg(true)
                          .withDescription("Path for CSV input").create("i"));

        
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

        if ((cmd.hasOption('o') && cmd.hasOption('i'))
            || !(cmd.hasOption('o') || cmd.hasOption('i'))
            || (cmd.hasOption('o') && cmd.getArgs().length < 1)) {
            printUsage();
            System.exit(0);
        }

        if (cmd.hasOption('o') && cmd.getArgs().length > 0) {
            final String outputName = cmd.getOptionValue("o"); 
            final int iterations = Integer.parseInt(cmd.getArgs()[cmd.getArgs().length - 1]);
            new App().run(new File(outputName), iterations);
        }
        else if (cmd.hasOption('i')) {
            final String inputName = cmd.getOptionValue("i");
            new App().run(new File(inputName));            
        }
        
        System.exit(0);
    }

    public static void printUsage() {
        System.out.println(new StringBuilder()
                           .append("Usage:\n")
                           .append("    App [-i <input CSV>] [-o <output CSV>] <number of records to generate>\n\n")
                           .append("            -i <input CSV>  : Used when reading from a CSV. Provides input CSV path\n")
                           .append("            -o <output CSV> : Used when writing to a CSV. Provides output CSV path\n")
                           .append("            <number of records to generate> : Only required with -o.\n")
                           .append("\n")
                           .append("            Note: either -o or -i can be used, but not both at the same time.\n")
                           .toString());
    }
}
