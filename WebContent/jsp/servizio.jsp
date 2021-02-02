
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="${servizio.nome}"/>
</jsp:include>
	<section>
		<c:if test="${!utente.admin}">
			<h1>${servizio.nome}</h1>
		</c:if>
		<c:if test="${utente.admin}">
			<form action="AdminServizio" method="post">
				<h1>${servizio.nome}
					<input type="hidden" name="id" value="${servizio.id}">
					<input type="submit" value="Modifica">
					<input type="submit" name="rimuovi" value="Rimuovi">
				</h1>
			</form>
		</c:if>
		<p>${servizio.descrizione}</p>
	
		<grid>
			<c:forEach items="${offerte}" var="offerta">
				<div col="1/3">
					<a href="Offerta?id=${offerta.id}"><img src="img/offerte/${offerta.id}.jpg"></a>
				</div>
				<div col="2/3">
					<h3>
						<a href="Offerta?id=${offerta.id}">${offerta.nome}</a>
					</h3>
					<p>${offerta.descrizione}</p>
					<h4>Prezzo: ${offerta.prezzoEuro} &euro;</h4>
				</div>
			</c:forEach>
			<c:if test="${empty offerte}">
				<div col="1">Nessun offerta nel servizio.</div>
			</c:if>
	</section>
<%@include file="footer.jsp"%>