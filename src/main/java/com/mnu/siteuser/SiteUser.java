package com.mnu.siteuser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SiteUser {

	/*
	 * @Id @Column(length=50) private String email;
	 * 
	 * private String name; private String passwd;
	 * 
	 * public String getEmail( ) { return email; } public void setEmail(String e) {
	 * email = e; }
	 * 
	 * public String getName( ) { return name; } public void setName(String n) {
	 * name = n; }
	 * 
	 * public String getPasswd( ) { return passwd; } public void setPasswd(String p)
	 * { passwd = p; }
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long userNo;

	@Column(length=50, unique=true, nullable=false)
	private String email;

	@Column(nullable=false, length=20)
	private String name;

	@Column(nullable=false, length=20, name="password")
	private String passwd; // 멤버필드 이름과 다른 password 컬럼에 저장

	public Long getUserNo() { return userNo; }
	
	public void setUserNo(Long n) { userNo = n; }

	public String getEmail() { return email; }
	public void setEmail(String e) { email = e; }

	public String getName() { return name; }
	public void setName(String n) { name = n; }

	public String getPasswd() { return passwd; }
	public void setPasswd(String p) { passwd = p; }
	
}
