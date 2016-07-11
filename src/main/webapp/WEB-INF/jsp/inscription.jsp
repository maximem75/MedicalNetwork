<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="layout.inscription">

    <tiles:putAttribute name="body">

        <div id="middle">
            <div class="content_middle">
                <form id="inscription_form" method="POST" action="http://localhost:8080/user/register">
                    <br class="div-inscription">

                        <div class="row">
                        <div class="col-md-offset-2 col-md-8">
                        <h1> Inscription <br/> <small> Merci de renseigner vos informations </small></h1>
                        </div>
                        </div>

                        <div class="row">
                        <div class="col-md-offset-2 col-md-3">
                        <div class="form-group">
                        <label for="login">Identifiant</label>
                        <input type="text" class="form-control register" id="login" placeholder="Identifiant" name="login">
                        </div>
                        </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-2 col-md-3">
                                <div class="form-group">
                                    <label for="Password">Mot de passe</label>
                                    <input type="password" class="form-control register" id="password" placeholder="Mot de passe" name="password">
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
                        <div class="form-group">
                        <label>Nom</label>
                        <input type="text" class="form-control register" id="name" placeholder="Nom" name="name">
                        </div>
                        </div>
                        <div class="col-md-offset-1 col-md-3">
                            <div class="form-group">
                                <label>Pr&eacute;nom</label>
                                <input type="text" class="form-control register" id="firstname" placeholder="Pr&eacute;nom" name="firstname">
                            </div>
                        </div>
                        </div>

                        <div class="row">
                        <div class="col-md-offset-2 col-md-3">
                        <div class="input-group">
                            <span class="input-group-addon glyphicon glyphicon-calendar"></span>
                            <input type="date" class="form-control register" placeholder="Date de Naissance" aria-describedby="basic-addon1" name="birthday" id="birthday">
                        </div>
                        </div>
                        <div class="col-md-offset-1 col-md-3">
                            <div class="input-group">
                                <span class="input-group-addon glyphicon glyphicon-earphone"></span>
                                <input type="text" class="form-control register" placeholder="T&eacute;l&eacute;phone" aria-describedby="basic-addon1" name="phone" id="phone">
                            </div>
                        </div>
                        </div>
                        </br>
                        <div class="row">
                        <div class="col-md-offset-2 col-md-3">
                        <div class="form-group">
                        <label for="Email">Email</label>
                        <input type="email" class="form-control register" id="email" placeholder="Enter email" name="email">
                        </div>
                        </div>
                            </br>
                        <div class="col-md-offset-1 col-md-3">
                            <select class="form-control register" id="idcategory">
                            </select>
                        </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-2 col-md-7">
                                <div class="form-group">
                                    <small>L'identifiant doit comporter 5 caract&egrave;res minimum, le mot de passe doit en comporter 6.</small>
                                </div>
                            </div>
                        </div>

                        <br/>
                        <div class="row">
                        <div class="col-md-offset-2 col-md-1">
                        <button type="submit" class="btn  btn-primary " id="btnid">Envoyer mes informations</button>
                        </div>
                        </div>

                </form>
            </div>
        </div>

    </tiles:putAttribute>

    <tiles:putAttribute name="script">

    </tiles:putAttribute>

</tiles:insertDefinition>