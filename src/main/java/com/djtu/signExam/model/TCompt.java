package com.djtu.signExam.model;
import com.djtu.signExam.model.support.EntityObject;
import com.djtu.signExam.model.support.annotation.Table;
import com.djtu.signExam.model.support.annotation.Column;
import com.djtu.signExam.model.support.annotation.Id;
import java.util.Date;
/**
 * Description:No Description Available
 * <p>from schema <strong>CompetitionHub</strong> in table <strong>t_compt</strong></p>
 * <p>Do not modify the source code randomly</p>
 * 
 *  @author auto_generate model
 */
 @Table("t_compt")
public class TCompt extends EntityObject{
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
	 * 	 <li>Field Name  : "title"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("title")
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "comptIntro"</li>
	 * 	 <li>Field Type  : "TEXT"</li>
	 * 	 <li>Field Length: "65535"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("comptIntro")
	private String comptIntro;
	public String getComptIntro() {
		return comptIntro;
	}
	public void setComptIntro(String comptIntro) {
		this.comptIntro = comptIntro;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "content"</li>
	 * 	 <li>Field Type  : "TEXT"</li>
	 * 	 <li>Field Length: "65535"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("content")
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "sponsor"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("sponsor")
	private String sponsor;
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "level"</li>
	 * 	 <li>Field Type  : "INT"</li>
	 * 	 <li>Field Length: "10"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("level")
	private int level;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "address"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "1000"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("address")
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "comptDate"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("comptDate")
	private String comptDate;
	public String getComptDate() {
		return comptDate;
	}
	public void setComptDate(String comptDate) {
		this.comptDate = comptDate;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "deadline_s"</li>
	 * 	 <li>Field Type  : "DATETIME"</li>
	 * 	 <li>Field Length: "19"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("deadline_s")
	private Date deadlineS;
	public Date getDeadlineS() {
		return deadlineS;
	}
	public void setDeadlineS(Date deadlineS) {
		this.deadlineS = deadlineS;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "deadline_e"</li>
	 * 	 <li>Field Type  : "DATETIME"</li>
	 * 	 <li>Field Length: "19"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("deadline_e")
	private Date deadlineE;
	public Date getDeadlineE() {
		return deadlineE;
	}
	public void setDeadlineE(Date deadlineE) {
		this.deadlineE = deadlineE;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "isSupport"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("isSupport")
	private boolean isSupport;
	public boolean getIsSupport() {
		return isSupport;
	}
	public void setIsSupport(boolean isSupport) {
		this.isSupport = isSupport;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "supportIntro"</li>
	 * 	 <li>Field Type  : "TEXT"</li>
	 * 	 <li>Field Length: "65535"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("supportIntro")
	private String supportIntro;
	public String getSupportIntro() {
		return supportIntro;
	}
	public void setSupportIntro(String supportIntro) {
		this.supportIntro = supportIntro;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "sp_intro"</li>
	 * 	 <li>Field Type  : "TEXT"</li>
	 * 	 <li>Field Length: "65535"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("sp_intro")
	private String spIntro;
	public String getSpIntro() {
		return spIntro;
	}
	public void setSpIntro(String spIntro) {
		this.spIntro = spIntro;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "sp_isWorks"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("sp_isWorks")
	private boolean spIsWorks;
	public boolean getSpIsWorks() {
		return spIsWorks;
	}
	public void setSpIsWorks(boolean spIsWorks) {
		this.spIsWorks = spIsWorks;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "sp_worksIntro"</li>
	 * 	 <li>Field Type  : "TEXT"</li>
	 * 	 <li>Field Length: "65535"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("sp_worksIntro")
	private String spWorksIntro;
	public String getSpWorksIntro() {
		return spWorksIntro;
	}
	public void setSpWorksIntro(String spWorksIntro) {
		this.spWorksIntro = spWorksIntro;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "sp_type"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("sp_type")
	private boolean spType;
	public boolean getSpType() {
		return spType;
	}
	public void setSpType(boolean spType) {
		this.spType = spType;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "sp_maxMember"</li>
	 * 	 <li>Field Type  : "INT"</li>
	 * 	 <li>Field Length: "10"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("sp_maxMember")
	private int spMaxMember;
	public int getSpMaxMember() {
		return spMaxMember;
	}
	public void setSpMaxMember(int spMaxMember) {
		this.spMaxMember = spMaxMember;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "status"</li>
	 * 	 <li>Field Type  : "INT"</li>
	 * 	 <li>Field Length: "10"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("status")
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "statusIntro"</li>
	 * 	 <li>Field Type  : "TEXT"</li>
	 * 	 <li>Field Length: "65535"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("statusIntro")
	private String statusIntro;
	public String getStatusIntro() {
		return statusIntro;
	}
	public void setStatusIntro(String statusIntro) {
		this.statusIntro = statusIntro;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "sk_t_userAdmin"</li>
	 * 	 <li>Field Type  : "INT"</li>
	 * 	 <li>Field Length: "10"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("sk_t_userAdmin")
	private int skTUserAdmin;
	public int getSkTUserAdmin() {
		return skTUserAdmin;
	}
	public void setSkTUserAdmin(int skTUserAdmin) {
		this.skTUserAdmin = skTUserAdmin;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "is_vaild"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("is_vaild")
	private boolean isVaild;
	public boolean getIsVaild() {
		return isVaild;
	}
	public void setIsVaild(boolean isVaild) {
		this.isVaild = isVaild;
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
}
