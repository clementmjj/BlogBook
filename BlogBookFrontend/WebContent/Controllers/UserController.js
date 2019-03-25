myApp.controller("UserController", function($scope, $http, $location,
		$rootScope, $cookieStore) {
	
	$scope.user = {
		'username' : '',
		'password' : '',
		'firstName' : '',
		'lastName' : '',
		'email' : '',
		'role' : '',
		'status' : '',
		'isOnline' : ''
	}

	$scope.register = function() {
		console.log('Registering user...');
		$scope.user.role = 'Student';
		$scope.user.status = 'A';
		$scope.user.isOnline = 'Off';
		console.log($scope.user);
		$http.post(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/registerUser', $scope.user)
				.then(function(response) {
					console.log('Registered');
					$location.path('/login');
				})
	}

	$scope.loginCheck = function() {
		console.log('Logging in user...');
		$http.post(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/checkLogin', $scope.user).then(
				function(response) {
					console.log('login successfull');
					$http.get(
							'http://localhost:' + location.port
									+ '/BlogBookMiddleware/getUser/'
									+ $scope.user.username).then(
							function(response) {
								$rootScope.currentUser = response.data;
								console.log($rootScope.currentUser);
								$cookieStore.put('userDetails',
										$rootScope.currentUser);
								$location.path('/');
							});

				});
	}

	$scope.logout = function() {
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetails');
		$location.path('/');
	}

});