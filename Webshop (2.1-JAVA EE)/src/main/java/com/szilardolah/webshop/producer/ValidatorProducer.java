package com.szilardolah.webshop.producer;

import com.szilardolah.webshop.qualifier.ValidatorQualifier;
import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class ValidatorProducer {
  
    @Produces @ValidatorQualifier
    public Validator produceValidator() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }    
}
