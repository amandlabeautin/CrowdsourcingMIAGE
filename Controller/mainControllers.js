angular.module('myModule', ['ui.bootstrap']);
var app = angular.module("myModule", []);

app.controller("mainController", function($scope){
//
});

app.controller("headerCtrl", function($scope){
//
});

app.controller("footerCtrl", function($scope){
//
});

app.controller("menuCtrl", function($scope){
//
});

app.controller("contentCtrl", function($scope){
	$scope.data = [{"name": "Apple Pan","adresse":"The 10801 W. Pico Blvd. West LA","telephone":"310-475-3585","type":"American"},
		{"name": "Arnie Morton's of Chicago","adresse":"435 S. La Cienega Blvd. Los Angeles","telephone":"310-246-1501","type":" Steakhouses"},
      {"name": "Art's Deli","adresse": "12224 Ventura Blvd. Studio City","telephone": "818-762-1221","type" :"Delis"},
      {"name":"Asahi Ramen","adresse":"2027 Sawtelle Blvd. West LA","telephone" :"310-479-2231","type":"Noodle Shops"},
      {"name":"Baja Fresh","adresse":"3345 Kimber Dr. Westlake Village","telephone":"805-498-4049","type":"Mexican"},
      {"name":"Bel-Air Hotel","adresse":"701 Stone Canyon Rd. Bel Air","telephone":"310-472-1211","type":"Californian"},
      {"name":"Belvedere","adresse":"The 9882 Little Santa Monica Blvd. Beverly Hills","telephone":" 310-788-2306","type":" Pacific New Wave"},
      {"name":"Benita's Frites","adresse":" 1433 Third St. Promenade Santa Monica","telephone":" 310-458-2889","type":" Fast Food"}];
});