
package Control;

import java.io.IOException;


import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Offerta;
import Model.OffertaDAO;
import Model.Servizio;
import Model.Utente;


@WebServlet("/AdminOfferta")
public class AdminOffertaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final OffertaDAO offertaDAO = new OffertaDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente == null || !utente.isAdmin()) {
			throw new MyServletException("Utente non autorizzato");
		}

		String idstr = request.getParameter("id");
		if (idstr != null) {
			if (request.getParameter("rimuovi") != null) {
				offertaDAO.doDelete(Integer.parseInt(idstr));
				request.setAttribute("notifica", "Offerta rimossa con successo.");
			} else {
				Offerta offerta;
				String nome = request.getParameter("nome");
				String descrizione = request.getParameter("descrizione");
				String prezzoCent = request.getParameter("prezzoCent");
				if (nome != null && descrizione != null && prezzoCent != null) {
					// modifica/aggiunta prodotto
					offerta = new Offerta();
					offerta.setNome(nome);
					offerta.setDescrizione(descrizione);
					offerta.setPrezzoCent(Long.parseLong(prezzoCent));

					String[] servizi= request.getParameterValues("servizi");
					offerta.setServizi(servizi != null ? Arrays.stream(servizi).map(id -> {
						Servizio c = new Servizio();
						c.setId(Integer.parseInt(id));
						return c;
					}).collect(Collectors.toList()) : Collections.emptyList());

					if (idstr.isEmpty()) { // aggiunta nuovo prodotto
						offertaDAO.doSave(offerta);
						request.setAttribute("notifica", "Offerta aggiunta con successo.");
					} else { // modifica prodotto esistente
						offerta.setId(Integer.parseInt(idstr));
						offertaDAO.doUpdate(offerta);
						request.setAttribute("notifica", "Offerta modificata con successo.");
					}
				} else {
					int id = Integer.parseInt(idstr);
					offerta = offertaDAO.doRetrieveById(id);
				}
				request.setAttribute("offerta", offerta);
			}
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/adminofferta.jsp");
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
