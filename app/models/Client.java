package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.validation.Unique;
import play.db.jpa.GenericModel;

/**
 * 客户
 * @author Craig Lee
 *
 */
@Entity
public class Client extends GenericModel {
	@Id
    @GeneratedValue
	public Long cid;
	@Column(unique=true)
	public String cemail;
	public String cpassword;
	public String cname;//公司名
	public String cprovince;//所在省份
	public String ccity;//城市
	public String cstreet;//街道
	public String cphone;//联系电话
	public String cfield;//业务领域
	
	public Client(String cemail, String cpassword, String cname,
			String cprovince, String ccity, String cstreet, String cphone,
			String cfield) {
		this.cemail = cemail;
		this.cpassword = cpassword;
		this.cname = cname;
		this.cprovince = cprovince;
		this.ccity = ccity;
		this.cstreet = cstreet;
		this.cphone = cphone;
		this.cfield = cfield;
	}
	
	/**
	 * 登录
	 * @param cemail
	 * @param cpassword
	 * @return
	 */
	public static Client login(String cemail,String cpassword){
		return find("byCemailAndCpassword", cemail,cpassword).first();
	}
	
	
}
