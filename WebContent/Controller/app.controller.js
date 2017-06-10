angular
    .module('app')
    .controller('homeCtrl', homeCtrl)
    .controller('homeConnectCtrl', homeConnectCtrl)
    .controller('modalController', modalController)
    .controller('RegisterCtrl', RegisterCtrl)
    .controller('SigninCtrl', SigninCtrl)
    .controller('adminCtrl', adminCtrl);

  var variableSimi = [];

  function homeCtrl($scope){
    
  };

  // Controller de la page d'accueil des personnes connectés
  function homeConnectCtrl($scope, $uibModal, $log,$window){
    
      // Ouverture de la fenetre modal 
      var modalPopup = function () {
        return $scope.modalInstance = $uibModal.open({
          templateUrl: './View/pairs/formModal.html',
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

        // Log Success message
      $scope.handleSuccess = function (data) {
        $log.info('Modal closed: ' + data);
      };

      // Log Dismiss message
      $scope.handleDismiss = function (reason) {
        $log.info('Modal dismissed: ' + reason);
      };
  };

  // Controller de la fenetre modal des pairs
  function modalController($scope, $window, $http, $filter, UserService){
    $scope.radioChoice = true;

    $scope.slider = {
      value: 0,
      options: {
          showSelectionBar: true,
          getSelectionBarColor: function(value) {
              if (value <= 0.3)
                  return 'red';
              if (value <= 0.5)
                  return 'orange';
              if (value <= 0.7)
                  return 'yellow';
              return '#2AE02A';
          },
          floor: 0,
          ceil: 1,
          step: 0.1,
          precision: 1
      }
    };

    $http({
      method: 'GET',
      url: 'http://127.0.0.1:8080/ProjetPPD/getRandomPairServlet',
    }).then(function (success){
        $scope.pairServlets = success.data;
        console.log(success.data);
    },function (error){
        console.log('error : ' + error.status)
        console.log('error : ' + error);
     });

    $scope.select = function (item) {
      if ($scope.isSimilar) {
        console.log(item.elem1);
        item.selected ? item.selected = false : item.selected = true;
      }
    };

    // Fonction qui prepare en JSON en fonction des réponses de l'utilisateur
    $scope.yes = function () {
      var temp = {};
      if($scope.isSimilar){
          temp = angular.fromJson($scope.pairServlets); 
          temp["nonSimilaire"] = false;
          temp["valUser"] = $scope.slider.value;
          console.log(temp);
          for (var i in temp) {
            if (typeof temp[i].selected == 'undefined' && typeof temp[i].elem1 != 'undefined') {
                temp[i]["selected"] = false; 
            }
          }
      }else {
          temp["nonSimilaire"] = true;
      }

      temp["user"] = UserService.getUser().id;
      console.log(temp);

  	  $scope.jsonResultSend = temp;
  	  $http({
  			method: 'POST',
  			url: 'http://127.0.0.1:8080/ProjetPPD/postPairServlet',
  			headers: {'Content-Type': 'application/json'},
  			data:  $scope.jsonResultSend 
  		}).then(function (success){
          alert('Les données ont été envoyées');
  			 $scope.next();
  	   },function (error){
  			console.log('error : ' + error.status);
  	   });
    };

      // Fonction qui ...
    $scope.no = function () {
        $scope.modalInstance.dismiss('No Button Clicked');
    };
  
    // fonction qui ...
    $scope.next = function () {
      alert(" Une nouvelle pair va vous être présentée. ")
  	  $http({
  			method: 'GET',
  			url: 'http://127.0.0.1:8080/ProjetPPD/getRandomPairServlet',
  		}).then(function (success){
        angular.element(document.querySelectorAll('input[name="inlineRadioOptions"]')).prop('checked', false);
        $scope.pairServlets = success.data;
  			console.log(success);
  	   },function (error){
  			console.log('error : ' + error.status)
  			console.log('error : ' + error);
  	   });
    };

    $scope.choicePairs = function() {
        $scope.radioChoice = false;
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
  function RegisterCtrl(UserService, UtilService, $http, $location, $rootScope, FlashService, $scope) {
      $scope.createUser = function(user){
        var temp = {};

        temp["loginUser"] = user.login;
        temp["passwordUser"] = user.password;
        temp["isAdmin"] = false;

        $scope.jsonResultSend = temp;
        $http({
          method: 'POST',
          url: 'http://127.0.0.1:8080/ProjetPPD/postNewUser',
          headers: {'Content-Type': 'application/json'},
          data:  $scope.jsonResultSend 
        }).then(function (success){
          alert('Vous etes bien inscrit !');
        },function (error){
          console.log('error : ' + error.status);
        });
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
        alert('Bonjour');
        $location.path('/home');
      },function(response) {
          console.log('Login failed');
          $scope.loginFailed = true;
      });
    };
  };

  /* Controller de la page d'Administration*/
  function adminCtrl($scope, $http, $uibModal, $log, $window, UserService){
    $scope.IsHidden = true;

    /*Affichage des users*/
    $http({
      method: 'GET',
      url: 'http://127.0.0.1:8080/ProjetPPD/getListUser',
    }).then(function (response){
        for (var i = 0; i < response.data.length; i++) {
          if(response.data[i].administrator == true){
            response.data[i].administrator = "OUI";
          }else{
            response.data[i].administrator = "non";
          }
        }
        
        $scope.users = response.data;
      },function (error){
        console.log('error : ' + error.status)
        console.log('error : ' + error);
    });
    $scope.alerts = [];

    // Affichage des pairs selectionnés par le idUser, et affichage du loginUser
    $scope.showDetailsPair = function(idUser, loginUser) {
      $scope.userSelected = loginUser;
      $scope.IsHidden = false;
    };

    $scope.deleteUserModal = function(idUser, loginUser) {
        $scope.idUser = idUser;
        $scope.loginUser = loginUser;
        modalPopupDelete($scope).result
          .then(function (data) {
            $scope.handleSuccess(data);
          })
          .then(null, function (reason) {
            $scope.handleDismiss(reason);
          });
    };

    $scope.openModal = function() {
      modalAddUser().result
          .then(function (data) {
            $scope.handleSuccess(data);
          })
          .then(null, function (reason) {
            $scope.handleDismiss(reason);
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

    var modalAddUser = function() {
      return $scope.modalInstance = $uibModal.open({
          templateUrl: './View/admin/addUser.html',
          scope: $scope
        });
    };

    var modalPopupDelete = function ($scope) {
        return $scope.modalInstance = $uibModal.open({
          templateUrl: './View/admin/modalConfirmation.view.html',
          size:'sm',
          scope: $scope
        });
    };

    $scope.no = function () {
      $scope.modalInstance.dismiss('No Button Clicked')
    };

    $scope.deleteUser = function(idUser){

      console.log(idUser);

      var deleteTemp = {};
      deleteTemp["idUser"] = idUser;
      $scope.jsonResultSend = deleteTemp;

      $http({
        method: 'POST',
        url: 'http://127.0.0.1:8080/ProjetPPD/postDeleteUser',
        headers: {'Content-Type': 'application/json'},
        data:  $scope.jsonResultSend 
      }).then(function (success){
        $scope.modalInstance.dismiss('No Button Clicked')
        alert('L\'utilisateur a été supprimé !');
        $scope.usersTable.reload();
      },function (error){
          console.log('error : ' + error.status);
      });
    };

    $scope.addUserAdmin = function(form) {
      var temp = {};

      temp["loginUser"] = form.username;
      temp["passwordUser"] = form.password;
      if(form.role == 'Admin'){
        temp["isAdmin"] = true;
      }else{
        temp["isAdmin"] = false;
      }

      $scope.jsonResultSend = temp;
      $http({
        method: 'POST',
        url: 'http://127.0.0.1:8080/ProjetPPD/postNewUser',
        headers: {'Content-Type': 'application/json'},
        data:  $scope.jsonResultSend 
      }).then(function (success){
        modalAddUser().dismiss('cancel');
        $scope.alerts.push({msg: 'L\'utilisateur a été ajouté !'});
      },function (error){
        console.log('error : ' + error.status);
      });
    };
  };
