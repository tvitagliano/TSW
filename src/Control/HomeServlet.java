
package Control;

import java.io.IOException;



import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Offerta;
import Model.OffertaDAO;







@WebServlet("")
public class HomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final OffertaDAO offertaDAO = new OffertaDAO();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Offerta> offerte = offertaDAO.doRetrieveAll(0, 10);
		request.setAttribute("offerte", offerte);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/index.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
