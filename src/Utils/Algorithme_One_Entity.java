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

public class Algorithme_One_Entity {
	
	private static final Object key = new Object() {};
	
	public static void launch(JsonElement obj){
		synchronized(key){
			try{

				JsonObject json = obj.getAsJsonObject();
				boolean nonSimilaire = json.get("nonSimilaire").getAsBoolean();
				int idPair = json.get("idPair").getAsInt();
				if(nonSimilaire){
					Pair p = DBService.SELECT_PAIR_BY_ID(idPair);
					DBService.UPDATE_DECREMENT_PAIR_NBRVOTE(p);
				}else{
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
	
					// On récupère la pair à traiter
					Pair pair = DBService.SELECT_PAIR_BY_ID(idPair);
					DBService.UPDATE_INCREMENT_PAIR_NBRVOTE(pair);
					pair.setNbrVote(pair.getNbrVote()+1);

					if(pair.getNbrVote() > 0){
						// On récupère les attributs de la pair
						ArrayList<Attribut> listAttributs = DBService.SELECT_ALL_ATTRIBUT_ONE_ENTITY_FOR_PAIR(idPair);
		
						for (Attribut attribut : listAttributs) {
							pair.addAttribut(attribut);
						}
						DBService.UPDATE_SIMILARITE_PRE_TRAITEMENT(pair);
						
						// On met à jour la note de chaque attributs
						Utils.calculNoteAttribut(pair, attrSim1, attrSim2, attrSim3, attrSim4, attrSim5, val);

						pair.getListAttribut().removeAll(pair.getListAttribut());
			
						ArrayList<Attribut> listAttr = DBService.SELECT_ALL_ATTRIBUT_ONE_ENTITY_FOR_PAIR_ND(pair.getId());
						for (Attribut attribut : listAttr) {
							pair.addAttribut(attribut);
						}
						
						// On calcul la moyenne de la pair en fonctione des nouvelles moyennes d'attributs
						pair.setVal(DBService.INSERT_PAIR_TABLE_SIMILARITE(pair));
						
						// On gï¿½nï¿½re l'enregistrement dans la table de similaritï¿½ pour les nouvelles moyennes d'attributs
						SimilarPair simPPrime = Utils.GenerateSimilarPrime(pair);

						boolean attribut1Valid = false;
						boolean attribut2Valid = false;
						boolean attribut3Valid = false;
						boolean attribut4Valid = false;
						boolean attribut5Valid = false;
						
						if(simPPrime.getIdAttribut1() != -1){
							attribut1Valid = true;
						}
						if(simPPrime.getIdAttribut2() != -1){
							attribut2Valid = true;
						}
						if(simPPrime.getIdAttribut3() != -1){
							attribut3Valid = true;
						}
						if(simPPrime.getIdAttribut4() != -1){
							attribut4Valid = true;
						}
						if(simPPrime.getIdAttribut5() != -1){
							attribut5Valid = true;
						}
						
						if(simPPrime.getMoySimilar() >= 0.8){
							DBService.DELETE_LHS_RHS_TEMP(DBService.SELECT_PAIR_BY_ID(simPPrime.getId()));
							DBService.INSERT_LHS_AND_RHS_TEMP(idPair, DBService.SELECT_ALL_ATTRIBUT_FOR_PAIR(simPPrime.getId()), attribut1Valid, attribut2Valid, attribut3Valid, attribut4Valid, attribut5Valid);
						}
						else{
							DBService.DELETE_LHS_RHS_TEMP(DBService.SELECT_PAIR_BY_ID(simPPrime.getId()));
						}
						//}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
