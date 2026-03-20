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
	
	private boolean accepted; 	//indica si la oferta ha sido aceptada o no, por defecto es false, cuando el vendedor acepta la oferta se cambia a true
	@OneToMany
	private Sale sale;
	
	public Offer() {
		super();
		accepted=false;
	}
	public Offer(Buyer buyer, float offeredPrice, Sale sale) {
		super();
		this.buyer = buyer;
		this.offeredPrice = offeredPrice;
		this.accepted = false;
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

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
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
	
}
