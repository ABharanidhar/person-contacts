package com.ab.dbdesign.contact.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.dbdesign.contact.dao.ContactDAO;
import com.ab.dbdesign.contact.dto.ContactDTO;
import com.ab.dbdesign.contact.entity.Address;
import com.ab.dbdesign.contact.entity.Contact;
import com.ab.dbdesign.contact.entity.Date;
import com.ab.dbdesign.contact.entity.Phone;



@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO contactDAO;

	@Override
	public List<ContactDTO> getAllContacts() {

		List<Contact> contacts = contactDAO.getAllContactDetails();
		List<Address> addresses = contactDAO.getAllAddressDetails();
		List<Phone> phones = contactDAO.getAllPhoneDetails();
		List<Date> dates = contactDAO.getAllDateDetails();
		
		
		List<ContactDTO> dtos = new ArrayList<>();
		ContactDTO dto;
		
		Map<Integer, ContactDTO> map = new HashMap<>();

		// insert all contact details into contactDTO
		for (Contact contact : contacts) {
			dto = new ContactDTO();
			dto.setContact(contact);
			map.put(contact.getId(), dto);
		}

		// insert all addresses details into contactDTO
		for (Address address: addresses) {
			dto = map.get(address.getContactId());
			dto.getAddresses().add(address);
		}

		// insert all phones details into contactDTO
		for (Phone phone: phones) {
			dto = map.get(phone.getContactId());
			dto.getPhones().add(phone);
		}

		// insert all contact details into contactDTO
		for (Date date: dates) {
			dto = map.get(date.getContactId());
			dto.getDates().add(date);
		}
		
		for( ContactDTO contactDTO: map.values()) {
			dtos.add(contactDTO);
		}
		
		return dtos;
	}

	
	@Override
	@Transactional
	public Boolean addContact(ContactDTO contactDTO) {
		int contactId = contactDAO.addContactDetails(contactDTO.getContact());
		
		contactDAO.addAddressDetails(contactId, contactDTO.getAddresses());
		contactDAO.addPhoneDetails(contactId, contactDTO.getPhones());
		contactDAO.addDateDetails(contactId, contactDTO.getDates());
		
		return true;
	}
	
	
	@Override
	public Boolean deleteCompleteContactDetails(int contactId) {
		contactDAO.deleteContactDetails(contactId);
		return true;
	}


	@Override
	@Transactional
	public Boolean editContact(ContactDTO oldDTO, ContactDTO newDTO) {
//		//compare new and old results
//		if(!oldDTO.contact.getFirstName().equals(newDTO.getContact().getFirstName()) ||
//				!oldDTO.contact.getMiddleName().equals(newDTO.getContact().getMiddleName()) ||
//				!oldDTO.contact.getLastName().equals(newDTO.getContact().getLastName()) ){
//			//updateContact table based on contact_id
//		}
//		if(oldDTO.getAddresses().size()!=newDTO.getAddresses().size()) {
//			//updateAddress
//		}
//		if(oldDTO.getDates().size()!=newDTO.getDates().size()) {
//			//updateDates
//		}
//		if(oldDTO.getPhones().size()!=newDTO.getPhones().size()) {
//			//updatePhones
//		}
		
		//insert new fields
		//update new fields
		//remove deleted fields
		//END
		contactDAO.updateContactDetails(newDTO.getContact());
		contactDAO.updateAddressDetails(newDTO.getContact().getId(), newDTO.getAddresses());
		contactDAO.updatePhoneDetails(newDTO.getContact().getId(), newDTO.getPhones());
		contactDAO.updateDateDetails(newDTO.getContact().getId(), newDTO.getDates());
		
		Set<Integer> data = new HashSet<>();
		for(Address address: newDTO.getAddresses()) {
			if(address.getId()>0)
				data.add(address.getId());
		}
		
		for(Address address: oldDTO.getAddresses()) {
			if(!data.contains(address.getId())) {
				contactDAO.deleteAddressDetails(address.getId());
			}
		}
		
		data.clear();
		for(Phone phone: newDTO.getPhones()) {
			if(phone.getId()>0)
				data.add(phone.getId());
		}
		for(Phone phone: oldDTO.getPhones()) {
			if(!data.contains(phone.getId())) {
				contactDAO.deletePhoneDetails(phone.getId());
			}
		}
		
		data.clear();
		for(Date date: newDTO.getDates()) {
			if(date.getId()>0)
				data.add(date.getId());
		}
		for(Date date: oldDTO.getDates()) {
			if(!data.contains(date.getId())) {
				contactDAO.deleteDateDetails(date.getId());
			}
		}

		return true;
	}


	@Override
	public List<ContactDTO> searchContact(String data) {

		List<Contact> contacts = contactDAO.searchContactDetailsAndGetIds(data);
		
		List<Integer> contactIds = new ArrayList<>();
		
		for(Contact contact: contacts) {
			contactIds.add(contact.getId());
		}
		
		//List<Contact> contacts = contactDAO.getContactDetailsBasedOnIds(contactIds);
		List<Address> addresses = contactDAO.getAllAddressDetailsBasedOnIds(contactIds);
		List<Phone> phones = contactDAO.getAllPhoneDetailsBasedOnIds(contactIds);
		List<Date> dates = contactDAO.getAllDateDetailsBasedOnIds(contactIds);
		
		
		List<ContactDTO> dtos = new ArrayList<>();
		ContactDTO dto;
		
		Map<Integer, ContactDTO> map = new HashMap<>();

		// insert all contact details into contactDTO
		for (Contact contact : contacts) {
			dto = new ContactDTO();
			dto.setContact(contact);
			map.put(contact.getId(), dto);
		}

		// insert all addresses details into contactDTO
		for (Address address: addresses) {
			dto = map.get(address.getContactId());
			dto.getAddresses().add(address);
		}

		// insert all phones details into contactDTO
		for (Phone phone: phones) {
			dto = map.get(phone.getContactId());
			dto.getPhones().add(phone);
		}

		// insert all contact details into contactDTO
		for (Date date: dates) {
			dto = map.get(date.getContactId());
			dto.getDates().add(date);
		}
		
		for( ContactDTO contactDTO: map.values()) {
			dtos.add(contactDTO);
		}
		
		return dtos;
		
	}

	
	
}
