package com.ab.dbdesign.contact.dao;

import java.util.List;

import com.ab.dbdesign.contact.entity.Address;
import com.ab.dbdesign.contact.entity.Contact;
import com.ab.dbdesign.contact.entity.Date;
import com.ab.dbdesign.contact.entity.Phone;



public interface ContactDAO {

	public List<Contact> getAllContactDetails();

	public List<Address> getAllAddressDetails();

	public List<Phone> getAllPhoneDetails();

	public List<Date> getAllDateDetails();

	public int addContactDetails(Contact contact);

	public void addAddressDetails(int contactId, List<Address> addresses);

	public void addPhoneDetails(int contactId, List<Phone> phones);

	public void addDateDetails(int contactId, List<Date> dates);

	public void deleteContactDetails(int contactId);

	public void updateContactDetails(Contact contact);

	public void updateAddressDetails(int id, List<Address> addresses);

	public void updatePhoneDetails(int id, List<Phone> phones);

	public void updateDateDetails(int id, List<Date> dates);

	void deleteAddressDetails(int id);

	void deletePhoneDetails(int id);

	void deleteDateDetails(int id);

	public List<Contact> searchContactDetailsAndGetIds(String data);

	public List<Contact> getContactDetailsBasedOnIds(List<Integer> contactIds);

	public List<Address> getAllAddressDetailsBasedOnIds(List<Integer> contactIds);

	public List<Phone> getAllPhoneDetailsBasedOnIds(List<Integer> contactIds);

	public List<Date> getAllDateDetailsBasedOnIds(List<Integer> contactIds);
	
}
