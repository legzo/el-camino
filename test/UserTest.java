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
	public void savePickupPoints() {
		User user = new User("Test").save();

		new PickupPoint(user, "Mi casa", Direction.to).save();

		assertEquals(1, User.count());
		assertEquals(1, PickupPoint.count());

		new PickupPoint(user, "L'école de mon fils", Direction.to).save();

		assertEquals(2, user.pickupPoints.size());
	}
}
