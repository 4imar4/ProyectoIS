package businessLogic;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import dataAccess.DataAccess;
import domain.Buyer;
import domain.Offer;
import domain.Sale;
import domain.Seller;
import domain.User;
import domain.Transaccion;
import exceptions.FileNotUploadedException;
import exceptions.MustBeLaterThanTodayException;
import exceptions.SaleAlreadyExistException;

import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;


/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	 private static final int baseSize = 160;

		private static final String basePath="src/main/resources/images/";
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		dbManager=new DataAccess();		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		dbManager=da;		
	}
    

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
	public Sale createSale(String title, String description,int status, float price, Date pubDate, String sellerEmail, File file) throws  FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException {
		dbManager.open();
		Sale product=dbManager.createSale(title, description, status, price, pubDate, sellerEmail, file);		
		dbManager.close();
		return product;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Sale> getSales(String desc){
		dbManager.open();
		List<Sale>  rides=dbManager.getSales(desc);
		dbManager.close();
		return rides;
	}
	
	/**
	    * {@inheritDoc}
	    */
		@WebMethod 
		public List<Sale> getPublishedSales(String desc, Date pubDate) {
			dbManager.open();
			List<Sale>  rides=dbManager.getPublishedSales(desc,pubDate);
			dbManager.close();
			return rides;
		}
	/**
	    * {@inheritDoc}
	    */
	@WebMethod public BufferedImage getFile(String fileName) {
		return dbManager.getFile(fileName);
	}

    
	public void close() {
		DataAccess dB4oManager=new DataAccess();
		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    /**
	 * {@inheritDoc}
	 */
    @WebMethod public Image downloadImage(String imageName) {
        File image = new File(basePath+imageName);
        try {
            return ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
	 * {@inheritDoc}
	 */
    public int hacerLogin(String correo, String contrasena) {
    	dbManager.open();
    	User us=dbManager.getUsuario(correo);
		dbManager.close();
		if(us==null) {
			return -1;
		}else {
			if(us.getContrasena().equals(contrasena)) {
				return 1;
			}else {
				return 0;
			}
		}
    }
    public User getUsuario(String correo) {
    	dbManager.open();
    	User us=dbManager.getUsuario(correo);
		dbManager.close();
		return us;
    }
    public boolean registrarse(String correo, String contrasena, String tipo,String nombre) {
    	dbManager.open();
    	User us=dbManager.getUsuario(correo);
		if(us==null) {
			dbManager.crearUsuario(correo,contrasena,tipo,nombre);
			dbManager.close();
			return true;
		}
		dbManager.close();
		return false;
    }
    
    public boolean anadirCompra(String ofertTexto,Sale s,Buyer comprador) throws NumberFormatException{
    	float oferta=Float.parseFloat(ofertTexto);
    	if(oferta>=0.0) {
    		dbManager.open();
    		dbManager.createOferta(s,oferta,comprador);
    		dbManager.close();
    		return true;
    	}else {
    		return false;
    	}
    }
    
	public List<Offer> getOffers(User usuario){
		dbManager.open();
		List<Offer>  rides=dbManager.getOffers((Seller) usuario);
		dbManager.close();
		return rides;
	}
	
	public List<Offer> getOffersComprador(User usuario){
		dbManager.open();
		List<Offer>  rides=dbManager.getOffersComprador((Buyer) usuario);
		dbManager.close();
		return rides;
	}
	
	@Override
	public Transaccion recargarSaldo(String email, float cantidad) {
		dbManager.open();
		Transaccion recarga = dbManager.recargarSaldo(email, cantidad);
		dbManager.close();
		return recarga;
	}

	@Override
	public List<Transaccion> getTransacciones(String email) {
		dbManager.open();
		List<Transaccion> transacciones = dbManager.getTransacciones(email);
		dbManager.close();
		return transacciones;
	}

	@Override
	public float getSaldo(String email) {
		dbManager.open();
		float saldo = dbManager.getSaldo(email);
		dbManager.close();
		return saldo;
	}

}

