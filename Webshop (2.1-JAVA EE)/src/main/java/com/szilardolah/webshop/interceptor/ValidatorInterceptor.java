package com.szilardolah.webshop.interceptor;

import com.szilardolah.webshop.annotation.WebshopBean;
import com.szilardolah.webshop.qualifier.ValidatorQualifier;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Interceptor
@Validation
public class ValidatorInterceptor {

    @Inject @ValidatorQualifier
    private Validator validator;
    
 
    @AroundInvoke
    public Object logMethod(InvocationContext ic) {
        checkWebshopBeanAnnotation(ic.getParameters());
        Object obj = null;
        try {
            obj = ic.proceed();
        } catch (Exception ex) {
            Logger.getLogger(ValidatorInterceptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    public Object[] checkWebshopBeanAnnotation(Object[] params) {
        for (Object param : params) {
            if (param.getClass().isAnnotationPresent(WebshopBean.class)) {
                validateBean(param);
            }        
        }
        return params;
    }

    
    public void validateBean(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (violations.isEmpty()) {
            while (violations.iterator().hasNext()) {
                ConstraintViolation<Object> violation = violations.iterator().next();
                throw new ValidationException(
                        "Validation error: " + violation.getMessage() + 
                        " Property: " + violation.getPropertyPath().toString()
                );
            }
        }
    }
}
