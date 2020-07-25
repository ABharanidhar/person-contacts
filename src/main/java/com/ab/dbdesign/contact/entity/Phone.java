package com.ab.dbdesign.contact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {

	@Id
	@Column(name = "phone_id")
	private int id;

	@Column(name = "phone_type")
	private String type;

	@Column(name = "area_code")
	private String areaCode;

	@Column(name = "phone_number")
	private String number;

	@Column(name = "contact_id")
	private int contactId;

	public Phone() {
	}

	public Phone(String type, String areaCode, String number, int contactId) {
		this.type = type;
		this.areaCode = areaCode;
		this.number = number;
		this.contactId = contactId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

}
