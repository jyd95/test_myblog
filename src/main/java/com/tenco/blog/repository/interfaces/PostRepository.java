package com.tenco.blog.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.blog.repository.model.Post;

@Mapper
public interface PostRepository {
	// 인덱스 페이지 전체 글 조회 / 페이징
	public List<Post> findAllPost(@Param ("limit") int limit, @Param("offset") int offset);
	public int insert(Post post);
	public int updatePost(Post post);
	public int deleteById(Integer id);
	public int countPost();
	public Post findById(int id);
	public String findPasswordById(int id);
}
