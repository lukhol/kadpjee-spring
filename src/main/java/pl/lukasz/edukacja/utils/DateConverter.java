package pl.lukasz.edukacja.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.lukasz.edukacja.service.UserService;

public class DateConverter implements Converter<String, Date> {

	@Autowired
	UserService userService;
	
	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            return null;
        }
	}

}
