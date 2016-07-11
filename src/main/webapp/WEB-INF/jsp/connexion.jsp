<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="layout.connexion">

    <tiles:putAttribute name="body">

        <div id="middle" style="width: 400px;">
            <div class="content_middle">
                <form id="login-form" action="http://localhost:8080/user/login" method="POST">
                      <br class="div-login">

                      <h2 class="form-signin-heading">Identification</h2>
                      <label for="login_id" class="sr-only">Email address</label>
                      <input type="text" id="login_id" class="form-control" placeholder="Identifiant" required autofocus>
                      </br>
                      <label for="password_id" class="sr-only">Mot de passe</label>
                      <input type="password" id="password_id" class="form-control" placeholder="Mot de passe" required>
                         </br>
                      <a href="http://localhost:8080/inscription">Pas encore inscrit?</a>
                      <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>

                </form>
            </div>
        </div>


    </tiles:putAttribute>

    <tiles:putAttribute name="script">

    </tiles:putAttribute>

</tiles:insertDefinition>