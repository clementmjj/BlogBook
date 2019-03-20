myApp.controller("BlogCommentController", function($scope, $http, $location,
		$rootScope, $cookieStore) {
	
	$scope.blogId = $cookieStore.get("showBlogId");
	$scope.blogComment = {
		'blogId' : $scope.blogId,
		'commentMessage' : '',
		'username' : ''
	}

	$scope.addBlogComment = function() {
		$scope.blogComment.username = $rootScope.currentUser.username;
		$http.post(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/addBlogComment',
				$scope.blogComment).then(function(response) {
			console.log('Blog comment added');
			console.log(response.data);
		});
	}

	$scope.getBlogCommentList = function(blogId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/getBlogCommentList/'
						+ $scope.blogId, $scope.blogComment).then(
				function(response) {
					$scope.blogCommentList = response.data;
					console.log($scope.blogCommentList);
				});
	}

});