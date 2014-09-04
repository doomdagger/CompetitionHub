package com.djtu.signExam.model;
import com.djtu.signExam.model.support.EntityObject;
import com.djtu.signExam.model.support.annotation.Table;
import com.djtu.signExam.model.support.annotation.Column;
import com.djtu.signExam.model.support.annotation.Id;
/**
 * Description:No Description Available
 * <p>from schema <strong>CompetitionHub</strong> in table <strong>t_signin</strong></p>
 * <p>Do not modify the source code randomly</p>
 * 
 *  @author auto_generate model
 */
 @Table("t_signin")
public class TSignin extends EntityObject{
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
	 * Description:外键：赛事表ID
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
	 * 	 <li>Field Name  : "sk_t_student"</li>
	 * 	 <li>Field Type  : "INT"</li>
	 * 	 <li>Field Length: "10"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("sk_t_student")
	private int skTStudent;
	public int getSkTStudent() {
		return skTStudent;
	}
	public void setSkTStudent(int skTStudent) {
		this.skTStudent = skTStudent;
	}
	/**
	 * Description:赛事名称
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "comptName"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "1000"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("comptName")
	private String comptName;
	public String getComptName() {
		return comptName;
	}
	public void setComptName(String comptName) {
		this.comptName = comptName;
	}
	/**
	 * Description:小组编号，用于区分每个小组
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "teamNo"</li>
	 * 	 <li>Field Type  : "INT"</li>
	 * 	 <li>Field Length: "10"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("teamNo")
	private int teamNo;
	public int getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
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
	 * 	 <li>Field Name  : "number"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("number")
	private String number;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "email"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("email")
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "cellphone"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("cellphone")
	private String cellphone;
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "creditCard"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "500"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("creditCard")
	private String creditCard;
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "worksName"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "200"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("worksName")
	private String worksName;
	public String getWorksName() {
		return worksName;
	}
	public void setWorksName(String worksName) {
		this.worksName = worksName;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "worksIntro"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "1000"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("worksIntro")
	private String worksIntro;
	public String getWorksIntro() {
		return worksIntro;
	}
	public void setWorksIntro(String worksIntro) {
		this.worksIntro = worksIntro;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "advisor"</li>
	 * 	 <li>Field Type  : "VARCHAR"</li>
	 * 	 <li>Field Length: "1000"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("advisor")
	private String advisor;
	public String getAdvisor() {
		return advisor;
	}
	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}
	/**
	 * Description:是否已经通过审核
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "isValid"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("isValid")
	private boolean isValid;
	public boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}
	/**
	 * Description:
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "isDelete"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("isDelete")
	private boolean isDelete;
	public boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * Description:是否为组长
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "isLeader"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("isLeader")
	private boolean isLeader;
	public boolean getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}
	/**
	 * Description:是否获奖
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "isReward"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("isReward")
	private boolean isReward;
	public boolean getIsReward() {
		return isReward;
	}
	public void setIsReward(boolean isReward) {
		this.isReward = isReward;
	}
	/**
	 * Description:对消除学分有帮助
	 * <p>
	 * Mapped field info in table:
	 * <ul>
	 * 	 <li>Field Name  : "isHelpCredit"</li>
	 * 	 <li>Field Type  : "BIT"</li>
	 * 	 <li>Field Length: "0"</li>
	 *   <li>Refer Info  : ""</li>
	 * </ul>
	 * </p>
	 */
	@Column("isHelpCredit")
	private boolean isHelpCredit;
	public boolean getIsHelpCredit() {
		return isHelpCredit;
	}
	public void setIsHelpCredit(boolean isHelpCredit) {
		this.isHelpCredit = isHelpCredit;
	}
}
