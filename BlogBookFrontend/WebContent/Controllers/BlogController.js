myApp.controller("BlogController", function($scope, $http, $location,
		$rootScope, $cookieStore) {

	$scope.blog = {
		'blogTitle' : '',
		'blogContent' : '',
		'username' : '',
		'status' : '',
		'likes' : 0,
		'dislikes' : 0
	}
	$scope.blogDetail;
	$scope.addBlog = function() {
		console.log('Adding blog...');
		$scope.blog.username = $rootScope.currentUser.username;
		$scope.blog.status = 'NA';
		$http.post(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/addBlog', $scope.blog).then(
				function(response) {
					console.log('Blog added');
					console.log(response.data);
					$location.path('/blogList');
				});
	}

	$scope.getBlogList = function() {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/getBlogList').then(
				function(response) {
					console.log('Loading all blogs')
					$scope.blogList = response.data;
					console.log($scope.blogList);
				});
	}

	$scope.approveBlog = function(blogId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/approveBlog/' + blogId).then(
				function(response) {
				});
	}

	$scope.rejectBlog = function(blogId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/rejectBlog/' + blogId).then(
				function(response) {
				});
	}

	$scope.deleteBlog = function(blogId) {
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/deleteBlog/' + blogId).then(
				function(response) {
				});
	}

	$scope.showBlog = function(blogId) {
		if (blogId == undefined)
			blogId = $cookieStore.get("showBlogId");
		if ($cookieStore.get("showBlogId") == undefined) {
			$cookieStore.put("showBlogId", blogId);
		} else if ($cookieStore.get("showBlogId") != blogId) {
			$cookieStore.put("showBlogId", blogId);
		} else {
			$http.get(
					'http://localhost:' + location.port
							+ '/BlogBookMiddleware/getBlog/'
							+ $cookieStore.get("showBlogId")).then(
					function(response) {
						$scope.blogDetail = response.data;
					});
		}
	}

	$scope.incrementLike = function(blogId) {
		console.log('Incrementing like of blog ' + blogId + ' ...');
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/incrementLike/' + blogId).then(
				function(response) {
				});
	}

	$scope.incrementDislike = function(blogId) {
		console.log('Incrementing dislike of blog ' + blogId + ' ...');
		$http.get(
				'http://localhost:' + location.port
						+ '/BlogBookMiddleware/incrementDislike/' + blogId)
				.then(function(response) {
				});
	}
});