myApp.controller("FriendController", function($scope, $http, $location, $rootScope) {
	$scope.friendList;
	$scope.friendRequests;
	$scope.suggestedFriendList;

	// retrieving friend list
	$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/listFriends/' + $rootScope.currentUser.username).then(function(response) {
		$scope.friendList = response.data;
		$scope.friendList.forEach(function(friend) {
			$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getProfilePic/' + friend.username).then(function(response) {
				if (response.data) {
					friend.profilePicUrl = 'http://localhost:' + location.port + '/BlogBookMiddleware/showProfilePic/' + friend.username;
				} else {
					friend.profilePicUrl = 'http://localhost:' + location.port + '/BlogBookFrontend/Resources/Images/empty-profile-pic.png';
				}
			});
		});
		$scope.friendList.forEach(function(friend) {
			$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getUser/' + friend.username).then(function(response) {
				friend.firstName = response.data.firstName;
				friend.lastName = response.data.lastName;
			});
		});
		console.log("Friend list retrieved");
	});

	// retrieving friend requests
	$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/listPendingFriends/' + $rootScope.currentUser.username).then(function(response) {
		$scope.friendRequests = response.data;
		$scope.friendRequests.forEach(function(friend) {
			$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getProfilePic/' + friend.username).then(function(response) {
				if (response.data) {
					friend.profilePicUrl = 'http://localhost:' + location.port + '/BlogBookMiddleware/showProfilePic/' + friend.username;
				} else {
					friend.profilePicUrl = 'http://localhost:' + location.port + '/BlogBookFrontend/Resources/Images/empty-profile-pic.png';
				}
			});
		});

		$scope.friendRequests.forEach(function(friend) {
			$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getUser/' + friend.username).then(function(response) {
				friend.firstName = response.data.firstName;
				friend.lastName = response.data.lastName;
			});
		});
		console.log("Friend requests retrieved");
	});

	// retrieving suggested friend list
	$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/listSuggestedFriends/' + $rootScope.currentUser.username).then(function(response) {
		$scope.suggestedFriendList = response.data;
		$scope.suggestedFriendList.forEach(function(friend) {
			$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getProfilePic/' + friend.username).then(function(response) {
				if (response.data) {
					friend.profilePicUrl = 'http://localhost:' + location.port + '/BlogBookMiddleware/showProfilePic/' + friend.username;
				} else {
					friend.profilePicUrl = 'http://localhost:' + location.port + '/BlogBookFrontend/Resources/Images/empty-profile-pic.png';
				}
			});
		});

		$scope.suggestedFriendList.forEach(function(friend) {
			$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getUser/' + friend.username).then(function(response) {
				friend.firstName = response.data.firstName;
				friend.lastName = response.data.lastName;
			});
		});
		console.log("Suggested friend list retrieved");
	});

	$scope.sendFriendReq = function(friendUsername) {
		var newFriendReq = {
			"username" : $rootScope.currentUser.username,
			"friendUsername" : friendUsername,
			"status" : "P"
		};
		$http.post('http://localhost:' + location.port + '/BlogBookMiddleware/sendFriendReq/', newFriendReq).then(function(response) {
			console.log("Friend request sent");
			location.reload();
		});
	};

	$scope.acceptFriendReq = function(friendReqId) {
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/acceptFriendReq/' + friendReqId).then(function(response) {
			console.log("Friend request accepted");
			location.reload();
		});
	};

	$scope.rejectFriendReq = function(friendReqId) {
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/rejectFriendReq/' + friendReqId).then(function(response) {
			console.log("Friend request rejected");
			location.reload();
		});
	};

	$scope.unfriend = function(friendUsername) {
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/unfriend/' + $rootScope.currentUser.username + '/' + friendUsername).then(function(response) {
			console.log("Unfriended");
			location.reload();
		});
	};

});