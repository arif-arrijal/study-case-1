<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix = "rms" uri = "/WEB-INF/tags/link.tld"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="en">
<%@include file="../fragment/head.jsp"%>

<body onload="onLoad()">
<div class="demo-layout-transparent mdl-layout mdl-js-layout">
    <%@include file="../fragment/header.jsp"%>
    <%@include file="../fragment/drawer.jsp"%>

    <div class="mdl-layout mdl-js-layout box-center">
        <main class="mdl-layout__content">
            <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">User Name</th>
                    <th>Password</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items = "${users}" var="user">
                    <tr>
                        <td class="mdl-data-table__cell--non-numeric"><c:out value = "${user.userName}"/></td>
                        <td><c:out value = "${user.password}"/></td>
                        <td>
                            <a id="edit-btn" href="${pageContext.request.contextPath.concat("/users/").concat(user.id).concat("/edit")}" class="mdl-button mdl-js-button mdl-button--icon">
                                <i class="material-icons">edit</i>
                            </a>
                            <div class="mdl-tooltip" data-mdl-for="edit-btn">Edit user</div>

                            <a onclick="deleteUser(${user.id})" id="delete-btn" class="mdl-button mdl-js-button mdl-button--icon">
                                <i class="material-icons">delete</i>
                            </a>
                            <div class="mdl-tooltip" data-mdl-for="delete-btn">Delete user</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <br>

            <a id="new-btn" href="${pageContext.request.contextPath.concat("/users/new")}" class="mdl-button mdl-js-button mdl-button--fab mdl-button--colored mdl-js-ripple-effect">
                <i class="material-icons">add</i>
            </a>
            <div class="mdl-tooltip" data-mdl-for="new-btn">Add new user</div>
        </main>
    </div>

</div>
<script>
    function deleteUser(id) {
        var deleteUrl = "${pageContext.request.contextPath}" + "/users/" + id + "/delete";
        var r = confirm("Are you sure delete this user?");
        if (r === true) {
            window.location.assign(deleteUrl);
        }
    }

    function onLoad() {
        var errorMsg = document.getElementById("errorMsg");
        if (errorMsg && errorMsg.value) {
            var data = {
                message: errorMsg.value,
                timeout: 2000,
                actionText: 'Undo'
            };

            setTimeout(function () {
                var snackbarContainer = document.querySelector('#message-container');
                snackbarContainer.MaterialSnackbar.showSnackbar(data);
            }, 0);
        }

    }
</script>
</body>
</html>