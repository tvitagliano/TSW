
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OffertaDAO {

	public List<Offerta> doRetrieveAll(int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT id, nome, descrizione, prezzoCent FROM offerta LIMIT ?, ?");
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			ArrayList<Offerta> offerte = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Offerta p = new Offerta();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setDescrizione(rs.getString(3));
				p.setPrezzoCent(rs.getLong(4));
				p.setServizi(getServizi(con, p.getId()));
				offerte.add(p);
			}
			return offerte;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Offerta doRetrieveById(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT id, nome, descrizione, prezzoCent FROM offerta WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Offerta p = new Offerta();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setDescrizione(rs.getString(3));
				p.setPrezzoCent(rs.getLong(4));
				p.setServizi(getServizi(con, p.getId()));
				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Offerta> doRetrieveByServizio(int servizio, int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, nome, descrizione, prezzoCent FROM offerta LEFT JOIN offerta_servizio ON id=idofferta WHERE idservizio=? LIMIT ?, ?");
			ps.setInt(1, servizio);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Offerta> offerte = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Offerta p = new Offerta();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setDescrizione(rs.getString(3));
				p.setPrezzoCent(rs.getLong(4));
				p.setServizi(getServizi(con, p.getId()));
				offerte.add(p);
			}
			return offerte;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Offerta> doRetrieveByNomeOrDescrizione(String against, int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, nome, descrizione, prezzoCent FROM offerta WHERE MATCH(nome, descrizione) AGAINST(?) LIMIT ?, ?");
			ps.setString(1, against);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Offerta> offerte = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Offerta p = new Offerta();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setDescrizione(rs.getString(3));
				p.setPrezzoCent(rs.getLong(4));
				p.setServizi(getServizi(con, p.getId()));
				offerte.add(p);
			}
			return offerte;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Offerta> doRetrieveByNome(String against, int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, nome, descrizione, prezzoCent FROM offerta WHERE MATCH(nome) AGAINST(? IN BOOLEAN MODE) LIMIT ?, ?");
			ps.setString(1, against);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Offerta> offerte = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Offerta p = new Offerta();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setDescrizione(rs.getString(3));
				p.setPrezzoCent(rs.getLong(4));
				p.setServizi(getServizi(con, p.getId()));
				offerte.add(p);
			}
			return offerte;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doSave(Offerta offerta) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO offerta (nome, descrizione, prezzoCent) VALUES(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, offerta.getNome());
			ps.setString(2, offerta.getDescrizione());
			ps.setLong(3, offerta.getPrezzoCent());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			offerta.setId(id);

			PreparedStatement psCa = con
					.prepareStatement("INSERT INTO offerta_servizio (idofferta, idservizio) VALUES (?, ?)");
			for (Servizio c : offerta.getServizi()) {
				psCa.setInt(1, id);
				psCa.setInt(2, c.getId());
				psCa.addBatch();
			}
			psCa.executeBatch();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doUpdate(Offerta offerta) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("UPDATE offerta SET nome=?, descrizione=?, prezzoCent=? WHERE id=?");
			ps.setString(1, offerta.getNome());
			ps.setString(2, offerta.getDescrizione());
			ps.setLong(3, offerta.getPrezzoCent());
			ps.setInt(4, offerta.getId());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			}

			if (offerta.getServizi().isEmpty()) {
				PreparedStatement psCaDel = con.prepareStatement("DELETE FROM offerta_servizio WHERE idofferta=?");
				psCaDel.setInt(1, offerta.getId());
				psCaDel.executeUpdate();
			} else {
				PreparedStatement psCaDel = con
						.prepareStatement("DELETE FROM offerta_servizio WHERE idofferta=? AND idservizio NOT IN ("
								+ offerta.getServizi().stream().map(c -> String.valueOf(c.getId()))
										.collect(Collectors.joining(","))
								+ ")");
				psCaDel.setInt(1, offerta.getId());
				psCaDel.executeUpdate();

				PreparedStatement psCa = con.prepareStatement(
						"INSERT IGNORE INTO offerta_servizio (idofferta, idservizio) VALUES (?, ?)");
				for (Servizio c : offerta.getServizi()) {
					psCa.setInt(1, offerta.getId());
					psCa.setInt(2, c.getId());
					psCa.addBatch();
				}
				psCa.executeBatch();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doDelete(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM offerta WHERE id=?");
			ps.setInt(1, id);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static List<Servizio> getServizi(Connection con, int idOfferta) throws SQLException {
		PreparedStatement ps = con.prepareStatement(
				"SELECT id, nome, descrizione FROM servizio LEFT JOIN offerta_servizio ON id=idservizio WHERE idofferta=?");
		ps.setInt(1, idOfferta);
		ArrayList<Servizio> servizi = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Servizio c = new Servizio();
			c.setId(rs.getInt(1));
			c.setNome(rs.getString(2));
			c.setDescrizione(rs.getString(3));
			servizi.add(c);
		}
		return servizi;
	}
}
