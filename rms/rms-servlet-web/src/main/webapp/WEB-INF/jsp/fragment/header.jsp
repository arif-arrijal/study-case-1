<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<header class="mdl-layout__header mdl-layout__header--transparent">
    <div class="mdl-layout__header-row">
        <!-- Title -->
        <span class="mdl-layout-title">RMS</span>
        <!-- Add spacer, to align navigation to the right -->
        <div class="mdl-layout-spacer"></div>
        <!-- Navigation -->
        <nav class="mdl-navigation">
            <c:if test="${loginUser != null}">
                <a class="mdl-navigation__link" href="${pageContext.request.contextPath.concat("/users/list")}">Users</a>
                <a class="mdl-navigation__link" href="${pageContext.request.contextPath.concat("/logout")}">Logout</a>
            </c:if>
        </nav>
    </div>
</header>