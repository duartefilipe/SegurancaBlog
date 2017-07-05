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
				
	function mascaraSenha(o,f){
	    v_obj=o;
	    v_fun=f;
	    setTimeout("execmascara1()",1);
	}
	function execmascara1(){
		    v_obj.value=v_fun(v_obj.value);
	}
	function alphanum1( v ){
		    v=v.replace(/[^a-zA-Z0-9]/g," ");			
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

		<div class="container">
			<div class="row">
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<h3 class="text-center">Altera Dados do Post</h3>
						<br>
							<form action="alteraPostUsuarioComum" method="post">
								<input type="hidden" name="idPosts" value="${PostAltera.ID}">
								<label for="titulo">Titulo</label> 
								<input type="text" id="titulo" name="titulo" class="form-control" value="${PostAltera.titulo}" onkeyup="mascaraSenha(this,alphanum1)" required /> 
								<label for="descricao">Descrição</label> 
								<textarea type="text" id="descricao" rows="5" cols="50"  name="descricao" value="${PostAltera.descricao}" onkeyup="mascaraSenha(this,alphanum1)" class="form-control"  required> ${PostAltera.descricao} </textarea> 
								<br>
								<div class="pull-right">
								   <button type="submit" class="btn btn-primary">Alterar Post</button>
								</div>
							</form>
					</div>
					<div class="col-lg-2"></div>
			</div>
	 	</div>

</c:if>	
</body>
</html>