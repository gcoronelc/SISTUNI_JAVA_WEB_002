<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>${accion} CLIENTE</h1>
	<form id="formCliente">
		<input type="hidden" name="accion" value="${accion}" />
		<table>
			<tr>
				<td width="100">Código</td>
				<td>${cliente.codigo}<input type="hidden" name="codigo" value="${cliente.codigo}"/></td>
			</tr>
			<tr>
				<td>Paterno</td>
				<td>
					<c:if test="${accion == 'ELIMINAR' }">${cliente.paterno}</c:if>
					<c:if test="${accion != 'ELIMINAR' }"><input type="text" name="paterno" value="${cliente.paterno}"/></c:if>				
				</td>
			</tr>
			<tr>
				<td>Materno</td>
				<td>
					<c:if test="${accion == 'ELIMINAR' }">${cliente.materno}</c:if>
					<c:if test="${accion != 'ELIMINAR' }"><input type="text" name="materno" value="${cliente.materno}"/></c:if>				
				</td>
			</tr>
			<tr>
				<td>Nombre</td>
				<td>
					<c:if test="${accion == 'ELIMINAR' }">${cliente.nombre}</c:if>
					<c:if test="${accion != 'ELIMINAR' }"><input type="text" name="nombre" value="${cliente.nombre}"/></c:if>				
				</td>
			</tr>
			<tr>
				<td>DNI</td>
				<td>
					<c:if test="${accion == 'ELIMINAR' }">${cliente.dni}</c:if>
					<c:if test="${accion != 'ELIMINAR' }"><input type="text" name="dni" value="${cliente.dni}"/></c:if>				
				</td>
			</tr>
			<tr>
				<td>Ciudad</td>
				<td>
					<c:if test="${accion == 'ELIMINAR' }">${cliente.ciudad}</c:if>
					<c:if test="${accion != 'ELIMINAR' }"><input type="text" name="ciudad" value="${cliente.ciudad}"/></c:if>				
				</td>
			</tr>
			<tr>
				<td>Dirección</td>
				<td>
					<c:if test="${accion == 'ELIMINAR' }">${cliente.direccion}</c:if>
					<c:if test="${accion != 'ELIMINAR' }"><input type="text" name="direccion" value="${cliente.direccion}"/></c:if>				
				</td>
			</tr>
			<tr>
				<td>Teléfono</td>
				<td>
					<c:if test="${accion == 'ELIMINAR' }">${cliente.telefono}</c:if>
					<c:if test="${accion != 'ELIMINAR' }"><input type="text" name="telefono" value="${cliente.telefono}"/></c:if>				
				</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>
					<c:if test="${accion == 'ELIMINAR' }">${cliente.email}</c:if>
					<c:if test="${accion != 'ELIMINAR' }"><input type="text" name="email" value="${cliente.email}"/></c:if>				
				</td>
			</tr>
			
		</table>
		
		<input type="button" value="Procesar" id="btnProcesar" />
	</form>
	<script type="text/javascript">
		$("#btnProcesar").click(function(){
			var data = $("#formCliente").serialize();
			$.post("ClienteProcesar",data,function(objJson){
				alert(objJson.texto);
			});
		});
	</script>
</body>
</html>