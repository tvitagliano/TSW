<%@page import="Model.Servizio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
	 <%@ page import="java.util.List,  Model.Servizio, Model.Utente " %>
<html>
<head>
 <% 
		List <Servizio> servizi =  (List <Servizio>) request.getAttribute("servizi");
 		Utente utenti = (Utente) request.getAttribute("utente");
	%>
<title>MALTOUR- ${param.pageTitle}</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link href="bare.min.css" rel="stylesheet"><!-- http://www.barecss.com -->
<script src="ricerca.js"></script>
</head>
<body>
	<nav>
		<label>
			<input type="checkbox">
			<header>
				<a href="."><img id="logo"src="img/logo.png">Maltour</a>
			</header>
			<ul>
				<li>
					<form action="Ricerca" method="get">
						<input type="text" name="q" list="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)" value="<c:out value="${param.q}" />">
						<datalist id="ricerca-datalist"></datalist>
					</form>
				</li>
				<li>
					<a href=".">HOME</a>
				</li>
				<li><a>SERVIZI</a>
					<menu>
					<% 
						for(int i = 0; i< servizi.size() ; i++){
						Servizio servizio =  servizi.get(i);
						 %>
						 <menuitem>
							<a href="Servizio?id=<%=servizio.getId() %>">
							<%= servizio.getNome() %></a></menuitem>
		
					<%
						}
					%>
			
					</menu>
				</li>
				<li><a href="Carrello"><img id="carrello"src="img/carrello.png"></a></li>
				<li>
					<c:choose>
						<c:when test="${utente == null}">
							<a><img id="omino"src="img/omino.png"></a>
							<menu>
								<menuitem>
									<card>
										<form action="Login" method="post">
											<input type="text" name="username" placeholder="Username"><br>
											<input type="password" name="password" placeholder="Password"><br>
											<input type="submit" value="Login">
										</form>
									</card>
								</menuitem>
								<menuitem><a href="RegistrazioneForm">Registrazione</a></menuitem>
							</menu>
						</c:when>
						<c:otherwise>
							<a>${utente.admin ? 'Admin' : 'Account'}</a>
							<menu>
								<c:if test="${utente.admin}">
									<menuitem><a href="AdminServizio">Aggiungi Servizio</a></menuitem>
									<menuitem><a href="AdminOfferta">Aggiungi Offerta</a></menuitem>
									<menuitem><a href="AdminUtenti">Utenti</a></menuitem>
									<hr style="margin:0px;">
								</c:if>
								${utente.nome}
								<menuitem>
									<card>
										<form action="Logout">
											<input type="submit" value="Logout">
										</form>
									</card>
								</menuitem>
							</menu>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</label>
	</nav>