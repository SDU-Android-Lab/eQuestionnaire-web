package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	public String acontent;
	public Blob aimage;

	@ManyToOne
	public Questionnaire pquestionnaire;

	public Advertisement(String acontent, Blob aimage,
			Questionnaire pquestionnaire) {
		this.acontent = acontent;
		this.aimage = aimage;
		this.pquestionnaire = pquestionnaire;
	}

}
