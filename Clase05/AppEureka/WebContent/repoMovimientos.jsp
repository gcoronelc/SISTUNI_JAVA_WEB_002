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
		<h1>REPORTE DE MOVIMIENTOS</h1>
		<form id="form2">
			Cuenta: 
			<input type="text" name="cuenta" />
			<input type="button" id="btnConsultar" value="Consultar" />
		</form>
	</div>
	<div id="egcc_gif" style="display: none;">
		<img src='img/ajax-loader.gif' alt='' /> Espere que estamos trabajuando...
	</div>
	<div id="egcc_contenido">
		<p>Aquí irá el contendido</p>
	</div>
	<script type="text/javascript">
	
		$("#btnConsultar").click(function(){
			
			$("#egcc_contenido").hide();
			$("#egcc_gif").show();
						
			var data = $("#form2").serialize();
			var urlRepo = "RepoMovimientos?" + data;
			var html = "<object width='100%' height='600' data='" + urlRepo + "'></object>";
			$("#egcc_contenido").html(html);
			
			$("#egcc_contenido").show();
			$("#egcc_gif").hide();
		});
	
	</script>
</body>
</html>