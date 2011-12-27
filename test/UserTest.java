import models.Address;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class UserTest extends UnitTest {

	@Before
	public void setup() {
		Fixtures.deleteDatabase();
	}

	@Test
	public void shouldCalculateNullDistancesBetweenPPWhenNoCoords() {
		Address bordeaux = new Address("Bordeaux");
		Address fargues = new Address("Fargues");

		double distanceTo = bordeaux.distanceTo(fargues);

		assertTrue(distanceTo == 0);
	}

	@Test
	public void shouldCalculateDistancesBetweenPP() {
		Address bordeaux = new Address("Bordeaux");
		Address fargues = new Address("Fargues");
		Address taff = new Address("Pessac");

		bordeaux.latitude = 44.843899f;
		bordeaux.longitude = -0.5641846999999416f;

		fargues.latitude = 44.824248f;
		fargues.longitude = -0.445824000000016f;

		taff.latitude = 44.77674f;
		taff.longitude = -0.6526569999999765f;

		double farguesToBx = bordeaux.distanceTo(fargues);
		double farguesToPessac = taff.distanceTo(fargues);

		assertEquals(17.15202038330252, farguesToPessac, 0);
		assertEquals(9.585097776031919, farguesToBx, 0);
	}

	@Test
	public void shouldCalculateDistancesBetweenPPAsString() {
		Address bordeaux = new Address("Bordeaux");
		Address fargues = new Address("Fargues");

		bordeaux.latitude = 44.843899f;
		bordeaux.longitude = -0.5641846999999416f;

		fargues.latitude = 44.824248f;
		fargues.longitude = -0.445824000000016f;

		String farguesToBx = bordeaux.distanceToAsString(fargues);

		assertEquals("9,59 kms", farguesToBx);
	}
}
