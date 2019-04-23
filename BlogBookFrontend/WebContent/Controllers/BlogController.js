myApp.controller("BlogController", function($scope, $http, $location, $rootScope, $cookieStore) {

	$scope.blogList = [];
	$scope.userBlogList = [];
	$scope.blogDetail;
	$scope.editBlogInfo;
	$scope.existingBlogLike = undefined;
	$scope.existingBlogDislike = undefined;

	$scope.blog = {
		'blogTitle' : '',
		'blogContent' : '',
		'username' : '',
		'status' : '',
		'likes' : 0,
		'dislikes' : 0
	};


	$scope.addBlog = function() {
		$scope.blog.username = $rootScope.currentUser.username;
		$scope.blog.status = 'P';
		$http.post('http://localhost:' + location.port + '/BlogBookMiddleware/addBlog', $scope.blog).then(function(response) {
			
			$("#addBlogModal").modal("hide");
			$("#addBlogSuccessfulModal").on('hidden.bs.modal', function(){
			    location.reload();
			  });
			$("#addBlogSuccessfulModal").modal("show");
			console.log('Blog added');
		}).catch(function(e){
			console.log(e);
			var errorList=e.data;
			var allErrorElements=document.getElementsByClassName("error");
			for(i=0;i<allErrorElements.length;i++)
			  {
				allErrorElements[i].innerHTML="";
			  }
			errorList.forEach(function(error){
				var element=document.getElementById(error.fieldName+"Error");
				if(!element.innerHTML)
					element.innerHTML=error.errorMessage;
				else
					element.innerHTML+="<br>"+error.errorMessage;
			});
		});
	}

	$scope.showEditBlog = function() {
		var urlText = window.location.href;
		var editBlogId = urlText.substring(urlText.indexOf("=") + 1);
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getBlog/' + editBlogId).then(function(response) {
			$scope.editBlogInfo = response.data;
			delete $scope.editBlogInfo.createdDate;
		}).catch(function(e){
			console.log(e);
		});
	}

	$scope.updateBlog = function() {
		$scope.editBlogInfo.status = 'P';
		$http.post('http://localhost:' + location.port + '/BlogBookMiddleware/updateBlog', $scope.editBlogInfo).then(function(response) {
			$("#updateBlogSuccessfulModal").on('hidden.bs.modal', function(){
			    $location.path("/blog");
			    $scope.$apply();
			  });
			$("#updateBlogSuccessfulModal").modal("show");
			console.log("Blog updated.");
		}).catch(function(e){
			console.log(e);
			var errorList=e.data;
			var allErrorElements=document.getElementsByClassName("error");
			for(i=0;i<allErrorElements.length;i++)
			  {
				allErrorElements[i].innerHTML="";
			  }
			errorList.forEach(function(error){
				var element=document.getElementById(error.fieldName+"Error");
				if(!element.innerHTML)
					element.innerHTML=error.errorMessage;
				else
					element.innerHTML+="<br>"+error.errorMessage;
			});
		});
	}

	$scope.getBlogs = function() {
		// get all approved blogs
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getBlogList').then(function(response) {

			$scope.blogList = response.data;
			$scope.blogList.sort(function(a, b) {
				return new Date(a.createdDate) - new Date(b.createdDate);
			});

			console.log("Blog list retrieved.");
		}).catch(function(e){
			console.log(e);
		});
		// get all blogs created by current user
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getUserBlogList/' + $rootScope.currentUser.username).then(function(response) {
			$scope.userBlogList = response.data;
			$scope.userBlogList.sort(function(a, b) {
				return new Date(a.createdDate) - new Date(b.createdDate);
			});
			console.log($rootScope.currentUser.username + "'s blog list retrieved.");
		}).catch(function(e){
			console.log(e);
		});
	};

	$scope.approveBlog = function(blogId) {
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/approveBlog/' + blogId).then(function(response) {
			console.log("Blog approved");
			location.reload();
		});
	}

	$scope.rejectBlog = function(blogId) {
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/rejectBlog/' + blogId).then(function(response) {
			console.log("Blog rejected");
			location.reload();
		});
	}

	$scope.deleteBlog = function(blogId) {
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/deleteBlog/' + blogId).then(function(response) {
			console.log("Blog deleted");
			var blogRow = document.getElementById("blogRow" + blogId);
			blogRow.parentNode.removeChild(blogRow);
		});
	}

	$scope.showBlog = function(blogId) {
		if($location.path()!='/showBlog')
			{
			$cookieStore.put("showBlogId",blogId);
			$location.path("/showBlog");
			}
		else
			{
				blogId=$cookieStore.get("showBlogId");
				$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getBlog/' + blogId).then(function(response) {
					$scope.blogDetail = response.data;
				}).catch(function(e){
					console.log(e);
				});
				// get existing Like
				$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getBlogLike/' + blogId + '/' + $rootScope.currentUser.username).then(function(response) {
					if (response.data) {
						$scope.existingBlogLike = response.data;
						console.log("Existing like retrieved.");
					}
				}).catch(function(e){
					console.log(e);
				});
				// get existing Dislike
				$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getBlogDislike/' + blogId + '/' + $rootScope.currentUser.username).then(function(response) {
					if (response.data) {
						$scope.existingBlogDislike = response.data;
						console.log("Existing dislike retrieved.");
					}
				}).catch(function(e){
					console.log(e);
				});
			}
	}

	$scope.likeClick = function() {
		// check if there is an existing like by user
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getBlogLike/' + $cookieStore.get("showBlogId") + '/' + $rootScope.currentUser.username).then(
				function(response) {
					var existingLike = response.data;
					if (!existingLike) {
						// add new like
						var blogLike = {
							'blogId' : $cookieStore.get("showBlogId"),
							'username' : $rootScope.currentUser.username
						};
						$http.post('http://localhost:' + location.port + '/BlogBookMiddleware/addBlogLike', blogLike).then(function() {
							document.getElementById("blog-like-count").innerHTML = Number(document.getElementById("blog-like-count").innerHTML) + 1;
							console.log("Blog Liked.");
						});
						// increment blog like value
						$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/incrementLike/' + $cookieStore.get("showBlogId"));
						// check if there is an existing
						// dislike by user
						$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getBlogDislike/' + $cookieStore.get("showBlogId") + '/' + $rootScope.currentUser.username).then(
								function(response) {
									var existingDislike = response.data;
									if (existingDislike) {
										// delete
										// blog
										// dislike
										$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/deleteBlogDislike/' + existingDislike.blogDislikeId).then(function() {
											document.getElementById("blog-dislike-count").innerHTML = Number(document.getElementById("blog-dislike-count").innerHTML) - 1;
											console.log("Dislike removed.");
										});
										// decrement
										// blog
										// dislikes
										// value
										$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/decrementDislike/' + $cookieStore.get("showBlogId"));
									}
								});
					} else {
						// delete existing like
						$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/deleteBlogLike/' + existingLike.blogLikeId).then(function() {
							document.getElementById("blog-like-count").innerHTML = Number(document.getElementById("blog-like-count").innerHTML) - 1;
							console.log("Existing like removed.");
						});
						// decrement blog like value
						$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/decrementLike/' + $cookieStore.get("showBlogId"));
					}
				});
	};

	$scope.dislikeClick = function() {
		// check if there is an existing dislike by user
		$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getBlogDislike/' + $cookieStore.get("showBlogId") + '/' + $rootScope.currentUser.username).then(function(response) {
			var existingDislike = response.data;
			if (!existingDislike) {
				// add new dislike
				var blogDislike = {
					'blogId' : $cookieStore.get("showBlogId"),
					'username' : $rootScope.currentUser.username
				};
				$http.post('http://localhost:' + location.port + '/BlogBookMiddleware/addBlogDislike', blogDislike).then(function() {
					document.getElementById("blog-dislike-count").innerHTML = Number(document.getElementById("blog-dislike-count").innerHTML) + 1;
					console.log("Blog disliked.");
				});
				// increment blog dislike value
				$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/incrementDislike/' + $cookieStore.get("showBlogId"));
				// check if there is an existing
				// like by user
				$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/getBlogLike/' + $cookieStore.get("showBlogId") + '/' + $rootScope.currentUser.username).then(function(response) {
					var existingLike = response.data;
					if (existingLike) {
						// delete
						// blog
						// like
						$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/deleteBlogLike/' + existingLike.blogLikeId).then(function() {
							document.getElementById("blog-like-count").innerHTML = Number(document.getElementById("blog-like-count").innerHTML) - 1;
							console.log("Like removed.");
						});
						// decrement
						// blog
						// likes
						// value
						$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/decrementLike/' + $cookieStore.get("showBlogId"));
					}
				});
			} else {
				// delete existing dislike
				$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/deleteBlogDislike/' + existingDislike.blogDislikeId).then(function() {
					document.getElementById("blog-dislike-count").innerHTML = Number(document.getElementById("blog-dislike-count").innerHTML) - 1;
					console.log("Existing dislike removed.");
				});
				// decrement blog dislike value
				$http.get('http://localhost:' + location.port + '/BlogBookMiddleware/decrementDislike/' + $cookieStore.get("showBlogId"));
			}
		});
	};
});

function blogMenuSwitch(tabId) {
	var tabPaneAllBlogs = document.getElementById("tabPane-AllBlogs");
	var tabPaneYourBlogs = document.getElementById("tabPane-YourBlogs");
	switch (tabId) {
	case "tab-AllBlogs":
		tabPaneYourBlogs.style.display = "none";
		tabPaneAllBlogs.style.display = "";
		break;
	case "tab-YourBlogs":
		tabPaneAllBlogs.style.display = "none";
		tabPaneYourBlogs.style.display = "";
		break;
	}
}