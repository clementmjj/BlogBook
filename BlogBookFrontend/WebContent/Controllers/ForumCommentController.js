myApp.controller("ForumCommentController", function($scope, $http, $location,
		$rootScope, $cookieStore, $compile) {
	
	$scope.blogComment = {
		'blogId' : $scope.blogId,
		'commentMessage' : '',
		'username' : ''
	};

});