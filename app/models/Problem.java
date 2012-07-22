package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.jpa.Blob;
import play.db.jpa.GenericModel;

/**
 * 调查问卷中的问题
 * 
 * @author Craig Lee
 * 
 */
@Entity
public class Problem extends GenericModel {
	@Id
	@GeneratedValue
	public Long pid;
	public Integer ptype;// 问题类型，选择题还是简答题
	public String pcontent;
	public Integer pchoiceSize;
	public Blob pimage;
	public Blob paudio;

	@ManyToOne
	public Questionnaire questionnaire;

	public Problem(Integer ptype, String pcontent, Integer pchoiceSize,
			Blob pimage, Blob paudio, Questionnaire questionnaire) {
		this.ptype = ptype;
		this.pcontent = pcontent;
		this.pchoiceSize = pchoiceSize;
		this.pimage = pimage;
		this.paudio = paudio;
		this.questionnaire = questionnaire;
	}

}
