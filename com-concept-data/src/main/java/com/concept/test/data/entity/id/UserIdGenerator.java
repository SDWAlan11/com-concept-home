package com.concept.test.data.entity.id;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.concept.test.data.entity.Employee;
import com.concept.test.data.entity.User;

public class UserIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		if(!(object instanceof User))
		throw new HibernateException("Id is not available");
		
		User user = (User)object;
		StringBuilder llaveGenerada = new StringBuilder();
		llaveGenerada.append(user.getLastName().substring(0, 2).toUpperCase());
		llaveGenerada.append(user.getFirstName().substring(0, 2).toUpperCase());
		llaveGenerada.append(user.getCreatedDatetime().format(DateTimeFormatter.ofPattern("MMdd")));
		return llaveGenerada.toString();
		
	}

}
