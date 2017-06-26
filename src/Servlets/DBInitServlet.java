package Servlets;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataBaseManager.DBConnectManager;
import DataBaseManager.DBService;
import DataBean.Attribut;
import DataBean.LHS_RHS;
import DataBean.Pair;
import ProjectCore.CollectData;

@SuppressWarnings("serial")
public class DBInitServlet extends  HttpServlet {
	
	 public void init() throws ServletException
	    {
			System.out.println("----------");
			System.out.println("---------- Server Initialization started ----------");
			System.out.println("----------");
			
	  		DBConnectManager.InitConnexion();
			

			System.out.println("----------");
			System.out.println("---------- Server Connection established ----------");
			System.out.println("----------");
			
			
			DBService.INIT_DB();
			
			System.out.println("----------");
			System.out.println("---------- Database cleaned ----------");
			System.out.println("----------");
			
			String PathFile1 = getServletContext().getRealPath("Files/Dataset1.txt");
			String PathFile2 = getServletContext().getRealPath("Files/Dataset2.txt");
					
			CollectData.collectData(PathFile1, PathFile2);
			
			System.out.println("----------");
			System.out.println("---------- Database generated ----------");
			System.out.println("----------");
			
			System.out.println("----------");
			System.out.println("---------- Server Initialization successfully ----------");
			System.out.println("----------");
			
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						while(true){
							System.out.println("Je dors...");
							Thread.sleep(10000);
							System.out.println("Check si matching dependencie similaires...");

							ArrayList<Integer> listIdAp = DBService.SELECT_IDPAIR_LHS_RHS_APRIORI();
							ArrayList<Integer> listIdOe = DBService.SELECT_IDPAIR_LHS_RHS_ONE_ENTITY();

							if(!listIdAp.isEmpty() && !listIdOe.isEmpty()){
								ArrayList<ArrayList<LHS_RHS>> list_LHS_RHS_Apriori = new ArrayList<>();
								ArrayList<ArrayList<LHS_RHS>> list_LHS_RHS_OneEntity = new ArrayList<>();
	
								for (Integer integer : listIdAp) {
									ArrayList<LHS_RHS> list_LHS_RHS_AprioriById = DBService.SELECT_ALL_LHS_RHS_APRIORI(integer);
									list_LHS_RHS_Apriori.add(list_LHS_RHS_AprioriById);
								}
	
								for (Integer integer : listIdOe) {
									ArrayList<LHS_RHS> list_LHS_RHS_OneEntityById = DBService.SELECT_ALL_LHS_RHS_ONE_ENTITY(integer);
									list_LHS_RHS_OneEntity.add(list_LHS_RHS_OneEntityById);
								}
															
								ArrayList<ArrayList<doublon>> listValide = new ArrayList<>();
								
								for (ArrayList<LHS_RHS> list_lhs_rhsAp : list_LHS_RHS_Apriori) {
									for (ArrayList<LHS_RHS> list_lhs_rhsOe : list_LHS_RHS_OneEntity) {
										if(list_lhs_rhsOe.get(0).getIdPair() == list_lhs_rhsAp.get(0).getIdPair()){
											for (LHS_RHS lhs_rhsAp : list_lhs_rhsAp) {
												for (LHS_RHS lhs_rhsOe : list_lhs_rhsOe) {
													ArrayList<doublon> temp = new ArrayList<>();
	
													String[] tabLHSAp = lhs_rhsAp.getLHS().split(",");
													String[] tabLHSOe = lhs_rhsOe.getLHS().split(",");
													for (String elem1 : tabLHSAp) {
														for (String elem2 : tabLHSOe) {
															if(elem1.equals(elem2)){
																temp.add(new doublon(elem1, lhs_rhsOe.getIdPair()));
																listValide.add(temp);
															}
														}
													}
												}
											}
										}
									}
								}
								
								if(!listValide.isEmpty()){
									for (ArrayList<doublon> arrayById : listValide) {
										ArrayList<Attribut> listAttr = new ArrayList<>();
										int idPair = arrayById.get(0).idPair;
										DBService.DELETE_MATCHING_DEPENDENCIE(idPair);
										for (doublon doublon2 : arrayById) {
											Attribut attr = DBService.SELECT_ATTRIBUT_FOR_PAIR_BY_NAME(idPair, doublon2.elem);
											listAttr.add(attr);
										}
										Pair p = new Pair();
										p.setId(idPair);
										String lhs = "";
										String rhs = "";
										for (Attribut attribut : listAttr) {
											lhs = lhs + " " + attribut.getElem1();
											rhs = rhs + " " + attribut.getElem2();
										}
										p.setObj1(lhs);
										p.setObj2(rhs);
										DBService.INSERT_MATCHING_DEPENDENCIE(p);
									}
								}
							}
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			
			thread.start();
	    }
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	class doublon {
		String elem;
		int idPair;
		
		public doublon(String elem, int idPair){
			this.elem = elem;
			this.idPair = idPair;
		}
	}
}
