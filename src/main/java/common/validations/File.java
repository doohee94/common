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

import org.springframework.web.multipart.MultipartFile;

@Constraint(validatedBy = {File.FileValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface File {
	String message() default "잘못된 파일 형식입니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean nullable() default false;

	String[] acceptExtensions() default {};

	class FileValidator implements ConstraintValidator<File, MultipartFile> {

		private File annotation;

		private static final int NOT_FOUND = -1;
		public static final char EXTENSION_SEPARATOR = '.';
		private static final char UNIX_SEPARATOR = '/';
		private static final char WINDOWS_SEPARATOR = '\\';

		@Override
		public void initialize(File constraintAnnotation) {
			this.annotation = constraintAnnotation;
		}

		@Override
		public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

			if (annotation.nullable() && value == null) {
				return true;
			}

			if (value == null) {
				return false;
			}

			return annotation.acceptExtensions().length <= 0 ||
				Arrays.stream(annotation.acceptExtensions())
					.anyMatch(t -> t.equalsIgnoreCase(getExtension(value.getOriginalFilename())));
		}

		public  String getExtension(final String filename) {
			if (filename == null) {
				return null;
			}
			final int index = indexOfExtension(filename);
			if (index == NOT_FOUND) {
				return "";
			} else {
				return filename.substring(index + 1);
			}
		}

		public  int indexOfExtension(final String filename) {
			if (filename == null) {
				return NOT_FOUND;
			}
			final int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);
			final int lastSeparator = indexOfLastSeparator(filename);
			return lastSeparator > extensionPos ? NOT_FOUND : extensionPos;
		}

		public  int indexOfLastSeparator(final String filename) {
			if (filename == null) {
				return NOT_FOUND;
			}
			final int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
			final int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
			return Math.max(lastUnixPos, lastWindowsPos);
		}

	}

}
