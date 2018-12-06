<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="mdl-layout__drawer">
    <span class="mdl-layout-title">RMS</span>
    <c:if test="${loginUser != null}">
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="${pageContext.request.contextPath.concat("/users/list")}">Users</a>
            <a class="mdl-navigation__link" href="${pageContext.request.contextPath.concat("/logout")}">Logout</a>
        </nav>
    </c:if>
</div>