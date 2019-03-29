myApp.controller("FriendController", function($scope, $http, $location,
		$rootScope) {

	$scope.portNo = location.port;
	$scope.friendList;
	$scope.pendingFriendList;
	$scope.suggestedFriendList;

	// retrieving friend list
	$http.get(
			'http://localhost:' + location.port
					+ '/BlogBookMiddleware/listFriends/'
					+ $rootScope.currentUser.username).then(function(response) {
		$scope.friendList = response.data;
		console.log("Friend list retrieved");
	});

	// retrieving friend requests
	$http.get(
			'http://localhost:' + location.port
					+ '/BlogBookMiddleware/listPendingFriends/'
					+ $rootScope.currentUser.username).then(function(response) {
		$scope.pendingFriendList = response.data;
		console.log("Friend requests retrieved");
	});

	// retrieving suggested friend list
	$http.get(
			'http://localhost:' + location.port
					+ '/BlogBookMiddleware/listSuggestedFriends/'
					+ $rootScope.currentUser.username).then(function(response) {
		$scope.suggestedFriendList = response.data;
		console.log("Suggested friend list retrieved");
	});

	$scope.acceptFriendReq = function(friendReqId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/acceptFriendReq/' + friendReqId)
				.then(function(response) {
					console.log("Friend request accepted");
				});
	};

	$scope.rejectFriendReq = function(friendReqId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/rejectFriendReq/' + friendReqId)
				.then(function(response) {
					console.log("Friend request rejected");
				});
	};

});