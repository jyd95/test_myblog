package com.tenco.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.blog.handler.exception.DataDeleveryException;
import com.tenco.blog.handler.exception.RedirectException;
import com.tenco.blog.repository.interfaces.PostRepository;
import com.tenco.blog.repository.model.Post;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PostService {
	
	@Autowired
	private final PostRepository postRepository;
	
	
	@Transactional
	public void createPost(Post post) {
		int result = 0;
		try {
			result = postRepository.insert(post);
		} catch (DataDeleveryException e) {
			throw new DataDeleveryException("게시글 작성에 실패하였습니다.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RedirectException("알 수 없는 오류", HttpStatus.SERVICE_UNAVAILABLE);
		}
		if(result != 1) {
			throw new DataDeleveryException("게시글 작성 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public Post readById(int id) {
		Post post = null;
		post = postRepository.findById(id);
		return post;
	}
	
	@Transactional
	public List<Post> readAllPost(int page, int size){
		List<Post> list = new ArrayList<>();
		int limit = size;
		int offset = (page -1) * size;
		list = postRepository.findAllPost(limit, offset);
		return list;
	}
	
	public int countPost() {
		return postRepository.countPost();
	}
	
	@Transactional
	public void updatePost(Post post) {
		postRepository.updatePost(post);
	}
	
	@Transactional
	public void deletePost(int id) {
		postRepository.deleteById(id);
	}
	
	@Transactional
	public String readPasswordById(int id) {
		return postRepository.findPasswordById(id);
	}
	
}
