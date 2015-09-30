package encapsulation;

import no.hal.jex.runtime.JExercise;

import java.util.Date;

import no.hal.jex.standalone.JexStandalone;
import junit.framework.TestCase;

@JExercise(
	description="A Person must contain a name, an email, a birthday and gender. These attributes should be properly encapsulated and have getters and setters that ensure valid attributes"
)
public class PersonTest extends TestCase {

	private Person person;
	private Date birthday;
	private static int[] factors1 = {3, 7, 6, 1, 8, 9, 4, 5, 2}, factors2 = {5, 
		4, 3, 2, 7, 6, 5, 4, 3, 2}; 

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		person = new Person();
	}
	
	private void testExceptionAndValue(Exception e, Class<? extends Exception> c, Object expected, Object actual) {
		assertTrue(e + " should be assignable to " + c, c.isAssignableFrom(e.getClass()));
		assertEquals(expected, actual);
	}
	
	@JExercise(
			tests="setName(String)",
			description="The setName(String) sets the name to the argument string, given that the name is within a valid format"
	)
	public void testSetName() {
		String name = person.getName();
		testInvalidName("Ola", name);
		testInvalidName("O N", name);
		testInvalidName("O. Nordmann", name);
		try {
			person.setName("Espen Askeladd");
			assertEquals("Espen Askeladd", person.getName());
		} catch (Exception e) {
			fail("Espen Askeladd is a valid name");
		}
	}

	private void testInvalidName(String invalidName, String existingName) {
		try {
			person.setName(invalidName);
			fail(invalidName + " is an invalid name");
		} catch (Exception e) {
			testExceptionAndValue(e, IllegalArgumentException.class, existingName, person.getName());			
		}
	}

	@SuppressWarnings("deprecation")
	@JExercise(
		tests="void setBirthday(String)",
		description="The setBirthday(String) should set birthday to input argument, given that the birthday is on a valid format."
	)
	public void testSetBirthday() {
		try {
			Date birth = new Date(115,11,31);
			person.setBirthday(birth);
			fail("The birthday cannot be in the future");
		} catch (Exception e) {
			testExceptionAndValue(e, IllegalArgumentException.class, birthday, person.getBirthday());
		}
		Date birthday2 = new Date(93, 11, 12);
		try {
			person.setBirthday(birthday2);
			assertEquals(birthday2, person.getBirthday());	
		} catch (Exception e) {
			fail(birthday2 + " is a valid birthday");
		}
	}
	
	@JExercise(
			tests="void setEmail(String)",
			description="The setEmail(String) should set email to input argument, given that the email is on a valid format"
	)
	public void testSetEmail() {
		person.setName("Ola Nordmann");
		String email = person.getEmail();
		testInvalidEmail("ola.nordmann@ntnu", email, IllegalArgumentException.class);
		testInvalidEmail("ola.nordmann(at)ntnu.no", email, IllegalArgumentException.class);
		testInvalidEmail("espen.askeladd@eventyr.no", email, IllegalStateException.class);
		try {
			person.setEmail("ola.nordmann@ntnu.no");
			assertEquals("ola.nordmann@ntnu.no", person.getEmail());
		} catch (Exception e) {
			fail("ola.nordmann@ntnu.no is a valid email");
		}
	}

	private void testInvalidEmail(String invalidEmail, String existingEmail, Class<? extends Exception> ex) {
		try {
			person.setEmail(invalidEmail);
			fail(invalidEmail + " is an invalid email");
		} catch (Exception e) {
			testExceptionAndValue(e, ex, existingEmail, person.getEmail());
		}
	}

	@JExercise(
			tests="void setGender(String)",
			description="The setGender(String) should set gender to input argument, given that the gender is on a valid format"
	)	
	public void testSetGender() {
		String validGenders = "FM\0";
		char gender = person.getGender();
		for (char c = '\0'; c < '\uFFFF'; c++) {
			try {
				person.setGender(c);
				if (validGenders.indexOf(c) < 0) {
					fail(c + " is an invalid gender");
				}
				gender = person.getGender();
			} catch(Exception e) {
				if (validGenders.indexOf(c) >= 0) {
					fail("F, M and \\0 are valid genders");
				} else {
					testExceptionAndValue(e, IllegalArgumentException.class, gender, person.getGender());
				}
			}
		}
	}
	
	@SuppressWarnings({ "deprecation" })
	@JExercise(
			tests="void setSSN(String)",
			description="The setSSN(String) should set social security number to input argument, given that the SSN is valid"
	)	
	public void testSetSSN() {
		try {
			Date birth = new Date("94/01/01");
			person.setBirthday(birth);
			person.setGender('M');	
		} catch (Exception e ) {
			fail();
		}
		try {
			person.setSSN("010194111" + generateValid(1, 1, 1, "010194"));
			assertEquals("01019411156", person.getSocialsec());			
		} catch (Exception e ) {
			System.out.println(e);
			fail();
		}
		try {
			person.setSSN("010194112" + generateValid(1, 1, 2, "010194"));	
			fail();
		} catch (Exception e) {
			assertEquals("01019411156", person.getSocialsec());
		}
		try {
			person.setSSN("01019411122");
			fail();
		} catch (Exception e) {
			assertEquals("01019411156", person.getSocialsec());
		}
	}
	
	private static String generateValid(int n1, int n2, int n3, String birthday) {
		birthday = birthday + n1 + n2 + n3;
		int K1 = 0;
		int K2 = 0;
		for(int i = 0; i < birthday.length(); i++) {
			K1 += factors1[i]*Character.getNumericValue(birthday.charAt(i));
			K2 += factors2[i]*Character.getNumericValue(birthday.charAt(i));
		}
		K1 = 11- (K1 % 11);
		if (K1 == 11) {
			K1 = 0;
		}
		K2 += K1*factors2[9];
		K2 = 11 - (K2 % 11);
		if (K2 == 11) {
			K2 = 0;
		}
		return K1 + "" + K2;
	}
	
	public static void main(String[] args) {
		JexStandalone.main(PersonTest.class);
	}
}
