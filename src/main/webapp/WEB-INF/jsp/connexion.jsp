<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="layout.connexion">

    <tiles:putAttribute name="body">

        <div id="middle">
            <form id="login-form" action="http://localhost:8080/user/login" method="GET">
                <div class="div-login">
                    <ul class="login-center">
                        <li>
                            <span class="formulaire">Identifiant</span><input type="text" class="input-login inpt" id="login_id" />
                        </li>
                        <li>
                            <span class="formulaire">Mot de passe</span><input type="password" class="input-password inpt" id="password_id" />
                        </li>
                        <li>
                            <input type="submit" value="Connexion" class="btn" />
                        </li>
                    </ul>
                </div>
            </form>
        </div>

    </tiles:putAttribute>

    <tiles:putAttribute name="script">

    </tiles:putAttribute>

</tiles:insertDefinition>