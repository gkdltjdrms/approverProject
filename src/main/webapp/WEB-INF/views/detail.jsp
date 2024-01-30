<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="util.BoardStatusConverter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="EUC-KR">
<title>�Խñ� �� ����</title>
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

table {
	width: 60%;
	border-collapse: collapse;
	margin: 20px auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	padding: 15px;
	text-align: left;
	vertical-align: top;
}

input[type="text"], textarea {
	width: 100%;
	padding: 10px;
	box-sizing: border-box;
	margin-bottom: 10px;
}

.button-container {
	text-align: center;
}

button {
	display: inline-block;
	width: 100px;
	margin: 20px 10px;
	padding: 10px;
	background-color: #4caf50;
	color: white;
	text-align: center;
	text-decoration: none;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

a {
	display: inline-block;
	width: 100px;
	margin: 20px auto;
	padding: 10px;
	background-color: #4caf50;
	color: white;
	text-align: center;
	text-decoration: none;
	border-radius: 5px;
}

a:hover {
	background-color: #45a049;
}

/* üũ�� ������ ���� ��Ÿ�� */
input[type="checkbox"]:checked {
	background-color: #4caf50; /* üũ�� ������ �� ������ ���� */
	border-color: #4caf50; /* üũ�� ������ �� �׵θ� ���� ���� */
}
</style>
</head>
<body>


	<h1>�Խñ� �� ���� </h1>
	<form id="boardForm" action="updateBoard" method="post">
		<input type="hidden" name="seq" value="${board.seq}"> <input
			type="hidden" name="id" value="${board.id}"> <input
			type="hidden" name="payOption" value="${board.payOption}"> <input
			type="hidden" id="loginId" name="loginId" value="${user.id}">
		<table>
			<tr>
				<td>�����û</td>
				<td>����</td>
				<td>����</td>
			</tr>
			<tr>
				<td><input type="checkbox" id="approvalRequest"
					name="approvalRequest" disabled></td>
				<td><input type="checkbox" id="manager" name="manager" disabled></td>
				<td><input type="checkbox" id="director" name="director"
					disabled></td>
			</tr>
		</table>

		<c:if test="${not empty board}">
		<table border="1">
				<tr>
					<th>��ȣ</th>
					<td id="seq-cell">${board.seq}</td>
				</tr>
				<tr>
					<th>�ۼ���</th>
					<td>${board.memName}</td>
				</tr>
				<tr>
					<th>����</th>
					<td><c:choose>
							<c:when
								 test="${board.payOption eq 'save' or (board.payOption eq 'reject' and board.id eq user.id)}">
								<input type="text" id="subject" name="subject"
									value="${board.subject}">
							</c:when>
							<c:otherwise>
								<input type="hidden" id="subject" name="subject"
									value="${board.subject}">
                ${board.subject}
            </c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<th>����</th>
				
					<td><c:choose>
							<c:when
								 test="${board.payOption eq 'save' or (board.payOption eq 'reject' and board.id eq user.id)}">
								<textarea id="content" name="content">${board.content}</textarea>
							</c:when>
							<c:otherwise>
								<input type="hidden" id="content" name="content"
									value="${board.content}">
                ${board.content}
            </c:otherwise>
						</c:choose></td>
				</tr>
			</table>
		</c:if>

		<!-- ��ư�� -->
		<div class="button-container">
			<button type="submit" id="saveButton" name="action" value="save"
        style="${(board.payOption eq 'save') or (board.payOption eq 'reject' and board.id eq user.id) ? 'display: inline-block;' : 'display: none;'}">�ӽ�����</button>
	
	<button type="submit" id="approvalButton" name="action" value="approval"
        style="${(board.payOption eq 'save' or (board.payOption eq 'reject' and board.id eq user.id)) 
                || (board.payOption eq 'wait' && (loggedInUserRank eq '����' || (delegateInfo != null && delegateInfo.delegateApproverRank eq '����')))
                || (board.payOption eq 'checking' && (loggedInUserRank eq '����' || (delegateInfo != null && delegateInfo.delegateApproverRank eq '����')))
                 ? 'display: inline-block;' : 'display: none;'}">����</button>

	<button type="submit" id="rejectButton" name="action" value="reject"
        style="${(board.payOption eq 'save' or (board.payOption eq 'reject' and board.id eq user.id)) 
                || (board.payOption eq 'wait' && loggedInUserRank eq '����') 
                || (board.payOption eq 'checking' && loggedInUserRank eq '����')
                || (board.payOption eq 'wait' && delegateInfo != null && (delegateInfo.delegateApproverRank eq '����' || delegateInfo.delegateApproverRank eq '����'))
                || (board.payOption eq 'checking' && delegateInfo != null && (delegateInfo.delegateApproverRank eq '����' || delegateInfo.delegateApproverRank eq '����'))
                 ? 'display: inline-block;' : 'display: none;'}">�ݷ�</button>
			<a href="/myapp/mainList">������� ���ư���</a>
		</div>

		<!-- Assuming 'historyList' is the list of History objects from the controller -->

		<c:if test="${not empty historyList}">
			<h2>���� �̷�</h2>
			<table border="1">
				<tr>
					<th>��ȣ</th>
					<th>������</th>
					<th>������</th>
					<th>�������</th>
				</tr>
				<c:forEach var="history" items="${historyList}">
					<tr>
						<td>${history.historyNum}</td>
						<td><fmt:formatDate value="${history.check_Date}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>
						 <c:choose>
				        <c:when test="${not empty history.delegatecheckMemName}">
				            ${history.checkMemName}(${history.delegatecheckMemName})
				        </c:when>
				        <c:otherwise>
				           ${history.checkMemName}
				        </c:otherwise>
				    </c:choose>
						</td>
						<td>${BoardStatusConverter.getKoreanStatus(history.payOption)}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>


	</form>


	<!-- JSP ������ �� ��ũ��Ʈ �κ� -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script>
$(document).ready(function() {
	

    const approvalRequestCheckbox = $('#approvalRequest');
    const managerCheckbox = $('#manager');
    const directorCheckbox = $('#director');

    // ����ġ ���� ������ �����ϱ� ���� ������ üũ�ڽ��� �����ϴ��� Ȯ��
    if (approvalRequestCheckbox.length > 0 && managerCheckbox.length > 0 && directorCheckbox.length > 0) {
        switch('${board.payOption}') { // JSP �������� ���� ���� ���
            case 'wait':
                approvalRequestCheckbox.prop('checked', true);
                managerCheckbox.prop('checked', false);
                directorCheckbox.prop('checked', false);
                break;
            case 'checking':
                approvalRequestCheckbox.prop('checked', true);
                managerCheckbox.prop('checked', true);
                directorCheckbox.prop('checked', false);
                break;
            case 'end':
                approvalRequestCheckbox.prop('checked', true);
                managerCheckbox.prop('checked', true);
                directorCheckbox.prop('checked', true);
                break;
            default:
                approvalRequestCheckbox.prop('checked', false);
                managerCheckbox.prop('checked', false);
                directorCheckbox.prop('checked', false);
                break;
        }
    } else {
        console.error('One or more checkboxes not found.');
    }
});



</script>




</body>
</html>
