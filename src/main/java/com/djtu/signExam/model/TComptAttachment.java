package com.djtu.signExam.model;
import com.djtu.signExam.model.support.EntityObject;
import com.djtu.signExam.model.support.annotation.Table;
import com.djtu.signExam.model.support.annotation.Column;
import com.djtu.signExam.model.support.annotation.Id;
import java.util.Date;
/**
 * Description:No Description Available
 * <p>from schema <strong>CompetitionHub</strong> in table <strong>t_compt_attachment</strong></p>
 * <p>Do not modify the source code randomly</p>
 * 
 *  @author auto_generate model
 */
 @Table("t_compt_attachment")
public class TComptAttachment extends EntityObject{
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
	 * 	 <li>Field Name  : "sk_t_compt"</li>
	 * 	 <li>Field Type  : "INT"</li>
	 * 	 <li>Field Length: "10"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("sk_t_compt")
	private int skTCompt;
	public int getSkTCompt() {
		return skTCompt;
	}
	public void setSkTCompt(int skTCompt) {
		this.skTCompt = skTCompt;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "name"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "introduction"</li>
	 * 	 <li>Field Type  : "TEXT"</li>
	 * 	 <li>Field Length: "65535"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("introduction")
	private String introduction;
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "createtime"</li>
	 * 	 <li>Field Type  : "DATETIME"</li>
	 * 	 <li>Field Length: "19"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("createtime")
	private Date createtime;
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * Description:0为学生报名，1为赛事
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "type"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("type")
	private boolean type;
	public boolean getType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "sk_t_signup"</li>
	 * 	 <li>Field Type  : "INT"</li>
	 * 	 <li>Field Length: "10"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("sk_t_signup")
	private int skTSignup;
	public int getSkTSignup() {
		return skTSignup;
	}
	public void setSkTSignup(int skTSignup) {
		this.skTSignup = skTSignup;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "is_delete"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("is_delete")
	private boolean isDelete;
	public boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "is_valid"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("is_valid")
	private boolean isValid;
	public boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}
	/**
	 * Description:保存路径
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "savepath"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "2000"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("savepath")
	private String savepath;
	public String getSavepath() {
		return savepath;
	}
	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
}
