package com.BikkadIT.CrudOperation.Controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.BikkadIT.CrudOperation.Payloads.FileResponse;
import com.BikkadIT.CrudOperation.serviceI.FileServiceI;

@RestController
@RequestMapping("/file")
public class FileController {

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	private FileServiceI fileServiceI;
	
	@Value("${project.image}")
	private String path;
	
	//upload image
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileupload(@RequestParam("image") MultipartFile image) throws IOException {
		
		logger.info("Request entering for the upload image ");
		String uploadImage1 = this.fileServiceI.uploadImage(path, image);
					
		logger.info("Request completed for the upload image ");
		return new ResponseEntity<FileResponse>(new FileResponse(uploadImage1, "Image uploaded successfully"),HttpStatus.OK);
	
	}
	
	// method to get image
	
	@GetMapping(value="/images/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		
		logger.info("Request entering for the download image ");
		InputStream resource = this.fileServiceI.getResource(path, imageName);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
		logger.info("Request completed for the download image ");
	}
	
}
