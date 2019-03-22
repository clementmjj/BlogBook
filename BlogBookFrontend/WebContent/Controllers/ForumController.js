myApp.controller("ForumController", function($scope, $http, $location,
		$rootScope, $cookieStore) {

	$scope.forum = {
		'forumTitle' : '',
		'forumContent' : '',
		'username' : '',
		'status' : ''
	};
	$scope.forumDetail;
	$scope.editForumInfo;
	
	$scope.addForum = function() {
		console.log('Adding forum...');
		$scope.forum.username = $rootScope.currentUser.username;
		$scope.forum.status = 'NA';
		$http.post(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/addForum', $scope.forum).then(
				function(response) {
					console.log('Forum added');
					console.log(response.data);
					$location.path('/forumList');
				});
	}

	$scope.showEditForum = function() {
		var urlText = window.location.href;
		var editForumId = urlText.substring(urlText.indexOf("=") + 1);
		console.log("getting forum...");
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/getForum/' + editForumId).then(
				function(response) {
					$scope.editForumInfo = response.data;
					console.log($scope.editForumInfo);
					delete $scope.editForumInfo.createdDate;
				});
	}

	$scope.updateForum = function() {
		console.log("updating forum...");
		$http.post(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/updateForum',
				$scope.editForumInfo).then(function(response) {
			console.log(response.data);
			$location.path("/forumList");
		});
	}

	$scope.getForumList = function() {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/getForumList').then(
				function(response) {
					console.log('Loading all forums')
					$scope.forumList = response.data;
					console.log($scope.forumList);
				});
	}

	$scope.approveForum = function(forumId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/approveForum/' + forumId).then(
				function(response) {
					console.log("Forum approved");
				});
	}

	$scope.rejectForum = function(forumId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/rejectForum/' + forumId).then(
				function(response) {
					console.log("Forum rejected");
				});
	}

	$scope.deleteForum = function(forumId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/deleteForum/' + forumId).then(
				function(response) {
					console.log("Forum deleted");
				});
	}

	$scope.showForum = function(forumId) {
		if (forumId == undefined)
			forumId = $cookieStore.get("showForumId");
		if ($cookieStore.get("showForumId") == undefined) {
			$cookieStore.put("showForumId", forumId);
		} else if ($cookieStore.get("showForumId") != forumId) {
			$cookieStore.put("showForumId", forumId);
		} else {
			$http.get(
					'http://localhost:' + location.port
							+ '/BlogBookMiddleware/getForum/'
							+ $cookieStore.get("showForumId")).then(
					function(response) {
						$scope.forumDetail = response.data;
					});
		}
	}
});