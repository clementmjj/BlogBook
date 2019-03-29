myApp.controller("NotificationController", function($scope, $http, $location,
		$rootScope) {

	$scope.notificationList;

	$http.get(
			'http://localhost:' + location.port
					+ '/BlogBookMiddleware/getNotifications/'
					+ $rootScope.currentUser.username).then(function(response) {
		$scope.notificationList = response.data;
		console.log("Notification list retrieved.");
	});

	$scope.deleteNotification = function(notificationId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/deleteNotification/'
						+ notificationId).then(function(response) {
			console.log("Notification deleted.");
		});
	};

});