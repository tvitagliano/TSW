<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="${offerta.nome}"/>
</jsp:include>
	<section>

		<grid>
	
			<div col="1/3">
				<img src="img/offerta/${offerta.id}.jpg">
			</div>
		
			<div col="1/3">
				<h3>${offerta.nome}</h3>
				${offerta.descrizione}
			</div>
			
			<div col="1/3">
			
			
				<c:if test="${utente.admin}">
					<form action="AdminProdotto" method="post">
						<input type="hidden" name="id" value="${offerta.id}">
						<input type="submit" value="Modifica">
						<input type="submit" name="rimuovi" value="Rimuovi">
					</form>
				</c:if>
				
				<p>SERVIZI:
				
				<c:forEach items="${offerta.servizi}" var="servizio" varStatus="status">
						<a href="Servizio?id=<c:out value="${servizio.id}"/>"><c:out value="${servizio.nome}" /></a><c:if test="${not status.last}">, </c:if>
					</c:forEach>
					
				</p>
				<h4>PREZZO PER PERSONA: ${offerta.prezzoEuro} &euro;</h4>
				<form action="Carrello" method="post">
					<label>PERSONE:</label>
					<select name="addNum">
					
					
						<c:forEach begin="1" end="4" varStatus="loop">
							<option value="${loop.index}">${loop.index}</option>
						</c:forEach>
						
						
					</select>
					<input type="hidden" name="prodId" value="${offerta.id}">
					<input type="submit" value="Aggiungi al carrello">
				</form>
			</div>
	
		</grid>
	</section>
<%@include file="footer.jsp"%>