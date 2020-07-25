package com.ab.dbdesign.contact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "date")
public class Date {

	@Id
	@Column(name = "date_id")
	private int id;

	@Column(name = "date_type")
	private String type;

	@Column(name = "date_entry")
	private String dateEntry;

	@Column(name = "contact_id")
	private int contactId;

	public Date() {
	}

	public Date(String type, String dateEntry, int contactId) {
		this.type = type;
		this.dateEntry = dateEntry;
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

	public String getDateEntry() {
		return dateEntry;
	}

	public void setDateEntry(String dateEntry) {
		this.dateEntry = dateEntry;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	
}
