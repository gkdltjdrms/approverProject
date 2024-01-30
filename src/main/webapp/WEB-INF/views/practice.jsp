<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Information</title>
    <style>
        table {
            border-collapse: collapse;
            width: 50%;
            margin: 20px;
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
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Age</th>
                <th>Job</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Kim</td>
                <td>20</td>
                <td data-job="student" title="학생" onmouseover="showTitle(this)">Student</td>
            </tr>
            <tr>
                <td>Lee</td>
                <td>25</td>
                <td data-job="programmer" title="개발자" onmouseover="showTitle(this)">Programmer</td>
            </tr>
            <tr>
                <td>Choi</td>
                <td>30</td>
                <td data-job="doctor" title="의사" onmouseover="showTitle(this)">Doctor</td>
            </tr>
        </tbody>
    </table>

    <script>
    </script>
</body>
</html>
