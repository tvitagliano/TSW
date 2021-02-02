<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="operazione" value="${param.rimuovi != null ? 'Rimozione' : (servizio == null ? 'Aggiungi' : 'Modifica')}"/>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="${operazione} servizio"/>
</jsp:include>

	<section>
		<h1>${operazione} servizio</h1>
		<h5>${notifica}</h5>
		<c:if test="${param.rimuovi == null}">
			<form action="AdminServizio" method="post">
				<input type="hidden" name="id" value="${servizio.id}">
				<label>Nome</label>
				<input type="text" name="nome" value="${servizio.nome}"><br>
				<label>Descrizione</label>
				<textarea name="descrizione">${servizio.descrizione}</textarea>
				<input type="submit" value="${operazione}">
				<c:if test="${servizio != null}">
					<input type="submit" name="rimuovi" value="Rimuovi">
				</c:if>
			</form>
		</c:if>
	</section>
	
<%@include file="footer.jsp"%>