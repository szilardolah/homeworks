package com.szilardolah.mini.interceptor;

import com.szilardolah.mini.annotation.WebshopBean;
import com.szilardolah.mini.qualifier.ValidatorQualifier;
import java.io.Serializable;
import java.util.Set;
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
public class ValidatorInterceptor implements Serializable{

    @Inject @ValidatorQualifier
    private transient Validator validator;
    
 
    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        checkWebshopBeanAnnotation(ic.getParameters());
        return ic.proceed();
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
