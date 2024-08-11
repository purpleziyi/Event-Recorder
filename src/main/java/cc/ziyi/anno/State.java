package cc.ziyi.anno;

/* Code patterns refers to other existing annotated source codes eg @NotEmpty*/

import cc.ziyi.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented  // meta annotation
@Target({ElementType.FIELD})  // meta annotation
@Retention(RetentionPolicy.RUNTIME)  // meta annotation
@Constraint( validatedBy = {StateValidation.class})  // identify the class of providing the validation logic
public @interface State {
    // Provide message after validation fails
    String message() default "The value of the state parameter must be either 'Published' or 'Draft'.";

    // assign group
    Class<?>[] groups() default {};

    // obtain State-annotation's additional info
    Class<? extends Payload>[] payload() default {};
}
