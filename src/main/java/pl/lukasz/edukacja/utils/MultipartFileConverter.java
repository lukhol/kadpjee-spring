package pl.lukasz.edukacja.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

import pl.lukasz.edukacja.service.UserService;

public class MultipartFileConverter implements Converter<MultipartFile, byte[]> {

	@Autowired
	UserService userService;
	
	@Override
	public byte[] convert(MultipartFile source) {
		try{
			if(!source.isEmpty()) return source.getBytes();
		}catch(Exception e){
			
		}
		return null;
	}
}
