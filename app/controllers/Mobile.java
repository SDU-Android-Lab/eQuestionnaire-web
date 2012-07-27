package controllers;

import java.io.InputStream;

import models.Problem;
import play.mvc.Controller;

/**
 * 处理移动终端的请求
 * 
 * @author Craig Lee
 * 
 */
public class Mobile extends Controller {

	/**
	 * 获取图片
	 * 
	 * @param pid
	 */
	public static void getImage(long pid) {
		Problem problem = Problem.findById(pid);
		InputStream pic = problem.image.get();
		if (pic != null) {
			renderBinary(pic);
		} else {
			String error = "no such file";
			renderText(error);
		}

	}

	/**
	 * 获取声音
	 * 
	 * @param pid
	 */
	public static void getAudio(long pid) {
		Problem problem = Problem.findById(pid);
		InputStream audio = problem.audio.get();
		if (audio != null) {
			renderBinary(audio);
		} else {
			String error = "no such file";
			renderText(error);
		}

	}

}
