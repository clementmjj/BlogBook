myApp.controller("ForumController", function($scope, $http, $location,
		$rootScope, $cookieStore) {

	$scope.forum;

	$scope.addForum = function() {
		$scope.forum = {
			'forumTitle' : '',
			'forumContent' : '',
			'forumStatus' : '',
			'status' : ''
		};
	};

});