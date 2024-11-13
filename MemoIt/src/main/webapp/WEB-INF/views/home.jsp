<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
</head>
<body>
    <div class="container">
        <!-- 페이지 제목 -->
        <h1>MemoIt</h1>

        <!-- 로그인된 사용자 ID 및 로그아웃 버튼을 오른쪽 상단에 배치 -->
        <div class="auth-buttons">
            <c:choose>
                <c:when test="${not empty userId}">
                    <span>Welcome, ${userId}</span> <!-- 로그인한 유저의 ID 표시 -->
                    <a href="<c:url value='/logout' />" class="btn">Logout</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/login' />" class="btn">Login</a>
                    <a href="<c:url value='/register' />" class="btn">Register</a>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- 메모 작성 폼 -->
        <form action="${pageContext.request.contextPath}/addMemo" method="post" class="memo-form">
            <textarea name="content" placeholder="Write a note..."></textarea>
            <button type="submit" class="save-btn">Save</button>
        </form>

        <!-- 메모 목록 -->
        <div class="memo-list">
            <h3>My Memos</h3>
            <ul>
                <c:forEach var="memo" items="${memos}">
                    <li class="memo-item">
                        <span>${memo.content} - ${memo.createdAt}</span>
                        <!-- 메모 삭제 버튼: 메모 박스 밖에 위치하며, 커서 올리면 나타나도록 설정 -->
                        <form action="${pageContext.request.contextPath}/deleteMemo" method="post" class="delete-form">
                            <input type="hidden" name="memoId" value="${memo.id}" />
                            <button type="submit" class="delete-btn">X</button>
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>
