<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a
					href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a
					href="${pageContext.request.contextPath}/${authUser.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
						<!-- 리스트 영역 -->
					</tbody>
				</table>

				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" name="name" value=""></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" name="desc" value=""></td>
					</tr>
				</table>
				
				<input type="hidden" name="id" value="${authUser.id }">

				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->


		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>


	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	//리스트 출력
	$(document).ready(function() {
	 console.log("화면 로딩 직전");
	
	 	//ajax 요청하기
		$.ajax({
			
			url : "${pageContext.request.contextPath }/${blogVo.id}/admin/cateList",
			type : "post", 
			contentType : "application/json",
			//data : ,

			//dataType : "json",
			success : function(categoryList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(categoryList);

				//화면에 그리기
				for (var i = 0; i < categoryList.length; i++) {
					render(categoryList[i], "down");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	
	 });

	//카테고리 추가
	$("#btnAddCate").on(
			"click",
			function() {

				console.log("추가 버튼 클릭");

				var categoryVo = {
					id : $("[name=id]").val(),
					cateName : $("[name=name]").val(),
					description : $("[name=desc]").val(),
				}

				console.log(categoryVo);

				$.ajax({

					url : "${pageContext.request.contextPath}/${blogVo.id}/admin/addCategory",

					type : "get",
					//contentType : "application/json",
					data : categoryVo,

					dataType : "json",
					success : function(result) {
						/*성공시 처리해야될 코드 작성*/
						console.log(result);
						render(result, "up");

						//입력폼 초기화
						$("[name=name]").val("");
						$("[name=desc]").val("");

					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
				});

			});
	 
	 	//이거 입력 안했을땐 안넘어가게 해주면 좋을듯

	 
	 
	//카테고리 한개 렌더링
	function render(categoryVo, type) {

		var str = "";
		str += '<tr id="t-'+categoryVo.cateNo+'">';
		str += '   <td>'+categoryVo.cateNo+'</td>';
		str += '   <td>'+categoryVo.cateName+'</td>';
		if (categoryVo.postNum != null) {
			str += '   <td>'+categoryVo.postNum+'</td>';
		} else {
			str += '   <td>0</td>';
		}
		str += '   <td>'+categoryVo.description+'</td>';
		str += '   <td class="text-center"><img data-no="'+categoryVo.cateNo+'" class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>';
		str += '   </colgroup>';
		str += '</tr>';
		
		
		if (type === 'down') {
			$("#cateList").append(str);
		} else if (type === 'up') {
			$("#cateList").prepend(str);
		} else {
			console.log("방향을 지정해 주세요");
		}

	};
	
	
	//카테고리 삭제 --> 글작성 후에 포스트 수 고치고 다시 하기...
	$("#cateList").on("click",".btnCateDel", function() {
			console.log("삭제 버튼 클릭");
			
			var cateNo = $(this).data("no");
			
			console.log(cateNo);

			$.ajax({

				url : "${pageContext.request.contextPath}/${blogVo.id}/admin/delCate",

				type : "get",
				//contentType : "application/json",
				data : {cateNo : cateNo},

				dataType : "json",
				success : function(result) {
					/* 성공시 처리해야될 코드 작성 */
					console.log(result);
					
					if(result === 1) {
						
						$("#t-"+cateNo).remove();
						
					}else {
						
						alert("삭제할 수 없습니다.");
					}
					

				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
			
 
		});
	
	
	
</script>


</html>