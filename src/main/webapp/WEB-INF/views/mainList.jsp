<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="util.BoardStatusConverter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>

<style>
/* CSS for mouseover effect on table rows */
tbody tr:hover {
	background-color: #f5f5f5;
	/* Change this to your desired hover color */
}

/* CSS for board table */
table {
	border-collapse: collapse;
	width: 80%; /* Adjust the width as needed */
	margin: 20px 0; /* Add some margin for better visual appearance */
}

th, td {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

th {
	background-color: #f2f2f2;
}

</style>



<head>

<meta charset="EUC-KR">
<title>Login Success</title>
</head>
<body>
	<%-- Check if session attributes are present --%>
	<c:if test="${empty sessionScope.loggedInUserId}">
		<%-- Redirect to payLogin.jsp if session attributes are not present --%>
		<% response.sendRedirect(request.getContextPath() + "/payLogin.jsp"); %>
	</c:if>

	<%
    // ���ǿ��� �α����� ������� ������ �����ɴϴ�.
    String loggedInUserId = (String) session.getAttribute("loggedInUserId");
    String loggedInUserName = (String) session.getAttribute("loggedInUserName");
    String loggedInUserRank = (String) session.getAttribute("loggedInUserRank");
%>
<%
    Date delegateStartTime = (Date) request.getAttribute("delegateStartTime");
Date delegateEndTime = (Date) request.getAttribute("delegateEndTime");
    // Now you can use delegateStartTime in your JSP
%>

<%
    Date now = new Date();
boolean isBetween = delegateStartTime != null && delegateEndTime != null && delegateStartTime.before(now) && delegateEndTime.after(now);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>


	  <div id="head">


<div>
    <h1>
        <% if (isBetween) { %>
            ${loggedInUserName}(${loggedInUserRank}) -- ${delegateUserName}(${delegateUserRank}) �� ȯ���մϴ�
        <% } else { %>
            ${loggedInUserName}(${loggedInUserRank})
        <% } %>
    </h1>
    <h3>
        <% if (isBetween) { %>
            �븮 ���� �ð�: <span>${delegateStartTime}</span>
        <% } %>
    </h3>
    <h3>
        <% if (isBetween) { %>
            �븮 ���� �ð�: <span>${delegateEndTime}</span>
        <% } %>
    </h3>
</div>



        <form action="logout" method="post">
            <button type="submit">�α׾ƿ�</button>
        </form>

		<c:choose>
			<c:when
				test="${loggedInUserRank eq '����' or loggedInUserRank eq '����'}">
				<button>�븮 ����</button>
			</c:when>
			<c:otherwise>
				<%-- Do nothing or display alternative content for other ranks --%>
			</c:otherwise>
		</c:choose>

		<div>
			<form action="payWriteForm" method="get">
				<input type="hidden" name="id" value="${loggedInUserId}"> <input
					type="hidden" name="userName" value="${loggedInUserName}">
				<input type="hidden" name="userRank" value="${loggedInUserRank}">
				<button type="submit">�۾���</button>
			</form>

		</div>
	</div>

	<!-- �˻�â �κ� -->
	<form id="searchForm" action="search" method="get"
		accept-charset="UTF-8">
		<input type="hidden" name="id" value="${loggedInUserId}" readonly>
		<select name="searchType" id="searchType">
			<option value="all" ${param.searchType eq 'all' ? 'selected' : ''}>����</option>
			<option value="writer"
				${param.searchType eq 'writer' ? 'selected' : ''}>�ۼ���</option>
			<option value="approver"
				${param.searchType eq 'approver' ? 'selected' : ''}>������</option>
			<option value="titleContent"
				${param.searchType eq 'titleContent' ? 'selected' : ''}>����+����</option>
		</select> 
		<input type="text" name="searchKeyword" placeholder="�˻��� �Է�"
			value="${param.searchKeyword}">

		<!-- �߰�: ������� ��Ӵٿ� �޴� -->
		<select name="approveStatus" id="approveStatus">
			<option value="">�������</option>
			<option value="save">�ӽ�����</option>
			<option value="wait">������</option>
			<option value="checking">������</option>
			<option value="end">����Ϸ�</option>
			<option value="reject">�ݷ�</option>
		</select>

		<!-- �߰�: �����ϰ� ������ �Է� -->
		<br> <label for="startDate">������:</label> <input type="date"
			name="startDate" id="startDate"> <label for="endDate">������:</label>
		<input type="date" id="endDate" name="endDate">

		<button type="submit">�˻�</button>
	</form>


	<!-- ���� ����Ʈ �κ� -->
	<table border="1">
		<thead>
			<tr>
				<th>��ȣ</th>
				<th>�ۼ���</th>
				<th>����</th>
				<th>�ۼ���</th>
				<th>������</th>
				<th>������</th>
				<th>�������</th>
				
			</tr>
		</thead>
		<tbody>
    <!-- ���⿡ �������� ������ ���� ����Ʈ �����͸� �ݺ��ؼ� ����ϴ� �κ��� ���ϴ�. -->
    <c:choose>
        <c:when test="${not empty boardList}">
            <c:forEach var="board" items="${boardList}">
                <tr>
                    <td>${board.seq}</td>
                    <td>${board.memName}</td>
                    <td>${board.subject}</td>
                    <td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td><fmt:formatDate value="${board.checkDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                   <td>
				    <c:choose>
				        <c:when test="${not empty board.delegateName}">
				            ${board.checkBoardName}(${board.delegateName})
				        </c:when>
				        <c:otherwise>
				            ${board.checkBoardName}
				        </c:otherwise>
				    </c:choose>
				</td>
                    <td>${BoardStatusConverter.getKoreanStatus(board.payOption)}</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="7">�ۼ��� ���� �����ϴ�.</td>
            </tr>
        </c:otherwise>
    </c:choose>
</tbody>

	</table>
	<!-- jQuery�� �ε��ϴ� ��ũ��Ʈ �߰� -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script>
    $(document).ready(function() {
    	
    	// �븮 ���� ��ư Ŭ�� �̺�Ʈ ó��
    	$('button:contains("�븮 ����")').on('click', function() {
    	    // �˾� â�� �븮 ���� ������ ���̺� �������� ǥ��
    	    var popup = window.open('instead', 'ApprovalPopup', 'width=600,height=600');
    	    
    	    // �˾� â�� ȭ�� ����� ��ġ��Ű��
    	    if (popup) {
    	        var left = (screen.width - 400) / 2;
    	        var top = (screen.height - 300) / 2;
    	        popup.moveTo(left, top);
    	    }
    	});

    	
    	
        
     // approveStatus�� ���� ����� ������ �̺�Ʈ ó��
        $('#approveStatus').on('change', function() {
            // ���⿡�� AJAX ��û ����
            updateSearch();
        });
		
     // ���̺� �� Ŭ�� �̺�Ʈ ó��
        $('tbody').on('click', 'tr', function() {
            // ������ �࿡�� seq ���� ������
            var seq = $(this).find('td:first').text(); // ù ��° td ���� �ִ� �� (seq ��)
            
            // ������ �̵��ϴ� form ���� ����
            var form = $('<form></form>');
            form.attr('method', 'GET');
            form.attr('action', 'detail');

            // seq ���� form�� �߰�
            var seqField = $('<input></input>');
            seqField.attr('type', 'hidden');
            seqField.attr('name', 'seq');
            seqField.attr('value', seq);
            
            // loggedInUserId ���� form�� �߰�
            var userIdField = $('<input></input>');
            userIdField.attr('type', 'hidden');
            userIdField.attr('name', 'userId');
            userIdField.attr('value', '${loggedInUserId}'); // �������� ������ loggedInUserId ��
			
         // loggedInUserId ���� form�� �߰�
            var userRankField = $('<input></input>');
            userRankField.attr('type', 'hidden');
            userRankField.attr('name', 'userRank');
            userRankField.attr('value', '${loggedInUserRank}'); // �������� ������ loggedInUserRank ��

            
            // form�� body�� �߰�
            form.append(seqField);
            form.append(userIdField);
            form.append(userRankField); // userRank ���� �߰�
            $('body').append(form);
			
            
            // form ����
            form.submit();
        });



        
        
    });

    function updateSearch() {
        var form = document.getElementById('searchForm');

        // AJAX ��û�� ���� ������ ����
        var formData = {
            id: form.id.value,
            searchType: form.searchType.value,
            searchKeyword: form.searchKeyword.value,
            approveStatus: form.approveStatus.value,
            startDate: form.startDate.value,
            endDate: form.endDate.value
        };

     // AJAX ȣ���Ͽ� �˻� ��� ������Ʈ
        $.ajax({
            url: "searchoption",
            type: 'GET',
            async: true,
            data: formData,
            dataType: "json", // JSON ������ �����
            contentType: "application/json",
            success: function(data) {
                // �����κ��� ���� JSON �����͸� ����Ͽ� �ʿ��� �۾� ����
                console.log(data); // ������ ������ �ֿܼ� �α� ���
                console.log(JSON.stringify(data));
                
                // ���� �����͸� �̿��Ͽ� ���̺� ������Ʈ
                var tbody = $('tbody');

                // ���� ���̺� ���� �ʱ�ȭ
                tbody.empty();

                if (data.length === 0) {
                    // �����Ͱ� ���� ��� �޽����� ���
                    var noDataMessage = '<tr><td colspan="7">�ۼ��� ���� �����ϴ�.</td></tr>';
                    tbody.html(noDataMessage);
                } else {
                    // ���� �����͸� �̿��Ͽ� ���̺� �� �߰�
                    for (var i = 0; i < data.length; i++) {
                        var checkDate = data[i].checkDate ? formatDateTime(data[i].checkDate) : ''; // �������� �ִ� ��쿡�� ���� ����
                        var checkBoardName = data[i].checkBoardName || ''; // �����ڰ� �ִ� ��쿡�� ���, ������ �� ���ڿ�

                        // ���ϵ� ��¥ �� �ð� �������� ����
                        var formattedRegDate = formatDateTime(data[i].regDate);

                        var row = '<tr><td>' + data[i].seq +
                            '</td><td>' + data[i].memName +
                            '</td><td>' + data[i].subject +
                            '</td><td>' + formattedRegDate +
                            '</td><td>' + checkDate +
                            '</td><td>' + checkBoardName +
                            '</td><td>' + data[i].payOption +
                            '</td></tr>';

                        // ���� ���̺� ���� �߰��ϴ� ��� tbody�� ���ο� ������ ��ü�մϴ�.
                        tbody.append(row);
                    }
                }
            },
            error: function(request, status, error) {
                console.log(error); // ������ ������ �ֿܼ� ���� �α� ���
                alert('����3');
            }
        });

        return false;

    }
    
 // ��¥ �� �ð��� Ư�� �������� �����ϴ� �Լ�
    function formatDateTime(dateTimeString) {
        var date = new Date(dateTimeString);
        var year = date.getFullYear();
        var month = ('0' + (date.getMonth() + 1)).slice(-2);
        var day = ('0' + date.getDate()).slice(-2);
        var hours = ('0' + date.getHours()).slice(-2);
        var minutes = ('0' + date.getMinutes()).slice(-2);
        var seconds = ('0' + date.getSeconds()).slice(-2);

        return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
    }
</script>


</body>
</html>
