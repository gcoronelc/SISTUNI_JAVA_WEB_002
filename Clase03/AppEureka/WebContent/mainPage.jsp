<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/estilos.css">
<link rel="stylesheet" type="text/css" href="menu/menu.css">
<script type="text/javascript" src="jquery/jquery.js"></script>
<title>APP EUREKA</title>
</head>
<body>

<div id="_PAGINA">

	<div id="_CABECERA">
 		<table style="width: 100%">
 			<tr>
 				<td><img alt="" src="img/logo.gif"></td>
 				<td>
 					Usuario: Gustavo Coronel<br/>
 					<a href="#">Salir</a>
 				</td>
 			</tr>
 		</table>	
	</div>
	
	<div id="_MENU">
 		<jsp:include page="menu/menu.jsp"/>
	</div>
	
	<div id="_CONTENIDO">
		<div style="width: 100%; text-align: center;">
 		<img alt="" src="img/eureka.jpg">
 		</div>	
	</div>

</div>
<script type="text/javascript">

function loadPage(page){
	$("#_CONTENIDO").load(page);
}


</script>

</body>
</html>