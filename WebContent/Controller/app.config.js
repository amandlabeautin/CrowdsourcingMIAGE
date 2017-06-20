angular
    .module('app')
	.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
		$locationProvider.hashPrefix('');
		$stateProvider
			.state('signin', {
                url: '/',
                templateUrl: './View/authentification/signin.view.html',
                controller: 'SigninCtrl',
                access: 'public'
	        })
	        .state('home', {
                url: '/home',
                templateUrl: './View/pairs/home.view.html',
                controller: 'homeConnectCtrl',
                access: 'user'
	        })
	        .state('register', {
                url: '/register',
                templateUrl: './View/authentification/register.view.html',
                controller: 'RegisterCtrl',
                access: 'public'
	        })
	        .state('admin', {
                url: '/admin',
                templateUrl: './View/admin/admin.view.html',
                controller: 'adminCtrl',
                access: 'admin'
	        });
	    $urlRouterProvider.otherwise("/");
	});