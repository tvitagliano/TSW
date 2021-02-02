
package Control;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Utente;
import Model.UtenteDAO;


@WebServlet("/AdminUtenti")
public class AdminUtentiServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final UtenteDAO utenteDAO = new UtenteDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAdmin(request);

		List<Utente> utenti = utenteDAO.doRetrieveAll(0, 10);
		request.setAttribute("utenti", utenti);

		
		
		
		String idstr = request.getParameter("id");
		if (idstr != null) {
			request.getAttribute("utenti");
		
		Utente utente = null;
		if (!idstr.isEmpty()) {
			int id = Integer.parseInt(idstr);
			utente = utenti.stream().filter(c -> c.getId() == id).findAny().get();
		}

		if (request.getParameter("rimuovi") != null) {
			utenteDAO.doDelete(utente.getId());
			utenti.remove(utente);
			request.setAttribute("notifica", "utente rimosso con successo.");
		} else {
			String nome = request.getParameter("nome");
			if (nome != null) { // modifica/aggiunto utente
				if (utente == null) { // aggiunto nuovo utente
					utente = new Utente();
					utente.setNome(nome);
					utenteDAO.doSave(utente);
					utenti.add(utente);
					request.setAttribute("notifica", "utente aggiunto con successo.");
				} else { // modifica categoria esistente
					utente.setNome(nome);
					utenteDAO.doUpdate(utente);
					request.setAttribute("notifica", "utente modificato con successo.");
				}
			}
			request.setAttribute("utente", utente);
		
			}
		}

		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/adminutenti.jsp");
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
