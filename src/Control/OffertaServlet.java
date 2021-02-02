
package Control;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Offerta;
import Model.OffertaDAO;



@WebServlet("/Offerta")
public class OffertaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final OffertaDAO offertaDAO = new OffertaDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Offerta offerta = offertaDAO.doRetrieveById(id);
		if (offerta == null) {
			throw new MyServletException("Offerta non trovata.");
		}
		request.setAttribute("offerta", offerta);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/offerta.jsp");
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
