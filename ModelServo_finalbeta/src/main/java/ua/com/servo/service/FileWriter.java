package ua.com.servo.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder{
		MODELSERVO
	}
	
	boolean write(Folder folder, MultipartFile file, int i);
}
