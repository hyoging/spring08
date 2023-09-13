package com.care.root.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.dto.FileDTO;
import com.care.root.mybatis.FileMapper;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired FileMapper mapper;

	@Override
	public void fileProcess(String id, String name, MultipartFile file) {
		
		FileDTO dto = new FileDTO();
		dto.setId(id);
		dto.setName(name);
		
		if( !file.isEmpty()) { // file.getSize() != 0

			SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmss-");
			// sysFileName = 20230913102910-
			String sysFileName = fo.format(new Date());
			sysFileName += file.getOriginalFilename();
			System.out.println("sysFileName : " + sysFileName);
			File saveFile = new File(IMAGE_REPO + "/" + sysFileName);
			try {
				file.transferTo(saveFile);
				dto.setImgName(sysFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			dto.setImgName("nan");
		}
		
		try {
			mapper.saveData(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<FileDTO> getData() {
		List<FileDTO> list = null;
		try {
			list = mapper.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void delete(String file, String id) {
		try {
			int result = mapper.delete(id);
			if(result == 1) {
				File d = new File(IMAGE_REPO + "/" + file);
				System.out.println("d.exist() => " + d.exists());
				if(d.exists()) {
					d.delete();
					
				}
				d.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void fileUpdateProcess(String id, String name, MultipartFile file) {
		FileDTO dto = new FileDTO();
		dto.setId(id);
		dto.setName(name);
		
		System.out.println("-------------------" +dto.getId());
		
		if( !file.isEmpty()) { // file.getSize() != 0

			SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmss-");
			// sysFileName = 20230913102910-
			String sysFileName = fo.format(new Date());
			sysFileName += file.getOriginalFilename();
			System.out.println("sysFileName : " + sysFileName);
			File saveFile = new File(IMAGE_REPO + "/" + sysFileName);
			try {
				file.transferTo(saveFile);
				dto.setImgName(sysFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			dto.setImgName("nan");
		}
		
		try {
			mapper.modify(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public FileDTO mod(String id) {
		FileDTO dto = mapper.mod(id);
		return dto;
	}

}
