package com.zensark.javaeedemo;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensark.javaeedemo.entities.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper mapper = new ObjectMapper();
	private EntityManagerFactory emFactory;
	private EntityManager em;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        emFactory = Persistence.createEntityManagerFactory("default");
        em = emFactory.createEntityManager();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	createProduct("Soap", "Dove soap bar", 10);
    	createProduct("Dairy Milk", "Cadbury Dairy Milk", 5);
    	createProduct("Sunflower Oil", "Cooking refined sunflower oil", 50);
    	createProduct("Cookies", "Chocolate chip cookies", 20);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	TypedQuery<Product> query = em.createQuery("from Product p", Product.class);
	 	List<Product> products = query.getResultList();
		
	 	response.setContentType("application/json");
	 	response.getWriter().write(mapper.writeValueAsString(products));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void createProduct(String name, String description, double price) {
		Product soap = new Product();
		soap.setName(name);
		soap.setDescription(description);
		soap.setPrice(price);
		
		// Begin a transaction
		em.getTransaction().begin();
		// Persist the data
		em.persist(soap);
		// Commit the transaction
		em.getTransaction().commit();
	}
	
	
	@Override
	public void destroy() {
		em.close();
		emFactory.close();
		super.destroy();
	}
	
}
