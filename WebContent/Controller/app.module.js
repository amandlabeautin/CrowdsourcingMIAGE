// app.module.js
angular
   .module('app', [
   		'ui.bootstrap',
   		'ui.bootstrap.modal',
   		'rzModule',
		'ngRoute',
		'ui.router',
		'ngCookies',
		'ui-notification'
	])
    .run(['$rootScope', '$state', '$timeout','UserService','UtilService','$transitions', '$trace',
		function ($rootScope, $state, $timeout, UserService, UtilService, $transitions, $trace) {

			$transitions.onStart( {}, function($transition$, event) {
				var nameR = $transition$.$to().name;
			    var access = $transition$.$to().access;

				if (access == 'admin' && UserService.isAdminLoggedIn()) {
					$state.transitionTo('admin');
				}
				else if (access == 'user' && UserService.isUserLoggedIn() ){

				}
				else if (access == 'public'){

				}
				else {
		    		$state.transitionTo('register');
		    	}
				
		    	//$rootScope.currentNavLink=toState.name;
			})
			$transitions.onError({}, function(transition) {
	            console.log('error', transition.error().message, transition);
	        });
			
			$rootScope.$on('NotificationEvent', function (event, message) {
			  	  //console.log(message);
			  	  $rootScope.message = message;
			  	  if(message.type == 'error'){
			  		UtilService.notifyError(message.msg);
			  	  } else {
			  		UtilService.notifyInfo(message.msg);
			  	  }
			  	  
			  	  $timeout(function(){
			  		  delete $rootScope.message;
			  	  }, 3000);
			 });
		
			$rootScope.isUserLoggedIn = function(){
		        return UserService.isUserLoggedIn();
			}

			$rootScope.isAdminLoggedIn = function(){
		        return UserService.isAdminLoggedIn();
			}
	 
			$rootScope.logout = function()
			{
				console.log('Logging out..');
				UserService.logoutAdmin();
				$state.transitionTo('register');
			}    
		}
	]
);