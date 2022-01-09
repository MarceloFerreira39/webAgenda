<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="./style.css">
<link rel="icon" href="./imagens/favicon.png">
<title>Contatos</title>
</head>
<body>
	<h1>Editar contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="box3" readonly
				value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="box1" 
				value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="box2" 
				value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="box1" 
				value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="btn1" onclick="validar()">
	</form>
	<script src="./scripts/validador.js"></script>
</body>
</html>