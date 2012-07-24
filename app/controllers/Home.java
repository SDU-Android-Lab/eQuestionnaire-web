package controllers;

import models.Client;
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
			index();

		if (state) {
			Client client = Client.find("byEmail", email).first();
			if (client != null && client.password.equals(password)) {
				// 登录成功
				session.put("cid", client.cid);
				index();
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
	public static void index() {
		render();
	}
}
