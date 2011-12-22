import models.Direction;
import models.PickupPoint;
import models.User;

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
	public void shouldSavePickupPointsUserRelationCorrectly() {
		User user = new User("Test").save();

		new PickupPoint(user, "Mi casa", Direction.to).save();

		assertEquals(1, User.count());
		assertEquals(1, PickupPoint.count());

		PickupPoint pp = new PickupPoint(user, "L'école de mon fils",
				Direction.to);
		pp.save();

		assertEquals(2, user.pickupPoints.size());
	}

	@Test
	public void shouldNotFailToCreatePPWithoutOwner() {
		new PickupPoint("test", Direction.from);
	}

	@Test
	public void shouldCalculateNullDistancesBetweenPPWhenNoCoords() {
		PickupPoint bordeaux = new PickupPoint("Bordeaux", Direction.to);
		PickupPoint fargues = new PickupPoint("Fargues", Direction.to);

		double distanceTo = bordeaux.distanceTo(fargues);

		assertTrue(distanceTo == 0);
	}

	@Test
	public void shouldCalculateDistancesBetweenPP() {
		PickupPoint bordeaux = new PickupPoint("Bordeaux", Direction.to);
		PickupPoint fargues = new PickupPoint("Fargues", Direction.to);
		PickupPoint taff = new PickupPoint("Pessac", Direction.to);

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
		PickupPoint bordeaux = new PickupPoint("Bordeaux", Direction.to);
		PickupPoint fargues = new PickupPoint("Fargues", Direction.to);

		bordeaux.latitude = 44.843899f;
		bordeaux.longitude = -0.5641846999999416f;

		fargues.latitude = 44.824248f;
		fargues.longitude = -0.445824000000016f;

		String farguesToBx = bordeaux.distanceToAsString(fargues);

		assertEquals("9,59 kms", farguesToBx);
	}
}
