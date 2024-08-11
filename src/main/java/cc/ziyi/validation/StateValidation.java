package cc.ziyi.validation;

import cc.ziyi.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    /**
     *
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     *
     * @return false - validation fails; true - validation pass
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // provide validation logic
        if(value == null) {
            return false;
        }
        if(value.equals("Published") || value.equals("Draft")){
            return true;   // the only case to pass the validation
        }
        return false;
    }
}
