package com.sh.mybatis.student.model.dto;

import java.time.LocalDateTime;

public class Student {
	private int no;
	private String name;
	private String tel;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int no, String name, String tel, LocalDateTime createAt, LocalDateTime updateAt,
			LocalDateTime deleteAt) {
		super();
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.createdAt = createAt;
		this.updatedAt = updateAt;
		this.deletedAt = deleteAt;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createAt) {
		this.createdAt = createAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updateAt) {
		this.updatedAt = updateAt;
	}
	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(LocalDateTime deleteAt) {
		this.deletedAt = deleteAt;
	}
	@Override
	public String toString() {
		return "Student [no=" + no + ", name=" + name + ", tel=" + tel + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", deletedAt=" + deletedAt + "]";
	}
	
	
	
	
	
}
