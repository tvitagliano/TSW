
package Control;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Carrello;
import Model.Carrello.OffertaQuantita;
import Model.OffertaDAO;


@WebServlet("/Carrello")
public class CarrelloServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final OffertaDAO offertaDAO = new OffertaDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		if (carrello == null) {
			carrello = new Carrello();
			session.setAttribute("carrello", carrello);
		}

		String prodIdStr = request.getParameter("prodId");
		if (prodIdStr != null) {
			int prodId = Integer.parseInt(prodIdStr);

			String addNumStr = request.getParameter("addNum");
			if (addNumStr != null) {
				int addNum = Integer.parseInt(addNumStr);

				OffertaQuantita prodQuant = carrello.get(prodId);
				if (prodQuant != null) {
					prodQuant.setQuantita(prodQuant.getQuantita() + addNum);
				} else {
					carrello.put(offertaDAO.doRetrieveById(prodId), addNum);
				}
			} else {
				String setNumStr = request.getParameter("setNum");
				if (setNumStr != null) {
					int setNum = Integer.parseInt(setNumStr);
					if (setNum <= 0) {
						carrello.remove(prodId);
					} else {
						OffertaQuantita prodQuant = carrello.get(prodId);
						if (prodQuant != null) {
							prodQuant.setQuantita(setNum);
						} else {
							carrello.put(offertaDAO.doRetrieveById(prodId), setNum);
						}
					}
				}
			}
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/carrello.jsp");
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
