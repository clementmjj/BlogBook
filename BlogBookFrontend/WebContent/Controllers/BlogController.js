myApp.controller("BlogController", function($scope, $http, $location,
		$rootScope) {

	$scope.blog = {
		'blogTitle' : '',
		'blogContent' : '',
		'username' : '',
		'status' : '',
		'likes' : 0,
		'dislikes' : 0
	}
	$scope.blogList;

	$scope.addBlog = function() {
		console.log('Adding blog...');
		$scope.blog.username = $rootScope.currentUser.username;
		$scope.blog.status = 'NA';
		$http.post('http://localhost:8079/BlogBookMiddleware/addBlog',
				$scope.blog).then(function(response) {
			console.log('Blog added');
			console.log(response.data);
			$location.path('/');
		});
	}

	function getBlogList() {
		$http.get('http://localhost:8079/BlogBookMiddleware/getBlogList').then(
				function(response) {
					console.log('Loading all blogs')
					$scope.blogList = response.data;
					console.log($scope.blogList);
				});
	}
	getBlogList();

	$scope.approveBlog = function(blogId) {
		console.log('Approving blog...');
		$http.get(
				'http://localhost:8079/BlogBookMiddleware/approveBlog/'
						+ blogId).then(function(response) {

		});
	}

	$scope.rejectBlog = function(blogId) {
		console.log('Rejecting blog...');
		$http
				.get(
						'http://localhost:8079/BlogBookMiddleware/rejectBlog/'
								+ blogId).then(function(response) {

				});
	}

	$scope.deleteBlog = function(blogId) {
		console.log('Deleting blog...');
		$http
				.get(
						'http://localhost:8079/BlogBookMiddleware/deleteBlog/'
								+ blogId).then(function(response) {

				});
	}

	$scope.showBlog = function(blogId) {
		console.log('Getting blog ' + blogId + ' ...');
		$http.get('http://localhost:8079/BlogBookMiddleware/getBlog/' + blogId)
				.then(function(response) {
					$rootScope.blogDetail = response.data;
					$location.path('/showBlog');
				});
	}

	$scope.incrementLike = function(blogId) {
		console.log('Incrementing like of blog ' + blogId + ' ...');
		$http.get(
				'http://localhost:8079/BlogBookMiddleware/incrementLike/'
						+ blogId).then(function(response) {
		});
	}

	$scope.incrementDislike = function(blogId) {
		console.log('Incrementing dislike of blog ' + blogId + ' ...');
		$http.get(
				'http://localhost:8079/BlogBookMiddleware/incrementDislike/'
						+ blogId).then(function(response) {
		});
	}
});