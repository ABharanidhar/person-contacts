package com.ab.dbdesign.contact.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ab.dbdesign.contact.entity.Address;
import com.ab.dbdesign.contact.entity.Contact;
import com.ab.dbdesign.contact.entity.Date;
import com.ab.dbdesign.contact.entity.Phone;


public class ContactDTO implements Comparable<ContactDTO>{

	public Contact contact;
	public List<Address> addresses;
	public List<Phone> phones;
	public List<Date> dates;


	public ContactDTO() {
		addresses = new ArrayList<>();
		phones = new ArrayList<>();
		dates = new ArrayList<>();
	}

	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}


	public List<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}


	public List<Phone> getPhones() {
		return phones;
	}


	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}


	public List<Date> getDates() {
		return dates;
	}


	public void setDates(List<Date> dates) {
		this.dates = dates;
	}


	@Override
	public int compareTo(ContactDTO o) {
		return this.getContact().getFirstName().compareTo(o.getContact().getFirstName());
	}
	
	

}
