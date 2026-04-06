package businessLogic;

import java.io.File;
import java.util.Date;
import java.util.List;

import domain.Buyer;
import domain.Offer;
import domain.Sale;
import domain.User;
import domain.Transaccion;
import exceptions.FileNotUploadedException;
import exceptions.MustBeLaterThanTodayException;
import exceptions.SaleAlreadyExistException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.awt.image.BufferedImage;
import java.awt.Image;

import gui.*;
/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

	/**
	 * This method creates/adds a product to a seller
	 * 
	 * @param title of the product
	 * @param description of the product
	 * @param status 
	 * @param selling price
	 * @param category of a product
	 * @param publicationDate
	 * @return Sale
	 */
   @WebMethod
	public Sale createSale(String title, String description, int status, float price, Date pubDate, String sellerEmail, File file) throws  FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException;
	
	
	/**
	 * This method retrieves the products that contain desc
	 * 
	 * @param desc the text to search
	 * @return collection of sales that contain desc 
	 */
	@WebMethod public List<Sale> getSales(String desc);
	
	/**
	 * 	 * This method retrieves the products that contain a desc text in a title and the publicationDate today or before
	 * 
	 * @param desc the text to search
	 * @param pubDate the date  of the publication date
	 * @return collection of sales that contain desc and published before pubDate
	 */
	@WebMethod public List<Sale> getPublishedSales(String desc, Date pubDate);

	
	/**
	 * This method calls the data access to initialize the database with some sellers and products.
	 * It is only invoked  when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
		
	@WebMethod public Image downloadImage(String imageName);
	/**
	 * 
	 * @param usuario correo electronico
	 * @param contrasena contraseña
	 * @return   si no hay usuario con ese correo return -1, si la contraseña es incorrecta return 0, si ha salido todo bien return 1
	 */
	public int hacerLogin(String usuario, String contrasena);
	public User getUsuario(String correo);
	public boolean registrarse(String correo, String contrasena, String tipo,String nombre);
	public boolean anadirCompra(String ofertTexto,Sale s,Buyer comprador) throws NumberFormatException, Exception;
	public List<Offer> getOffers(User usuario, String desc);
	public List<Offer> getOffersComprador(User usuario, String desc);
	public Transaccion recargarSaldo(String email, float cantidad);
	public List<Transaccion> getTransacciones(String email);
	public float getSaldo(String email);
	public void aceptarOferta(Offer o);
}
