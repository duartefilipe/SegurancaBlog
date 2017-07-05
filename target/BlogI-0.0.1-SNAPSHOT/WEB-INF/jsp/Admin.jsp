<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

                        <div class="collapse navbar-collapse"
                             id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav pull-right">
                                <li><a href=""> ${logado.login.toUpperCase()} <i class="fa fa-user"></i>
                                    </a></li>
                                <li><a class="navbar-brand" href="logout"> Sair <i class="fa fa-sign-in"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </nav>



            <div class="container-fluid">
                <div class="row">
                    <c:if test="${msgErro!=null}">
                        <div id="esconde">
                            <div class="alert alert-danger fade in">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong class="text-center">Usuario Contém um POST</a></strong>
                            </div>
                        </div>
                    </c:if>
                    <div class="col-md-6">
                        <header class="text-center"><h2> Lista de Posts</h2></header>
                            <c:if test="${msgSucesso!=null}">
                            <div id="esconde">
                                <div class="alert alert-success fade in">
                                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                    <strong class="text-center">${msgSucesso}</strong>
                                </div>
                            </div>
                        </c:if>
                        <button type="button" class="btn btn-default navbar-btn" data-toggle="modal" data-target="#modal">Novo Post</button>
                        <table  class="table  ">
                            <thead>
                                <tr>
                                    <th class="text-center">Titulo</th>
                                    <th class="text-center">Usuario</th>
                                    <th class="text-center">Data</th>
                                    <th class="text-center">Alterar</th>
                                    <th class="text-center">Deletar</th>
                                </tr>

                            </thead>
                            <tbody>
                                <c:forEach var="post" items="${ListaDePosts}">
                                    <tr>
                                        <td class="text-center">${post.titulo}</td>
                                        <td class="text-center">${post.usuario.nome}</td>
                                        <td class="text-center">${post.data}</td>
                                        <td class="text-center">
                                            <a style="color: black" class="glyphicon glyphicon-refresh" href="RedirecionaAlteraPostUsuarioAdmin?id=${post.ID}"></a>
                                        </td>
                                        <td class="text-center">	
                                            <a style="color: black" class="glyphicon glyphicon-trash" href="deletaPostUsuarioAdmin?id=${post.ID}"></a>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>

                    <div class="col-md-6">
                        <c:if test="${logado.tipo == true}">

                            <header class="text-center"><h2> Lista de Usuários</h2></header>
                                <c:if test="${logado.tipo == true}">

                                <div class="row">
                                    <button type="button" class="btn btn-default navbar-btn" data-toggle="modal" data-target="#modalUsuario">Novo usuario</button>
                                    <table  class="table  ">
                                        <thead>
                                            <tr>
                                                <th class="text-center">Login</th>
                                                <th class="text-center">Nome</th>
                                                <th class="text-center">Ativo</th>
                                                <th class="text-center">Ativar | Desativar</th>
                                                <th class="text-center">Deletar</th>
                                            </tr>

                                        </thead>
                                        <tbody>

                                            <c:forEach var="user" items="${ListaUsuario}">
                                                <tr>
                                                    <td class="text-center">${user.login}</td>
                                                    <td class="text-center">${user.nome}</td>
                                                    <td class="text-center">${user.ativo}</td>
                                                    <td class="text-center">	
                                                        <a style=" color: black" class="glyphicon glyphicon-edit" href="situacaoUsuario?id=${user.id}"></a>
                                                    </td>
                                                    <td class="text-center">	
                                                        <a style="color: black" class="glyphicon glyphicon-trash" href="deletaUsuario?id=${user.id}"></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>			
                                </div>
                            </c:if>


                            </tbody>
                            </table>			
                        </div>
                    </c:if>
                </div>
            </div>



            <!-- -------MODAL CADASTRAR POST ------------------------------------------->

            <div class="modal" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">Cadastro de Post</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-lg-4"></div>
                                <div class="col-lg-4">
                                    <form action="cadastraPostAdmin" method="post">
                                        <label for="titulo">Titulo</label> 
                                        <input type="text" id="titulo" name="titulo" onkeyup="mascaraSenha(this, alphanum1)" class="form-control"  required /> 
                                        <label for="descricao">Descrição</label> 
                                        <textarea type="text" id="descricao" rows="3" onkeyup="mascaraSenha(this, alphanum1)"  name="descricao" class="form-control"  required></textarea> 
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

            <!-- --------------------MODAL CADASTRAR USUARIO -->


            <div class="modal" id="modalUsuario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel">Cadastro de Usuário</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-lg-4"></div>
                                <div class="col-lg-4">
                                    <form action="cadastraUsuarioAdmin" method="post">
                                        <label for="login">Login</label> 
                                        <input type="text" id="login" name="login" class="form-control" onkeyup="mascaraSenha(this, alphanum1)" required /> 
                                        <label for="login">Nome</label> 
                                        <input type="text" id="nome" name="nome" class="form-control" onkeyup="mascaraSenha(this, alphanum1)" required /> 
                                        <label for="senha">Senha</label>
                                        <input type="text" id="senha" name="senha" class="form-control" onkeyup="mascaraSenha(this, alphanum1)" required/>
                                        <br>
                                        <div class="form-group text-center">
                                            <label for="tipoo" >Tipo Usuario</label>
                                            <select name="tipoo"  class="form-control" required>
                                                <option>-------</option>
                                                <option value= "1">Usuário Admin</option>
                                                <option value= "2">Usuário Comum</option>
                                            </select>
                                        </div>

                                        <br><br><br>    
                                        <button type="submit" class="btn btn-primary" >Cadastrar Usuario</button>
                                    </form>
                                </div>
                                <div class="col-lg-4"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </c:if>
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
    </body>
</html>