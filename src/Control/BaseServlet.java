
package Control;

import java.io.IOException;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Model.Servizio;
import Model.ServizioDAO;
import Model.Utente;


public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ServizioDAO servizioDAO = new ServizioDAO();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Servizio> servizi = servizioDAO.doRetrieveAll();
		request.setAttribute("servizi", servizi);
		super.service(request, response);
	}

	protected void checkAdmin(HttpServletRequest request) throws ServletException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente == null || !utente.isAdmin()) {
			throw new MyServletException("Utente non autorizzato");
		}
	}
}
