<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='/WEB-INF/view/layout/header.jsp'%>


<div class="container p-5">
    <div class="card">
        <div class="card-header"><b>게시글 작성</b></div>
        <div class="card-body">
            <form action="/board/save" method="POST">
                <div class="mb-3">
                    <input type="text" class="form-control" name="writer" value="ㅇㅇ">
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" name="password" value="123123">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" placeholder="제목을 입력해 주세요." name="title">
                </div>
                <div class="mb-3">
                    <textarea class="form-control" rows="5" name="content"></textarea>
                </div>
                <button type="submit" class="btn btn-primary form-control">작성 완료</button>
            </form>
        </div>
    </div>
</div>

<%@ include file='/WEB-INF/view/layout/footer.jsp'%>
