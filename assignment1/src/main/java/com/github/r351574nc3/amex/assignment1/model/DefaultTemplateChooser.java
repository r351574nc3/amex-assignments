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
import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class DefaultTemplateChooser implements TemplateChooser {

    protected List<TemplateBuilder> templates;

    protected TestContentGenerator offerGenerator;
    protected TestContentGenerator dateGenerator;
    protected TestContentGenerator breedGenerator;
    protected TestContentGenerator animalDescriptionGenerator;
    protected TestContentGenerator instructionGenerator;
    protected TestContentGenerator resetLinkGenerator;
    protected TestContentGenerator phoneNumberGenerator;

    public DefaultTemplateChooser() {
        templates = new ArrayList<TemplateBuilder>() {{
                add(new TemplateBuilder() {
                        public EmailNotificationTestData build() {
                            return new EmailNotificationTestData() {{
                                setSubject("Special Offer");
                                setContent("<html><head><title>Special Offer</title></head><body>You have been preapproved for {PZN1}. Please contact us at {PZN2} by {PZN3}.</body></html>");
                                getAdditionalFields().add(getOfferGenerator().<String>generate());
                                getAdditionalFields().add(getPhoneNumberGenerator().<String>generate());
                                getAdditionalFields().add(getDateGenerator().<Date>generate().toString());
                            }};
                        }
                    });
                add(new TemplateBuilder() {
                        public EmailNotificationTestData build() {
                            return new EmailNotificationTestData() {{
                                setSubject("Password Reset");
                                setContent("<html><head><title>Password Reset</title></head><body>You are receiving this email because you have requested a password reset. If you did not request this, then {PZN1}. To reset your password, click on the password reset link {PZN2}. For further questions, call {PZN3}.</body></html>");
                                getAdditionalFields().add(getInstructionGenerator().<String>generate());
                                getAdditionalFields().add(getResetLinkGenerator().<String>generate());
                                getAdditionalFields().add(getPhoneNumberGenerator().<String>generate());                                
                            }};
                        }
                    });
                add(new TemplateBuilder() {
                        public EmailNotificationTestData build() {
                            return new EmailNotificationTestData() {{
                                setSubject("Lost Animal");
                                setContent("<html><head><title>Lost Animal</title></head><body>We have recovered a lost {PZN1}. The animal is described as, {PZN2}. If you are the owner, please contact your building manager or dial {PZN3}. If the animal is not recovered by {PZN4}, it will be taken to animal control.</body></html>");
                                getAdditionalFields().add(getBreedGenerator().<String>generate());
                                getAdditionalFields().add(getAnimalDescriptionGenerator().<String>generate());
                                getAdditionalFields().add(getPhoneNumberGenerator().<String>generate());
                                getAdditionalFields().add(getDateGenerator().<Date>generate().toString());
                            }};
                        }
                    });
            }};
    }

    @Inject
    public void setOfferGenerator(@Named("Offer") final TestContentGenerator offerGenerator) {
        this.offerGenerator = offerGenerator;
    }

    public TestContentGenerator getOfferGenerator() {
        return this.offerGenerator;
    }

    @Inject
    public void setDateGenerator(@Named("Date") final TestContentGenerator dateGenerator) {
        this.dateGenerator = dateGenerator;
    }
    
    public TestContentGenerator getDateGenerator() {
        return this.dateGenerator;
    }

    @Inject
    public void setPhoneNumberGenerator(@Named("Phone Number") final TestContentGenerator phoneNumberGenerator) {
        this.phoneNumberGenerator = phoneNumberGenerator;
    }

    public TestContentGenerator getPhoneNumberGenerator() {
        return this.phoneNumberGenerator;
    }
    
    @Inject
    public void setBreedGenerator(@Named("Breed") final TestContentGenerator breedGenerator) {
        this.breedGenerator = breedGenerator;
    }

    public TestContentGenerator getBreedGenerator() {
        return this.breedGenerator;
    }
    
    @Inject
    public void setAnimalDescriptionGenerator(@Named("Animal Description") final TestContentGenerator animalDescriptionGenerator) {
        this.animalDescriptionGenerator = animalDescriptionGenerator;
    }

    public TestContentGenerator getAnimalDescriptionGenerator() {
        return this.animalDescriptionGenerator;
    }

    @Inject
    public void setResetLinkGenerator(@Named("Reset Link") final TestContentGenerator resetLinkGenerator) {
        this.resetLinkGenerator = resetLinkGenerator;
    }

    public TestContentGenerator getResetLinkGenerator() {
        return this.resetLinkGenerator;
    }
    
    @Inject
    public void setInstructionGenerator(@Named("Instructions") final TestContentGenerator instructionGenerator) {
        this.instructionGenerator = instructionGenerator;
    }

    public TestContentGenerator getInstructionGenerator() {
        return this.instructionGenerator;
    }


    
    /**
     * Randomly selects a template.
     *
     */
    public TestData getTemplate() {
        final Integer idx = new Random().nextInt(templates.size());
        return templates.get(idx).build();
    }

    /**
     * Cast the instance
     */
    public <T> T cast() {
        return (T) this;
    }

    static interface TemplateBuilder {
        EmailNotificationTestData build();
    }
}
