package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.CorsFilter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import DataBaseManager.DBService;
import DataBean.Attribut;
import DataBean.Pair;
import DataBean.SimilarPair;
import Utils.Algorithme_One_Entity;
import Utils.Apriori;
import Utils.Utils;

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

		Algorithme_One_Entity.launch(request);
		Algorithme_One_Entity.launch(request);
		
	}

}
