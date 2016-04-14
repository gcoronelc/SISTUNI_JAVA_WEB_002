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
			var previo = "<img src='img/ajax-loader.gif' alt='' />";
			$("#egcc_contenido").html(previo);
			$.post("ClienteConsultar",data,function(objJson){
				if(objJson.codigo == 1){
					var varTabla = "<table>";
					varTabla += "<tr>";
					varTabla += "<th>CODIGO</th>";
					varTabla += "<th>PATERNO</th>";
					varTabla += "<th>MATERNO</th>";
					varTabla += "<th>NOMBRE</th>";
					varTabla += "<th>DNI</th>";
					varTabla += "<th>EMAIL</th>";
					varTabla += "</tr>";
					
					// alert(objJson.texto);
					var jsonObj = $.parseJSON(objJson.texto);
					
					$.each(jsonObj,function(index,objCliente){
						varTabla += "<tr>";
						varTabla += "<td>" + objCliente.codigo + "</td>";
						varTabla += "<td>" + objCliente.paterno + "</td>";
						varTabla += "<td>" + objCliente.materno + "</td>";
						varTabla += "<td>" + objCliente.nombre + "</td>";
						varTabla += "<td>" + objCliente.dni + "</td>";
						varTabla += "<td>" + objCliente.email + "</td>";
						varTabla += "</tr>";
					});
					
					varTabla += "</table>";
					$("#egcc_contenido").html(varTabla);
				} else {
					$("#egcc_contenido").text(objJson.texto);	
				}
			});
		});
	
		$("#btnExcel").click(function (e) {
		    window.open('data:application/vnd.ms-excel,' + $('#egcc_contenido').html());
		    e.preventDefault();
		});
	</script>
</body>
</html>