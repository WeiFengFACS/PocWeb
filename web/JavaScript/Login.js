function Login($scope, $http, ServletCall, $routeParams, $modal, $log) {
	$scope.resultMessage = true;
	
	$scope.params = $routeParams;
	$scope.adminButton = true;
	$scope.empButton = true;
	$scope.managerButton = true;
	$scope.changeColor = function changeColor(e) {

        alert("tapped");

      };
//	
//	$scope.manageMenu = function(){
//		if ($scope.params.userName == null){
//			alert("inside null value!!!" + $scope.adminButton);
//			$scope.adminButton = true;
//			$scope.empButton = true;
//			$scope.managerButton = true;
//		} else if ($scope.params.role == "admin") {
//			
//			$scope.homeButton = true;
//			alert("get inside veri " + $scope.homeButton);
//			$scope.adminButton = false;
////			window.location = "index.html#/AdminMenu/"
////				+ $scope.params.userName;
//		}
//	};
//	
//	$scope.manageMenu();
	
	$scope.register = function() {
		window.location = "index.html#/Registration";
	};
	
	$scope.menu = [
	    { name : "adminButton"},
	    { name : "managerButton"},
	    { name : "empButton"}];
	
	
	
	$scope.goAdmin = function() {
		alert("call go admin");
	};
	
//	
	
	
	$scope.openModal = function() {
		var modalInstance = $modal.open({
			templateUrl : './LoginModal.html',
			controller : ModalLogin,
		});

//		modalInstance.result.then(function(selectedItem) {
//			$scope.selected = selectedItem;
//		}, function() {
//			$log.info('Modal dismissed at: ' + new Date());
//		});
	};
	
}
var ModalLogin = function($scope, ServletCall, $modalInstance) {
//	$scope.menu = menus;
	$scope.resultMessage = true;
	$scope.session={};
	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};

	$scope.submitLogin = function() {
		if ($scope.session.userName == null || $scope.session.userName == "") {
			$scope.message = "Please enter the email address";
			$scope.resultMessage = false;
		} else if (!checkIsEmail($scope.session.userName)) {
			$scope.message = "Please enter the valid email address";
			$scope.resultMessage = false;
		} else if ($scope.session.password == null || $scope.session.password == "") {
			$scope.message = "Please enter the password";
			$scope.resultMessage = false;
		} else {
			ServletCall.LoginFactory($scope.session.userName, $scope.session.password, $scope);
		}
	};
	

};
	
//	$scope.GetLoginReturn = function(data){
//		alert("inside login " + data);
//		$scope.result = data.ResponseChecker;
//		alert($scope.result);
//    	$scope.role = data.Role;
//    	alert($scope.role);
//    	if ($scope.result == 0){
//    		$scope.resultMessage = false;
//    		$scope.message = data.Message;
//    	} else {
//    		ServletCall.setLogInFactory($scope.userName, $scope.role, $scope);
//    	}
//	};
//
//	$scope.setLogInReturn = function(data){
//		switch ($scope.role) {
//		case "admin":
//			window.location = "index.html#/AdminMenu/" + $scope.userName;
//			break;
//
//		case "manager":
//			window.location = "index.html#/ManagerMenu/" + $scope.userName;
//			break;
//
//		default:
//			window.location = "index.html#/EmployeeMenu/" + $scope.userName;
//			break;
//		}
//	};	
	
	//Added Code!!!!!!!!!!1
//	 $scope.items = ['item1', 'item2', 'item3'];

//	 $scope.user = {
//		        user: $scope.userName,
//		        password: $scope.password
//		    };
//
//		    $scope.open = function () {
//
//		        $modal.open({
//		            templateUrl: 'myModalContent.html',
//		            backdrop: true,
//		            windowClass: 'modal',
//		            controller: function ($scope, $modalInstance, $log, user) {
//		                $scope.user = user;
//		                $scope.ok = function () {
//		                    $log.log('Submiting user info.');
//		                    $log.log(user);
//		                    $modalInstance.dismiss('cancel');
//		                }
//		                $scope.cancel = function () {
//		                    $modalInstance.dismiss('cancel');
//		                };
//		            },
//		            resolve: {
//		                user: function () {
//		                    return $scope.user;
//		                }
//		            }
//		        });
//		    };
	 
	 
//	 
//	  $scope.open = function () {
//alert("call open");
//	    var modalInstance = $modal.open({
//	      templateUrl: 'myModalContent.html',
//	      controller: ModalInstanceCtrl,
////	      resolve: {
////	        userName: function () {
////	          return $scope.userName;
////	        },
////	        password : function() {
////	        	return $scope.password;
////	        }
////	      }
////	      resolve: {
////	          items: function () {
////	            return $scope.items;
////	          }
////	        }
//	    });
//
////	    modalInstance.result.then(function (selectedItem) {
////	      $scope.selected = selectedItem;
////	    }, function () {
////	      $log.info('Modal dismissed at: ' + new Date());
////	    });
//	  };


//var ModalInstanceCtrl = function ($scope, $modalInstance) {
//	$scope.items = ['item1', 'item2', 'item3'];
//	
//	  $scope.selected = {
//	    item: $scope.items[0]
//	  };
//	  
//	$scope.submitLogin = function(){
//		alert("inside submit button");
//		alert($scope.selected.item);
//		 alert("username is " + $scope.userNameNew);
//	};
//	
//	  $scope.ok = function () {
//		  alert("username is " + $scope.userNameModal);
//			alert("password is " + $scope.passwordModel);
//			alert($scope.user.userName);
//	    $modalInstance.close($scope.user.userName);
//	  };
//
//	  $scope.cancel = function () {
//	    $modalInstance.dismiss('cancel');
//	  };
//	};
	
function checkIsEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

function checkNotNull(ch) {
	if (ch != null && ch != "")
		return true;
}

function checkIsRole(ch) {
	var chLower = ch.toLowerCase();
	if (chLower == 'a' || chLower == 'm' || chLower == 'd' || chLower == 'q'
			|| chLower == 't')
		return true;
}

function checkIsCharacter(ch) {
	var c = /^[ A-Za-z]*$/;
	return c.test(ch);
}

function checkIsInteger(ch) {
	// if(ch >= '0' && ch <= '9')return true;
	var i = /^[0-9]*[1-9][0-9]*$/;
	return i.test(ch);
}

function checkIsGender(ch) {
	var newGender = ch.toLowerCase();
	if (newGender == 'female' || newGender == "male")
		return true;
}

function checkInputRange(ch) {
	if (ch > 0 && ch <= 100)
		return true;
}

function checkAgeRange(ch) {
	if (ch >= 16 && ch <= 65)
		return true;
}

function checkExpRange(ch) {
	if (ch >= 0 && ch <= 49)
		return true;
}

function checkIsPhoneNum(phoneNum) {
	// var ph = /^(\()?\d{3}(\))?(-|\s)?\d{3}(-|\s)\d{4}$/;
	var ph = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
	return ph.test(phoneNum);

}


//$scope.posts = [];
//$scope.loginVerification = function() {
//       $http({
//           method : 'POST',
//           url : './Login',
//           data : 'userName=' + $scope.userName + '&password=' + $scope.password,
//           headers : {
//               'Content-Type' : 'application/x-www-form-urlencoded'
//           }
//       }). success(function(data) {
////           $scope.status = status;
////           $scope.data = data;
//       	$scope.result = data.ResponseChecker;
//       	$scope.role = data.Role;
////       	alert($scope.role);
////       	alert("result is " + $scope.result);
//       	if ($scope.result == 0){
//       		$scope.resultMessage = false;
//       		$scope.message = data.Message;
//       	} else {
//       		$scope.setLogIn();
//       	}
//       	
////       	var parseJSON = JSON.parse(date);
//       	
//         }).
//         error(function(data) {
//       	  alert("Servlet Call Error");
////           $scope.data = data || "Request failed";
////           $scope.status = status;
//       });
//
//};

//$scope.setLogIn = function() {
//	 $http({
//           method : 'POST',
//           url : './SessionLogIn',
//           data : 'userName=' + $scope.userName + '&role=' + $scope.role,
//           headers : {
//               'Content-Type' : 'application/x-www-form-urlencoded'
//           }
//       }). success(function(data) {
//          switch ($scope.role) {
//		case "admin":
//			// window.location = "AdminMenu.html?email=" + userName;
//			window.location = "index.html#/AdminMenu/" + $scope.userName;
//			break;
//
//		case "manager":
//			window.location = "index.html#/ManagerMenu/" + $scope.userName;
//			break;
//
//		default:
//			window.location = "index.html#/EmployeeMenu/" + $scope.userName;
//			break;
//		}
//       	
////       	var parseJSON = JSON.parse(date);
//       	
//         }).
//         error(function(data) {
//       	  alert("Servlet Call Error");
////           $scope.data = data || "Request failed";
////           $scope.status = status;
//       });
//}

// callServlet(userName, password);
// setLogIn(userName, password);

// if ($scope.validateLogin.$valid) {
// // Submit as normal
// alert("userName=" + $scope.userName + "\npassword="
// + $scope.password);
// $scope.userName = "";
// $scope.password = "";
// } else {
// $scope.validateLogin.submitted = true;
// }
// $scope.show = false;
// window.location = "#/Logout";