<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>CONSULTA DE MOVIMIENTOS</h1>
		<form id="form1">
			Cuenta: 
			<input type="text" name="cuenta" />
			<input type="button" id="btnConsultar" value="Consultar" />
		</form>
	</div>
	<div id="egcc_contenido">
		<p>Aquí irá el contendido</p>
	</div>
	<script type="text/javascript">
	
		$("#btnConsultar").click(function(){
			var data = $("#form1").serialize();
			$.post("CuentaConMovimientos",data,function(objJson){
				$("#egcc_contenido").text(objJson.texto)	
			});
		});
	
	</script>
</body>
</html>