package com.tenco.blog.repository.model;

import org.springframework.http.HttpStatus;

import com.tenco.blog.handler.exception.DataDeleveryException;
import com.tenco.blog.utils.Define;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	private int id;
	private String title;
	private String content;
	private String writer;
	private String password;
	
	public void checkPassword(String password) {
		if(!this.password.equals(password)) {
			throw new DataDeleveryException(Define.FAIL_ACCOUNT_PASSWROD, HttpStatus.BAD_REQUEST);
		}
	}
	
}
