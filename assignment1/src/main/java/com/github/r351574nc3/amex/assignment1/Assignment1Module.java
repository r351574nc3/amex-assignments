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


import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import com.github.r351574nc3.amex.assignment1.csv.DefaultGenerator;
import com.github.r351574nc3.amex.assignment1.csv.Generator;
import com.github.r351574nc3.amex.assignment1.model.AmountGenerator;
import com.github.r351574nc3.amex.assignment1.model.AnimalDescriptionGenerator;
import com.github.r351574nc3.amex.assignment1.model.BreedGenerator;
import com.github.r351574nc3.amex.assignment1.model.DateGenerator;
import com.github.r351574nc3.amex.assignment1.model.EmailAddressGenerator;
import com.github.r351574nc3.amex.assignment1.model.InstructionsGenerator;
import com.github.r351574nc3.amex.assignment1.model.NameGenerator;
import com.github.r351574nc3.amex.assignment1.model.OfferGenerator;
import com.github.r351574nc3.amex.assignment1.model.PhoneNumberGenerator;
import com.github.r351574nc3.amex.assignment1.model.ResetLinkGenerator;
import com.github.r351574nc3.amex.assignment1.model.TemplateChooser;
import com.github.r351574nc3.amex.assignment1.model.DefaultTemplateChooser;
import com.github.r351574nc3.amex.assignment1.model.TestContentGenerator;
import com.github.r351574nc3.amex.assignment1.model.DefaultTestContentService;
import com.github.r351574nc3.amex.assignment1.model.TestContentService;

/**
 *
 * @author Leo Przybylski
 */
public class Assignment1Module extends AbstractModule {
    
    @Override 
    protected void configure() {
        
        bind(TestContentGenerator.class).annotatedWith(Names.named("Amount")).to(AmountGenerator.class);
        bind(TestContentGenerator.class).annotatedWith(Names.named("Animal Description")).to(AnimalDescriptionGenerator.class);
        bind(TestContentGenerator.class).annotatedWith(Names.named("Breed")).to(BreedGenerator.class);
        bind(TestContentGenerator.class).annotatedWith(Names.named("Date")).to(DateGenerator.class);
        bind(TestContentGenerator.class).annotatedWith(Names.named("Email Address")).to(EmailAddressGenerator.class);
        bind(TestContentGenerator.class).annotatedWith(Names.named("Instructions")).to(InstructionsGenerator.class);
        bind(TestContentGenerator.class).annotatedWith(Names.named("Name")).to(NameGenerator.class);
        bind(TestContentGenerator.class).annotatedWith(Names.named("Offer")).to(OfferGenerator.class);
        bind(TestContentGenerator.class).annotatedWith(Names.named("Phone Number")).to(PhoneNumberGenerator.class);
        bind(TestContentGenerator.class).annotatedWith(Names.named("Reset Link")).to(ResetLinkGenerator.class);

        bind(TemplateChooser.class).to(DefaultTemplateChooser.class);

        bind(TestContentService.class).to(DefaultTestContentService.class);
        
        bind(Generator.class).to(DefaultGenerator.class);
    }
}
