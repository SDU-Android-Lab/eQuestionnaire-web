package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
import play.db.jpa.Blob;
import play.db.jpa.GenericModel;

/**
 * 调查问卷中的广告
 * 
 * @author Craig Lee
 * 
 */
@Entity
public class Advertisement extends GenericModel {
	@Id
	@GeneratedValue
	public Long aid;

	@Column(columnDefinition = "TEXT", nullable = false)
	public String content;
	public Blob image;

	@ManyToOne
	public Questionnaire questionnaire;

	public Advertisement(String content, Blob image, Questionnaire questionnaire) {
		this.content = content;
		this.image = image;
		this.questionnaire = questionnaire;
	}

}
