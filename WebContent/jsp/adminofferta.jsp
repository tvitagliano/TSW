<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="operazione" value="${param.rimuovi != null ? 'Rimozione' : (offerta == null ? 'Aggiungi' : 'Modifica')}"/>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="${operazione} offerta"/>
</jsp:include>
	<section>
		<h1>${operazione} offerta</h1>
		<h5>${notifica}</h5>
		<c:if test="${param.rimuovi == null}">
			<form action="AdminOfferta" method="post">
				<input type="hidden" name="id" value="${prodotto.id}">
				<label>Servizi</label>
				<c:forEach items="${servizi}" var="servizio">
					<input type="checkbox" name="servizi" value="${servizio.id}" <c:if test="${offerta.servizi.stream().anyMatch(c -> c.id == servizio.id).orElse(false)}">checked</c:if>><label>${servizio.nome}</label>
				</c:forEach>
				<label>Nome</label>
				<input type="text" name="nome" value="${offerta.nome}">
				<label>Descrizione</label>
				<textarea name="descrizione">${offerta.descrizione}</textarea>
				<label>Prezzo (cent):</label>
				<input type="number" name="prezzoCent" value="${offerta.prezzoCent}">
				<label>Immagine</label>
				<h5>TODO UPLOAD</h5>
				<input type="submit" value="${operazione}">
				<c:if test="${offerta != null}">
					<input type="submit" name="rimuovi" value="Rimuovi">
				</c:if>
			</form>
		</c:if>
	</section>
<%@include file="footer.jsp"%>