package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Blob;
import play.db.jpa.GenericModel;

/**
 * 调查问卷
 * 
 * @author Craig Lee
 * 
 */
@Entity
public class Questionnaire extends GenericModel {
	@Id
	@GeneratedValue
	public Long qid;

	@ManyToOne
	public Client client;
	public String name;
	public Date createDate;// 创建日期
	public Date finishDate;// 完成调查任务日期

	@OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
	public List<Problem> problems;// 所包含的问题

	@OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
	public List<Advertisement> advertisements;// 所包含的广告

	public Integer template;// 使用的模版
	public Integer sampleSize;// 调查样本数量
	public Double cost;// 价格
	public Integer status;// 状态，分为 未开始、进行中、 结束状态
	public String note;// 备注

	public Questionnaire(Client client, String name, Date finishDate,
			Integer template, Integer sampleSize, Double cost, String note) {
		this.client = client;
		this.name = name;
		this.createDate = new Date();
		this.finishDate = finishDate;
		this.problems = new ArrayList<Problem>();
		this.advertisements = new ArrayList<Advertisement>();
		this.template = template;
		this.sampleSize = sampleSize;
		this.cost = cost;
		this.note = note;
	}

	/**
	 * 添加一个问题
	 * 
	 * @param ptype
	 * @param pcontent
	 * @param pchoiceSize
	 * @param pimage
	 * @param paudio
	 * @return
	 */
	public Questionnaire addProblem(Integer type, String content,
			Integer choiceSize, Blob image, Blob audio) {
		Problem newProblem = new Problem(type, content, choiceSize, image,
				audio, this);
		this.problems.add(newProblem);
		this.save();
		return this;
	}

	/**
	 * 添加一个广告
	 * 
	 * @param acontent
	 * @param aimage
	 * @return
	 */
	public Questionnaire addAdvertisement(String content, Blob image) {
		Advertisement newAd = new Advertisement(content, image, this);
		this.advertisements.add(newAd);
		this.save();
		return this;
	}
}
