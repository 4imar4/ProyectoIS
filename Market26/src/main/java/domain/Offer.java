package domain;

import java.util.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Offer implements Serializable{  //Implementamos Serializable para poder guardar objetos de esta clase en la base de datos

	private static final long serialVersionUID = 1L; //Añadimos un serialVersionUID para evitar problemas de serialización
	
	@Id
	@GeneratedValue
	private Integer offerId;
	
	private Buyer buyer; 	//email del comprador, para evitar problemas de serialización, ya que un objeto de tipo User podría tener una lista de ofertas, lo que podría generar un bucle infinito al intentar serializarlo
	private float offeredPrice; //precio ofrecido por el comprador
	
	private int accepted; 	//indica si la oferta ha sido aceptada 1 o no -1 o si esta pendiente 0
	@OneToMany
	private Sale sale;
	
	public Offer() {
		super();
		accepted=0;
	}
	public Offer(Buyer buyer, float offeredPrice, Sale sale) {
		super();
		this.buyer = buyer;
		this.offeredPrice = offeredPrice;
		this.accepted = 0;
		this.sale = sale;
	}



	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}
	
	public float getOfferedPrice() {
		return offeredPrice;
	}

	public void setOfferedPrice(float offeredPrice) {
		this.offeredPrice = offeredPrice;
	}

	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public Buyer getBuyer() {
		return buyer;
	}



	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}



	public Sale getSale() {
		return sale;
	}



	public void setSale(Sale sale) {
		this.sale = sale;
	}



	@Override
	public String toString() {
		return "ID de la oferta (" + offerId + "), sale (" + sale.toString() + "), buyer (" + buyer.toString()
				+ "), offeredPrice(" + offeredPrice + ") accepted=" + accepted;
	}
	@Override
	public int hashCode() {
		return Objects.hash(offerId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		return Objects.equals(offerId, other.offerId);
	}
	
}
