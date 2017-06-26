package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataBaseManager.DBService;
import DataBean.Attribut;
import DataBean.LHS_RHS;
import DataBean.Pair;
import DataBean.RandomPair;
import DataBean.SimilarPair;
import Utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
			final GsonBuilder builder = new GsonBuilder();
			final Gson gson = builder.create();
			
			JsonArray resultPair = new JsonArray();
			ArrayList<Pair> listPairs = DBService.SELECT_ALL_PAIR();

			JsonArray resultAttribut = new JsonArray();
			
			for (Pair pair : listPairs) {
				JsonArray resultAttributByPair = new JsonArray();
				ArrayList<Attribut> listAttrByPair = DBService.SELECT_ALL_ATTRIBUT_FOR_PAIR(pair.getId());
				for (Attribut a : listAttrByPair) {
					JsonObject obj = new JsonObject();
					obj.add("JsonAttribut", gson.toJsonTree(a));
					resultAttributByPair.add(obj);
				}
				resultAttribut.add(resultAttributByPair);
				JsonObject obj = new JsonObject();
				obj.add("JsonPair", gson.toJsonTree(pair));
				resultPair.add(obj);
			}
			
			JsonArray resultSimilarPair = new JsonArray();
			ArrayList<SimilarPair> listSimilarPair = DBService.SELECT_ALL_PRETRAITEMENT();

			JsonArray resultAttributApriori = new JsonArray();			
			JsonArray resultAttributOneEntity = new JsonArray();
			
			
			for (SimilarPair pair : listSimilarPair) {
				JsonArray resultAttributAprioriByPair = new JsonArray();
				JsonArray resultAttributOneEntityByPair = new JsonArray();
				
				ArrayList<Attribut> listAttrAprioriByPair = DBService.SELECT_ALL_ATTRIBUT_APRIORI_FOR_PAIR_ND(pair.getId());
				ArrayList<Attribut> listAttrOneEntityByPair = DBService.SELECT_ALL_ATTRIBUT_ONE_ENTITY_FOR_PAIR_ND(pair.getId());
				
				for (Attribut a : listAttrAprioriByPair) {
					JsonObject obj = new JsonObject();
					obj.add("JsonAttributApriori", gson.toJsonTree(a));
					resultAttributAprioriByPair.add(obj);
				}
				
				for (Attribut a : listAttrOneEntityByPair) {
					JsonObject obj = new JsonObject();
					obj.add("JsonAttributOneEntity", gson.toJsonTree(a));
					resultAttributOneEntityByPair.add(obj);
				}
				resultAttributApriori.add(resultAttributAprioriByPair);
				resultAttributOneEntity.add(resultAttributOneEntityByPair);

				JsonObject obj = new JsonObject();
				obj.add("JsonSimilarPair", gson.toJsonTree(pair));
				resultSimilarPair.add(obj);
			}

			JsonArray resultSimilarRAp = new JsonArray();
			JsonArray resultSimilarROe = new JsonArray();
			ArrayList<SimilarPair> listSimilarR = DBService.SELECT_ALL_SIMILAR_R();
			ArrayList<SimilarPair> listSimilarRRemaster = DBService.SELECT_ALL_SIMILAR_R_REMASTER();
			
			for (SimilarPair similarPair : listSimilarR) {
				JsonObject obj = new JsonObject();
				obj.add("JsonSimilariteR", gson.toJsonTree(similarPair));
				resultSimilarROe.add(obj);

			}

			for (SimilarPair similarPair : listSimilarRRemaster) {
				JsonObject obj = new JsonObject();
				obj.add("JsonSimilariteRRemaster", gson.toJsonTree(similarPair));
				resultSimilarRAp.add(obj);
			}

			JsonArray resultSimilarRPrimeAp = new JsonArray();
			JsonArray resultSimilarRPrimeOe = new JsonArray();
			ArrayList<SimilarPair> listSimilarRPrime = DBService.SELECT_ALL_SIMILAR_R_PRIME();
			ArrayList<SimilarPair> listSimilarRPrimeRemaster = DBService.SELECT_ALL_SIMILAR_R_PRIME_REMASTER();
			
			for (SimilarPair similarPair : listSimilarRPrime) {
				JsonObject obj = new JsonObject();
				obj.add("JsonSimilariteRPrime", gson.toJsonTree(similarPair));
				resultSimilarRPrimeOe.add(obj);

			}

			for (SimilarPair similarPair : listSimilarRPrimeRemaster) {
				JsonObject obj = new JsonObject();
				obj.add("JsonSimilariteRPrimeRemaster", gson.toJsonTree(similarPair));
				resultSimilarRPrimeAp.add(obj);
			}

			JsonArray resultLHSRHSOe = new JsonArray();
			JsonArray resultLHSRHSAp = new JsonArray();
			ArrayList<LHS_RHS> listLHSRHSOe = DBService.SELECT_ALL_LHS_RHS_ONE_ENTITY();
			ArrayList<LHS_RHS> listLHSRHSAp = DBService.SELECT_ALL_LHS_RHS_APRIORI();
			
			for (LHS_RHS lhs_rhs : listLHSRHSOe) {
				JsonObject obj = new JsonObject();
				obj.add("JsonLHS_RHSOe", gson.toJsonTree(lhs_rhs));
				resultLHSRHSOe.add(obj);

			}

			for (LHS_RHS lhs_rhs : listLHSRHSAp) {
				JsonObject obj = new JsonObject();
				obj.add("JsonLHS_RHSAp", gson.toJsonTree(lhs_rhs));
				resultLHSRHSAp.add(obj);
			}

			JsonArray resultMD = new JsonArray();
			ArrayList<Pair> listMD = DBService.SELECT_ALL_MATCHING_DEPENDENCIE();
			
			for (Pair pair : listMD) {
				JsonObject obj = new JsonObject();
				obj.add("JsonMD", gson.toJsonTree(pair));
				resultMD.add(obj);

			}
			
			JsonArray resultFinal = new JsonArray();
			resultFinal.add(resultPair);
			resultFinal.add(resultAttribut);
			resultFinal.add(resultSimilarPair);
			resultFinal.add(resultAttributApriori);
			resultFinal.add(resultAttributOneEntity);
			resultFinal.add(resultSimilarRAp);
			resultFinal.add(resultSimilarROe);
			resultFinal.add(resultSimilarRPrimeOe);
			resultFinal.add(resultSimilarRPrimeAp);
			resultFinal.add(resultLHSRHSOe);
			resultFinal.add(resultLHSRHSAp);
			resultFinal.add(resultMD);
			
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
