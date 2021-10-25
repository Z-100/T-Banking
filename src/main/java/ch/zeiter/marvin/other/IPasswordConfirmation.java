//package ch.zeiter.marvin.other;
//
//import ch.zeiter.marvin.Blueprints.Account;
//
//import java.util.function.Function;
//import java.util.regex.Pattern;
//
//import static ch.zeiter.marvin.other.IPasswordConfirmation.ValidationResult.MIN_MAX_CHAR;
//import static ch.zeiter.marvin.other.IPasswordConfirmation.ValidationResult.SUCCESS;
//
///**
// * Could've done this with a simple regex.
// * But why use a simple solution, when there's
// * an even harder one?
// */
//public interface IPasswordConfirmation
//		extends Function<Account, IPasswordConfirmation.ValidationResult> {
//
//	static IPasswordConfirmation hasMinMaxChar() {
//		return acc -> {
//			return acc.getPassword().length() > 8
//					&& acc.getPassword().length() < 32 ?
//					SUCCESS : MIN_MAX_CHAR;
//		};
//	}
//
//
//	static IPasswordConfirmation hasOneLetterUpper() {
//		return acc -> {
//			return acc.getPassword().contains(Pattern.compile("(?=.*[A-Z])"));
//		}
//	}
//
//	static IPasswordConfirmation hasOneLetterLower() {
//
//	}
//
//	static IPasswordConfirmation hasOneNumber() {
//
//	}
//
//	static IPasswordConfirmation hasOneSpecialChar() {
//		return acc -> acc.getPassword().contains() ?
//	}
//
//	enum ValidationResult {
//		SUCCESS, MIN_MAX_CHAR, ONELETTER_UPPER, ONELETTER_LOWER, ONE_NUMBER, ONE_SPECIAL_CHAR
//	}
//}
