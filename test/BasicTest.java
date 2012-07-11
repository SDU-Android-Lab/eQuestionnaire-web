import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {
	@Before
	public void setup() {
		Fixtures.deleteDatabase();
	}


}
