package domain;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Buyer extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 

	public Buyer() {
		super();
	}

	public Buyer(String email, String name,String contrasena) {
		super(email, name,contrasena);
	}
	
	
	
	public String toString(){
		return super.toString();
	}
	
	
	
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buyer other = (Buyer) obj;
		if (super.getEmail() != other.getEmail())
			return false;
		return true;
	}

	
}
