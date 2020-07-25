package com.ab.dbdesign.contact.service;

import java.util.List;

import com.ab.dbdesign.contact.dto.ContactDTO;


public interface ContactService {

	
	public List<ContactDTO> getAllContacts();

	public Boolean addContact(ContactDTO contactDTO);

	public Boolean deleteCompleteContactDetails(int contactId);

	public Boolean editContact(ContactDTO oldContactDTO, ContactDTO newContactDTO);

	public List<ContactDTO> searchContact(String data);
}
