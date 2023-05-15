<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>

  // 취소하면 이전 페이지로 돌아간다.
  function fnCancel(){
    $('#btnCancel').on('click', function(){
	  history.back();
    })
  }
  
  // 모두 동의
  function fnCheckAll(){
	$('#checkAll').on('click', function(){

	})  
  }
	  
  // 개별 선택
  function fnCheckOne(){
  		$('.checkOne').on('click', function(){
			
		})
  }
  
  // 가입 페이지로 이동하기(frmAgree의 submit)
  function fnFrmAgreeSubmit(){
	  $('#frmAgree').on('submit', function(event){
		  if($('#service').is(':checked') == false || $('#privacy').is(':checked') == false){  /* 서비스가 체크 되어 있느냐 확인하는법 */
		  alert('필수 약관에 동의해야만 가입할 수 있습니다.');
		  event.preventDefault();
		  return;
		 }
	  })
  }
  
  // 함수 호출  
  $(function(){
	fnCancel();
	fnCheckAll();
	fnCheckOne();
	fnFrmAgreeSubmit();
  })
  
</script>
</head>
<body>

  <div>
  
    <h1>약관 동의하기</h1>
    
    <form id="frmAgree" action="${contextPath}/user/join.form"> <!-- 가입화면으로 넘어갈때 파라미터 두개 넘어간다, location, event -->
    
      <div>
        <input type="checkbox" id="checkAll">
        <label for="checkAll">모두 동의</label>
      </div>
      
      <hr>
      
      <div>
        <input type="checkbox" id="service" class="checkOne">
        <label for="service" >이용약관 동의(필수)</label>
        <div>
          <textarea>본 약관은 ...</textarea>
        </div>
      </div>
      
      <div>
        <input type="checkbox" id="privacy" class="checkOne">
        <label for="privacy">개인정보수집 동의(필수)</label>
        <div>
          <textarea>개인정보보호법에 따라 ...</textarea>
        </div>
      </div>
      
      <div>
      <!-- 
      1. 체크한 경우: 파라미터 location이 on값을 가지고 전달된다.
      2. 체크 안 한 경우 : 파라미터 loction이 전달되지 않는다. 
       -->
        <input type="checkbox" id="location" name="location" class="checkOne"> <!-- 체크박스에 value값을 따로 설정 안해주면 value=on 이라는 값을 가진다, 만약 체크가 안되어있으면 그냥 아무값도 안가진다(넘어가질 않는다) 
       																				 off값을 가지는게 아니라 고로 location 파라미터 값은 필수가 아니다 -->
        <label for="location">위치정보수집 동의(선택)</label>
        <div>
          <textarea>위치정보 ...</textarea>
        </div>
      </div>
      
      <div>
      <!-- 
      1. 체크한 경우: 파라미터 event이 on값을 가지고 전달된다.
      2. 체크 안 한 경우 : 파라미터 event이 전달되지 않는다. 
      -->
        <input type="checkbox" id="event" name="event" class="checkOne">
        <label for="event">이벤트 동의(선택)</label>
        <div>
          <textarea>이벤트 ...</textarea>
        </div>
      </div>
      
      <hr>
      
      <div>
        <input type="button" value="취소" id="btnCancel">
        <button>다음</button>
      </div>
    
    </form>
    
  </div>
  
</body>
</html>