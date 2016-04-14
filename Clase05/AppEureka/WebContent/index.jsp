<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	#LOGON{
		width: 400px;
		background-color: white;
		margin: 10px auto;
		padding: 10px;
	}
</style>
</head>
<body bgcolor="blue">
	<div id="LOGON">
		<h1>INICIO DE SESION</h1>
		<form method="post" action="LogonIngresar">
		<table>
			<tr>
				<td rowspan="3"><img alt="" src="img/logon.png"></td>
				<td>Usuario: </td>
				<td><input type="text" name="usuario" /></td>
			</tr>
			<tr>
				<td>Clave: </td>
				<td><input type="password" name="clave" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Ingresar" /></td>
			</tr>
		</table>
		<p>Error: ${error}</p>
		</form>
	</div>
</body>
</html>