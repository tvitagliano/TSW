 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Home"/>
</jsp:include>
	  <%@ page import="java.util.List,  Model.Offerta " %>
	<h1>Offerte</h1>
	
	<head>
 	
  <title>SLIDESWOW</title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <% 
		List <Offerta> offerte =  (List <Offerta>) request.getAttribute("offerte");
	%>
  <script>
  
    $(document).ready(function(){     
     slideshow(5,1000,5000);     
    });
    

    function slideshow(n,t1,t2){

     var x = 0;
     var txt = 0;
     var stato = 1;
     $("#foto2").animate({"opacity":"0"},0);
     $("#foto2").animate({"left":"100%"},0);
     $("#foto2").animate({"opacity":"1"},0);      
     $("#foto1").attr("src",fotografie(x,n));
     $("#slide p").html(scritte(txt,n));
     x++;
     $("#foto2").attr("src",fotografie(x,n));

     var intervallo = setInterval(function(){
       x++;txt++;
       if(x>n-1){x=0;}
       if(txt>n-1){txt=0;}
       stato = cambiaSlide(x,n,stato,t1,txt);
     },t2);
    }

    function cambiaSlide(x,n,stato,t,txt){
     if(stato==1){
      var elemento1 = "#foto1";
      var elemento2 = "#foto2";
     }else{
      var elemento1 = "#foto2";
      var elemento2 = "#foto1";
     }
     $(elemento2).animate({"left":"0px"},t);
     $(elemento1).animate({"left":"-100%"},t,function(){
      $("#slide p").html(scritte(txt,n));
      $(elemento1).animate({"opacity":"0"},0);
      $(elemento1).animate({"left":"100%"},0);
      $(elemento1).animate({"opacity":"1"},0,function(){
        $(elemento1).attr("src",fotografie(x,n));        
      });
     });
     
     if(stato==1){stato=0;}else{stato=1;}
     return stato;
    }
    

    function fotografie(x,n){
     var foto = new Array(n);
 
     foto[0] = "img/offerte/1.jpg";
     foto[1] = "img/offerte/2.jpg";
     foto[2] = "img/offerte/3.jpg";
     foto[3] = "img/offerte/4.jpg";
     foto[4] = "img/offerte/5.jpg";

     
     return foto[x];
    }

    function scritte(x,n){
     var testi = new Array(n);
   
     testi[0] = "Singapore";
     testi[1] = "Cuba";
     testi[2] = "Miami";
     testi[3] = "Mauritus";
     testi[4] = "Bankok";
     
     return testi[x];
    }
    
  </script>
  <style>
  
   #slide{
   
    overflow:hidden;
  
    width:auto;height:300px;
    position:relative;
    top:20px;left:20px;   
    background:white;
    box-shadow:0px 0px 5px black;
   }
   
   #slide p{

    font-family:Arial, sans-serif;
    font-size:150%;
    text-align:center;
    position:relative;
    width:200px;height:40px;
    top:200px;left:40px;
    padding:10px;
    background:rgba(20,20,20,0.5);
    color:white;

    opacity:0;
   }
   
 
   #slide img{
    width:100%;height:100%;
    position:absolute;
    top:0px;left:0px;
   }
   #slide:hover p{
    opacity:1;
   }
  </style>
  
  
</head>
<body>

<div id="slide">
 <img id="foto1" src="" />
 <img id="foto2" src="" />
 <p></p>
</div>

</body>

	<section>
		<h2>Offerte in evidenza</h2>

		<grid>
		<% 
			for ( int i = 0; i < offerte.size(); i++){
				Offerta offerta = offerte.get(i);
				%>
			<div col="1/3">
				<h3>
					<a href="Offerta?id=<%= offerta.getId() %>"><%= offerta.getNome() %></a>
				</h3>
				<a href="Offerta?id=<%= offerta.getId() %>"><img src="img/offerte/<%= offerta.getId() %>.jpg"></a>
				<h4>Prezzo: <%= offerta.getPrezzoEuro() %> &euro;</h4>
			</div>
			<%
			}
			%>
		</grid>
	</section>
	
	
	

<%@include file="footer.jsp"%>