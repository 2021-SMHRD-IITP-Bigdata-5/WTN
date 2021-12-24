<%@page import="com.message.DTO.memberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 뉴스메인페이지 -->
	<%
			memberDTO dto = (memberDTO)session.getAttribute("dto");
	
	%>
		<%if(dto==null){ %>
		<h1>로그인페이지</h1>
		<%}else{ %>
			<h1><%=dto.getMem_name() %>님 환영합니다</h1>
			 <%} %>
	<nav id="menu">	
		<ul class="links">
				<form action = "LoginCon.do" method="post">
				<% if(dto==null){ %>
				<h5>로그인</h5>
				<input type="text" name="mem_id" placeholder="id를 입력하세요"><br>
				<input type="password" name="mem_pw" placeholder="PW를 입력하세요"><br>
				<input type="submit" value="로그인" class="button fit"><br>
				<%}else{ %>
				<a href="update.jsp">정보수정</a>/<a href="LogoutCon.do">로그아웃</a><%} %>
				<!--<input type="submit" value="로그인" class="button fit">-->
				</form>
		</ul>
		<ul class="actions vertical">
			<h5>회원가입</h5>
				<form action="JoinCon.do" method="post">
					<input id="JoinMember_id" type="text" name="mem_id" placeholder="id를 입력하세요">
					<button type="button" id="check" onclick="idCheck()">중복체크</button><p id="result"></p><br>
					<input type="password" id="pw" onchange="check_pw()" name="mem_pw" placeholder="비밀번호를 입력하세요"><br>
					<input type="password" id="pw2" onchange="check_pw()" placeholder="비밀번호를 한번 더 입력하세요"><p id="pwcheck"></p><br>
					<input type="text" name="mem_name" placeholder="이름을 입력하세요"><br>
					<input type="text" name="mem_tel" placeholder="전화번호를 입력하세요"><br>
					<input type="submit" value="회원가입" onchange="success()" class="button fit">
				</form>
		</ul>
	</nav>
<%-- 	<% if(dto!=null){ %>
		<a href="update.jsp">정보수정</a>
		<a href="LogoutCon.do">로그아웃</a>
									
	<%} %> --%>
			<script src="js/jquery-3.6.0.min.js"></script>
			<script type="text/javascript">
					function idCheck(){
	               $.ajax({
	                  url:"check.do",
	                  
	                  type : "get",
	                  data : {
	                     "mem_id":$('#JoinMember_id').val()
	                     
	                  },
	                  success : function(res){
	                     console.log(res)
	                     if(res == 'true'){
	                        $('#result').html("중복된아이디입니다").css('color','red');
	                     }else{
	                        $('#result').html("사용가능").css('color','green');
	                     }
	                     
	                  },
	                     error:function(){
	                         alert("요청 실패!");
	               }
	                  });
	            }
			</script>
			 <script>
	       function check_pw(){
            var pw = document.getElementById('pw').value;
            if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
                if(document.getElementById('pw').value==document.getElementById('pw2').value){
                    document.getElementById('pwcheck').innerHTML='비밀번호가 일치합니다.'
                    document.getElementById('pwcheck').style.color='blue';
                }
                else{
                    document.getElementById('pwcheck').innerHTML='비밀번호가 일치하지 않습니다.';
                    document.getElementById('pwcheck').style.color='red';
                }
            }
        } 

    </script>

</body>
</html>