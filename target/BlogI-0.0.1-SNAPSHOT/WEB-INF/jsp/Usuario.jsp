<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

        <link href="<c:url value='/resources/css/bootstrap.min.css'/>"rel="stylesheet" type="text/css" />

        <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>"rel="stylesheet">
        <script src="<c:url value='/resources/js/ie-emulation-modes-warning.js'/>"></script>

        <link href="<c:url value='/resources/js/bootstrap.min.js'/>"/>
        <script src="<c:url value='/resources/js/jquery-1.8.3.min.js'/>"></script>
        <script src="<c:url value='/resources/js/jquery.maskedinput.min.js'/>"></script>
        <link href="<c:url value='/resources/assets/js/jquery-ui-min.js'/>"/>



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

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Home Page Admin</title>
    </head>
    <body>


        <c:if  test="${sessionScope['logado'] != null}">


        <nav class="navbar navbar-default">
            <div class="container-fluid" style="background: black;">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#" >Segurança Blog </a> 
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse"
                         id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav pull-right">
                            <li><a href="">${logado.login.toUpperCase()} <i class="fa fa-user"></i>
                                </a></li>
                            <li><a class="navbar-brand" href="logout"> Sair <i class="fa fa-sign-in"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                <header class="text-center"><h2> Lista de Post</h2></header>
                <button type="button" class="btn btn-default navbar-btn" data-toggle="modal" data-target="#modalPost">Novo Post</button>
                <table  class="table table-striped table-bordered ">
                    <thead>
                        <tr>
                            <th class="text-center">Titulo</th>
                            <th class="text-center">Data</th>
                            <th class="text-center">ALTERAR</th>
                            <th class="text-center">DELETAR</th>
                        </tr>

                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${listaPost}">
                            <tr>
                                <td class="text-center">${p.titulo}</td>
                                <td class="text-center">${p.data}</td>
                                <td class="text-center">
                                    <a class="btn btn-success" href="RedirecionaAlteraPost?id=${p.ID}"><i class="fa fa-refresh"></i></a>
                                </td>
                                <td class="text-center">	
                                    <a class="btn btn-danger" href="deletaPostUsuarioComum?id=${p.ID}"><i class="fa fa-trash"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>			

            </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <!-- -------MODAL CADASTRAR POST ------------------------------------------->

        <div class="modal" id="modalPost" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Cadastro de Post</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-lg-4"></div>
                            <div class="col-lg-4">
                                <form action="cadastraPostComum" method="post">
                                    <label for="titulo">Titulo</label> 
                                    <input type="text" id="titulo" name="titulo" class="form-control"  onkeyup="mascaraSenha(this, alphanum1)" required /> 
                                    <label for="descricao">Descrição</label> 
                                    <textarea type="text" id="descricao" rows="5" cols="50"  name="descricao" class="form-control"  onkeyup="mascaraSenha(this, alphanum1)"  required></textarea> 
                                    <br><br>
                                    <button type="submit" class="btn btn-primary">Cadastrar Post</button>

                                </form>
                            </div>
                            <div class="col-lg-4"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- --------------------MODAL CADASTRAR POST -->

    </c:if> 	

</body>
</html>