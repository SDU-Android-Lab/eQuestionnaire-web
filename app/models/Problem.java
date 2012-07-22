package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
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
	public Integer type;// 问题类型，选择题还是简答题
	
	@MaxSize(1000)
	public String content;
	public Integer choiceSize;
	public Blob image;
	public Blob audio;

	@ManyToOne
	public Questionnaire questionnaire;

	public Problem(Integer type, String content, Integer choiceSize,
			Blob image, Blob audio, Questionnaire questionnaire) {
		this.type = type;
		this.content = content;
		this.choiceSize = choiceSize;
		this.image = image;
		this.audio = audio;
		this.questionnaire = questionnaire;
	}

}
