package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import DataBaseManager.DBService;
import Utils.Algorithme_Apriori_Remaster;
import Utils.Algorithme_One_Entity;

/**
 * Servlet implementation class getRandomPairServlet
 */
@WebServlet("/postPairServlet")
public class postPairServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public postPairServlet() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //response.addHeader("Access-Control-Allow-Origin", "*");

		JsonParser parser = new JsonParser();
		JsonElement obj = parser.parse(request.getReader());
		
		Algorithme_One_Entity.launch(obj);
		Algorithme_Apriori_Remaster.launch(obj);
		
	}

}
