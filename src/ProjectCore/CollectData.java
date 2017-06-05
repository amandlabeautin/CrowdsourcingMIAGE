package ProjectCore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import DataBaseManager.DBService;
import DataBean.Attribut;
import DataBean.Pair;
import Utils.Jaro;
import Utils.Utils;

public class CollectData {

	static ArrayList<Pair> tablePreTraitement = new ArrayList<>();
	static ArrayList<Pair> tableSimilariteRPrime = new ArrayList<>();
	
	public static void collectData(String path1, String path2) {
		try {

			File file1 = new File(path1);
			File file2 = new File(path2);
			//File file1 = new File("/Files/Dataset1.txt");appHome = 
			//File file2 = new File("/Files/Dataset2.txt");
			BufferedReader br1 = new BufferedReader(new FileReader(file1));
			BufferedReader br2;
			String line1 = "";
			String line2 = "";
						
			double val;
			br2 = new BufferedReader(new FileReader(file2));

			while ((line1 = br1.readLine()) != null) {
				while ((line2 = br2.readLine()) != null) {
					Pair p = new Pair(line1, line2);
					
					line1 =line1.replaceAll("\"", "");
					String[] tab1 = line1.split(",");

					line2 =line2.replaceAll("\"", "");
					String[] tab2 = line2.split(",");

					int length = tab1.length;
					if (tab2.length < length)
						length = tab2.length;
					String[] listNom = {"RestaurantName", "RestaurantAdress", "RestaurantVille", "RestaurantPhone", "RestaurantType", };
					for (int i = 0 ; i < length-1 ; i++){
						String elem1 = tab1[i].trim();
						String elem2 = tab2[i].trim();
						/*val = Utils.minDistance(elem1, elem2);
						double mult = 0.1;
						val = (1 - (val * mult));
						val = Double.parseDouble(new DecimalFormat("#.#").format(val).replace(',', '.'));
						if(val < 0)
							val = 0;
						Jaro jar = new Jaro();
						double valJaro = jar.similarity(elem1, elem2);
						if(valJaro < val){
							val = valJaro;
						}*/
						Attribut a = new Attribut(p, listNom[i], elem1, elem2, 0, 2, 0);
						p.addAttribut(a);
					}
					DBService.INSERT_PAIR(p);
					DBService.INSERT_PAIR_TABLE_PRE_TRAITEMENT(p);
					//DBService.INSERT_PAIR_TABLE_SIMILARITE(p);
				}
			}
			br2.close();
			br1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
