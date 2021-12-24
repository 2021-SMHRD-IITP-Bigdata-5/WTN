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
<!-- 정보수정페이지 -->
	<%
			memberDTO dto = (memberDTO)session.getAttribute("dto");
	%>
	<script src="js/jquery-3.6.0.min.js"></script>
	<nav id="Update">	
						<ul class="actions vertical">
						<form action="UpdateCon.do" method="post">
							<h5>회원정보수정</h5>
								<%=dto.getMem_name()%>님의 정보<br>
								<input type="password" id="pw" onchange="check_pw()" name="mem_pw" placeholder="비밀번호를 입력하세요"><br>
								<input type="password" id="pw2" onchange="check_pw()" placeholder="비밀번호를 한번 더 입력하세요"><br>
								<input name="mem_name" type="text" placeholder="이름을 입력하세요" ><br>
								<input name="mem_tel" type="text" placeholder="전화번호를 임력하세요"><br>
								<p id="check"></p><br>
								<!-- <input type="submit"  value="정보수정" class="button fit"> -->
						</form>
						</ul>
					</nav>
	<script type="text/javascript">
        function check_pw(){
        	var pw = document.getElementById('pw').value;
            if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
                if(document.getElementById('pw').value==document.getElementById('pw2').value){
                    document.getElementById('check').innerHTML='<input type="submit"  value="정보수정" class="button fit">';
                    document.getElementById('check').style.color='blue';
                    
                }
                else{
                    document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                    document.getElementById('check').style.color='red';
                }
            }
        }
	</script>		
</body>
</html>