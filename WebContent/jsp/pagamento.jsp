<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
   
<!DOCTYPE html>
<html>
<head>

<link href="bare.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Pagamento completato</title>
</head>
<body>

<p>PAGAMENTO</p>

<%if(session.getAttribute("message")!= null){
 String message = (String) session.getAttribute("message");
	%>
	
	<%=message%>
	
	<%
	}
	%>

	<a att href="Carrello">RITORNA</a>




<footer>
		<grid>
		<div col="1/2">
			<p>
				MALTOUR <br><br>

				Indirizzo : Trav. P.Falciatore, 1/8<br>
				80047 S.Giuseppe Ves. (NA)<br>
				Tel.: +39 081 528 04 18<br>
				Tel.: +39 081 528 07 37<br>
				Email: info@maltour.it<br>
			</p>
		</div>

		<div col="1/2" txt="r">
			<a href="#"><img src="img/logo.png"></a>
			<a att href="#">...</a>
		</div>
		</grid>
	</footer>

</body>
</html>
