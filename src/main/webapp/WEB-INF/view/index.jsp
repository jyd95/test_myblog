<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%@ include file='/WEB-INF/view/layout/header.jsp'%>

<div class="container p-5">

	<table class="table table-striped">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>글 제목</th>
				<th>글 내용</th>
				<th>작성자</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="post" items="${postList}">
				<tr>
					<th>${post.id}</th>
					<th>${post.title}</th>
					<th>${post.content}</th>
					<th>${post.writer}</th>
					<th>
					<div class="d-flex">
						<form action="/board/${post.id}/updateForm" method="get">
							<button class="btn btn-warning">수정</button>
						</form>
						<form action="/board/${post.id}/deleteForm">
							<button class="btn btn-danger">삭제</button>
						</form>
					</div>
					</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="d-flex justify-content-center">
			<ul class="pagination">
				<!-- previous Page Link -->
				<li class="page-item <c:if test='${curruntPage == 1}'>disabled</c:if>">
					<a class="page-link" href="/page=1/size=${size}">First Page</a>
				</li>
				<li class="page-item <c:if test='${curruntPage == 1}'>disabled</c:if>">
					<a class="page-link" href="/page=${curruntPage-1}/size=${size}">Previous</a>
				</li>
				<!-- Page Numbers -->
				
				
				<%
				Integer curruntPage = (Integer) request.getAttribute("curruntPage");
			    Integer totalPages = (Integer) request.getAttribute("totalPages");
				int begin = Math.max(1, curruntPage - 2);
				int end = Math.min(totalPages , curruntPage + 2);
				%>
				<c:forEach var="page" begin="${begin}" end="${end}">
    				<li class="page-item <c:if test='${page == curruntPage}'>active</c:if>">
        				<a class="page-link" href="/page=${page}/size=${size}">${page}</a>
    				</li>
				</c:forEach>
				<!-- next Page Link -->
				<li class="page-item <c:if test='${curruntPage == totalPages}'>disabled</c:if>">
					<a class="page-link" href="/page=${curruntPage+1}/size=${size}">Next</a>
				</li>
				<li class="page-item <c:if test='${curruntPage == totalPages}'>disabled</c:if>">
					<a class="page-link" href="/page=${totalPages}/size=${size}">Last Page</a>
				</li>
			</ul>
		</div>

</div>

<%@ include file='/WEB-INF/view/layout/footer.jsp'%>