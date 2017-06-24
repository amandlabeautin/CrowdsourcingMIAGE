package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataBaseManager.DBService;
import DataBean.User;

/**
 * Servlet implementation class postSearchUser
 */
@WebServlet("/postSearchUser")
public class postSearchUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Default constructor. 
     */
    public postSearchUser() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //response.addHeader("Access-Control-Allow-Origin", "*");
		JsonParser parser = new JsonParser();
		JsonElement obj = parser.parse(request.getReader());
		JsonObject json = obj.getAsJsonObject();
		
		String loginUser = json.get("login").getAsString();
		String passwordUser = json.get("password").getAsString();
		
		try {
			User user = DBService.SELECT_USER_WITH_LOGIN_AND_PASSWORD(loginUser, passwordUser);
			
			response.setStatus(200);
			JSONObject result = new JSONObject(user);
			
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
