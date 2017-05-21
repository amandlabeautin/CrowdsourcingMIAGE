angular
    .module('app')
	.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
		$locationProvider.hashPrefix('');
		$stateProvider
			.state('signin', {
                url: '/',
                templateUrl: '../View/authentification/signin.view.html',
                controller: 'SigninCtrl',
                access: 'public'
	        })
	        .state('home', {
                url: '/home',
                templateUrl: '../View/pairs/home.view.html',
                controller: 'homeCtrl'
	        })
	        .state('register', {
                url: '/register',
                templateUrl: '../View/authentification/register.view.html',
                controller: 'RegisterCtrl',
                access: 'public'
	        })
	        .state('admin', {
                url: '/admin',
                templateUrl: '../View/admin/admin.view.html',
                controller: 'adminCtrl'
	        });
	    $urlRouterProvider.otherwise("/");
	})
	.run(['$rootScope', '$state', '$timeout','UserService','UtilService',
	    function ($rootScope, $location, $timeout, UserService, UtilService) {
		
			$rootScope.$on('$stateChangeStart', function(evt, toState, toParams, fromState, fromParams) {
				console.log(toState);
		    	console.log(toState.name);
				var access = toState.access;
				console.log(toState.access);
		    	if(access != 'public'){
		    		if(UserService.isUserLoggedIn()){
		    			$rootScope.currentNavLink=toState.name;
		    		} else {
		    			evt.preventDefault();
		    			console.log('redirect to login');
		    			$location.path("/");
		    		}
		    	}else{
		    		$location.path("/home");
		    	}
				
		    	//$rootScope.currentNavLink=toState.name;
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
				$location.path('/');
			}
		    
		}
	]);