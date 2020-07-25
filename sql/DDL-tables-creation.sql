CREATE TABLE `Contact-Info`.CONTACT(
		contact_id int primary key auto_increment,
        fname varchar(40) NOT NULL,
        mname varchar(40),
        lname varchar(40) NOT NULL
);

CREATE TABLE `Contact-Info`.ADDRESS(
		address_id int primary key auto_increment,
        address_type varchar(20) NOT NULL,
        street_address varchar(100) NOT NULL,
        city varchar(40) NOT NULL,
        state varchar(40) NOT NULL,
        zip varchar(12) NOT NULL,
        contact_id int,
		foreign key(contact_id) references CONTACT(contact_id) ON DELETE CASCADE
);

CREATE TABLE `Contact-Info`.PHONE(
		phone_id int primary key auto_increment,
		phone_type varchar(20) NOT NULL,
        area_code varchar(10) NOT NULL,
        phone_number varchar(20) NOT NULL,
		contact_id int,
		foreign key(contact_id) references CONTACT(contact_id) ON DELETE CASCADE
);

CREATE TABLE `Contact-Info`.DATE(
		date_id int primary key auto_increment,
		date_type varchar(40) NOT NULL,
        date_entry date NOT NULL,
        contact_id int,
		foreign key(contact_id) references CONTACT(contact_id) ON DELETE CASCADE
);



