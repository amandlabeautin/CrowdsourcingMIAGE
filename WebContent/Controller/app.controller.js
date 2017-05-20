angular
    .module('app')
    .controller('homeCtrl', homeCtrl)
    .controller('modalController', modalController)
    .controller('RegisterCtrl', RegisterCtrl)
    .controller('SigninCtrl', SigninCtrl)
    .controller('adminCtrl', adminCtrl)
    .controller('addUserCtrl', addUserCtrl);

  var variableSimi = [];

  // Controller de la page d'accueil des personnes connectés
  function homeCtrl($scope, $uibModal, $log,$window){
    
      // Ouverture de la fenetre modal 
      var modalPopup = function () {
        return $scope.modalInstance = $uibModal.open({
          templateUrl: 'View/formModal.html',
          size:'lg',
          scope: $scope
        });
      };

      // Fonction qui controle l'affichage du modal. Affichage dans la console les erreurs si il y a lieu
      $scope.showModalForm = function () {
        modalPopup().result
          .then(function (data) {
            $scope.handleSuccess(data);
          })
          .then(null, function (reason) {
            $scope.handleDismiss(reason);
          });
      };

      // Affichage du Tooltip
      $scope.placement = {
        options: [
          'top',
          'top-left',
          'top-right',
          'bottom',
          'bottom-left',
          'bottom-right',
          'left',
          'left-top',
          'left-bottom',
          'right',
          'right-top',
          'right-bottom'
        ],
        selected: 'top'
      };

      // fonction qui ...
      $scope.go = function ( path ) {
        $location.path( path );
      };
  };

  // Controller de la fenetre modal
  function modalController($scope, $window, $http, $filter){
    $http.get("View/dataEnter.json")
      .then(function(response) {
        $scope.dataRestaurants = response.data;
    });

    $scope.select = function (item) {
      if ($scope.IsPair) {
        item.selected ? item.selected = false : item.selected = true;
      }else {
        $window.alert("Merci de cocher la case '1.' ci-dessus. ");
      }  
    };

    // Fonction qui prepare en JSON en fonction des réponses de l'utilisateur
    $scope.yes = function () {
      var temp = [];
      temp = angular.fromJson($scope.dataRestaurants); 
      temp["Val"] = $scope.sliders.sliderValue;

      for (var i in temp) {
        if (typeof temp[i].selected == 'undefined' && typeof temp[i].elem1 != 'undefined') {
            temp[i]["selected"] = false; 
        }
      }
      
      console.log(temp);
	  $scope.jsonResultSend = temp;
	  $http({
			method: 'POST',
			url: 'http://127.0.0.1:8080/ProjetPPD/postPairServlet',
			headers: {'Content-Type': 'application/json'},
			data:  $scope.jsonResultSend 
		}).then(function (success){
			
	   },function (error){
			console.log('error : ' + error.status);
	   });
    };

      // Fonction qui ...
    $scope.no = function () {
        $scope.modalInstance.dismiss('No Button Clicked')
    };
  
    // fonction qui ...
    $scope.next = function ($scope) {
	  $http({
			method: 'GET',
			url: 'http://127.0.0.1:8080/ProjetPPD/getRandomPairServlet',
		}).then(function (success){
			console.log(success);
	   },function (error){
			console.log('error : ' + error.status)
			console.log('error : ' + error);
	   });
	  
    };

    // Log Success message
    $scope.handleSuccess = function (data) {
      $log.info('Modal closed: ' + data);
    };

    // Log Dismiss message
    $scope.handleDismiss = function (reason) {
      $log.info('Modal dismissed: ' + reason);
    };

    // fonction qui ...
    $scope.isActive = function(item) {
      return $scope.selected === item;
    };

    // Si le checkbox est désactivé, les champs selectionnés sont initialisés
    $scope.isDontPair = function() {
      if(!$scope.IsPair){
        angular.element(document.querySelectorAll('#table tr')).removeClass("selected");
      }
    };

    $scope.formRange = {
      min: 0,
      max: 1,
      step: 0.1,
      precision: 2,
      orientation: 'horizontal',  // vertical
      handle: 'round', //'square', 'triangle' or 'custom'
      tooltip: 'show', //'hide','always'
      tooltipseparator: ':',
      tooltipsplit: false,
      enabled: true,
      naturalarrowkeys: false,
      range: false,
      ngDisabled: false,
      reversed: false
    };
  };

  // Controller de la page d'inscription
  function RegisterCtrl(UserService, $location, $rootScope, FlashService, $scope) {
      $scope.createUser = function(user){
        var userExist = $http.get('http://localhost:8080/user/checkUserExists',{params: {'login': user.login}}).
            then(function  (response) { 
              userExist = response.data;
            });
            if (userExist = false) {
              $http.get('http://localhost:8080/users/add',{params: {'name': user.login, 'password' : user.password}}).
                  then(function  (response) {
                    if(response.data == 'SAVED') {
                      $location.path('/');
                    }})
            .catch(function(response, status) {
              console.error('Gists error', response.status, response.data);
                $scope.loginFailed = true;
                  UtilService.notifyError('Invalid Login Credentials');
          });
            } else {
              $scope.inscriptionForm.$error.userExist = true;
            }
      };
  };

  // Controller de la page de connexion
  function SigninCtrl($scope, $http, $location, UserService){

    $scope.connectUser = function(user){
      $http({
          method: 'POST',
          url: 'http://127.0.0.1:8080/ProjetPPD/postSearchUser',
          data: JSON.stringify(user),
          headers: {'Content-Type': 'application/json'}
      })
      .then(function  (response) { 
        if (response.data.administrator == false) {
            UserService.setUser(response.data);
        } else {
          console.log(response.data.administrator);
            UserService.setAdmin(response.data);
        }
        $location.path('/home');
      },function(response) {
          console.log('Login failed');
          $scope.loginFailed = true;
      });
    };
  };

  function adminCtrl($scope, $http){
    $http({
      method: 'GET',
      url: 'http://127.0.0.1:8080/ProjetPPD/getListUser',
    }).then(function (response){
        console.log(response);
      },function (error){
        console.log('error : ' + error.status)
        console.log('error : ' + error);
    });
  };

  function addUserCtrl(){
  };
