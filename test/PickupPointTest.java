import models.Instant;

import org.junit.Test;

import play.test.UnitTest;

public class PickupPointTest extends UnitTest {

	@Test
	public void shouldConsiderInstantsEqualWhenTheyAre() {
		Instant i1 = new Instant(12, 30);
		Instant i2 = new Instant(12, 30);
		assertEquals(i1, i2);
	}
}
