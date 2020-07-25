package com.ab.dbdesign.contact.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ab.dbdesign.contact.entity.Address;
import com.ab.dbdesign.contact.entity.Contact;
import com.ab.dbdesign.contact.entity.Date;
import com.ab.dbdesign.contact.entity.Phone;



@Repository
public class ContactDAOImpl implements ContactDAO {

	private EntityManager entityManager;

	@Autowired
	public ContactDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Contact> getAllContactDetails() {

		Session session = entityManager.unwrap(Session.class);

		Query<Contact> query = session.createNativeQuery("SELECT * FROM CONTACT", Contact.class);

		List<Contact> list = query.getResultList();

		return list;
	}

	@Override
	@Transactional
	public List<Address> getAllAddressDetails() {
		Session session = entityManager.unwrap(Session.class);

		Query<Address> query = session.createNativeQuery("SELECT * FROM ADDRESS", Address.class);

		List<Address> list = query.getResultList();

		return list;
	}

	@Override
	@Transactional
	public List<Phone> getAllPhoneDetails() {
		Session session = entityManager.unwrap(Session.class);

		Query<Phone> query = session.createNativeQuery("SELECT * FROM PHONE", Phone.class);

		List<Phone> list = query.getResultList();

		return list;
	}

	@Override
	@Transactional
	public List<Date> getAllDateDetails() {
		Session session = entityManager.unwrap(Session.class);

		Query<Date> query = session.createNativeQuery("SELECT * FROM DATE", Date.class);

		List<Date> list = query.getResultList();

		return list;
	}
	

	@Override
	public List<Contact> getContactDetailsBasedOnIds(List<Integer> contactIds) {

		Session session = entityManager.unwrap(Session.class);

		Query<Contact> query = session.createNativeQuery("SELECT * FROM CONTACT WHERE contact_id IN(?)", Contact.class);

		query.setParameter(1, contactIds);	
		List<Contact> list = query.getResultList();

		return list;
	}

	@Override
	public List<Address> getAllAddressDetailsBasedOnIds(List<Integer> contactIds) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder q = new StringBuilder("SELECT * FROM ADDRESS WHERE contact_id IN (");
		for(Integer id: contactIds) {
			q.append(id+",");
		}
		q.append("0)");
		Query<Address> query = session.createNativeQuery(q.toString(), Address.class);
		List<Address> list = query.getResultList();

		return list;
	}

	@Override
	public List<Phone> getAllPhoneDetailsBasedOnIds(List<Integer> contactIds) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder q = new StringBuilder("SELECT * FROM PHONE WHERE contact_id IN (");
		for(Integer id: contactIds) {
			q.append(id+",");
		}
		q.append("0)");
		Query<Phone> query = session.createNativeQuery(q.toString(), Phone.class);
		List<Phone> list = query.getResultList();

		return list;
	}

	@Override
	public List<Date> getAllDateDetailsBasedOnIds(List<Integer> contactIds) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder q = new StringBuilder("SELECT * FROM DATE WHERE contact_id IN (");
		for(Integer id: contactIds) {
			q.append(id+",");
		}
		q.append("0)");
		Query<Date> query = session.createNativeQuery(q.toString(), Date.class);
		List<Date> list = query.getResultList();

		return list;
	}

	@Override
	//@Transactional
	public int addContactDetails(Contact contact) {

		Session session = entityManager.unwrap(Session.class);

		Query query = session.createNativeQuery("INSERT INTO CONTACT (fname,mname,lname) VALUES (?,?,?)");

		query.setParameter(1, contact.getFirstName());
		query.setParameter(2, contact.getMiddleName());
		query.setParameter(3, contact.getLastName());
		query.executeUpdate();

		javax.persistence.Query query1 = entityManager.createNativeQuery("SELECT @@IDENTITY");
		return Integer.parseInt(((BigInteger) query1.getSingleResult()).toString());
	}

	@Override
	//@Transactional
	public void addAddressDetails(int contactId, List<Address> addresses) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createNativeQuery(
				"INSERT INTO ADDRESS(address_type, street_address, city, state, zip, contact_id) VALUES (?,?,?,?,?,?)");

		for (Address address : addresses) {
			if(address.getType()==null || address.getType().isEmpty()) {
				continue;
			}
			query.setParameter(1, address.getType());
			query.setParameter(2, address.getStreetAddress());
			query.setParameter(3, address.getCity());
			query.setParameter(4, address.getState());
			query.setParameter(5, address.getZip());
			query.setParameter(6, contactId);
			query.executeUpdate();
		}

	}

	@Override
	//@Transactional
	public void addPhoneDetails(int contactId, List<Phone> phones) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createNativeQuery(
				"INSERT INTO PHONE(phone_type, area_code, phone_number, contact_id) VALUES (?,?,?,?)");

		for (Phone phone : phones) {
			if(phone.getType()==null || phone.getType().isEmpty()) {
				continue;
			}
			query.setParameter(1, phone.getType());
			query.setParameter(2, phone.getAreaCode());
			query.setParameter(3, phone.getNumber());
			query.setParameter(4, contactId);
			query.executeUpdate();
		}

	}

	@Override
	//@Transactional
	public void addDateDetails(int contactId, List<Date> dates) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createNativeQuery("INSERT INTO DATE(date_type, date_entry, contact_id) VALUES (?,?,?)");

		for (Date date : dates) {
			if(date.getType()==null || date.getType().isEmpty()) {
				continue;
			}
			query.setParameter(1, date.getType());
			query.setParameter(2, date.getDateEntry());
			query.setParameter(3, contactId);
			query.executeUpdate();
		}

	}

	@Override
	@Transactional
	public void deleteContactDetails(int contactId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createNativeQuery("DELETE FROM CONTACT WHERE contact_id=?");
		query.setParameter(1, contactId);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void deleteAddressDetails(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createNativeQuery("DELETE FROM ADDRESS WHERE address_id=?");
		query.setParameter(1, id);
		query.executeUpdate();
	}
	
	@Override
	@Transactional
	public void deletePhoneDetails(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createNativeQuery("DELETE FROM PHONE WHERE phone_id=?");
		query.setParameter(1, id);
		query.executeUpdate();
	}
	
	@Override
	@Transactional
	public void deleteDateDetails(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createNativeQuery("DELETE FROM DATE WHERE date_id=?");
		query.setParameter(1, id);
		query.executeUpdate();
	}
	
	
	@Override
	public void updateContactDetails(Contact contact) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createNativeQuery("UPDATE CONTACT SET fname=?,mname=?,lname=? WHERE contact_id=?");

		query.setParameter(1, contact.getFirstName());
		query.setParameter(2, contact.getMiddleName());
		query.setParameter(3, contact.getLastName());
		query.setParameter(4, contact.getId());
		query.executeUpdate();
		
	}

	@Override
	public void updateAddressDetails(int contactId, List<Address> addresses) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createNativeQuery(
				"UPDATE ADDRESS SET address_type=?, street_address=?, city=?, state=?, zip=? WHERE address_id=?");

		for (Address address : addresses) {
			if(address.getType()==null) {
				continue;
			}
			if(address.getId()<1) {
				addAddressDetails(contactId, new ArrayList<Address>(){{add(address);}});
			}else {
				query.setParameter(1, address.getType());
				query.setParameter(2, address.getStreetAddress());
				query.setParameter(3, address.getCity());
				query.setParameter(4, address.getState());
				query.setParameter(5, address.getZip());
				query.setParameter(6, address.getId());
				query.executeUpdate();
			}
		}

	}

	@Override
	public void updatePhoneDetails(int contactId, List<Phone> phones) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createNativeQuery(
				"UPDATE PHONE SET phone_type=?, area_code=?, phone_number=? WHERE phone_id=?");

		for (Phone phone : phones) {
			if(phone.getType()==null) {
				continue;
			}
			if(phone.getId()<1) {
				addPhoneDetails(contactId, new ArrayList<Phone>(){{add(phone);}});
			}else {
				query.setParameter(1, phone.getType());
				query.setParameter(2, phone.getAreaCode());
				query.setParameter(3, phone.getNumber());
				query.setParameter(4, phone.getId());
				query.executeUpdate();
			}
		}

	}

	@Override
	public void updateDateDetails(int contactId, List<Date> dates) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createNativeQuery("UPDATE DATE SET date_type=?, date_entry=? WHERE date_id=?");

		for (Date date : dates) {
			if(date.getType()==null) {
				continue;
			}
			if(date.getId()<1) {
				addDateDetails(contactId, new ArrayList<Date>(){{add(date);}});
			}else {
				query.setParameter(1, date.getType());
				query.setParameter(2, date.getDateEntry());
				query.setParameter(3, date.getId());
				query.executeUpdate();
			}
		}
	}

	@Override
	@Transactional
	public List<Contact> searchContactDetailsAndGetIds(String data) {

		Session session = entityManager.unwrap(Session.class);

		Query<Contact> query = session.createNativeQuery("SELECT * FROM CONTACT WHERE fname LIKE '%"+data+"%' "
				+ "OR mname LIKE '%"+data+"%' OR lname LIKE '%"+data+"%'", Contact.class);
		List<Contact> list = query.getResultList();

		query = session.createNativeQuery("SELECT * FROM CONTACT WHERE contact_id IN "
				+ "(SELECT contact_id FROM PHONE WHERE phone_number LIKE '%"+data+"%'"
						+ " OR phone_type LIKE '%"+data+"%' OR area_code LIKE '%"+data+"%') ", Contact.class);
		list.addAll(query.getResultList());
		
		query = session.createNativeQuery("SELECT * FROM CONTACT WHERE contact_id IN "
				+ "(SELECT contact_id FROM ADDRESS WHERE address_type LIKE '%"+data+"%'"
						+ "OR city LIKE '%"+data+"%' OR state LIKE '%"+data+"%' OR street_address LIKE '%"+data+"%'"
								+ " OR zip LIKE '%"+data+"%') ", Contact.class);
		list.addAll(query.getResultList());
		
		return list;
	}

}
