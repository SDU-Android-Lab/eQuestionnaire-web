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

		new Client("help@diqu.com.cn", "123456", "爱班网", "浙江", "宁波市",
				"鄞州区泰康中路666号", "0574-28900690", "IT").save();

		Client aiban = Client.find("byEmail", "help@diqu.com.cn").first();

		assertNotNull(aiban);
		assertEquals("help@diqu.com.cn", aiban.email);
	}

}
