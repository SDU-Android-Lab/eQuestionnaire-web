package model;

import models.Client;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

/**
 * 客户CRUD测试
 * 
 * @author Craig Lee
 * 
 */
public class ClientTest extends UnitTest {
	@Before
	public void setUp() {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("initial-data.yml");
	}

	// 保存与获取
	@Test
	public void createAndRetrieveClient() {

		new Client("aiban@diqu.com", "123456", "爱班网", "山东", "济南", "舜华路1500号",
				"0531-88712345", "IT").save();

		Client aiban = Client.find("byCemail", "aiban@diqu.com").first();

		assertNotNull(aiban);
		assertEquals("aiban@diqu.com", aiban.cemail);
	}

	@Test
	public void loginAsClient() {
		assertNotNull(Client.login("helloapple@apple.com.cn", "apple123"));
		assertNull(Client.login("helloapple@apple.com.cn", "apple133"));
		assertNull(Client.login("helloapple@apple.com", "apple123"));
	}
}
