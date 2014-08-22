package com.djtu.signExam.model;
import com.djtu.signExam.model.support.EntityObject;
import com.djtu.signExam.model.support.annotation.Table;
import com.djtu.signExam.model.support.annotation.Column;
import com.djtu.signExam.model.support.annotation.Id;
/**
 * Description:No Description Available
 * <p>from schema <strong>CompetitionHub</strong> in table <strong>t_user_admin</strong></p>
 * <p>Do not modify the source code randomly</p>
 * 
 *  @author auto_generate model
 */
 @Table("t_user_admin")
public class TUserAdmin extends EntityObject{
	/**
	 *  serialVersionUID, dedicated to object serialize.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "ID"</li>
	 * 	 <li>Field Type  : "INT"</li>
	 * 	 <li>Field Length: "10"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Id
	@Column("ID")
	private int ID;
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "u_username"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("u_username")
	private String uUsername;
	public String getUUsername() {
		return uUsername;
	}
	public void setUUsername(String uUsername) {
		this.uUsername = uUsername;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "u_usertype"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("u_usertype")
	private String uUsertype;
	public String getUUsertype() {
		return uUsertype;
	}
	public void setUUsertype(String uUsertype) {
		this.uUsertype = uUsertype;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "u_image"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("u_image")
	private String uImage;
	public String getUImage() {
		return uImage;
	}
	public void setUImage(String uImage) {
		this.uImage = uImage;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "u_cellphone"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("u_cellphone")
	private String uCellphone;
	public String getUCellphone() {
		return uCellphone;
	}
	public void setUCellphone(String uCellphone) {
		this.uCellphone = uCellphone;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "u_userPwd"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("u_userPwd")
	private String uUserPwd;
	public String getUUserPwd() {
		return uUserPwd;
	}
	public void setUUserPwd(String uUserPwd) {
		this.uUserPwd = uUserPwd;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "a_email"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("a_email")
	private String aEmail;
	public String getAEmail() {
		return aEmail;
	}
	public void setAEmail(String aEmail) {
		this.aEmail = aEmail;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "a_type"</li>
	 * 	 <li>Field Type  : "INT"</li>
	 * 	 <li>Field Length: "10"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("a_type")
	private int aType;
	public int getAType() {
		return aType;
	}
	public void setAType(int aType) {
		this.aType = aType;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "a_isSuper"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("a_isSuper")
	private boolean aIsSuper;
	public boolean getAIsSuper() {
		return aIsSuper;
	}
	public void setAIsSuper(boolean aIsSuper) {
		this.aIsSuper = aIsSuper;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "a_isActive"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("a_isActive")
	private boolean aIsActive;
	public boolean getAIsActive() {
		return aIsActive;
	}
	public void setAIsActive(boolean aIsActive) {
		this.aIsActive = aIsActive;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "a_isDelete"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("a_isDelete")
	private boolean aIsDelete;
	public boolean getAIsDelete() {
		return aIsDelete;
	}
	public void setAIsDelete(boolean aIsDelete) {
		this.aIsDelete = aIsDelete;
	}
}
