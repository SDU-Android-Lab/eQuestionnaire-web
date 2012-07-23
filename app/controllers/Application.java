package controllers;

import models.Client;
import play.data.validation.Error;
import play.data.validation.Validation.ValidationResult;
import play.mvc.Controller;

public class Application extends Controller {
	/**
	 * 首页
	 */
	public static void index() {
		render();
	}

	/**
	 * 解决方案
	 */
	public static void plan() {
		render();
	}

	/**
	 * 服务模式
	 */
	public static void service() {
		render();
	}

	/**
	 * 客户案例
	 */
	public static void clients() {
		render();
	}

	/**
	 * 关于我们
	 */
	public static void about() {
		render();
	}

	/**
	 * 注册
	 */
	public static void register(Client client) {
		ValidationResult emailResult = validation.email(client.email);
		if (emailResult.ok) {
			if (Client.isEmailRegistered(client.email)) {
				validation.addError("client.email", "该Email已被注册使用");
			}
		} else {
			validation.addError("client.email", "Email格式错误");
		}
		validation.required(client.password).message("密码不能为空");
		validation.required(client.companyName).message("公司名称不能为空");
		ValidationResult phoneResult = validation.required(client.phone);
		if (phoneResult.ok) {
			ValidationResult pr = validation.phone(client.phone);
			if (!pr.ok)
				validation.addError("client.email",
						"联系电话格式错误,应如：+86 (10)69445464");
		} else {
			validation.addError("client.phone", "联系电话不能为空");
		}
		validation.required(client.street).message("街道地址不能为空");
		validation.required(client.field).message("请选择业务领域");

		if ("0".equals(client.province)) {
			validation.addError("client.province", "请选择省份");
		}

		if ("0".equals(client.city)) {
			validation.addError("client.city", "请选择城市");

		}
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			registerInput();
		}
		client.save();
		String success = "成功";
		renderTemplate("Application/registerInput.html", success);
		registerInput();
	}

	/**
	 * 注册信息输入
	 */
	public static void registerInput() {
		render();
	}

	/**
	 * 登录
	 */
	public static void loginInput() {
		render();
	}

}