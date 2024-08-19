<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='/WEB-INF/view/layout/header.jsp'%>


<div class="container p-5">
    <div class="card">
        <div class="card-header"><b>게시글 삭제</b></div>
        <div class="card-body">
            <form action="/board/${id}/delete" method="POST">
                <div class="mb-3">
                    <input type="password" class="form-control" name="password" 
                    placeholder="게시글 작성시에 입력했던 비밀번호를 입력하세요">
                </div>
                <button type="submit" class="btn btn-primary form-control">삭제하기</button>
            </form>
        </div>
    </div>
</div>

<%@ include file='/WEB-INF/view/layout/footer.jsp'%>
