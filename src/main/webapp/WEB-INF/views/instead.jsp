<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>대리 결재 내용</title>
   <style>
        /* 수정된 CSS 스타일 */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            text-align: center;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 5px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        #buttons {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        button {
            margin: 0 10px;
            padding: 10px;
            cursor: pointer;
            background-color: #3498db;
            color: #fff;
            border: none;
            border-radius: 3px;
        }

        select {
            width: 150px;
            padding: 8px;
            margin-right: 10px;
        }
    </style>
</head>
<body>

	<%
    // 세션에서 로그인한 사용자의 정보를 가져옵니다.
    String loggedInUserId = (String) session.getAttribute("loggedInUserId");
    String loggedInUserName = (String) session.getAttribute("loggedInUserName");
    String loggedInUserRank = (String) session.getAttribute("loggedInUserRank");
%>

<div class="container">
    <h1>대리 결재</h1>
    
    
    
<form>
    <!-- 테이블로 대리 결재자 정보 표시 -->
    <table>
        <tr>
            <th>대리 결재자</th>
            <td>
                <select id="delegateSelector" >
                   <option value="chooseinstead">선택 </option>
                    <option value="">불러온 이름</option>
                    <!-- 필요에 따라 대리 결재자 옵션 추가 -->
                </select>
            </td>
        </tr>
        <tr>
            <th>직급</th>
            <td id="rank">직급</td>
        </tr>
        <tr>
            <th>대리자 정보</th>
            <td id="delegateInfo">${loggedInUserName}(${loggedInUserRank})</td>
            <input type="hidden" id="LoginId" value="${loggedInUserId}" />
             <input type="hidden" id="LoginRank" value="${loggedInUserRank}" >
              <input type="hidden" id="LoginName" value="${loggedInUserName}" >
            
        </tr>
    </table>
    </form>

    <!-- 버튼 영역 -->
    <div id="buttons">
        <button onclick="cancel()">취소</button>
        <button onclick="approve()">승인</button>
    </div>
</div>

<!-- 필요한 JavaScript 코드 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(function () {
    // 페이지 로드 시 대리자 목록 초기화
    updateDelegateInfo();

    // 대리자 선택이 변경될 때마다 rank 업데이트
    $('#delegateSelector').change(function() {
        updateRank();
    });
});

function updateDelegateInfo() {
    // 세션에서 가져온 값들
    var loggedInUserId = $('#LoginId').val();
    var loggedInUserName = $('#LoginName').val();
    var loggedInUserRank = $('#LoginRank').val();

    $.ajax({
        url: 'delegateList', // 위에서 작성한 Controller 메소드의 URL
        type: 'GET',
        dataType: 'json',
        data: {
            loggedInUserId: loggedInUserId,
            loggedInUserName: loggedInUserName,
            loggedInUserRank: loggedInUserRank
        },
        success: function (data) {
        	 console.log(data); // 데이터 확인용 로깅
            // 성공적으로 데이터를 받아왔을 때 실행되는 함수
            var delegateSelector = $('#delegateSelector');
            delegateSelector.empty(); // 기존 옵션 제거

            // '선택' 옵션 추가
            delegateSelector.append('<option value="chooseinstead">선택</option>');

            // 대리자 목록을 반복하여 옵션 추가
       for (var i = 0; i < data.length; i++) {
    var delegate = data[i];
    delegateSelector.append('<option value="' + delegate.ID + '">' + delegate.MEM_NAME + '</option>');
}

        },
        error: function () {
            // 실패했을 때 실행되는 함수
            alert('대리자 목록을 가져오는데 실패했습니다.');
        }
    });
}

function updateRank() {
    var selectedDelegateId = $('#delegateSelector').val();

    // 'chooseinstead'를 선택한 경우에는 '직급 없음'으로 표시
    if (selectedDelegateId === 'chooseinstead') {
        $('#rank').text('직급');
    } else {
        // 서버에 대리자 ID를 보내어 정보를 가져오는 예시
        $.ajax({
            url: 'getDelegateRank', // 실제 컨트롤러의 URL에 맞게 수정
            type: 'GET',
            dataType: 'json',
            data: {
                delegateId: selectedDelegateId
            },
            success: function (data) {
                // 성공적으로 데이터를 받아왔을 때 실행되는 함수
                // delegateInfoList를 업데이트하거나 직접 필요한 처리를 수행
                delegateInfoList = data;
                $('#rank').text(data.rank); // 'rank' 키로 접근
            },
            error: function () {
                alert('대리자 정보를 가져오는데 실패했습니다.');
            }
        });
    }
}

// 취소 버튼 클릭 시 동작
function cancel() {
    alert('취소');
    // 팝업 창 닫기
    window.close();
}

// 승인 버튼 클릭 시 동작
function approve() {
	 var selectedDelegateId = $('#delegateSelector').val();

	    if (selectedDelegateId === 'chooseinstead') {
	        alert('대리자를 선택하세요.');
	        return;
	    }

	    // Make an AJAX request to the approveDelegate endpoint
	    $.ajax({
	        url: 'approveDelegate',
	        type: 'GET',
	        dataType: 'text',
	        data: {
	            loggedInUserId: $('#LoginId').val(),
	            delegateId: selectedDelegateId
	        },
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        success: function (response) {
	            alert(response); // Show success or failure message
	            // Perform additional actions if needed
	            // 팝업 창 닫기
	            window.close();
	        },
	        error: function () {
	            alert('승인에 실패하였습니다.');
	        }
	    });
}

</script>


</body>
</html>
