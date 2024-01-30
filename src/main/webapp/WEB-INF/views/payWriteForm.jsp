<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>글쓰기 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 600px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            table-layout: fixed;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 15px;
            text-align: center;
            vertical-align: middle;
            width: 33.33%;
        }

        button {
            background-color: #4caf50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
        }

        button:hover {
            background-color: #45a049;
        }

        textarea {
            width: 100%;
            height: 150px;
            padding: 10px;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

    <h1>글쓰기 페이지</h1>

    <form id="submitWriteForm" action="submitWriteForm" method="post">

        <!-- 글 작성 양식 입력란 -->
        <label>번호: <span id="numberLa">${seqNum+1}번</span></label><br>
        <label>작성자: <span id="writerLa">${loggedInUserName}</span></label><br>
        <input type="hidden" id="id" name="id" value="${loggedInUserId}" readonly>
        <br><label>제목: <input type="text" id="subject" name="subject"></label><br>
        <label>내용: <br><textarea id="content" name="content"></textarea></label><br>
		<input type="hidden" id="payOption" name="payOption">
		
        <!-- 버튼들 -->
        <button type="button" id="saveButton">임시저장</button>
        <button type="submit" id="approvalButton">결재</button>

    </form>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        // JavaScript 코드 추가
        var loggedInUserRank = '${loggedInUserRank}';
		
        // '임시저장' 버튼 클릭 시 payOption 변경 및 폼 제출
        document.getElementById('saveButton').addEventListener('click', function() {
            document.getElementById('payOption').value = 'save';

            // 폼을 직접 제출
            document.getElementById('submitWriteForm').submit();
        });
  
    </script>
</body>
</html>
