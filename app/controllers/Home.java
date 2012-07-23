package controllers;

import models.Client;
import play.mvc.Controller;

/**
 * 个人主页
 * 
 * @author Craig Lee
 * 
 */
public class Home extends Controller {
	/**
	 * 登录
	 */
	public static void login(boolean isLogin, String email, String password) {
		if (isLogin) {
			Client client = Client.login(email, password);
		} else {
			render();
		}
	}
}
