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
	public Client qauthor;
	public String qname;
	public Date qcreateDate;// 创建日期
	public Date qfinishDate;// 完成调查任务日期

	@OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
	public List<Problem> qproblems;// 所包含的问题

	@OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
	public List<Advertisement> qadvertisements;// 所包含的广告

	public Integer qtemplete;// 使用的模版
	public Integer qsize;// 调查样本数量
	public Double qcost;// 价格
	public String qnote;// 备注

	public Questionnaire(Client qauthor, String qname, Date qfinishDate,
			Integer qtemplete, Integer qsize, Double qcost, String qnote) {
		this.qauthor = qauthor;
		this.qname = qname;
		this.qcreateDate = new Date();
		this.qfinishDate = qfinishDate;
		this.qproblems = new ArrayList<Problem>();
		this.qadvertisements = new ArrayList<Advertisement>();
		this.qtemplete = qtemplete;
		this.qsize = qsize;
		this.qcost = qcost;
		this.qnote = qnote;
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
	public Questionnaire addProblem(Integer ptype, String pcontent,
			Integer pchoiceSize, Blob pimage, Blob paudio) {
		Problem newProblem = new Problem(ptype, pcontent, pchoiceSize, pimage,
				paudio, this);
		this.qproblems.add(newProblem);
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
	public Questionnaire addAdvertisement(String acontent, Blob aimage) {
		Advertisement newAd = new Advertisement(acontent, aimage, this);
		this.qadvertisements.add(newAd);
		this.save();
		return this;
	}
}
