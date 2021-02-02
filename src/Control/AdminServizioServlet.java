
package Control;

import java.io.IOException;


import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Model.Servizio;
import Model.ServizioDAO;


@WebServlet("/AdminServizio")
public class AdminServizioServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final ServizioDAO servizioDAO = new ServizioDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		checkAdmin(request);

		String idstr = request.getParameter("id");
		if (idstr != null) {
			@SuppressWarnings("unchecked")
			List<Servizio> servizi = ((List<Servizio>) request.getAttribute("servizi"));

			Servizio servizio = null;
			if (!idstr.isEmpty()) {
				int id = Integer.parseInt(idstr);
				servizio = servizi.stream().filter(c -> c.getId() == id).findAny().get();
			}

			if (request.getParameter("rimuovi") != null) {
				servizioDAO.doDelete(servizio.getId());
				servizi.remove(servizio);
				request.setAttribute("notifica", "Servizio rimosso con successo.");
			} else {
				String nome = request.getParameter("nome");
				String descrizione = request.getParameter("descrizione");
				if (nome != null && descrizione != null) { // modifica/aggiunta categoria
					if (servizio == null) { // aggiunta nuova categoria
						servizio = new Servizio();
						servizio.setNome(nome);
						servizio.setDescrizione(descrizione);
						servizioDAO.doSave(servizio);
						servizi.add(servizio);
						request.setAttribute("notifica", "Servizio aggiunto con successo.");
					} else { // modifica categoria esistente
						servizio.setNome(nome);
						servizio.setDescrizione(descrizione);
						servizioDAO.doUpdate(servizio);
						request.setAttribute("notifica", "Servizio modificato con successo.");
					}
				}
				request.setAttribute("servizio", servizio);
			}
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/adminservizio.jsp");
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
