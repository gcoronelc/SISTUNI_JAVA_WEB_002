<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CONSULTAR CLIENTES</title>
</head>
<body>
	<div>
		<h1>CONSULTAR CLIENTES</h1>
		<form id="form1" action="ClienteConsultar">
			<table>
				<tr>
					<td>PATERNO</td>
					<td>MATERNO</td>
					<td>NOMBRE</td>
					<td rowspan="2" valign="bottom">
						<input type="button" id="btnConsultar" value="Consultar" />
						<input type="button" id="btnExcel" value="Exp. Excel" />
					</td>
				</tr>
				<tr>
					<td><input type="text" name="paterno" /></td>
					<td><input type="text" name="materno" /></td>
					<td><input type="text" name="nombre" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="egcc_contenido">
		<h3>Aquí va el resultado.</h3>
	</div>
	<script type="text/javascript">
	
		$("#btnConsultar").click(function(){
			var data = $("#form1").serialize();
			$.post("ClienteConsultar",data,function(objJson){
				if(objJson.codigo == 1){
					$("#egcc_contenido").text("Falta generar la tabla.");
				} else {
					$("#egcc_contenido").text(objJson.texto);	
				}
			});
		});
	
	</script>
</body>
</html>