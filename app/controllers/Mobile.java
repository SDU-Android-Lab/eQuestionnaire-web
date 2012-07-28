package controllers;

import java.io.InputStream;

import models.Advertisement;
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
	 * 获取问题图片
	 * 
	 * @param pid
	 */
	public static void getProblemImage(long pid) {
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
	 * 获取广告图片
	 * 
	 * @param aid
	 */
	public static void getAdvertisementImage(long aid) {
		Advertisement advertisement = Advertisement.findById(aid);
		InputStream pic = advertisement.image.get();
		if (pic != null) {
			renderBinary(pic);
		} else {
			String error = "no such file";
			renderText(error);
		}
	}

	/**
	 * 获取问题声音
	 * 
	 * @param pid
	 */
	public static void getProblemAudio(long pid) {
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
