package Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Carrello;


@WebServlet("/Pagamento")
public class PagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		System.out.println("il carrello è:");
		System.out.println(carrello);
		
		carrello.svuota();

		
		String message;
		message = "pagamento concluso";
		request.getSession().setAttribute("message",message);

		System.out.println(carrello);
		
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/pagamento.jsp");
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
