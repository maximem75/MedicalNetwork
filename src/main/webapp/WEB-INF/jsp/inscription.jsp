<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="layout.inscription">

    <tiles:putAttribute name="body">

        <div id="middle">
            <form id="inscription_form" method="POST" action="http://localhost:8080/user/register">
                <div class="div-inscription">

                    <div class="row">
                    <div class="col-md-offset-2 col-md-8">
                    <h1> Inscription <br/> <small> Merci de renseigner vos informations </small></h1>
                    </div>
                    </div>

                    <div class="row">
                    <div class="col-md-offset-2 col-md-3">
                    <div class="form-group">
                    <label for="Nom">Nom</label>
                    <input type="text" class="form-control" id="nom" placeholder="Nom" name="name">
                    </div>
                    </div>
                    <div class="col-md-offset-1 col-md-3">
                    <div class="form-group">
                    <label for="Prenom">Pr&eacute;nom</label>
                    <input type="text" class="form-control" id="prenom" placeholder="Pr&eacute;nom" name="firstname">
                    </div>
                    </div>
                    </div>

                    <div class="row">
                    <div class="col-md-offset-2 col-md-3">
                    <div class="form-group">
                    <label for="login_id">Identifiant</label>
                    <input type="text" class="form-control" id="login_id" placeholder="Identifiant" name="login">
                    </div>
                    </div>
                    <div class="col-md-offset-1 col-md-3">
                    <div class="form-group">
                    <label for="Email">Email</label>
                    <input type="text" class="form-control" id="email" placeholder="Enter email" name="email">
                    </div>
                    </div>
                    </div>

                    <div class="row">
                    <div class="col-md-offset-2 col-md-3">
                    <div class="form-group">
                    <label for="Password">Mot de passe</label>
                    <input type="password" class="form-control" id="password" placeholder="Mot de passe" name="password">
                    </div>
                    </div>
                    <div class="col-md-offset-1 col-md-3">
                    <div class="form-group">
                    <label for="Vpassword">V&eacute;rification mot de passe</label>
                    <input type="password" class="form-control" id="vpassword" placeholder="V&eacute;rification mot de passe">
                    </div>
                    </div>
                    </div>

                    <div class="row">
                    <div class="col-md-offset-2 col-md-3">
                    <div class="input-group">
                    <span class="input-group-addon glyphicon glyphicon-earphone"></span>
                    <input type="text" class="form-control" placeholder="T&eacute;l&eacute;phone" aria-describedby="basic-addon1" name="phone">
                    </div>
                    <div class="input-group">
                    <span class="input-group-addon glyphicon glyphicon-calendar"></span>
                    <input type="date" class="form-control" placeholder="Date de Naissance" aria-describedby="basic-addon1" name="date">
                    </div>
                    </div>
                    </div>

                    <br/>
                    <div class="row">
                    <div class="col-md-offset-2 col-md-1">
                    <button type="submit" class="btn  btn-primary " id="btnid">Envoyer mes informations</button>
                    </div>
                    </div>
                </div>
            </form>
        </div>

    </tiles:putAttribute>

    <tiles:putAttribute name="script">

    </tiles:putAttribute>

</tiles:insertDefinition>