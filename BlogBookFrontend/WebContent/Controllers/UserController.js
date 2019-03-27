myApp.controller("UserController", function($scope, $http, $location,
		$rootScope, $cookieStore) {

	$scope.profilePic;
	$scope.prifilePicUrl;
	if ($rootScope.currentUser) {
		$scope.prifilePicUrl = "http://localhost:" + location.port
				+ "/BlogBookMiddleware/showProfilePic/"
				+ $rootScope.currentUser.username;

		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/getProfilePic/'
						+ $rootScope.currentUser.username).then(
				function(response) {
					$scope.profilePic = response.data;
				});
	}
	;

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
					console.log('Registered');
					$location.path('/login');
				})
	};

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
	};

	$scope.logout = function() {
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetails');
		$location.path('/');
	};

	$scope.removeProfilePic = function() {
		$http.get('http://localhost:' + location.port
				+ '/BlogBookMiddleware/deleteProfilePic/'
				+ $rootScope.currentUser.username);
	};
});