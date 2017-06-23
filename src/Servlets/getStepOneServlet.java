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

import DataBaseManager.DBService;
import DataBean.Attribut;
import DataBean.Pair;
import DataBean.RandomPair;
import DataBean.SimilarPair;
import Utils.Utils;

/**
 * Servlet implementation class getRandomPairServlet
 */
@WebServlet("/getStepOne")
public class getStepOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public getStepOneServlet() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			response.setStatus(200);
			
			JSONArray resultPair = new JSONArray();
			ArrayList<Pair> listPairs = DBService.SELECT_ALL_PAIR();

			JSONArray resultAttribut = new JSONArray();
			
			for (Pair pair : listPairs) {
				JSONArray resultAttributByPair = new JSONArray();
				ArrayList<Attribut> listAttrByPair = DBService.SELECT_ALL_ATTRIBUT_FOR_PAIR(pair.getId());
				for (Attribut a : listAttrByPair) {
					JSONObject obj = new JSONObject(a);
					resultAttributByPair.put(obj);
				}
				resultAttribut.put(resultAttributByPair);
				JSONObject obj = new JSONObject(pair);
				resultPair.put(obj);
			}
			
			JSONArray resultSimilarPair = new JSONArray();
			ArrayList<SimilarPair> listSimilarPair = DBService.SELECT_ALL_PRETRAITEMENT();

			JSONArray resultAttributApriori = new JSONArray();			
			JSONArray resultAttributOneEntity = new JSONArray();
			
			
			for (SimilarPair pair : listSimilarPair) {
				JSONArray resultAttributAprioriByPair = new JSONArray();
				JSONArray resultAttributOneEntityByPair = new JSONArray();
				
				ArrayList<Attribut> listAttrAprioriByPair = DBService.SELECT_ALL_ATTRIBUT_APRIORI_FOR_PAIR(pair.getId());
				ArrayList<Attribut> listAttrOneEntityByPair = DBService.SELECT_ALL_ATTRIBUT_ONE_ENTITY_FOR_PAIR(pair.getId());
				
				for (Attribut a : listAttrAprioriByPair) {
					JSONObject obj = new JSONObject(a);
					resultAttributAprioriByPair.put(obj);
				}
				
				for (Attribut a : listAttrOneEntityByPair) {
					JSONObject obj = new JSONObject(a);
					resultAttributOneEntityByPair.put(obj);
				}
				resultAttributApriori.put(resultAttributAprioriByPair);
				resultAttributOneEntity.put(resultAttributOneEntityByPair);
			}
			
			JSONArray resultFinal = new JSONArray();
			resultFinal.put(resultPair);
			resultFinal.put(resultAttribut);
			resultFinal.put(resultSimilarPair);
			resultFinal.put(resultAttributApriori);
			resultFinal.put(resultAttributOneEntity);
			
			response.setContentType("application/json");   
			PrintWriter out = response.getWriter();
	        response.setStatus(200);
			out.print(resultFinal.toString());
			out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
