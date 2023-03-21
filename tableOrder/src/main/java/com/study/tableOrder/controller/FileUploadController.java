package com.study.tableOrder.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.study.tableOrder.service.BoardSerivce;
import com.study.tableOrder.vo.BoardVo;

@RestController
public class FileUploadController {

	@Autowired
	BoardSerivce boardSerivce;

	private static final String UPLOAD_DIR = "./src/main/resources/static/image";
    private static final long MAX_FILE_SIZE = 10485760; // 10MB 제한
    private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("image/png", "image/jpeg", "image/gif"); // 허용 확장자 목록
    private static final String FILE_NAME_PATTERN = "yyyyMMddHHmmssSSS"; // 파일 이름 패턴

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file
    										 ,@RequestParam("title") String title
    										 ,@RequestParam("content") String content) {
    	System.out.println(title);
    	System.out.println(content);

        try {
			// 파일 크기 체크
			if (file.getSize() > MAX_FILE_SIZE) {
			    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size exceeds the limit");
			}
			// 허용된 확장자 체크
			if (!ALLOWED_FILE_TYPES.contains(file.getContentType())) {
			    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File type not allowed");
			}
			// 파일 이름 중복 방지를 위한 고유한 파일 이름 생성
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FILE_NAME_PATTERN)) + "." + extension;


			// 파일 저장
			String filePath = Paths.get(UPLOAD_DIR, fileName).toString();
			Files.write(Paths.get(filePath), file.getBytes());

			// 테이블오더 상품 등록을 위한 객체 생성
			BoardVo Vo = new BoardVo();
			Vo.setTitle(title);
			Vo.setContent(content);
			Vo.setImage(fileName);

			// 테이블오더 상품 등록
			boardSerivce.insertBoard(Vo);

			// 성공 메시지 반환
			System.out.println("파일 업로드 성공");
			return ResponseEntity.ok().body("File uploaded successfully!");
    	} catch (IOException e) {
        	e.printStackTrace();
            // 에러 메시지 반환
        	System.out.println("파일 업로드 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
}