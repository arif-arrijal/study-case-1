<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<!doctype html>
<html lang="en">
<%@include file="fragment/head.jsp"%>

<body onload="onLoad()">
<div class="demo-layout-transparent mdl-layout mdl-js-layout">
	<%@include file="fragment/header.jsp"%>
	<%@include file="fragment/drawer.jsp"%>
	<%@include file="fragment/snackbar.jsp"%>

	<div class="mdl-layout mdl-js-layout box-center">
		<main class="mdl-layout__content box-center">
			<div class="mdl-card mdl-shadow--6dp">
				<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
					<h2 class="mdl-card__title-text">Acme Co.</h2>
				</div>
				<form action="login" method="post" novalidate>
					<div class="mdl-card__supporting-text">
						<div class="mdl-textfield mdl-js-textfield">
							<input class="mdl-textfield__input" type="text" id="username" name="username" required="required"/>
							<label class="mdl-textfield__label" for="username">Username</label>
						</div>
						<div class="mdl-textfield mdl-js-textfield">
							<input class="mdl-textfield__input" type="password" id="userpass" name="userpass" required="required"/>
							<label class="mdl-textfield__label" for="userpass">Password</label>
						</div>
					</div>
					<div class="mdl-card__actions mdl-card--border">
						<button type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Log in</button>
					</div>
				</form>
			</div>
		</main>
	</div>

</div>
<script>
    function onLoad() {
        var errorMsg = document.getElementById("errorMsg");
        if (errorMsg && errorMsg.value) {
            var data = {
                message: errorMsg.value,
                timeout: 3000,
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