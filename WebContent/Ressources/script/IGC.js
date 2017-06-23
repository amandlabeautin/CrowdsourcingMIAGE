/**
 * 
 */
 
$( document ).ready(function() {
	 
	 $.ajax({
			url : 'http://127.0.0.1:8080/ProjetPPD/getStepOne',
			success : function(responseText) {
				console.log(responseText);
			}
		});
 });