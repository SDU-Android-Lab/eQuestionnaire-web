import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {
	@Before
	public void setup() {
		Fixtures.deleteDatabase();
	}

	@Test
	public void createAndRetrieveUser() {
		// Create a new user and save it
		new User("craig", "123123", "craigleehi@qq.com").save();
		// Retrieve the user
		User craig = User.find("byName", "craig").first();
		// Test
		assertNotNull(craig);
		assertEquals("craig", craig.name);
	}

	@Test
	public void tryConnectAsUser() {
		// Create a new user and save it
		new User("bob", "secret", "bob@gmail.com").save();

		// Test
		assertNotNull(User.connect("bob", "bob@gmail.com"));
		assertNull(User.connect("bob", "bo@gmail.com"));
		assertNull(User.connect("bob", "bo2b@gmail.com"));
	}

}
