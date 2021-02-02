
package Model;

import java.util.List;


public class Offerta {
	private int id;
	private String nome;
	private String descrizione;
	private long prezzoCent;
	private List<Servizio> servizi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public long getPrezzoCent() {
		return prezzoCent;
	}

	public void setPrezzoCent(long prezzoCent) {
		this.prezzoCent = prezzoCent;
	}

	public String getPrezzoEuro() {
		return String.format("%.2f", prezzoCent / 100.);
	}

	public List<Servizio> getServizi() {
		return servizi;
	}

	public void setServizi (List<Servizio> servizi) {
		this.servizi = servizi;
	}

	@Override
	public String toString() {
		return "Offerta [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", prezzoCent=" + prezzoCent
				+ ", servizi=" + servizi + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((servizi == null) ? 0 : servizi.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (int) (prezzoCent ^ (prezzoCent >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offerta other = (Offerta) obj;
		if (servizi == null) {
			if (other.servizi != null)
				return false;
		} else if (!servizi.equals(other.servizi))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (prezzoCent != other.prezzoCent)
			return false;
		return true;
	}

}
