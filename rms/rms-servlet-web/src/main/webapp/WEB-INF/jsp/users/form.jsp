<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<!doctype html>
<html lang="en">
<%@include file="../fragment/head.jsp"%>

<body>
<div class="demo-layout-transparent mdl-layout mdl-js-layout">
	<%@include file="../fragment/header.jsp"%>
	<%@include file="../fragment/drawer.jsp"%>

	<div class="mdl-layout mdl-js-layout box-center">
		<main class="mdl-layout__content">
			<div class="mdl-card mdl-shadow--6dp">
				<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
					<h2 class="mdl-card__title-text">Acme Co.</h2>
				</div>
				<form action="users" method="post">
					<div class="mdl-card__supporting-text">
						<input type="hidden" id="userId" name="userId" value="${user != null ? user.id : ''}">
						<div class="mdl-textfield mdl-js-textfield">
							<input class="mdl-textfield__input" type="text" id="username" name="username" required value="${user != null ?  user.userName : ''}"/>
							<label class="mdl-textfield__label" for="username">Username</label>
						</div>
						<div class="mdl-textfield mdl-js-textfield">
							<input class="mdl-textfield__input" type="password" id="userpass" name="userpass" required value="${user != null ? user.password : ''}">
							<label class="mdl-textfield__label" for="userpass">Password</label>
						</div>

					</div>
					<div class="mdl-card__actions mdl-card--border">
						<button type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">${user == null ? 'SAVE' : 'UPDATE'}</button>
					</div>
				</form>
			</div>
		</main>
	</div>
</div>
</body>
</html>

<%--<%@ page language="java" pageEncoding="UTF-8" session="false"%>--%>
<%--<%@ taglib prefix = "rms" uri = "/WEB-INF/tags/link.tld"%>--%>
<%--<!doctype html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
  <%--<meta charset="utf-8">--%>

  <%--<title>RMS</title>--%>
  <%--<meta name="description" content="Index">--%>
  <%--<meta name="author" content="Mitrais">--%>
  <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
  <%--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
  <%--<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">--%>
  <%--<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>--%>
  <%--<rms:link type="stylesheet" href="css/styles.css?v=1.0"/>--%>

  <%--<!--[if lt IE 9]>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>--%>
  <%--<![endif]-->--%>
<%--</head>--%>

<%--<body onload="onLoad()">--%>
    <%--<div class="mdl-layout mdl-js-layout mdl-color--grey-100 box-center">--%>

    <%--</div>--%>
  <%--<script>--%>
	  <%--function onLoad() {--%>

	  <%--}--%>
  <%--</script>--%>
<%--</body>--%>
<%--</html>--%>
