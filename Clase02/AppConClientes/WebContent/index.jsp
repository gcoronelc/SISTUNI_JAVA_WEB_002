<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CONSULTAR CLIENTES</title>
</head>
<body>
	<h1>CONSULTAR CLIENTES</h1>
	<form method="post" action="ClienteConsultar">
	<table>
		<tr>
			<td>PATERNO</td>
			<td>MATERNO</td>
			<td>NOMBRE</td>
			<td rowspan="2" valign="bottom"><input type="submit" value="Consultar"/></td>
		</tr>
		<tr>
			<td><input type="text" name="paterno" /></td>
			<td><input type="text" name="materno" /></td>
			<td><input type="text" name="nombre" /></td>
		</tr>
	</table>
	</form>
</body>
</html>