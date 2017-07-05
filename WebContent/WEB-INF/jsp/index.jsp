<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

    <head>



        <title>Bem Vindo ao BLOG</title>

        <script src="<c:url value='/resources/js/ie-emulation-modes-warning.js'/>"></script>
        <link href="<c:url value='/resources/css/bootstrap.min.css'/>"rel="stylesheet">
        <link href="<c:url value='/resources/carousel.css'/>" rel="stylesheet">
        <link href="<c:url value='/resources/css/bootstrap.css'/>"rel="stylesheet">
        <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>"rel="stylesheet">
    </head> 

    <script type="text/javascript">

        function mascaraSenha(o, f) {
            v_obj = o;
            v_fun = f;
            setTimeout("execmascara1()", 1);
        }
        function execmascara1() {
            v_obj.value = v_fun(v_obj.value);
        }
        function alphanum1(v) {
            v = v.replace(/[^a-zA-Z0-9]/g, " ");
            return v;
        }
    </script>

    <script>
        function escondeDiv(el) {
            var display = document.getElementById(el).style.display;
            if (display == "none")
                document.getElementById(el).style.display = 'block';
            else
                document.getElementById(el).style.display = 'none';
        }
    </script>

    <body>

        <nav class="navbar navbar-default">
            <div class="container-fluid" style="background: black;">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#" > Segurança Blog </a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                        <form action="login" method="post" class="navbar-form navbar-right" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" name="login" onkeyup="mascaraSenha(this, alphanum1)" placeholder="Login" required>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" name="senha" onkeyup="mascaraSenha(this, alphanum1)" placeholder="Senha" required>
                            </div>
                            <button type="submit" class="btn btn-success">Entrar</button>

                        </form>
                    </div>
                    <!-- /.navbar-collapse -->
                </div>

                <!-- /.container-fluid -->
            </div>
        </nav>
        <c:if test="${msgdesativado!=null}">
            <div id="esconde">
                <div class="alert alert-warning">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong class="text-center">${msgdesativado}</strong>
                </div>
            </div>
        </c:if>


        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-9">
                    <div class="col-lg-12">
                        <h3 class="text-left">Posts </h3>

                        <c:forEach var="post" items="${ListaDePostsIndex}">
                            <div class="thumbnail">
                                <div class="#">
                                    Titulo: ${post.titulo}
                                    <div class="pull-right">
                                        Publicado em: ${post.data}
                                    </div>
                                </div>
                                <div class="">
                                    <hr>
                                    Descricao: ${post.descricao}
                                </div><br>
                                <div class="text-right">
                                    <a style="font-style: italic;" href="RedirecionaUmPost?id=${post.ID}">Saiba mais</a>
                                </div>
                            </div>
                        </c:forEach>

                        <a class="btn btn-primary"  href="MostraTodosPost"> Posts Antigos</a>
                        <br><br>


                        <!-- - POSTS ANTIGOOOS -->
                        <c:if test="${PostsAntigos!=null}">
                            <div id="minhaDiv">
                                <c:forEach var="antigo" items="${PostsAntigos}">
                                    <div class="thumbnail">
                                        <div class="#">
                                            Titulo: ${antigo.titulo}
                                            <div class="pull-right">
                                                Publicado em: ${antigo.data}
                                            </div>
                                        </div>
                                        <div class="">
                                            <hr>
                                            Descricao: ${antigo.descricao}
                                        </div><br>
                                        <div class="text-center">
                                            <a style="font-style: italic;" href="RedirecionaUmPost?id=${post.ID}">Saiba mais</a>
                                        </div>
                                    </div>
                                </c:forEach>
                                <button class="btn btn-danger" type="submit" onclick="escondeDiv('minhaDiv')"> Fechar </button>

                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="col-lg-3">
                    <h3>Busca pelo Blog</h3>
                    <div class="input-group" style="margin-top: 25px;">
                        <input type="text" class="form-control" placeholder="Search for...">
                        <span class="input-group-btn">
                            <button class="btn btn-primary" type="button">Procurar!</button>
                        </span>
                    </div>
                    <br><br>

                </div>
            </div>
        </div>
    </body>
</html>