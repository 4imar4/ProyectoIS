package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Transaccion implements Serializable {
    
    @Id 
    @GeneratedValue
    private Integer idTransaccion;
    private Date fecha;
    private float cantidad;
    private String tipo; // RECARGA o COBRO
    private String descripcion;
    private float saldoAnterior;
    private float nuevoSaldo;
    
    @ManyToOne
    private User usuario;

    public Transaccion(float cantidad, String tipo, String descripcion, User usuario, float saldoAnterior, float nuevoSaldo) {
        this.fecha = new Date();
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.saldoAnterior=saldoAnterior;
        this.nuevoSaldo=nuevoSaldo;
    }

	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public float getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(float saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public float getNuevoSaldo() {
		return nuevoSaldo;
	}

	public void setNuevoSaldo(float nuevoSaldo) {
		this.nuevoSaldo = nuevoSaldo;
	}

    
}