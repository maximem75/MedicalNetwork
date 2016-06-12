<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="layout.inscription">

    <tiles:putAttribute name="body">

        <div id="middle">
            <form id="#inscription_form" method="POST" action="http://localhost:8080/user">
                <div class="div-inscription">
                    <ul class="login-center">
                        <li>
                            <span class="formulaire">Nom de compte</span><input type="text" class="input-login inpt" id="login"/>
                        </li>
                        <li>
                            <span class="formulaire">Mot de passe</span><input type="password" class="input-password inpt" id="password"/>
                        </li>
                        <li>
                            <span class="formulaire">Ressaisire mot de passe</span><input type="password" class="input-password-2 inpt" id="password2"/>
                        </li>
                        <li>
                            <span class="formulaire">Nom</span><input type="text" class="input-name inpt" id="name"/>
                        </li>
                        <li>
                            <span class="formulaire">Prénom</span><input type="text" class="input-firstname inpt" id="firstname"/>
                        </li>
                        <li>
                            <span class="formulaire">Date de naissance</span><input type="text" class="input-date inpt" id="date"/>
                        </li>
                        <li>
                            <span class="formulaire">Téléphone</span><input type="text" class="input-phone inpt" id="phone"/>
                        </li>
                        <li>
                            <span class="formulaire">Email</span><input type="text" class="input-email inpt" id="mail"/>
                        </li>
                        <li>
                            <input type="submit" value="Inscription" class="btn" id="btnid"/>
                        </li>

                    </ul>
                </div>
            </form>
        </div>

    </tiles:putAttribute>

    <tiles:putAttribute name="script">

    </tiles:putAttribute>

</tiles:insertDefinition>