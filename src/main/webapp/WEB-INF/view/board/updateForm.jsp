<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='/WEB-INF/view/layout/header.jsp'%>
<div class="container p-5">
    <div class="card">
        <div class="card-header"><b>게시글 수정</b></div>
        <div class="card-body">
            <form action="/board/${id}/update" method="POST">
                <div class="mb-3">
                    <input type="text" class="form-control" name="title" value="${post.title}">
                </div>
                <div class="mb-3">
                    <textarea class="form-control" rows="5" name="content" >${post.content}</textarea>
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" name="password" placeholder="게시글 패스워드를 입력하세요">
                </div>
                <button type="submit" class="btn btn-primary form-control">수정 완료</button>
            </form>
        </div>
    </div>
</div>

<%@ include file='/WEB-INF/view/layout/footer.jsp'%>