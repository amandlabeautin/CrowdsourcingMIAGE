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
							Thread.sleep(1000);
							System.out.println("Check si matching dependencie similaires...");
	
							ArrayList<Pair> listPairOne = DBService.SELECT_ALL_MATCHING_DEPENDENCIE_ONE_ENTITY();
							ArrayList<Pair> listPairTwo = DBService.SELECT_ALL_MATCHING_DEPENDENCIE_REMASTER();
							
							for (Pair pair : listPairOne) {
								for (Pair pair2 : listPairTwo) {
									if(pair.getObj1().equals(pair2.getObj1())){
										if(pair.getObj2().equals(pair2.getObj2())){
											System.out.println("Matching dependencie similaire trouvée");
											DBService.INSERT_MATCHING_DEPENDENCIE(pair);
										}
									}
									else{
										System.out.println("Matching dependencie supprimée");
										DBService.DELETE_MATCHING_DEPENDENCIE(pair);
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

}
