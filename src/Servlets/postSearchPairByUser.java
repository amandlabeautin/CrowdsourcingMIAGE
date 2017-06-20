package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataBaseManager.DBService;
import DataBean.Attribut;

@WebServlet("/postSearchPairByUser")
public class postSearchPairByUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Default constructor. 
     */
    public postSearchPairByUser() {
    	
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //response.addHeader("Access-Control-Allow-Origin", "*");

		JsonParser parser = new JsonParser();
		JsonElement obj = parser.parse(request.getReader());
		JsonObject json = obj.getAsJsonObject();
		
		int idUser = json.get("idUser").getAsInt();
		try {
			ArrayList<Attribut> attributs = DBService.SELECT_PAIR_BY_USER(idUser);
			JSONArray result = new JSONArray();
			response.setStatus(200);
			
			for (Attribut attribut : attributs) {
				 JSONObject obj1 = new JSONObject(attribut);
				 result.put(obj1);
			 };
			response.setContentType("application/json");   
			PrintWriter out = response.getWriter();
	        response.setStatus(200);
			out.print(result.toString());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
