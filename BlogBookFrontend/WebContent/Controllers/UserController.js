myApp.controller("UserController", function($scope, $http, $location,
		$rootScope, $cookieStore) {

	$scope.profilePic;
	$scope.prifilePicUrl;
	$scope.removeProfilePicUrl;

	if ($rootScope.currentUser) {
		// setting the url of the current users profile picture
		$scope.prifilePicUrl = "http://localhost:" + location.port
				+ "/BlogBookMiddleware/showProfilePic/"
				+ $rootScope.currentUser.username;

		// retrieving the profile picture
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/getProfilePic/'
						+ $rootScope.currentUser.username).then(
				function(response) {
					$scope.profilePic = response.data;
					if ($scope.profilePic)
						$scope.removeProfilePicUrl = "http://localhost:"
								+ location.port
								+ "/BlogBookMiddleware/deleteProfilePic/"
								+ $rootScope.currentUser.username;
				});
	}

	$scope.user = {
		'username' : '',
		'password' : '',
		'firstName' : '',
		'lastName' : '',
		'email' : '',
		'role' : '',
		'status' : '',
		'isOnline' : ''
	};

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
					console.log('User registered');
					$location.path('/login');
				})
	};

	$scope.loginCheck = function() {
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
								$cookieStore.put('userDetails',
										$rootScope.currentUser);
								$location.path('/');
							});
				});
	};

	$scope.logout = function() {
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetails');
		$location.path('/');
	};

});