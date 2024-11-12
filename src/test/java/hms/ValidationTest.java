package hms;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidationTest {

	@Test
	void testInvalidEmailAddressFormat() {
		assertFalse(Validation.validateEmailAddress("soidjifdnf"));
	}

	@Test
	void testValidEmailAddress1() {
		assertTrue(Validation.validateEmailAddress("alice.brown@example.com"));
	}

	@Test
	void testValidEmailAddress2() {
		assertTrue(Validation.validateEmailAddress("bob.stone@example.com"));
	}

	@Test
	void testInvalidPasswordLength() {
		assertFalse(Validation.validatePassword("asdW$1"));
	}

	@Test
	void testInvalidPasswordUppercase() {
		assertFalse(Validation.validatePassword("quickbrownfoxjumps$1"));
	}

	@Test
	void testInvalidPasswordLowercase() {
		assertFalse(Validation.validatePassword("QUICKBROWNFOXJUMPS$1"));
	}

	@Test
	void testInvalidPasswordDigit() {
		assertFalse(Validation.validatePassword("QUICKBROWNFOXJUMPS$aosdno"));
	}

	@Test
	void testInvalidPasswordSpecialCharacter() {
		assertFalse(Validation.validatePassword("QUICKBROWNFOXJUMPS3aosdno"));
	}

	@Test
	void testInvalidPasswordSpace() {
		assertFalse(Validation.validatePassword("a1d#SAf3$ aso"));
	}

	@Test
	void testInvalidPasswordTab() {
		assertFalse(Validation.validatePassword("a1d#SAf3$\ttaso"));
	}

	@Test
	void testValidPassword() {
		assertTrue(Validation.validatePassword("@93aYE6P#CHAbR$eSu"));
	}

}
