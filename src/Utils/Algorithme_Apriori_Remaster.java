package Utils;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataBaseManager.DBService;
import DataBean.Attribut;
import DataBean.Pair;
import DataBean.SimilarPair;

public class Algorithme_Apriori_Remaster {

	private static final Object key = new Object() {};
	
	public static void launch(JsonElement obj){
		synchronized(key){
			try {
				JsonObject json = obj.getAsJsonObject();
				boolean nonSimilaire = json.get("nonSimilaire").getAsBoolean();
				int idPair = json.get("idPair").getAsInt();
				if(!nonSimilaire){
						
					JsonObject Attribut1 = json.get("attribut1").getAsJsonObject();
					boolean attrSim1 = Attribut1.get("selected").getAsBoolean();
					JsonObject Attribut2 = json.get("attribut2").getAsJsonObject();
					boolean attrSim2 = Attribut2.get("selected").getAsBoolean();
					JsonObject Attribut3 = json.get("attribut3").getAsJsonObject();
					boolean attrSim3 = Attribut3.get("selected").getAsBoolean();
					JsonObject Attribut4 = json.get("attribut4").getAsJsonObject();
					boolean attrSim4 = Attribut4.get("selected").getAsBoolean();
					JsonObject Attribut5 = json.get("attribut5").getAsJsonObject();
					boolean attrSim5 = Attribut5.get("selected").getAsBoolean();
					double val = json.get("valUser").getAsDouble();
					int idUser = json.get("user").getAsInt();
	
					
					
					// On récupère la pair à traiter
					Pair pair = DBService.SELECT_PAIR_BY_ID(idPair);

					if(pair.getNbrVote() > 0){
						// On récupère les attributs de la pair
						ArrayList<Attribut> listAttributs = DBService.SELECT_ALL_ATTRIBUT_FOR_PAIR(idPair);
						for (Attribut attribut : listAttributs) {
							pair.addAttribut(attribut);
						}
						
						Utils.calculNoteAttributApriori(pair, attrSim1, attrSim2, attrSim3, attrSim4, attrSim5, val);
			
						DBService.INSERT_PAIR_TABLE_SIMILARITE_APRIORI(pair, idUser);
						Utils.GenerateSimilarPrimeApriori(pair);
						
						// On récupère l'ensemble des éléments de R' pour apriori trié par ID (chaque ID dans une arrayList)
						ArrayList<ArrayList<SimilarPair>> listPairPrime = DBService.SELECT_TABLE_SIMILAR_R_PRIME_APRIORI();
						
						// On boucle sur l'ensemble arraylist d'ID
						for (ArrayList<SimilarPair> listPairForId : listPairPrime) {
							// On récupère un élément pour avoir l'id de la pair d'origine
							if(!listPairForId.isEmpty()){
								
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
								
								int nbrVotePair = listPairForId.size();
								
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
		
								boolean attribut1Valid = false;
								boolean attribut2Valid = false;
								boolean attribut3Valid = false;
								boolean attribut4Valid = false;
								boolean attribut5Valid = false;
		
								if(listAttr1.size() > (nbrVotePair / 2)){
									attribut1Valid = true;
								}
								if(listAttr2.size() > (nbrVotePair / 2)){
									attribut2Valid = true;
								}
								if(listAttr3.size() > (nbrVotePair / 2)){
									attribut3Valid = true;
								}
								if(listAttr4.size() > (nbrVotePair / 2)){
									attribut4Valid = true;
								}
								if(listAttr5.size() > (nbrVotePair / 2)){
									attribut5Valid = true;
								}
								
								// Si la moyenne est supérieur ou égal au seuil alors on créer une matching dépendencie temporaire
								if(moyGeneralPair >= 0.8){
									DBService.DELETE_LHS_RHS_TEMP_APRIORI(DBService.SELECT_PAIR_BY_ID(listPairForId.get(0).getIdPair()));
									DBService.INSERT_LHS_AND_RHS_TEMP_APRIORI(idPair, DBService.SELECT_ALL_ATTRIBUT_FOR_PAIR(listPairForId.get(0).getIdPair()), attribut1Valid, attribut2Valid, attribut3Valid, attribut4Valid, attribut5Valid);
								}
								else{
									DBService.DELETE_LHS_RHS_TEMP_APRIORI(DBService.SELECT_PAIR_BY_ID(listPairForId.get(0).getIdPair()));
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
