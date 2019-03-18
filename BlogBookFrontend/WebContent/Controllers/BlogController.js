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
		$http.post('http://localhost:8081/BlogBookMiddleware/addBlog',
				$scope.blog).then(function(response) {
			console.log('Blog added');
			console.log(response.data);
			$location.path('/');
		});
	}

	/*
	  $scope.getBlogList=function(){
	  $http.get('http://localhost:8081/BlogBookMiddleware/getBlogList').then(
	  function(response) { console.log('Loading all blogs') $scope.blogList =
	  response.data; console.log($scope.blogList); $location.path('/blogList');
	  }); }
	 */
	function getBlogList() {
		$http.get('http://localhost:8081/BlogBookMiddleware/getBlogList').then(
				function(response) {
					console.log('Loading all blogs')
					$scope.blogList = response.data;
					console.log($scope.blogList);
				});
	}
	getBlogList();
});