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

		JsonParser parser = new JsonParser();
		JsonElement obj = parser.parse(request.getReader());
		JsonObject json = obj.getAsJsonObject();

		int idPair = json.get("idPair").getAsInt();
		JsonObject Attribut1 = json.get("idAttribut1").getAsJsonObject();
		boolean attrSim1 = Attribut1.get("selected").getAsBoolean();
		JsonObject Attribut2 = json.get("idAttribut2").getAsJsonObject();
		boolean attrSim2 = Attribut2.get("selected").getAsBoolean();
		JsonObject Attribut3 = json.get("idAttribut3").getAsJsonObject();
		boolean attrSim3 = Attribut3.get("selected").getAsBoolean();
		JsonObject Attribut4 = json.get("idAttribut4").getAsJsonObject();
		boolean attrSim4 = Attribut4.get("selected").getAsBoolean();
		JsonObject Attribut5 = json.get("idAttribut5").getAsJsonObject();
		boolean attrSim5 = Attribut5.get("selected").getAsBoolean();
		double val = json.get("Val").getAsDouble();
		
		// On récupère la pair à traiter
		Pair pair = DBService.SELECT_PAIR_BY_ID(idPair);
		
		// On récupère les attributs de la pair
		ArrayList<Attribut> listAttributs = DBService.SELECT_ALL_ATTRIBUT_FOR_PAIR(idPair);
		for (Attribut attribut : listAttributs) {
			attribut.setNbrVote(attribut.getNbrVote() + 1);
			pair.addAttribut(attribut);
		}
		
		Pair pairApriori = DBService.SELECT_PAIR_BY_ID(idPair);
		ArrayList<Attribut> listAttributsApriori = DBService.SELECT_ALL_ATTRIBUT_FOR_PAIR(idPair);
		for (Attribut attribut : listAttributsApriori) {
			pairApriori.addAttribut(attribut);
		}
		pairApriori.setVal(json.get("Val").getAsDouble());		
		
		ArrayList<Attribut> newList = new ArrayList<>();
		for (Attribut a : pairApriori.getListAttribut()) {
			DBService.INSERT_ATTRIBUT_R_APRIORI(a, pairApriori.getId());
			newList.add(a);
		}
		pairApriori.getListAttribut().removeAll(pairApriori.getListAttribut());
		for (Attribut attribut : newList) {
			pairApriori.addAttribut(attribut);
		}
		
		// On met à jour la note de chaque attributs
		Utils.calculNoteAttribut(pair, attrSim1, attrSim2, attrSim3, attrSim4, attrSim5, val);

		// On calcul la moyenne de la pair en fonctione des nouvelles moyennes d'attributs
		pair.setVal(DBService.INSERT_PAIR_TABLE_SIMILARITE(pair));
		
		// On génère l'enregistrement dans la table de similarité pour les nouvelles moyennes d'attributs
		SimilarPair simPPrime = Utils.GenerateSimilarPrime(pair);

		Utils.calculNoteAttributApriori(pairApriori, attrSim1, attrSim2, attrSim3, attrSim4, attrSim5, val);
		
		DBService.INSERT_PAIR_TABLE_SIMILARITE_APRIORI(pairApriori);
		Utils.GenerateSimilarPrimeApriori(pairApriori);
		
		if(simPPrime.getMoySimilar() >= 0.8){
			DBService.INSERT_MATCHING_DEPENDENCIE_TEMP_ONE(pair);
		}
		else{
			DBService.DELETE_MATCHING_DEPENDENCIE_TEMP_ONE(pair);
		}

		try {
			// ICI ON APPEL APRIORI !!
			// On récupère l'ensemble des éléments de R' pour apriori trié par ID (chaque ID dans une arrayList)
			ArrayList<ArrayList<SimilarPair>> listPairPrime = DBService.SELECT_TABLE_SIMILAR_R_PRIME_APRIORI();
			
			// On boucle sur l'ensemble arraylist d'ID
			for (ArrayList<SimilarPair> listPairForId : listPairPrime) {
				// On récupère un élément pour avoir l'id de la pair d'origine
				if(!listPairForId.isEmpty()){
					SimilarPair simP = DBService.SELECT_PAIR_TABLE_SIMILARITE_BY_ID(listPairForId.get(0).getIdPair());
					
					/*
					int nbrVote = simP.getNbrVote();
					int nbrElement = listPairForId.size();
					*/
					
					// L'ensemble des lists pour chaque attributs et la valeur moyenne de chacun
					ArrayList<Attribut> listAttr1 = new ArrayList<>();
					double valMoyAttribut1 = 0;
					ArrayList<Attribut> listAttr2 = new ArrayList<>();
					double valMoyAttribut2 = 0;
					ArrayList<Attribut> listAttr3 = new ArrayList<>();
					double valMoyAttribut3 = 0;
					ArrayList<Attribut> listAttr4 = new ArrayList<>();
					double valMoyAttribut4 = 0;
					ArrayList<Attribut> listAttr5 = new ArrayList<>();
					double valMoyAttribut5 = 0;
					
					// On boucle pour chaque attribut
					for (int i = 0; i < 5 ; i++) {
						// Pour chacun des enregistrements pour cet même pair
						for (SimilarPair similarPair : listPairForId) {
							switch (i) {
							case 0:
								if(similarPair.getIdAttribut1() != -1){
									Attribut attr1 = DBService.SELECT_ATTRIBUT_APRIORI(similarPair.getIdAttribut1());
									listAttr1.add(attr1);
								}
								break;
							case 1:
								if(similarPair.getIdAttribut2() != -1){
									Attribut attr2 = DBService.SELECT_ATTRIBUT_APRIORI(similarPair.getIdAttribut2());
									listAttr2.add(attr2);
								}
								break;
							case 2:
								if(similarPair.getIdAttribut3() != -1){
									Attribut attr3= DBService.SELECT_ATTRIBUT_APRIORI(similarPair.getIdAttribut3());
									listAttr3.add(attr3);
								}
								break;
							case 3:
								if(similarPair.getIdAttribut4() != -1){
									Attribut attr4 = DBService.SELECT_ATTRIBUT_APRIORI(similarPair.getIdAttribut4());
									listAttr4.add(attr4);
								}
								break;
							case 4:
								if(similarPair.getIdAttribut5() != -1){
									Attribut attr5 = DBService.SELECT_ATTRIBUT_APRIORI(similarPair.getIdAttribut5());
									listAttr5.add(attr5);
								}
								break;
							default:
								break;
							}
						}
						
						// On calcul la valeur moyenne des notes pour chaque attributs depuis la liste récupéré de chacun des éléments
						switch (i) {
						case 0:
							if(!listAttr1.isEmpty()){
								for (Attribut attribut1 : listAttr1) {
									valMoyAttribut1 = valMoyAttribut1 + attribut1.getVal();
								}
								valMoyAttribut1 = valMoyAttribut1 / listAttr1.size();
							}
							break;
						case 1:
							if(!listAttr2.isEmpty()){
								for (Attribut attribut2 : listAttr2) {
									valMoyAttribut2 = valMoyAttribut2 + attribut2.getVal();
								}
								valMoyAttribut2 = valMoyAttribut2 / listAttr2.size();
							}
							break;
						case 2:
							if(!listAttr3.isEmpty()){
								for (Attribut attribut3 : listAttr3) {
									valMoyAttribut3 = valMoyAttribut3 + attribut3.getVal();
								}
								valMoyAttribut3 = valMoyAttribut3 / listAttr3.size();
							}
							break;
						case 3:
							if(!listAttr4.isEmpty()){
								for (Attribut attribut4 : listAttr4) {
									valMoyAttribut4 = valMoyAttribut4 + attribut4.getVal();
								}
								valMoyAttribut4 = valMoyAttribut4 / listAttr4.size();
							}
							break;
						case 4:
							if(!listAttr5.isEmpty()){
								for (Attribut attribut5 : listAttr5) {
									valMoyAttribut5 = valMoyAttribut5 + attribut5.getVal();
								}
								valMoyAttribut5 = valMoyAttribut5 / listAttr5.size();
							}
							break;
						default:
							break;
						}
					}
					
					// On calcul la moyenne général de la pair en fonction des moyennes de chaque attribut et du nombre de vote pour chacun
					double moyGeneralPair = ((valMoyAttribut1 * listAttr1.size()) + (valMoyAttribut2 * listAttr2.size()) + (valMoyAttribut3 * listAttr3.size()) + (valMoyAttribut4 * listAttr4.size()) + (valMoyAttribut5 * listAttr5.size())) / (listAttr1.size() + listAttr2.size() + listAttr3.size() + listAttr4.size() + listAttr5.size());
					
					// Si la moyenne est supérieur ou égal au seuil alors on créer une matching dépendencie temporaire
					if(moyGeneralPair >= 0.8){
						DBService.INSERT_MATCHING_DEPENDENCIE_TEMP_TWO(DBService.SELECT_PAIR_BY_ID(simP.getIdPair()));
					}
					else{
						DBService.DELETE_MATCHING_DEPENDENCIE_TEMP_TWO(DBService.SELECT_PAIR_BY_ID(simP.getIdPair()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
