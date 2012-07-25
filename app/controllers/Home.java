package controllers;

import java.util.Date;
import java.util.List;

import models.Client;
import models.Questionnaire;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.Before;
import play.mvc.Controller;

/**
 * 个人主页
 * 
 * @author Craig Lee
 * 
 */
public class Home extends Controller {
	/**
	 * 未开始
	 */
	public static final int Q_STATUS_UNSTARTED = 0;
	/**
	 * 进行中
	 */
	public static final int Q_STATUS_UNDERWAY = 1;
	/**
	 * 进行到一半被终止
	 */
	public static final int Q_STATUS_ABORT = 2;
	/**
	 * 停止
	 */
	public static final int Q_STATUS_STOPED = 4;

	/**
	 * 每页大小
	 */
	public static final int PAGER = 10;

	/**
	 * 判断登录
	 */
	@Before(unless = "login")
	public static void checkAuthentification() {
		if (session.get("cid") == null)
			login(false, null, null);
	}

	/**
	 * 登录
	 */
	public static void login(boolean state, String email, String password) {
		if (session.get("cid") != null)
			index(0);

		if (state) {
			Client client = Client.find("byEmail", email).first();
			if (client != null && client.password.equals(password)) {
				// 登录成功
				session.put("cid", client.cid);
				index(0);
			} else {
				// 登录失败
				if (client == null) {
					validation.addError("email", "该Email账户不存在");
				} else {
					validation.addError("password", "密码错误");
				}
				params.flash();
				flash.put("unsuccess", true);
				render();
			}
		} else {
			render();
		}
	}

	/**
	 * 个人主页首页
	 */
	public static void index(int from) {
		Client client = Client.findById(Long.parseLong(session.get("cid")));
		long max = Questionnaire.count("byClient", client);
		List<Questionnaire> questionnaires = Questionnaire
				.find("select q from Questionnaire q where q.client = ? order by q.createDate desc",
						client).from(from).fetch(PAGER);
		render(questionnaires, from,max);
	}

	/**
	 * 登出
	 */
	public static void logout() {
		session.clear();
		Application.index();
	}

	/**
	 * 创建调查
	 */
	public static void newQuestionnaire(boolean state,
			Questionnaire questionnaire) {
		if (state) {
			Client client = Client.findById(Long.parseLong(session.get("cid")));
			questionnaire.client = client;
			questionnaire.createDate = new Date();
			questionnaire.status = Q_STATUS_UNSTARTED;
			questionnaire.save();
			index(0);
		} else {
			render();
		}
	}

	public static void viewQuestionnaire(long qid){
		Questionnaire questionnaire = Questionnaire.findById(qid);
		render(questionnaire);
	}
	
	/**
	 * 公司资料
	 */
	public static void profile() {
		render();
	}
	
	

}
