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
				int idPair = json.get("idPair").getAsInt();
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
				
				// On récupère les attributs de la pair
				ArrayList<Attribut> listAttributs = DBService.SELECT_ALL_ATTRIBUT_ONE_ENTITY_FOR_PAIR(idPair);

				for (Attribut attribut : listAttributs) {
					pair.addAttribut(attribut);
				}
				
				// On met à jour la note de chaque attributs
				int tauxVote = Utils.calculNoteAttribut(pair, attrSim1, attrSim2, attrSim3, attrSim4, attrSim5, val);
				
				
				//if(tauxVote>0){
				
					// On calcul la moyenne de la pair en fonctione des nouvelles moyennes d'attributs
					pair.setVal(DBService.INSERT_PAIR_TABLE_SIMILARITE(pair));
					
					// On gï¿½nï¿½re l'enregistrement dans la table de similaritï¿½ pour les nouvelles moyennes d'attributs
					SimilarPair simPPrime = Utils.GenerateSimilarPrime(pair);
					
					if(simPPrime.getMoySimilar() >= 0.8){
						DBService.INSERT_MATCHING_DEPENDENCIE_ONE_ENTITY(pair);
					}
					else{
						DBService.DELETE_MATCHING_DEPENDENCIE_ONE_ENTITY(pair);
					}
				//}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
