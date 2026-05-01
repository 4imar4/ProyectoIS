package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Mensaje implements Serializable {
    @Id 
    private Integer id;
    
    @ManyToOne
    private User emisor;
    
    @ManyToOne
    private User destinatario; 
    
    private String asunto; 
    private String cuerpo; 
    private Date fechaEnvio; 

    public Mensaje() {} 

    public Mensaje(User emisor, User receptor, String asunto, String cuerpo) {
        this.emisor = emisor;
        this.destinatario = receptor;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.fechaEnvio = new Date();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getEmisor() {
		return emisor;
	}

	public void setEmisor(User emisor) {
		this.emisor = emisor;
	}

	public User getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(User receptor) {
		this.destinatario = receptor;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

    
}