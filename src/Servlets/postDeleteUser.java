package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import DataBaseManager.DBService;
/**
 * Servlet implementation class postUser
 */
@WebServlet("/postDeleteUser")
public class postDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public postDeleteUser() {
    	
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
		JsonObject json = obj.getAsJsonObject();

		int idUser = json.get("idUser").getAsInt();

		DBService.DELETE_USER_TABLE_USER_WITH_ID(idUser);
	}

}
