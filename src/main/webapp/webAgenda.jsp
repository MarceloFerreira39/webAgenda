<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="br.com.webagenda.model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)request.getAttribute("contatos");

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="./style.css">
<link rel="icon" href="./imagens/favicon.png">


<title>Web Agenda</title>
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="./novo.html" class="btn1">Novo contato</a>
	<a href="report" class="btn2">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>
		</thead>
	
		<tbody>
			<%for(int i = 0; i < lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td>
						<a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="btn1">Editar</a>
						<a href="javascript: confirmar(<%=lista.get(i).getIdcon() %>)" class="btn2">Excluir</a>
					</td>
				</tr>
			<%} %>
		</tbody>
	</table>
	<script src="./scripts/confirming.js"></script>
</body>
</html>