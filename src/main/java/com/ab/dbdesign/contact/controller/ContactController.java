package com.ab.dbdesign.contact.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ab.dbdesign.contact.dto.ContactDTO;
import com.ab.dbdesign.contact.service.ContactService;

@Controller
@RequestMapping("/contact")
public class ContactController {

	
	private List<ContactDTO> globalContactDTO;
	
	private ContactService contactService;
	
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@GetMapping("/list")
	public String listContacts(Model model) {
		
		List<ContactDTO> contacts = contactService.getAllContacts();
		model.addAttribute("contacts", contacts);
		Collections.sort(contacts);
		globalContactDTO = contacts;
		
		return "list-contacts";
	}
	
	@GetMapping("/displayAddForm")
	public String displayAddContact(Model model) {
		
		ContactDTO contactData = new ContactDTO();
		model.addAttribute("contactData", contactData);
		
		return "add-contacts";
	}
	
	@PostMapping("/addContact")
	public String addContact(@ModelAttribute("contactData") ContactDTO contactDTO) {
		
		contactService.addContact(contactDTO);
		
		return "redirect:/contact/list";
	}
	
	
	@DeleteMapping("/deleteContact/{id}")
	public String deleteContact(@PathVariable int id) {
		
		contactService.deleteCompleteContactDetails(id);
		
		return "redirect:/contact/list";
	}
	
	@GetMapping("/displayEditForm/{id}")
	public String displayEditContact(@PathVariable int id, Model model) {
		
		if(globalContactDTO==null) {
			globalContactDTO = contactService.getAllContacts();
		}
		
		for(ContactDTO dto: globalContactDTO) {
			if(dto.getContact().getId()==id) {
				model.addAttribute("contactData", dto);
				break;
			}
		}
		return "edit-contact";
	}
	
	@PostMapping("/editContact")
	public String editContact(@ModelAttribute("contactData") ContactDTO contactDTO) {
		for(ContactDTO dto: globalContactDTO) {
			if(dto.getContact().getId()==contactDTO.getContact().getId()) {
				contactService.editContact(dto, contactDTO);				
				break;
			}
		}
		return "redirect:/contact/list";
	}
	
	@GetMapping("/searchContact")
	public String searchContact(@RequestParam("data") String data, Model model) {
		
		List<ContactDTO> contacts = contactService.searchContact(data);
		model.addAttribute("contacts", contacts);
		return "search-contacts";
	}
	
	
	@GetMapping("/index")
    public String greeting(Model model) {
        String[] dataa = {"TATA", "CTS", "MTS"};
        model.addAttribute("message", "Hello world!");
        model.addAttribute("datta", dataa);
        return "index";
    }
	
}









