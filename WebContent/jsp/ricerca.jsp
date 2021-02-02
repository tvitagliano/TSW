<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="${param.q}"/>
</jsp:include>
	<section>
		<grid>
			<c:forEach items="${offerte}" var="offreta">
				<div col="1/3">
					<a href="#"><img src="img/offerte/${offerta.id}.jpg"></a>
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
				<div col="1">Nessun offerta trovata.</div>
			</c:if>
	</section>
<%@include file="footer.jsp"%>