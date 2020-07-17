package com.concept.test.data.entity.adapter;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAdapter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		if (attribute != null) {
			return Date.valueOf(attribute);
		}
		else {
			return null;
		}
		
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		if(dbData != null)
			return dbData.toLocalDate();
		else
			return null;
	}
    
}