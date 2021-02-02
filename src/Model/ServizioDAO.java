
package Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServizioDAO {

	public List<Servizio> doRetrieveAll() {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT id, nome, descrizione FROM servizio");
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
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doSave(Servizio servizio) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("INSERT INTO servizio (nome, descrizione) VALUES(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, servizio.getNome());
			ps.setString(2, servizio.getDescrizione());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			servizio.setId(rs.getInt(1));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doUpdate(Servizio servizio) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("UPDATE servizio SET nome=?, descrizione=? WHERE id=?");
			ps.setString(1, servizio.getNome());
			ps.setString(2, servizio.getDescrizione());
			ps.setInt(3, servizio.getId());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doDelete(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM servizio WHERE id=?");
			ps.setInt(1, id);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
