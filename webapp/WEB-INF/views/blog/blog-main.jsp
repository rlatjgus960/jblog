<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 헤더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<c:if test="${blogVo.logoFile == null}">
					<!-- 기본이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
					</c:if>
					
					<c:if test="${blogVo.logoFile != null}">
					<!-- 사용자업로드 이미지 -->
					<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogVo.logoFile }">
					</c:if>
					
					<div id="nick">${blogVo.userName}(${blogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${cateList }" var="cList">
							<li><a href="${pageContext.request.contextPath}/${blogVo.id}/?cateNo=${cList.cateNo }">${cList.cateName }</a></li>
						</c:forEach>
						
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				
		<%-- 	<div id="postBox" class="clearfix">
						<div id="postTitle" class="text-left"><strong>08.페이징</strong></div>
						<div id="postDate" class="text-left"><strong>2020/07/23</strong></div>
						<div id="postNick">${blogVo.userName}(${blogVo.id})님</div>
				</div>
				<!-- //postBox -->
			
				<div id="post" >
					대통령은 법률이 정하는 바에 의하여 사면·감형 또는 복권을 명할 수 있다. 
					대통령의 임기는 5년으로 하며, 중임할 수 없다. 법관은 탄핵 또는 금고 이상의 
					형의 선고에 의하지 아니하고는 파면되지 아니하며, 징계처분에 의하지 아니하고는 
					정직·감봉 기타 불리한 처분을 받지 아니한다.
				</div>
				<!-- //post -->
				 --%>
				 
				 
				<!-- 글이 없는 경우 -->
				<c:if test="${postList == null }">
					<div id="postBox" class="clearfix">
								<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
								<div id="postDate" class="text-left"><strong></strong></div>
								<div id="postNick"></div>
					</div>
				</c:if>
			    
				<div id="post" >
				</div>
				
				
				
				
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						
						<c:forEach items="${postList }" var="pList">
						<tr>
							<td class="text-left"><a href="">${pList.postTitle}</a></td>
							<td class="text-right">${pList.regDate}</td>
						</tr>
						
						</c:forEach>
						
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>
</html>