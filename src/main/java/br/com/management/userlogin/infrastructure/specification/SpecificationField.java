package br.com.management.userlogin.infrastructure.specification;



import br.com.management.userlogin.infrastructure.persistence.SpecificationOperation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ FIELD })
public @interface SpecificationField {

    String property() default "";
    
    String join() default "";

    SpecificationOperation operation() default SpecificationOperation.EQUAL;

}
