package com.tenco.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.blog.handler.exception.DataDeleveryException;
import com.tenco.blog.repository.model.Post;
import com.tenco.blog.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	@Autowired
	private final PostService postService;
	
	@GetMapping("/")
	public String base(Model model) {
		int page = 1;
		int size = 5;
		int totalRecords = postService.countPost();
    	int totalPages = (int)Math.ceil((double) totalRecords / size);
    	List<Post> postList = postService.readAllPost(page, size);
    	model.addAttribute("postList", postList);
    	model.addAttribute("totalPages", totalPages);
    	model.addAttribute("curruntPage", page);
    	model.addAttribute("size", size);
		return "index";
	}
	
	
    
    // 기본 페이지 페이징
    @GetMapping("/{page}/{size}")
    public String index(@RequestParam(name ="page", defaultValue = "1") int page,
								@RequestParam(name ="siez", defaultValue = "5") int size, 
								Model model) {
    	int totalRecords = postService.countPost();
    	int totalPages = (int)Math.ceil((double) totalRecords / size);
    	List<Post> postList = postService.readAllPost(page, size);
    	model.addAttribute("postList", postList);
    	model.addAttribute("totalPages", totalPages);
    	model.addAttribute("curruntPage", page);
    	model.addAttribute("size", size);
    	return "index";
    }
    
    
    // 헤더의 글 작성하기 버튼 클릭 ==> 글 작성 페이지 이동
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
    
    // 글 작성 페이지에서 작성 완료 시 => DB저장 후 메인 페이지 이동
    @PostMapping("/board/save")
    public String save(HttpServletRequest httpServletRequest, Model model){
    	Post post = Post.builder()
    				.title(httpServletRequest.getParameter("title"))
    				.content(httpServletRequest.getParameter("content"))
    				.writer(httpServletRequest.getParameter("writer"))
    				.password(httpServletRequest.getParameter("password"))
    				.build();
    	postService.createPost(post);
    	return "redirect:/";
    }
    
    // 메인 페이지에서 수정 버튼 클릭 ==> 수정 폼으로 이동
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable(name="id") int id, Model model) {
    	Post post = postService.readById(id);
    	model.addAttribute("post", post);
        return "board/updateForm";
    }
    
    // 수정 폼에서 수정 완료 버튼 클릭 ==> DB에 수정사항 업데이트 후 메인페이지 이동
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable(name="id") int id, HttpServletRequest httpServletRequest){
    	
    	if(httpServletRequest.getParameter("password").equals(postService.readPasswordById(id))) {
    		Post post = Post.builder()
    				.id(id)
    				.title(httpServletRequest.getParameter("title"))
    				.content(httpServletRequest.getParameter("content"))
    				.password(httpServletRequest.getParameter("password"))
    				.build();
    		postService.updatePost(post);
    		return "redirect:/";
    	}else {
    		throw new DataDeleveryException("비밀번호가 틀렸습니다", HttpStatus.BAD_REQUEST);
    	}
    }
    
    // 메인 페이지에서 삭제 버튼 클릭 ==> 삭제 폼으로 이동
    @GetMapping("/board/{id}/deleteForm")
    public String getMethodName(@PathVariable(name="id") int id, Model model) {
    	model.addAttribute("id", id);
        return "board/deleteForm";
    }
    
    // 삭제 폼에서 삭제 버튼 클릭 시 ==> 삭제 후 메인 페이지 이동
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable(name="id") int id){
    	postService.deletePost(id);
        return "redirect:/";
    }
}
