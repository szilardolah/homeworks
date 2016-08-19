package com.szilardolah.mini.producer;

import com.szilardolah.mini.qualifier.ValidatorQualifier;
import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class ValidatorProducer implements Serializable{
  
    @Produces @ValidatorQualifier
    public Validator produceValidator() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        return vf.getValidator();
    }    
}
