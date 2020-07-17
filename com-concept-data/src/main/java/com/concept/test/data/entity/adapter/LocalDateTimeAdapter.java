package com.concept.test.data.entity.adapter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeAdapter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		if (attribute != null) {
			return Timestamp.valueOf(attribute);
		}
		else {
			return null;
		}
		
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
		if(dbData != null)
			return dbData.toLocalDateTime();
		else
			return null;
	}
    
}