<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <h1>MemoIt</h1>

        <div class="auth-buttons">
            <c:choose>
                <c:when test="${not empty userId}">
                    <span>Welcome, ${userId}</span>
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

        <!-- 분류된 메모 목록 -->
        <div class="categorized-memo-list">
            <h3>My Memos</h3>
            <c:forEach var="category" items="${categorizedMemos}">
                <!-- 카테고리 제목 -->
                <div class="memo-category">
                    <h4>${category.key}</h4> <!-- 카테고리 이름 출력 -->
                    <ul>
                        <!-- 해당 카테고리의 메모 목록 출력 -->
                        <c:forEach var="memo" items="${category.value}">
                            <li class="memo-item">
                                <span>${memo.content}</span>
                                <span class="memo-date">
                                    - <fmt:formatDate value="${memo.createdAt}" pattern="yyyy.MM.dd HH:mm"/>
                                </span>
                                <form action="${pageContext.request.contextPath}/deleteMemo" method="post" class="delete-form">
                                    <input type="hidden" name="memoId" value="${memo.id}" />
                                    <button type="submit" class="delete-btn">X</button>
                                </form>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>