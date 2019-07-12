var app = angular.module('myApp', [ 'ui.bootstrap','hammer', 'ui.utils' ]);// only one app , [
// 'ngRoute' ]


function detectmob() {
	   if(window.innerWidth <= 800 && window.innerHeight <= 600) {
	     return true;
	   } else {
	     return false;
	   }
	}
app.config([ '$routeProvider', routeProviderFunction ]);
app.directive('onTap', function () {
    return function (scope, element, attrs) {
        return $(element).hammer({
          prevent_default: false,
          drag_vertical: false
        })
          .bind("tap", function (ev) {
            return scope.$apply(attrs['onTap']);
          });
      };
    });
function detectmob() {
	   if(window.innerWidth <= 800 && window.innerHeight <= 600) {
	     return true;
	   } else {
	     return false;
	   }
	}

function CarouselDemoCtrl($scope) {
	  var slides = $scope.slides = [];
	  $scope.addSlide = function() {
	    var newWidth = 600 + slides.length;
	    slides.push({
	      image: 'http://placekitten.com/' + newWidth + '/300',
	      text: ['More','Extra','Lots of','Surplus'][slides.length % 4] + ' ' +
	        ['Cats', 'Kittys', 'Felines', 'Cutes'][slides.length % 4]
	    });
	  };
	  for (var i=0; i<4; i++) {
	    $scope.addSlide();
	  }
	}

function routeProviderFunction($routeProvider) {
	
	if(detectmob()){
		$routeProvider.when('/slide',{
			templateUrl : 'mobile/slide.html',
			//controller : CarouselDemoCtrl
		});
		
	$routeProvider.when('/default/', {
		templateUrl : 'mobile/Login.html',
		controller : Login
	});

	$routeProvider.when('/ContactUs', {
		templateUrl : 'mobile/ContactUs.html',
	// controller: TestCon2
	});

	
	$routeProvider.when('/Login', {
		templateUrl : 'mobile/Login.html',
		controller : Login
	});
	

	$routeProvider.when('/Registration', {
		templateUrl : 'mobile/Registration.html',
		controller : Register
	});

	$routeProvider.when('/AdminMenu/:userName', {
		templateUrl : 'mobile/AdminMenuNew.html',
		controller : AdminMenu
	});

	$routeProvider.when('/ManagerMenu/:userName', {
		templateUrl : 'mobile/ManagerMenu.html',
		controller : ManagerMenu
	});

	$routeProvider.when('/EmployeeMenu/:userName', {
		templateUrl : 'mobile/EmployeeMenu.html',
		controller : EmployeeMenu
	});

	
	$routeProvider.when('/AboutUs', {
		templateUrl : 'mobile/AboutUs.html',
		controller : AboutUs
	});
	

	$routeProvider.otherwise({
		redirectTo : 'mobile/default'
	});
}else{
	$routeProvider.when('/slide',{
		templateUrl : 'slide.html',
		//controller : CarouselDemoCtrl
	});
	
	$routeProvider.when('/default/', {
		templateUrl : 'Login.html',
		controller : Login
	});
	
	$routeProvider.when('/ContactUs', {
		
		templateUrl : 'ContactUs.html',
	// controller: TestCon2
	});

	
	$routeProvider.when('/Login', {
		templateUrl : 'Login.html',
		controller : Login
	});
	

	$routeProvider.when('/Registration', {
		templateUrl : 'Registration.html',
		controller : Register
	});

	$routeProvider.when('/AdminMenu/:userName', {
		templateUrl : 'AdminMenuNew.html',
		controller : AdminMenu
	});

	$routeProvider.when('/ManagerMenu/:userName', {
		templateUrl : 'ManagerMenu.html',
		controller : ManagerMenu
	});

	$routeProvider.when('/EmployeeMenu/:userName', {
		templateUrl : 'EmployeeMenu.html',
		controller : EmployeeMenu
	});

	
	$routeProvider.when('/AboutUs', {
		templateUrl : 'AboutUs.html',
		controller : AboutUs
	});
	

	$routeProvider.otherwise({
		redirectTo : '/default'
	});
}
}



app
		.factory(
				'ServletCall',
				function($http) {
					var setLogInFactory = function(userName, role, scope) {
	//					alert("getUserName is " + userName);
						return $http(
								{
									method : 'POST',
									url : './SessionLogIn',
									data : 'userName=' + userName + '&role='
											+ role,
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									}
								})
								.success(
										function(data) {
//											alert("username in back " + userName);
//											alert("role back is " + role);
											switch (role) {
											case "admin":
//												alert("index.html#/Login/"
//														+ userName + "/ro/" + role);
//												window.location = "index.html#/Login/"
//													+ userName + "/ro/" + role;
												window.location = "index.html#/AdminMenu/"
														+ userName;
												
												break;

											case "manager":
												window.location = "index.html#/ManagerMenu/"
														+ userName;
												break;

											default:
												window.location = "index.html#/EmployeeMenu/"
														+ userName;
												break;
											}
											
											scope.cancel();
											// scope.setLogInReturn(data);
											
										}).error(function(data) {
									alert("Servlet Call Error setLogin");
								});
					};
					return {
						updateURLFactory : function(userName, date, URL, scope) {
							return $http(
									{
										method : 'POST',
										url : './UpdateURL',
										data : "userName=" + userName + "&date=" + date
											+ "&URL=" + URL,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(
									function(data) {
//										scope.updateURLReturn(data);

									}).error(function(data) {
								alert("Servlet Call Error getTotalEmpNumberFactory");
							});
						},
						
						
						LoginFactory : function(userName, password, scope) {
							return $http(
									{
										method : 'POST',
										url : './Login',
										data : 'userName=' + userName
												+ '&password=' + password,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(
									function(data) {
										scope.result = data.ResponseChecker;
										scope.role = data.Role;
										if (scope.result == 0) {
											scope.resultMessage = false;
											scope.message = data.Message;
										} else {
											setLogInFactory(userName,
													scope.role, scope);
											
										}
										// scope.GetLoginReturn(data);
									}).error(function(data) {
								alert("Servlet Call Error login1");
							});
						},
						ckeckLogInFactory : function(userName, scope) {
							return $http(
									{
										method : 'POST',
										url : './SessionCheck',
										data : 'userName=' + userName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.ckeckLogInReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error checkLoginFactory");
							});
						},
						logOutFactory : function(userName) {
							return $http(
									{
										method : 'POST',
										url : './SessionLogOut',
										data : 'userName=' + userName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								window.location = "index.html#/Login";

							}).error(function(data) {
								alert("Servlet Call Error LogoutFactory");
							});
						},
						empRegisterFactory : function(userName, password,
								fName, lName, role, gender, age, phoneNum,
								address, add1, add2, scope) {
							return $http(
									{
										method : 'POST',
										url : './Register',
										// data : 'userName=' + $scope.userName
										// + '&password=' + $scope.password,
										data : "userName=" + userName
												+ "&password=" + password
												+ "&fName=" + fName + "&lName="
												+ lName + "&role=" + role
												+ "&gender=" + gender + "&age="
												+ age + "&phoneNum=" + phoneNum
												+ "&address=" + address
												+ "&add1=" + add1 + "&add2="
												+ add2,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.empRegisterReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error empRegisterFactory");
							});
						},
						viewGroupDetailFactory : function(groupID, scope) {
							return $http(
									{
										method : 'POST',
										url : './GetEmpByGroupID',
										data : 'groupID=' + groupID,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.viewGroupDetailReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error viewGroupDetailFactory");
							});
						},
						viewGroupFactory : function(scope) {
							return $http(
									{
										method : 'POST',
										url : './GetManagerByGroup',
										data : "userName=0",
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.viewGroupReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error viewGroupFactory");
							});
						},
						empRequestFactory : function(curPage, rowPerPage, scope) {
							return $http(
									{
										method : 'POST',
										url : './/RequestApprovalList',
										data : "page="
												+ (parseInt(curPage) - 1)
												+ "&rowPerPage=" + rowPerPage,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.empRequestReturn(data, curPage);

							}).error(function(data) {
								alert("Servlet Call Error empRequestFactory");
							});
						},
						getTotalPageFactory : function(curPage, rowPerPage,
								scope) {
							return $http(
									{
										method : 'POST',
										url : './GetInfo',
										data : "page="
												+ (parseInt(curPage) - 1)
												+ "&rowPerPage=" + rowPerPage
												+ "&info=requestApproval",
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(
									function(data) {
										scope.getTotalPageReturn(curPage,
												rowPerPage, data);

									}).error(function(data) {
								alert("Servlet Call Error getTotalPageFactory");
							});
						},
						approveOneFactory : function(userName, scope) {
							return $http(
									{
										method : 'POST',
										url : './ApproveOneRequest',
										// data : 'userName=' + $scope.userName
										// + '&password=' + $scope.password,
										data : "userName=" + userName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.approveOneReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error approveOneFactory");
							});
						},
						denyOneFactory : function(userName, scope) {
							return $http(
									{
										method : 'POST',
										url : './DenyOneRequest',
										// data : 'userName=' + $scope.userName
										// + '&password=' + $scope.password,
										data : "userName=" + userName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.denyOneReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error denyOneFactory");
							});
						},
						approveAllFactory : function(scope) {
							return $http(
									{
										method : 'POST',
										url : './ApproveAll',
										data : "userName=0",
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.approveAllReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error approveAllFactory");
							});
						},
						denyAllFactory : function(scope) {
							return $http(
									{
										method : 'POST',
										url : './DenyAll',
										data : "userName=0",
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.denyAllReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error denyAllFactory");
							});
						},
						adminRegisterFactory : function(userName, password,
								fName, lName, role, gender, age, phoneNum,
								address, add1, add2, scope) {
							return $http(
									{
										method : 'POST',
										url : './AddEmployeeByAdmin',
										// data : 'userName=' + $scope.userName
										// + '&password=' + $scope.password,
										data : "userName=" + userName
												+ "&password=" + password
												+ "&fName=" + fName + "&lName="
												+ lName + "&role=" + role
												+ "&gender=" + gender + "&age="
												+ age + "&phoneNum=" + phoneNum
												+ "&address=" + address
												+ "&add1=" + add1 + "&add2="
												+ add2,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.adminRegisterReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error adminRegisterFactory");
							});
						},
						removeEmpFactory : function(userName, check, scope) {
							return $http(
									{
										method : 'POST',
										url : './RemoveEmployee',
										data : 'userName=' + userName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {

								switch (check) {
								case "enter":
//									alert("operation is " + check);
									scope.removeEmpByEmterReturn(data);
									break;
								case "click":
									scope.removeEmpByClickReturn(data);
									break;
								}
							}).error(function(data) {
								alert("Servlet Call Error removeEmpFactory");
							});
						},
						getInfoByIDFactory : function(userName, operation,
								scope) {
							return $http(
									{
										method : 'POST',
										url : './ViewEmpByID',
										data : 'userName=' + userName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								switch (operation) {
								case "default":
									scope.getInfoByIDReturn(data);
									break;
								case "search":
									scope.getInfoByIDReturn1(data);
									break;
								}
							}).error(function(data) {
								alert("Servlet Call Error getInfoByIDFactory");
							});
						},
						viewAllEmpFactory : function(curPage, rowPerPage, operation, scope) {
							return $http(
									{
										method : 'POST',
										url : './ViewAllEmp',
										data : "page="
												+ (parseInt(curPage) - 1)
												+ "&rowPerPage=" + rowPerPage
												+ "&info=1",
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
//										alert("operation is " + operation);
										if(operation == "viewAll"){
											scope.viewAllEmpReturn(data, curPage);
										} else {
											scope.viewAllEmpDeleteReturn(data, curPage);
										}

							}).error(function(data) {
								alert("Servlet Call Error viewAllEmpFactory");
							});
						},
						getViewAllTotalPageFactory : function(curPage,
								rowPerPage, operation, scope) {
							return $http(
									{
										method : 'POST',
										url : './GetInfo',
										data : "page="
												+ (parseInt(curPage) - 1)
												+ "&rowPerPage=" + rowPerPage
												+ "&info=viewAll",
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(
									function(data) {
										scope.getViewAllTotalReturn(curPage,
												rowPerPage, operation, data);

									}).error(function(data) {
								alert("Servlet Call Error getViewAllTotalPageFactory");
							});
						},
						getTotalEmpNumberFactory : function(curPage,
								rowPerPage, operation, scope) {
							return $http(
									{
										method : 'POST',
										url : './ViewAllEmpInfo',
										data : "operation=" + operation,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(
									function(data) {
										scope.getTotalEmpNumberReturn(curPage,
												rowPerPage, operation, data);

									}).error(function(data) {
								alert("Servlet Call Error getTotalEmpNumberFactory");
							});
						},
						managerViewAllEmpFactory : function(curPage,
								rowPerPage, scope) {
							return $http(
									{
										method : 'POST',
										url : './ManagerViewAllEmp',
										data : "page="
												+ (parseInt(curPage) - 1)
												+ "&rowPerPage=" + rowPerPage,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.managerViewAllEmpReturn(data, curPage);

							}).error(function(data) {
								alert("Servlet Call Error managerViewAllEmpFactory");
							});
						},
						getGroupNumberFactory : function(curPage, rowPerPage,
								userName, operation, scope) {

							return $http(
									{
										method : 'POST',
										url : './GetGroupNumber',
										data : "page="
												+ (parseInt(curPage) - 1)
												+ "&rowPerPage=" + rowPerPage
												+ "&userName=" + userName
												+ "&operation=" + operation,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									})
									.success(
											function(data) {

												if (operation == "getNum") {
													scope.getGroupNumberReturn(
															curPage,
															rowPerPage, data);
												} else {
													scope
															.getGroupNumberDetailReturn(
																	curPage,
																	rowPerPage,
																	data);
												}

											}).error(function(data) {
										alert("Servlet Call Error getGroupNumberFactory");
									});
						},
						createGroupFactory : function(groupID, userName, scope) {
							return $http(
									{
										method : 'POST',
										url : './CreateGroup',
										data : "groupID=" + groupID
												+ "&userName=" + userName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.createGroupReturn(data);
							}).error(function(data) {
								alert("Servlet Call Error createGroupFactory");
							});
						},
						deleteFromGroupFactory : function(userName, scope) {
							return $http(
									{
										method : 'POST',
										url : './DeleteFromGroup',
										data : "userName=" + userName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.deleteFromGroupReturn(data);
							}).error(function(data) {
								alert("Servlet Call Error deleteFromGroupFactory");
							});
						},
						dropGroupFactory : function(userName, scope) {
							return $http(
									{
										method : 'POST',
										url : './DropGroup',
										data : "userName=" + userName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
								scope.dropGroupReturn(data);
							}).error(function(data) {
								alert("Servlet Call Error dropGroupFactory");
							});
						},
						getAvailableEmpFactory : function(curPage, rowPerPage, operation, scope) {
//alert("inside getAvailableEmpFactory call " + curPage + " rowPerPage is " + rowPerPage + " operation is "
//		+ operation);
							return $http(
									{
										method : 'POST',
										url : './GetAvailableEmp',
										data : "page="
												+ (parseInt(curPage) - 1)
												+ "&rowPerPage=" + rowPerPage
												+ "&operation=" + operation,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									})
									.success(
											function(data) {
												
												if (operation == "getNum") {
													
													scope.getAvailableEmpReturn(
															curPage,
															rowPerPage, data);
												} else {
//													alert("inside success view detail!!!!!!!!!!");
//													alert(data.length);
													scope.getAvailableEmpDetailReturn(
																	curPage,
																	rowPerPage,
																	data);
												}

											}).error(function(data) {
										alert("Servlet Call Error getAvailableEmpFactory");
									});
						},
						addToGroupFactory : function(userName, managerName, scope) {

							return $http(
									{
										method : 'POST',
										url : './AddToGroup',
										data : "userName=" + userName + "&managerEmail=" + managerName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									})
									.success(
											function(data) {

													scope.addToGroupReturn(data);
												

											}).error(function(data) {
										alert("Servlet Call Error addToGroupFactory");
									});
						},
						getTotalEmpNumberFactory1 : function(curPage,
								rowPerPage, operation, scope) {
							return $http(
									{
										method : 'POST',
										url : './ViewAllEmpInfo',
										data : "operation=" + operation,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(
									function(data) {
										scope.getTotalEmpNumberReturn1(curPage,
												rowPerPage, operation, data);

									}).error(function(data) {
								alert("Servlet Call Error getTotalEmpNumberFactory");
							});
						},
						saveTimesheetsFactory : function(userName, date, endDate,
								monR, monOT, tueR, tueOT, wedR, wedOT, thuR, thuOT,
								friR, friOT, satR, satOT, sunR, sunOT, approve,
								URL, submit, totalHr, regularHr, otHr, client, scope) {
//							alert("mon regular " + monR);
							return $http(
									{
										method : 'POST',
										url : './SaveTimesheets',
										// data : 'userName=' + $scope.userName
										// + '&password=' + $scope.password,
										data : "email=" + userName + "&date=" + date 
												+ "&endDate=" + endDate 
												+ "&monR=" + monR + "&monOT=" + monOT 
												+ "&tueR=" + tueR + "&tueOT=" + tueOT 
												+ "&wedR=" + wedR + "&wedOT=" + wedOT
												+ "&thuR=" + thuR + "&thuOT=" + thuOT 
												+ "&friR=" + friR + "&friOT=" + friOT 
												+ "&satR=" + satR + "&satOT=" + satOT
												+ "&sunR=" + sunR + "&sunOT=" + sunOT 
												+ "&approve=" + approve + "&URL=" + URL
												+ "&submit=" + submit + "&totalHr=" + totalHr
												+ "&regularHr=" + regularHr + "&otHr=" + otHr
												+ "&client=" + client,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
										scope.saveTimesheetsReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error empRegisterFactory");
							});
						},
						getTimesheetsFactory : function(userName, date, operation, scope) {
//							alert("call get timesheets factory");
							return $http(
									{
										method : 'POST',
										url : './GetTimesheetData',
										// data : 'userName=' + $scope.userName
										// + '&password=' + $scope.password,
										data : "email=" + userName + "&date=" + date + "&operation=" + operation,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
//										alert("inside get timesheetsFactory" + operation);
										if(operation == "view"){
											scope.getTimesheetsViewReturn(data, date);
										} else {
										scope.getTimesheetsEditReturn(data, date);
										}

							}).error(function(data) {
								alert("Servlet Call Error empRegisterFactory");
							});
						},
						getTotalTimesheetsFactory : function(userName, scope) {
//							alert("call get total timesheets");
							return $http(
									{
										method : 'POST',
										url : './GetTotalTimesheetData',
										// data : 'userName=' + $scope.userName
										// + '&password=' + $scope.password,
										data : "email=" + userName,
										headers : {
											'Content-Type' : 'application/x-www-form-urlencoded'
										}
									}).success(function(data) {
//										alert(data.totalHr);
										scope.getTotalTimesheetsReturn(data);

							}).error(function(data) {
								alert("Servlet Call Error empRegisterFactory");
							});
						},
					};
				});

// AngularJS Controller that uses the factory
// function HelloCtrl($scope, testFactory)
// {
// $scope.fromFactory = testFactory.sayHello("World");
// }

// function callLogin($scope, $http){
// alert("call Login");
// // $http({method: 'POST', url: '/Login'}).
// $http({method: 'POST', url: '/Login?userName=' + $scope.userName +
// '&password=' + $scope.password}).
// success(function(data, status) {
// // alert(status);
// $scope.status = status;
// $scope.data = data;
// // this callback will be called asynchronously
// // when the response is available
// }).
// // error(function(data, status, headers, config) {
// error(function(data, status) {
// // called asynchronously if an error occurs
// // or server returns response with an error status.
// });
//	
// }
// controller

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
	// if (ch == 'a' || ch == 'A' || ch == 'm' || ch == 'M' ||
	// ch == 'd' || ch == 'D' || ch == 'Q' || ch == 'q' ||
	// ch == 't' || ch == 'T') return true;
	if (chLower == 'a' || chLower == 'm' || chLower == 'd' || chLower == 'q'
			|| chLower == 't')
		return true;
}

function checkIsCharacter(ch) {
	// if(ch >= 'a' && ch <= 'z')return true;
	// if(ch >= 'A' && ch <= 'Z')return true;
	// var c = /^ [A-Za-z]+$/;
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

// function routeProviderFunction($routeProvider){
// alert("inside roter provider");
// $routeProvider.when('/default',{
// templateUrl: 'login.html',
// controller: funct2
// });
// // $routeProvider.when('/default', {
// //
// //// templeUrl: 'Test.html',
// //// templateUrl: 'Test.html',
// // templateUrl: 'Login.html',
// // controller: Login
// //// controller: signupController
// // });
// // alert("after call default");
// //// $routeProvider.when('/default',{
// //// templateUrl: 'HTML/login.html',
// //// controller: funct2
// //// });
// //
// // $routeProvider.when('/Login', {
// // templateUrl: 'Login.html',
// // controller: Login
// // });
// ////
// //// $routeProvider.when('/Logout', {
// //// templateUrl: 'HTML/Logout.html',
// //// controller: Logout
// //// });
// //
// // $routeProvider.otherwise({
// // redirectTo: '/defalut'
// // });
//	
// }
//
// function funct2($scope){
// $scope.signIn=function(){
// alert("username="+$scope.username+"\npassword="+$scope.password);
// $scope.username="";
// $scope.password="";
//		
// }
// }

//
// function signupController($scope){
// //app.controller('signupController', ['$scope', function($scope) {
// $scope.submitted = false;
// $scope.signupForm = function() {
// if ($scope.signup_form.$valid) {
// // Submit as normal
// } else {
// $scope.signup_form.submitted = true;
// }
// }
// // }]);
// }

// function Logout($scope){
// alert("Logout!");
// $scope.clickLogout = function() {
// $scope.show = true;
// window.location = "#/Login";
// };
//	
// }
//
// function TestController($scope){
// $scope.message="Hello World!";
//	
// $scope.show = true;
//	
// $scope.method = function(){
// $scope.message = "Good Bye!";
// };
// }

// create service
// app.factory('email', function(){
// return (userName);
// });

// !!!! Create Service
// app.service('ServletCallService', function(){
// this.LoginFactory= function($http, userName, password, scope){
// return $http({
// method : 'POST',
// url : './Login',
// data : 'userName=' + userName + '&password=' + password,
// headers : {
// 'Content-Type' : 'application/x-www-form-urlencoded'
// }
// }). success(function(data) {
// alert("inside factory");
// alert(data);
// scope.GetLoginReturn(data);
//	        	
// // $scope.result = data.ResponseChecker;
// // $scope.role = data.Role;
// // if ($scope.result == 0){
// // $scope.resultMessage = false;
// // $scope.message = data.Message;
// // } else {
// // $scope.setLogIn();
// // }
//	        	
// // var parseJSON = JSON.parse(date);
//	        	
// }).
// error(function(data) {
// alert("Servlet Call Error");
// // $scope.data = data || "Request failed";
// // $scope.status = status;
// });
// // return "Service says \"Hello " + text + "\"";
// };
// });
app
.factory(
		'TimesheetServletCall',
		function($http) {
		return {
			checkTimesheetExistFactory : function(userName, date, scope) {
				return $http(
						{
							method : 'POST',
							url : './CheckTimesheetExist',
							data : "userName=" + userName + "&date=" + date,
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						}).success(
						function(data) {
							scope.checkTimesheetExistReturn(data);

						}).error(function(data) {
					alert("Servlet Call Error getTotalEmpNumberFactory");
				});
			},
			getTotalTimesheetFactory : function(userName, curPage, rowPerPage, operation, scope) {
				return $http(
						{
							method : 'POST',
							url : './GetTotalTimesheetData',
							data : "userName=" + userName + "&curPage="  + (parseInt(curPage) - 1) 
							+ "&rowPerPage=" + rowPerPage
									+ "&operation=" + operation,
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						}).success(
						function(data) {
							if(operation == "getTotalNumber"){
								scope.getTotalTimesheetReturn(userName, curPage, rowPerPage, operation, data);
							} else {
								scope.getTimesheetByRowPerPageReturn(data);
							}
							

						}).error(function(data) {
					alert("Servlet Call Error getTotalEmpNumberFactory");
				});
			},
			getNeedApproveTimesheetFactory : function(userName, curPage, rowPerPage, operation, scope) {
				return $http(
						{
							method : 'POST',
							url : './GetNeedApproveTimesheetData',
							data : "userName=" + userName + "&curPage="  + (parseInt(curPage) - 1) 
							+ "&rowPerPage=" + rowPerPage
									+ "&operation=" + operation,
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						}).success(
						function(data) {
							if(operation == "getTotalNumber"){
								scope.getNeedApproveTimesheetReturn(userName, curPage, rowPerPage, operation, data);
							} else {
								scope.getNeedApproveTimesheetByRowPerPageReturn(data);
							}
							

						}).error(function(data) {
					alert("Servlet Call Error getTotalEmpNumberFactory");
				});
			},
			getSubmittedTimesheetFactory : function(userName, curPage, rowPerPage, operation, scope) {
				return $http(
						{
							method : 'POST',
							url : './GetSubmittedTimesheetData',
							data : "userName=" + userName + "&curPage="  + (parseInt(curPage) - 1) 
							+ "&rowPerPage=" + rowPerPage
									+ "&operation=" + operation,
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						}).success(
						function(data) {
							if(operation == "getTotalNumber"){
								scope.getSubmittedTimesheetReturn(userName, curPage, rowPerPage, operation, data);
							} else {
								scope.getSubmittedTimesheetByRowPerPageReturn(data);
							}
							

						}).error(function(data) {
					alert("Servlet Call Error getTotalEmpNumberFactory");
				});
			},
			submitTimesheetFactory : function(userName, date, operation, scope) {
				return $http(
						{
							method : 'POST',
							url : './SubmitTimesheet',
							data : "userName=" + userName + "&date="  + date + "&operation=" + operation,
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						}).success(
						function(data) {
								scope.submitTimesheetReturn(data);
						}).error(function(data) {
					alert("Servlet Call Error getTotalEmpNumberFactory");
				});
			},
			
			
		}
		});

app
.factory(
		'ServletCallPopup',
		function($http) {
			var setLogInFactory = function(userName, role, scope) {
				return $http(
						{
							method : 'POST',
							url : './SessionLogIn',
							data : 'userName=' + userName + '&role='
									+ role,
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						})
						.success(
								function(data) {
									switch (role) {
									case "admin":
										window.location = "index.html#/AdminMenu/"
												+ userName;
										break;

									case "manager":
										window.location = "index.html#/ManagerMenu/"
												+ userName;
										break;

									default:
										window.location = "index.html#/EmployeeMenu/"
												+ userName;
										break;
									}
									// scope.setLogInReturn(data);

								}).error(function(data) {
							alert("Servlet Call Error setLogin");
						});
			};
			return {
				LoginFactory : function(userName, password, scope) {
					return $http(
							{
								method : 'POST',
								url : './Login',
								data : 'userName=' + userName
										+ '&password=' + password,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(
							function(data) {
								scope.result = data.ResponseChecker;
								scope.role = data.Role;
								if (scope.result == 0) {
									scope.resultMessage = false;
									scope.message = data.Message;
								} else {
									setLogInFactory(scope.userName,
											scope.role, scope);
									scope.cancel();
								}
								// scope.GetLoginReturn(data);
							}).error(function(data) {
						alert("Servlet Call Error login2");
					});
				},
				ckeckLogInFactory : function(userName, scope) {
					return $http(
							{
								method : 'POST',
								url : './SessionCheck',
								data : 'userName=' + userName,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.ckeckLogInReturn(data);

					}).error(function(data) {
						alert("Servlet Call Error checkLoginFactory");
					});
				},
				logOutFactory : function(userName) {
					return $http(
							{
								method : 'POST',
								url : './SessionLogOut',
								data : 'userName=' + userName,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						window.location = "index.html#/Login";

					}).error(function(data) {
						alert("Servlet Call Error LogoutFactory");
					});
				},
				empRegisterFactory : function(userName, password,
						fName, lName, role, gender, age, phoneNum,
						address, add1, add2, scope) {
					return $http(
							{
								method : 'POST',
								url : './Register',
								// data : 'userName=' + $scope.userName
								// + '&password=' + $scope.password,
								data : "userName=" + userName
										+ "&password=" + password
										+ "&fName=" + fName + "&lName="
										+ lName + "&role=" + role
										+ "&gender=" + gender + "&age="
										+ age + "&phoneNum=" + phoneNum
										+ "&address=" + address
										+ "&add1=" + add1 + "&add2="
										+ add2,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.empRegisterReturn(data);

					}).error(function(data) {
						alert("Servlet Call Error empRegisterFactory");
					});
				},
				viewGroupDetailFactory : function(groupID, scope) {
					return $http(
							{
								method : 'POST',
								url : './GetEmpByGroupID',
								data : 'groupID=' + groupID,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.viewGroupDetailReturn(data);

					}).error(function(data) {
						alert("Servlet Call Error viewGroupDetailFactory");
					});
				},
				viewGroupFactory : function(scope) {
					return $http(
							{
								method : 'POST',
								url : './GetManagerByGroup',
								data : "userName=0",
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.viewGroupReturn(data);

					}).error(function(data) {
						alert("Servlet Call Error viewGroupFactory");
					});
				},
				empRequestFactory : function(curPage, rowPerPage, scope) {
					return $http(
							{
								method : 'POST',
								url : './/RequestApprovalList',
								data : "page="
										+ (parseInt(curPage) - 1)
										+ "&rowPerPage=" + rowPerPage,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.empRequestReturn(data, curPage);

					}).error(function(data) {
						alert("Servlet Call Error empRequestFactory");
					});
				},
				getTotalPageFactory : function(curPage, rowPerPage,
						scope) {
					return $http(
							{
								method : 'POST',
								url : './GetInfo',
								data : "page="
										+ (parseInt(curPage) - 1)
										+ "&rowPerPage=" + rowPerPage
										+ "&info=requestApproval",
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(
							function(data) {
								scope.getTotalPageReturn(curPage,
										rowPerPage, data);

							}).error(function(data) {
						alert("Servlet Call Error getTotalPageFactory");
					});
				},
				approveOneFactory : function(userName, scope) {
					return $http(
							{
								method : 'POST',
								url : './ApproveOneRequest',
								// data : 'userName=' + $scope.userName
								// + '&password=' + $scope.password,
								data : "userName=" + userName,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.approveOneReturn(data);

					}).error(function(data) {
						alert("Servlet Call Error approveOneFactory");
					});
				},
				denyOneFactory : function(userName, scope) {
					return $http(
							{
								method : 'POST',
								url : './DenyOneRequest',
								// data : 'userName=' + $scope.userName
								// + '&password=' + $scope.password,
								data : "userName=" + userName,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.denyOneReturn(data);

					}).error(function(data) {
						alert("Servlet Call Error denyOneFactory");
					});
				},
				approveAllFactory : function(scope) {
					return $http(
							{
								method : 'POST',
								url : './ApproveAll',
								data : "userName=0",
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.approveAllReturn(data);

					}).error(function(data) {
						alert("Servlet Call Error approveAllFactory");
					});
				},
				denyAllFactory : function(scope) {
					return $http(
							{
								method : 'POST',
								url : './DenyAll',
								data : "userName=0",
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.denyAllReturn(data);

					}).error(function(data) {
						alert("Servlet Call Error denyAllFactory");
					});
				},
				adminRegisterFactory : function(userName, password,
						fName, lName, role, gender, age, phoneNum,
						address, add1, add2, scope) {
					return $http(
							{
								method : 'POST',
								url : './AddEmployeeByAdmin',
								// data : 'userName=' + $scope.userName
								// + '&password=' + $scope.password,
								data : "userName=" + userName
										+ "&password=" + password
										+ "&fName=" + fName + "&lName="
										+ lName + "&role=" + role
										+ "&gender=" + gender + "&age="
										+ age + "&phoneNum=" + phoneNum
										+ "&address=" + address
										+ "&add1=" + add1 + "&add2="
										+ add2,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.adminRegisterReturn(data);

					}).error(function(data) {
						alert("Servlet Call Error adminRegisterFactory");
					});
				},
				removeEmpFactory : function(userName, check, scope) {
					return $http(
							{
								method : 'POST',
								url : './RemoveEmployee',
								data : 'userName=' + userName,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {

						switch (check) {
						case "enter":
							scope.removeEmpByEmterReturn(data);
							break;
						case "click":
							scope.removeEmpByClickReturn(data);
							break;
						}
					}).error(function(data) {
						alert("Servlet Call Error removeEmpFactory");
					});
				},
				getInfoByIDFactory : function(userName, operation,
						scope) {
					return $http(
							{
								method : 'POST',
								url : './ViewEmpByID',
								data : 'userName=' + userName,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						switch (operation) {
						case "default":
							scope.getInfoByIDReturn(data);
							break;
						case "search":
							scope.getInfoByIDReturn1(data);
							break;
						}
					}).error(function(data) {
						alert("Servlet Call Error getInfoByIDFactory");
					});
				},
				viewAllEmpFactory : function(curPage, rowPerPage, scope) {
					return $http(
							{
								method : 'POST',
								url : './ViewAllEmp',
								data : "page="
										+ (parseInt(curPage) - 1)
										+ "&rowPerPage=" + rowPerPage
										+ "&info=1",
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.viewAllEmpReturn(data, curPage);

					}).error(function(data) {
						alert("Servlet Call Error viewAllEmpFactory");
					});
				},
				getViewAllTotalPageFactory : function(curPage,
						rowPerPage, operation, scope) {
					return $http(
							{
								method : 'POST',
								url : './GetInfo',
								data : "page="
										+ (parseInt(curPage) - 1)
										+ "&rowPerPage=" + rowPerPage
										+ "&info=viewAll",
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(
							function(data) {
								scope.getViewAllTotalReturn(curPage,
										rowPerPage, operation, data);

							}).error(function(data) {
						alert("Servlet Call Error getViewAllTotalPageFactory");
					});
				},
				getTotalEmpNumberFactory : function(curPage,
						rowPerPage, operation, scope) {
					return $http(
							{
								method : 'POST',
								url : './ViewAllEmpInfo',
								data : "operation=" + operation,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(
							function(data) {
								scope.getTotalEmpNumberReturn(curPage,
										rowPerPage, operation, data);

							}).error(function(data) {
						alert("Servlet Call Error getTotalEmpNumberFactory");
					});
				},
				managerViewAllEmpFactory : function(curPage,
						rowPerPage, scope) {
					return $http(
							{
								method : 'POST',
								url : './ManagerViewAllEmp',
								data : "page="
										+ (parseInt(curPage) - 1)
										+ "&rowPerPage=" + rowPerPage,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.managerViewAllEmpReturn(data, curPage);

					}).error(function(data) {
						alert("Servlet Call Error managerViewAllEmpFactory");
					});
				},
				getGroupNumberFactory : function(curPage, rowPerPage,
						userName, operation, scope) {

					return $http(
							{
								method : 'POST',
								url : './GetGroupNumber',
								data : "page="
										+ (parseInt(curPage) - 1)
										+ "&rowPerPage=" + rowPerPage
										+ "&userName=" + userName
										+ "&operation=" + operation,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							})
							.success(
									function(data) {

										if (operation == "getNum") {
											scope.getGroupNumberReturn(
													curPage,
													rowPerPage, data);
										} else {
											scope
													.getGroupNumberDetailReturn(
															curPage,
															rowPerPage,
															data);
										}

									}).error(function(data) {
								alert("Servlet Call Error getGroupNumberFactory");
							});
				},
				createGroupFactory : function(groupID, userName, scope) {
					return $http(
							{
								method : 'POST',
								url : './CreateGroup',
								data : "groupID=" + groupID
										+ "&userName=" + userName,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.createGroupReturn(data);
					}).error(function(data) {
						alert("Servlet Call Error createGroupFactory");
					});
				},
				deleteFromGroupFactory : function(userName, scope) {
					return $http(
							{
								method : 'POST',
								url : './DeleteFromGroup',
								data : "userName=" + userName,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.deleteFromGroupReturn(data);
					}).error(function(data) {
						alert("Servlet Call Error deleteFromGroupFactory");
					});
				},
				dropGroupFactory : function(userName, scope) {
					return $http(
							{
								method : 'POST',
								url : './DropGroup',
								data : "userName=" + userName,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(function(data) {
						scope.dropGroupReturn(data);
					}).error(function(data) {
						alert("Servlet Call Error dropGroupFactory");
					});
				},
//				getAvailableEmpFactory : function(curPage, rowPerPage, operation, scope) {
//alert("inside getAvailableEmpFactory call");
//					return $http(
//							{
//								method : 'POST',
//								url : './GetAvailableEmp',
//								data : "page="
//										+ (parseInt(curPage) - 1)
//										+ "&rowPerPage=" + rowPerPage
//										+ "&operation=" + operation,
//								headers : {
//									'Content-Type' : 'application/x-www-form-urlencoded'
//								}
//							})
//							.success(
//									function(data) {
//
//										if (operation == "getNum") {
//											
//											scope.getAvailableEmpReturn(
//													curPage,
//													rowPerPage, data);
//										} else {
//											alert("inside success view detail!!!!!!!!!!!1");
//											scope.getAvailableEmpDetailReturn(
//															curPage,
//															rowPerPage,
//															data);
//										}
//
//									}).error(function(data) {
//								alert("Servlet Call Error getAvailableEmpFactory");
//							});
//				},
				addToGroupFactory : function(userName, managerName, scope) {

					return $http(
							{
								method : 'POST',
								url : './AddToGroup',
								data : "userName=" + userName + "&managerEmail=" + managerName,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							})
							.success(
									function(data) {

											scope.addToGroupReturn(data);
										

									}).error(function(data) {
								alert("Servlet Call Error addToGroupFactory");
							});
				},
				getTotalEmpNumberFactory1 : function(curPage,
						rowPerPage, operation, scope) {
					return $http(
							{
								method : 'POST',
								url : './ViewAllEmpInfo',
								data : "operation=" + operation,
								headers : {
									'Content-Type' : 'application/x-www-form-urlencoded'
								}
							}).success(
							function(data) {
								scope.getTotalEmpNumberReturn1(curPage,
										rowPerPage, operation, data);

							}).error(function(data) {
						alert("Servlet Call Error getTotalEmpNumberFactory");
					});
				},
				

			};
		});
