package model;

import java.util.Date;
import java.util.List;

import models.Advertisement;
import models.Client;
import models.Problem;
import models.Questionnaire;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

/**
 * 问卷调查CRUD
 * 
 * @author Craig Lee
 * 
 */
public class QuestionnaireTest extends UnitTest {
	@Before
	public void setUp() {
		Fixtures.deleteDatabase();
		Fixtures.loadModels("initial-data.yml");
	}

	@Test
	public void createQuestionnaire() {
		Client apple = Client.find("byEmail", "helloapple@apple.com.cn")
				.first();
		Questionnaire newQuestionnaire = new Questionnaire(apple,
				"2013年手机软件满意度调查", new Date(), 2, 30000, 50000d, null);
		newQuestionnaire.save();
		List<Questionnaire> questionnaires = Questionnaire.find("byClient",
				apple).fetch();
		assertEquals(2, questionnaires.size());
		Questionnaire questionnaire = Questionnaire.find("byName",
				newQuestionnaire.name).first();
		assertNotNull(questionnaire);
		assertEquals("2013年手机软件满意度调查", questionnaire.name);
		questionnaire.delete();
		assertEquals(1, Questionnaire.count());
	}

	@Test
	public void addProblemsAndAds() {
		Client apple = Client.find("byEmail", "helloapple@apple.com.cn")
				.first();
		assertEquals(1, Questionnaire.count());
		Questionnaire questionnaire = Questionnaire.find("byClient", apple)
				.first();
		assertNotNull(questionnaire);
		assertEquals(0, Problem.count());
		questionnaire.addProblem(0, "你满意这款手机软件么？ A、满意  B、还行  C、不满意", 3, null,
				null);
		assertEquals(1, Problem.count());
		questionnaire.addAdvertisement("the new software", null);
		assertEquals(1, Advertisement.count());
		
	}
}
