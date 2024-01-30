<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>Login Page</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            text-align: center;
            border: 1px solid #ccc;
            padding: 20px;
            width: 300px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        input {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="checkId" method="post" onsubmit="return validateForm()">
            <div>
                <label for="id">아이디:</label>
                <input type="text" id="id" name="id" required>
            </div>
            <div>
                <label for="password">비밀번호:</label>
                <input type="password" id="pwd" name="pwd" required>
            </div>
            <div>
                <input type="hidden" id="error" value="<%= request.getAttribute("error") %>">
                <button type="submit">로그인</button>
            </div>
        </form>
    </div>

	<!-- jQuery를 로드하는 스크립트 추가 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function() {
        var error = '<%= request.getParameter("error") %>';
        if (error !== null && error !== "null" && error !== "") {
            alert(error);
            return false;
        }
    });
</script>



</body>
</html>
