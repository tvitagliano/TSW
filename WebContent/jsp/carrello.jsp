<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>
	<section>
		<h1>CARRELLO</h1>
		<grid>
		
		
		<c:forEach items="${carrello.offerte}" var="pq">
			<div col="1/3">
				<a href="#"><img src="img/offerte/${pq.offerta.id}.jpg"></a>
			</div>
			<div col="2/3">
				<h3>
					<a href="Offerta?id=${pq.offerta.id}">${pq.offerta.nome}</a>
				</h3>
				<p>${pq.offerta.descrizione}</p>
				<h5>Quantità: ${pq.quantita}, Prezzo unit.: ${pq.offerta.prezzoEuro} &euro;, Prezzo tot.: ${pq.prezzoTotEuro} &euro;</h5>
				<form action="Carrello" method="post">
					<input type="hidden" name="prodId" value="${pq.offerta.id}">
					<input type="hidden" name="setNum" value="0">
					<input type="submit" value="Rimuovi">
				</form>
			</div>
		</c:forEach>
		
		<c:if test="${empty carrello.offerte}">
			<div col="1">Nessun offerta nel carrello</div>
		</c:if>
		
		</grid>
	</section>
	
	<c:if test="${not empty carrello.offerte}">
	<section>
		<grid>
		<div col="1/3">
			<h2>Totale: ${carrello.prezzoTotEuro} &euro;</h2>
		</div>
	
		<div col="1/3">
			<form action="Pagamento" method="post">
				<input  type="submit" value="Completa acquisto">
			</form>
		</div>
		</grid>
	</section>
	</c:if>
	
	
<%@include file="footer.jsp"%>