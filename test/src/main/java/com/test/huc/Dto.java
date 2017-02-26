package com.test.huc;

import org.springframework.web.multipart.MultipartFile;

public class Dto {
	private String id;
	private String name;
	private MultipartFile file;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "Dto [id=" + id + ", name=" + name + ", file=" + file + "]";
	}
	
	
}
