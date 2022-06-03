package common.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Constraint(validatedBy = {Enum.EnumValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<? extends java.lang.Enum<?>> enumClass();

	boolean nullable() default false;

	class EnumValidator implements ConstraintValidator<Enum, String> {

		private Enum annotation;

		@Override
		public void initialize(Enum constraintAnnotation) {
			this.annotation = constraintAnnotation;
		}

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {

			if(annotation.nullable() && value == null){
				return true;
			}

			Object[] enumValues = this.annotation.enumClass().getEnumConstants();

			return Arrays.stream(enumValues).anyMatch(t->value.equalsIgnoreCase(t.toString()));
		}
	}

}
