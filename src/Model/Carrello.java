
package Model;

import java.util.Collection;
import java.util.LinkedHashMap;



public class Carrello {
	public static class OffertaQuantita {
		private Offerta offerta;
		private int quantita;

		private OffertaQuantita(Offerta offerta, int quantita) {
			this.offerta = offerta;
			this.quantita = quantita;
		}

		public int getQuantita() {
			return quantita;
		}

		public void setQuantita(int quantita) {
			this.quantita = quantita;
		}

		public Offerta getOfferta() {
			return offerta;
		}

		public long getPrezzoTotCent() {
			return quantita * offerta.getPrezzoCent();
		}

		public String getPrezzoTotEuro() {
			return String.format("%.2f", quantita * offerta.getPrezzoCent() / 100.);
		}
	}

	private LinkedHashMap<Integer, OffertaQuantita> offerte = new LinkedHashMap<>();

	public Collection<OffertaQuantita> getProdotti() {
		return  offerte.values();
	}

	public OffertaQuantita get(int prodId) {
		return  offerte.get(prodId);
	}

	public void put(Offerta offerta, int quantita) {
		 offerte.put(offerta.getId(), new OffertaQuantita(offerta, quantita));
	}

	public OffertaQuantita remove(int prodId) {
		return  offerte.remove(prodId);
	}

	public long getPrezzoTotCent() {
		return  offerte.values().stream().mapToLong(p -> p.getPrezzoTotCent()).sum();
	}

	public String getPrezzoTotEuro() {
		return String.format("%.2f", getPrezzoTotCent() / 100.);
	}

	@Override
	public String toString() {
		return "Carrello [offerte=" +  offerte + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (( offerte == null) ? 0 :  offerte.hashCode());
		return result;
	}
	
	public void svuota() {
		if(getProdotti() != null) {
			 offerte.clear();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrello other = (Carrello) obj;
		if ( offerte == null) {
			if (other. offerte!= null)
				return false;
		} else if (! offerte.equals(other. offerte))
			return false;
		return true;
	}

}
