package com.BikkadIT.CrudOperation.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.BikkadIT.CrudOperation.Controller.CategoryController;
import com.BikkadIT.CrudOperation.serviceI.FileServiceI;

@Service
public class FileServiceImpl implements FileServiceI{

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		// File name
		logger.info("Initiating Dao call for upload image");
		
		String name=file.getOriginalFilename();
		
		//Full path
		String filePath=path+File.separator+name;
		
		// create folder if not created
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//file copy
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		 logger.info("Complete Dao call for upload image");
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		logger.info("Initiating Dao call for download image");
		String fullPath=path+File.separator+fileName;
		InputStream ins=new FileInputStream(fullPath);
		 logger.info("Complete Dao call for download image");
		return ins;
	}

}
