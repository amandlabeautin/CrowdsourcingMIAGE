/**
 * 
 */
 
$( document ).ready(function() {	 
	$.ajax({
		url : 'http://127.0.0.1:8080/ProjetPPD/getStepOne',
		success : function(obj) {
			Object.keys(obj).forEach(function(k) {
		    	   for(var ind in obj[k]) {
						if(Object.keys(obj[k][ind]) == "JsonPair"){
							for(var vals in obj[k][ind]){
								console.log(Object.keys(obj[k][ind][vals]));
				    	        $("#data_pair").append("<tr><td>"+ obj[k][ind][vals].id +"</td><td>"+ obj[k][ind][vals].obj1 +"</td><td>"+ obj[k][ind][vals].obj2 +"</td><td>"+ obj[k][ind][vals].nbrVote +"</td></tr>");
							}
						} else if(Object.keys(obj[k][ind]) == "JsonSimilarPair"){
							for(var vals in obj[k][ind]){
				    	        $("#data_simPair").append("<tr><td>"+ obj[k][ind][vals].idPair +"</td><td>"+ obj[k][ind][vals].idAttribut1 +"</td><td>"+ obj[k][ind][vals].idAttribut2 +"</td><td>"+ obj[k][ind][vals].idAttribut3 +"</td><td>"+ obj[k][ind][vals].idAttribut4 +"</td><td>"+ obj[k][ind][vals].idAttribut5 +"</td><td>"+ obj[k][ind][vals].moySimilar +"</td></tr>");
							}
						} else if(Object.keys(obj[k][ind]) == "JsonSimilariteR"){
							for(var vals in obj[k][ind]){
				    	        $("#data_similarite_r_oe").append("<tr><td>"+ obj[k][ind][vals].idPair +"</td><td>"+ obj[k][ind][vals].idAttribut1 +"</td><td>"+ obj[k][ind][vals].idAttribut2 +"</td><td>"+ obj[k][ind][vals].idAttribut3 +"</td><td>"+ obj[k][ind][vals].idAttribut4 +"</td><td>"+ obj[k][ind][vals].idAttribut5 +"</td><td>"+ obj[k][ind][vals].moySimilar +"</td><td>"+ obj[k][ind][vals].nbrVote +"</td></tr>");
							}
						} else if(Object.keys(obj[k][ind]) == "JsonSimilariteRRemaster"){
							for(var vals in obj[k][ind]){
				    	        $("#data_similarite_r_apriori").append("<tr><td>"+ obj[k][ind][vals].idPair +"</td><td>"+ obj[k][ind][vals].idAttribut1 +"</td><td>"+ obj[k][ind][vals].idAttribut2 +"</td><td>"+ obj[k][ind][vals].idAttribut3 +"</td><td>"+ obj[k][ind][vals].idAttribut4 +"</td><td>"+ obj[k][ind][vals].idAttribut5 +"</td><td>"+ obj[k][ind][vals].moySimilar +"</td></tr>");
							}
						} else if(Object.keys(obj[k][ind]) == "JsonSimilariteRPrime"){
							for(var vals in obj[k][ind]){
				    	        $("#data_similarite_r_prime_oe").append("<tr><td>"+ obj[k][ind][vals].idPair +"</td><td>"+ obj[k][ind][vals].idAttribut1 +"</td><td>"+ obj[k][ind][vals].idAttribut2 +"</td><td>"+ obj[k][ind][vals].idAttribut3 +"</td><td>"+ obj[k][ind][vals].idAttribut4 +"</td><td>"+ obj[k][ind][vals].idAttribut5 +"</td><td>"+ obj[k][ind][vals].moySimilar +"</td></tr>");
							}
						} else if(Object.keys(obj[k][ind]) == "JsonSimilariteRPrimeRemaster"){
							for(var vals in obj[k][ind]){
				    	        $("#data_similarite_r_prime_apriori").append("<tr><td>"+ obj[k][ind][vals].idPair +"</td><td>"+ obj[k][ind][vals].idAttribut1 +"</td><td>"+ obj[k][ind][vals].idAttribut2 +"</td><td>"+ obj[k][ind][vals].idAttribut3 +"</td><td>"+ obj[k][ind][vals].idAttribut4 +"</td><td>"+ obj[k][ind][vals].idAttribut5 +"</td><td>"+ obj[k][ind][vals].moySimilar +"</td></tr>");
							}
						} else if(Object.keys(obj[k][ind]) == "JsonLHS_RHSOe"){
							for(var vals in obj[k][ind]){
				    	        $("#data_lhs_rhs_oe").append("<tr><td>"+ obj[k][ind][vals].IdPair +"</td><td>"+ obj[k][ind][vals].LHS +"</td><td>"+ obj[k][ind][vals].RHS +"</td></tr>");
							}
						} else if(Object.keys(obj[k][ind]) == "JsonLHS_RHSAp"){
							for(var vals in obj[k][ind]){
				    	        $("#data_lhs_rhs_apriori").append("<tr><td>"+ obj[k][ind][vals].IdPair +"</td><td>"+ obj[k][ind][vals].LHS +"</td><td>"+ obj[k][ind][vals].RHS +"</td></tr>");
							}
						} else if(Object.keys(obj[k][ind]) == "JsonMD"){
							for(var vals in obj[k][ind]){
				    	        $("#data_md").append("<tr><td>"+ obj[k][ind][vals].id +"</td><td>"+ obj[k][ind][vals].obj1 +"</td><td>"+ obj[k][ind][vals].obj2 +"</td></tr>");
							}
						} else{
							for(var vals in obj[k][ind]){
								if(Object.keys(obj[k][ind][vals]) == "JsonAttribut"){
									for(var subVal in obj[k][ind][vals]){
										$("#data_attribut").append("<tr><td>"+ obj[k][ind][vals][subVal].id +"</td><td>"+ obj[k][ind][vals][subVal].pairId +"</td><td>"+ obj[k][ind][vals][subVal].nomAttribut +"</td><td>"+ obj[k][ind][vals][subVal].elem1 +"</td><td>"+ obj[k][ind][vals][subVal].elem2 +"</td><td>"+ obj[k][ind][vals][subVal].val +"</td><td>"+ obj[k][ind][vals][subVal].nbrVote +"</td></tr>");
									}
								} else if(Object.keys(obj[k][ind][vals]) == "JsonAttributApriori"){
									for(var subVal in obj[k][ind][vals]){
										console.log(Object.values(obj[k][ind][vals][subVal]));
										$("#data_attribut_apriori").append("<tr><td>"+ obj[k][ind][vals][subVal].id +"</td><td>"+ obj[k][ind][vals][subVal].pairId +"</td><td>"+ obj[k][ind][vals][subVal].nomAttribut +"</td><td>"+ obj[k][ind][vals][subVal].elem1 +"</td><td>"+ obj[k][ind][vals][subVal].elem2 +"</td><td>"+ obj[k][ind][vals][subVal].val +"</td><td>"+ obj[k][ind][vals][subVal].nbrVote +"</td></tr>");
									}
								} else if(Object.keys(obj[k][ind][vals]) == "JsonAttributOneEntity"){
									for(var subVal in obj[k][ind][vals]){
										$("#data_attribut_one_entity").append("<tr><td>"+ obj[k][ind][vals][subVal].id +"</td><td>"+ obj[k][ind][vals][subVal].pairId +"</td><td>"+ obj[k][ind][vals][subVal].nomAttribut +"</td><td>"+ obj[k][ind][vals][subVal].elem1 +"</td><td>"+ obj[k][ind][vals][subVal].elem2 +"</td><td>"+ obj[k][ind][vals][subVal].val +"</td><td>"+ obj[k][ind][vals][subVal].nbrVote +"</td></tr>");
									}
								}
							}
						}
		    	   }
			});
		}
	});
 });
