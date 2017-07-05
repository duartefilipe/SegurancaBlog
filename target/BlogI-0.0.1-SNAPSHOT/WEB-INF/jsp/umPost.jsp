<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<html>

<head>

<title>Bem Vindo ao BLOG</title>

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


</head> 

<body>

	<nav class="navbar navbar-default">
	 <div class="container-fluid" style="background: black;">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="#" >Segurança Blog </a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index"><i class="fa fa-home" aria-hidden="true"></i> Inicio</a></li>
					
				</ul>

				<form action="login.htm" method="post" class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" name="login" placeholder="Login" required>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="senha" placeholder="Senha" required>
					</div>
					<button type="submit" class="btn btn-success">Entrar</button>
				</form>
			</div>
		</div>
	 </div>
	</nav>
<div class="container-fluid">

		<div class="row">
		
		<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<div class="col-lg-12">
					<h3 class="text-center">Post</h3>
					<div class="thumbnail">
							<div class="#">
									Titulo - ${Post.titulo}
								<div class="pull-right">
									Publicado em: ${Post.data}
								</div>
							</div>
							<div class="">
								<hr>
							    Descrição - ${Post.descricao}
							</div><br>
					</div>
				</div>
				<c:forEach var="p" items="${Post.comentarios}">
					<div class="col-lg-12">
						Comentário:
						<div class="thumbnail">
								<div class="#">
								  ${p.textocoment}
								</div>
						</div>
					</div>
				</c:forEach>
				<form  action="cadastraComentario" method="get">
				<div class="col-lg-12">
					<h3>Adicionar Comentário</h3>
                        
                        <input type="hidden" name="id_posts" value="${Post.ID}">
                            <div class="form-group-sm">
                                <label for="email">Escreva Seu Comentário:</label>
                                <textarea class="form-control" name="textocoment" onkeyup="mascaraSenha(this,alphanum1)" rows="3"></textarea>
                            </div><br>
                            <div class="form-group-sm pull-right">
                                <button class="btn btn-default" type="submit">Adicionar Comentário</button>
                            </div>
                   </div>
				</form>
			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>

</body>
</html>