
package Control;

import java.io.IOException;


import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Model.Offerta;
import Model.OffertaDAO;
import Model.Servizio;




@WebServlet("/Servizio")
public class ServizioServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final OffertaDAO offertaDAO = new OffertaDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Servizio> servizi = (List<Servizio>) request.getAttribute("servizi");
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("servizio", servizi.stream().filter(c -> c.getId() == id).findAny().get());

		List<Offerta> offerte = offertaDAO.doRetrieveByServizio(id, 0, 10);
		request.setAttribute("offerte", offerte);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/servizio.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
