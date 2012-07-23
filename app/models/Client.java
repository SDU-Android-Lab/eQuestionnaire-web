package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.validation.Required;
import play.db.jpa.GenericModel;

/**
 * 客户
 * 
 * @author Craig Lee
 * 
 */
@Entity
public class Client extends GenericModel {
	@Id
	@GeneratedValue
	public Long cid;

	@Required
	@Column(unique = true)
	public String email;
	public String password;
	public String companyName;// 公司名
	public String province;// 所在省份
	public String city;// 城市
	public String street;// 街道
	public String phone;// 联系电话
	public String field;// 业务领域

	public Client(String email, String password, String companyName,
			String province, String city, String street, String phone,
			String field) {
		this.email = email;
		this.password = password;
		this.companyName = companyName;
		this.province = province;
		this.city = city;
		this.street = street;
		this.phone = phone;
		this.field = field;
	}

	/**
	 * 登录
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public static Client login(String email, String password) {
		return find("byEmailAndPassword", email, password).first();
	}

	/**
	 * 判断email是否被注册
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmailRegistered(String email) {
		int num = find("byEmail", email).fetch().size();
		if (num == 0)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [cid=" + cid + ", email=" + email + ", password="
				+ password + ", companyName=" + companyName + ", province="
				+ province + ", city=" + city + ", street=" + street
				+ ", phone=" + phone + ", field=" + field + "]";
	}

}
