package dataAccess;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Seller;
import domain.User;
import domain.Buyer;
import domain.Offer;
import domain.Sale;
import domain.Transaccion;
import exceptions.FileNotUploadedException;
import exceptions.MustBeLaterThanTodayException;
import exceptions.SaleAlreadyExistException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;
    private static final int baseSize = 160;

	private static final String basePath="src/main/resources/images/";



	ConfigXML c=ConfigXML.getInstance();

     public DataAccess()  {
		if (c.isDatabaseInitialized()) {
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();
				System.out.println("File deleted");
			 } else {
				 System.out.println("Operation failed");
				}
		}
		open();
		if  (c.isDatabaseInitialized()) 
			initializeDB();
		System.out.println("DataAccess created => isDatabaseLocal: "+c.isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());

		close();

	}
     
    public DataAccess(EntityManager db) {
    	this.db=db;
    }

	
	
	/**
	 * This method  initializes the database with some products and sellers.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();

		try { 
	       
		    //Create sellers 
			Seller seller1=new Seller("seller1@gmail.com","Aitor Fernandez","aFernandez");
			Seller seller2=new Seller("seller22@gmail.com","Ane Gaztañaga","aGaztanaga");
			Seller seller3=new Seller("seller3@gmail.com","Test Seller","test");

			
			//Create products
			Date today = UtilDate.trim(new Date());
		
			
			seller1.addSale("futbol baloia", "oso polita, gutxi erabilita", 10, 2,  today, null);
			seller1.addSale("salomon mendiko botak", "44 zenbakia, 3 ateraldi",20,  2,  today, null);
			seller1.addSale("samsung 42\" telebista", "berria, erabili gabe", 175, 1,  today, null);


			seller2.addSale("imac 27", "7 urte, dena ondo dabil", 1, 200,today, null);
			seller2.addSale("iphone 17", "oso gutxi erabilita", 2, 400, today, null);
			seller2.addSale("orbea mendiko bizikleta", "29\" 10 urte, mantenua behar du", 3,225, today, null);
			seller2.addSale("polar kilor erlojua", "Vantage M, ondo dago", 3, 30, today, null);

			seller3.addSale("sukaldeko mahaia", "1.8*0.8, 4 aulkiekin. Prezio finkoa", 3,45, today, null);

			
			db.persist(seller1);
			db.persist(seller2);
			db.persist(seller3);

	
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method creates/adds a product to a seller
	 * 
	 * @param title of the product
	 * @param description of the product
	 * @param status 
	 * @param selling price
	 * @param category of a product
	 * @param publicationDate
	 * @return Product
 	 * @throws SaleAlreadyExistException if the same product already exists for the seller
	 */
	public Sale createSale(String title, String description, int status, float price,  Date pubDate, String sellerEmail, File file) throws  FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException {
		

		System.out.println(">> DataAccess: createProduct=> title= "+title+" seller="+sellerEmail);
		try {
		

			if(pubDate.before(UtilDate.trim(new Date()))) {
				throw new MustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.ErrorSaleMustBeLaterThanToday"));
			}
			if (file==null)
				throw new FileNotUploadedException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.ErrorFileNotUploadedException"));

			db.getTransaction().begin();
			
			Seller seller = db.find(Seller.class, sellerEmail);
			if (seller.doesSaleExist(title)) {
				db.getTransaction().commit();
				throw new SaleAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.SaleAlreadyExist"));
			}

			Sale sale = seller.addSale(title, description, status, price, pubDate, file);
			//next instruction can be obviated

			db.persist(seller); 
			db.getTransaction().commit();
			 System.out.println("sale stored "+sale+ " "+seller);

			

			   System.out.println("hasta aqui");

			return sale;
		} catch (NullPointerException e) {
			   e.printStackTrace();
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}
		
		
	}
	
	/**
	 * This method retrieves all the products that contain a desc text in a title
	 * 
	 * @param desc the text to search
	 * @return collection of products that contain desc in a title
	 */
	public List<Sale> getSales(String desc) {
		System.out.println(">> DataAccess: getProducts=> from= "+desc);

		List<Sale> res = new ArrayList<Sale>();	
		TypedQuery<Sale> query = db.createQuery("SELECT s FROM Sale s WHERE s.title LIKE ?1",Sale.class);   
		query.setParameter(1, "%"+desc+"%");
		
		List<Sale> sales = query.getResultList();
	 	 for (Sale sale:sales){
		   res.add(sale);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves the products that contain a desc text in a title and the publicationDate today or before
	 * 
	 * @param desc the text to search
	 * @return collection of products that contain desc in a title
	 */
	public List<Sale> getPublishedSales(String desc, Date pubDate) {
		System.out.println(">> DataAccess: getProducts=> from= "+desc);

		List<Sale> res = new ArrayList<Sale>();	
		TypedQuery<Sale> query = db.createQuery("SELECT s FROM Sale s WHERE s.title LIKE ?1 AND s.pubDate <=?2",Sale.class);   
		query.setParameter(1, "%"+desc+"%");
		query.setParameter(2,pubDate);
		
		List<Sale> sales = query.getResultList();
	 	 for (Sale sale:sales){
	 		 if(!sale.isVendido()) {
	 			 res.add(sale);
	 		 }
		  }
	 	return res;
	}

	public void open(){
		
		String fileName=c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
			  db = emf.createEntityManager();
    	   }
		System.out.println("DataAccess opened => isDatabaseLocal: "+c.isDatabaseLocal());

		
	}

	public BufferedImage getFile(String fileName) {
		File file=new File(basePath+fileName);
		BufferedImage targetImg=null;
		try {
             targetImg = rescale(ImageIO.read(file));
        } catch (IOException ex) {
            //Logger.getLogger(MainAppFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
		return targetImg;

	}
	
	public BufferedImage rescale(BufferedImage originalImage)
    {
		System.out.println("rescale "+originalImage);
        BufferedImage resizedImage = new BufferedImage(baseSize, baseSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, baseSize, baseSize, null);
        g.dispose();
        return resizedImage;
    }
	
	public User getUsuario(String correo) {
		return db.find(User.class, correo);
	}
	
	public void crearUsuario(String correo, String contrasena, String tipo,String nombre) {
		if(tipo.equals("Seller")||tipo.equals("Vendedor")||tipo.equals("Saltzaile")){
			db.getTransaction().begin(); 
			Seller s=new Seller(correo,nombre,contrasena);
			db.persist(s); 
			db.getTransaction().commit(); 
		}else {
			db.getTransaction().begin(); 
			Buyer b=new Buyer(correo,nombre,contrasena);
			db.persist(b); 
			db.getTransaction().commit();
		}				
	}
	
	public void createOferta(Sale s, float oferta,Buyer comprador) {
	    db.getTransaction().begin();
		Sale sale = db.find(Sale.class, s.getSaleNumber());
		sale.addOferta(comprador, oferta);
		db.persist(sale); 
		db.getTransaction().commit();	
	}
	
	public List<Offer> getOffers(Seller se) {
		System.out.println(">> DataAccess: getProducts=> ");
		List<Offer> res = new ArrayList<Offer>();	
		TypedQuery<Offer> query = db.createQuery("SELECT o FROM Offer o JOIN o.sale s WHERE s.seller.email = ?1",Offer.class);   
		query.setParameter(1, se.getEmail());
		
		List<Offer> offers = query.getResultList();
	 	 for (Offer offer:offers){
	 		if(offer.getAccepted()==0) {
	 			 res.add(offer);
	 		}
		  
		  }
	 	return res;
	}
	
	public List<Offer> getOffersComprador(Buyer bu) {
		System.out.println(">> DataAccess: getProducts=> ");
		List<Offer> res = new ArrayList<Offer>();	
		TypedQuery<Offer> query = db.createQuery("SELECT o FROM Offer o JOIN o.sale s",Offer.class);   
		
		List<Offer> offers = query.getResultList();
	 	 for (Offer offer:offers){
	 		 if(offer.getBuyer().getEmail().equals(bu.getEmail())) {
	 			   res.add(offer);
	 		 }
		  }
	 	return res;
	}
	
	public void aceptarOferta(Offer o){	
		db.getTransaction().begin();
		
		//kailai---------------------------------------------------------------------------------------------
	    Sale v = db.find(Sale.class, o.getSale().getSaleNumber()); 
	    User comprador = db.find(User.class, o.getBuyer().getEmail());
	    User vendedor = db.find(User.class, v.getSeller().getEmail());
	    float precioFinal = o.getOfferedPrice();
	    comprador.setSaldo(comprador.getSaldo() - precioFinal);
	    Transaccion pago = new Transaccion(precioFinal, "COBRO", "Pago por: " + v.getTitle(), comprador);
	    db.persist(pago);
	    vendedor.setSaldo(vendedor.getSaldo() + precioFinal);
	    Transaccion ingreso = new Transaccion(precioFinal, "INGRESO", "Venta de: " + v.getTitle(), vendedor);
	    db.persist(ingreso);
	    //fin kailai------------------------------------------------------------------------------------------
	    
	    //Sale s=o.getSale();
	    Sale s = db.find(Sale.class, o.getSale().getSaleNumber());
	    for(Offer of:s.getOfertas()) {
	    	if(of.equals(o)) {
	    		of.setAccepted(1);
	    	}else {
	    		of.setAccepted(-1);
	    	}
	    }
	    s.setVendido(true);
	    db.persist(s);
		db.getTransaction().commit();	
	}
	
	//kailai-----------------------------------------------------------------------------------------------------------------------------
	
	public Transaccion recargarSaldo(String email, float cantidad) {
        db.getTransaction().begin();
        User u = db.find(User.class, email); 
        
        if (u != null) {
            u.setSaldo(u.getSaldo() + cantidad);
            Transaccion t = new Transaccion(cantidad, "RECARGA", "Operación exitosa", u);
            db.persist(t);
            db.getTransaction().commit();
            return t;
        }
        db.getTransaction().commit();
        return null;
    }

    public List<Transaccion> getTransacciones(String email) {
        TypedQuery<Transaccion> query = db.createQuery(
            "SELECT t FROM Transaccion t WHERE t.usuario.email = ?1 ORDER BY t.fecha DESC", 
            Transaccion.class
        );
        query.setParameter(1, email);
        return query.getResultList();
    }
    
    public float getSaldo(String email) {
        User u = db.find(User.class, email);
        if (u != null) {
            return u.getSaldo();
        }
        return 0.0f;
    }

//fin kailai----------------------------------------------------------------------------------------------------------------------------
	
	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}
	
}
