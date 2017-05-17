angular
    .module('app')
	.config(function($routeProvider, $locationProvider) {
		$locationProvider.hashPrefix('');
		$routeProvider
			.when('/home', {
				templateUrl: 'View/home.view.html',
				controller : 'homeCtrl'
			})
			.when('/', {
				templateUrl: 'View/login.view.html',
				controller : 'loginCtrl'
			})
			.when('/register', {
				templateUrl: 'View/register.view.html',
				controller : 'RegisterCtrl'
			})
			.when('/admin', {
				templateUrl: 'View/admin.view.html',
				controller : 'adminCtrl'
			})
			.when('/addUser', {
				templateUrl: 'View/addUser.view.html',
				controller : 'addUserCtrl'
			})
			.otherwise({
				redirectTo: '/'
			});
	});