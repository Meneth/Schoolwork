package encapsulation;

import java.util.Date;

import no.hal.jex.runtime.JExercise;
import no.hal.jex.standalone.JexStandalone;
import junit.framework.TestCase;

@JExercise(
	description="A Person must contain a name, an email, a birthday, gender and social security number. These attributes should be properly encapsulated and have getters and setters that ensure valid attributes"
)
public class PersonTest2 extends TestCase {

	private static int[] factors1 = {3, 7, 6, 1, 8, 9, 4, 5, 2}, factors2 = {5, 
		4, 3, 2, 7, 6, 5, 4, 3, 2}; 
	
	Person person;
	Date birthday;

	@SuppressWarnings("deprecation")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		person = new Person();
		person.setName("Ola Nordmann");
		person.setEmail("ola.nordmann@ntnu.no");
		person.setGender('M');
		birthday = new Date(94,0,1);
		person.setBirthday(birthday);
	}
	
	@JExercise(
			tests="setName(String)",
			description="The setName(String) sets the name to the argument string, given that the name is within a valid format"
	)
	public void testSetName() {
		try {
			person.setName("Ola");
			fail();
		} catch (Exception e) {
			assertEquals("Ola Nordmann", person.getName());			
		}
		
		try {
			person.setName("O N");
			fail();
		} catch (Exception e) {
			assertEquals("Ola Nordmann", person.getName());			
		}
		
		try {
			person.setName("O. Nordmann");
			fail();
		} catch (Exception e) {
			assertEquals("Ola Nordmann", person.getName());			
		}
		
		try {
			person.setName("Espen Askeladd");
			assertEquals("Espen Askeladd", person.getName());			
		} catch (Exception e) {
			fail();
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
			fail();
		} catch (Exception e) {
			assertEquals(birthday, person.getBirthday());	
		}
		try {
			Date birth = new Date(93, 11, 12);			
			person.setBirthday(birth);
			assertEquals(birth, person.getBirthday());	
		} catch (Exception e) {
			fail();
		}
	}
	
	@JExercise(
			tests="void setEmail(String)",
			description="The setEmail(String) should set email to input argument, given that the email is on a valid format"
	)
	public void testSetEmail() {
		try {
			person.setEmail("ola.nordmann@ntnu");
			fail();
		} catch (Exception e) {			
			assertEquals("ola.nordmann@ntnu.no", person.getEmail());			
		}
		
		try {
			person.setEmail("ola.nordmann(at)ntnu.no");
			fail();
		} catch (Exception e) {			
			assertEquals("ola.nordmann@ntnu.no", person.getEmail());			
		}
		
		try {
			person.setEmail("espen.askeladd@eventyr.no");
			assertEquals("espen.askeladd@eventyr.no", person.getEmail());
		} catch (Exception e) {			
			fail();		
		}
		
		try {
			person.setEmail("espenaskeladd@eventyr.no");
			fail();		
		} catch (Exception e) {			
			assertEquals("espen.askeladd@eventyr.no", person.getEmail());
		}
		
		try {
			person.setEmail("e.a@ev.no");
			fail();		
		} catch (Exception e) {			
			assertEquals("espen.askeladd@eventyr.no", person.getEmail());
		}
	}
	
	@JExercise(
			tests="void setGender(String)",
			description="The setGender(String) should set gedner to input argument, given that the gender is on a valid format"
	)	
	public void testSetGender() {
		try {
			person.setGender('F');
			assertEquals('F', person.getGender());		
		} catch(Exception e) {
			fail();
		}
		try {
			person.setGender('O');
			fail();
		} catch (Exception e) {
			assertEquals('F', person.getGender());			
		}
		try {
			person.setGender('\0');
			assertEquals('\0', person.getGender());			
		} catch (Exception e) {
			fail();
		}
	}
	
	@JExercise(
			tests="void setSSN(String)",
			description="The setSSN(String) should set social security number to input argument, given that the SSN is valid"
	)	
	public void testSetSSN() {
		try {
			person.setSSN("010194111" + generateValid(1, 1, 1, "010194"));
			assertEquals("01019411156", person.getSocialsec());			
		} catch (Exception e ) {
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
		JexStandalone.main(PersonTest2.class);
	}
}