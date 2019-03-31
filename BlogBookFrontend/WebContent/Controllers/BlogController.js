myApp
		.controller(
				"BlogController",
				function($scope, $http, $location, $rootScope, $cookieStore) {

					$scope.blog = {
						'blogTitle' : '',
						'blogContent' : '',
						'username' : '',
						'status' : '',
						'likes' : 0,
						'dislikes' : 0
					};
					$scope.blogDetail;
					$scope.editBlogInfo;

					$scope.addBlog = function() {
						$scope.blog.username = $rootScope.currentUser.username;
						$scope.blog.status = 'NA';
						$http.post(
								'http://localhost:' + location.port
										+ '/BlogBookMiddleware/addBlog',
								$scope.blog).then(function(response) {
							console.log('Blog added');
							$location.path('/blogList');
						});
					}

					$scope.showEditBlog = function() {
						var urlText = window.location.href;
						var editBlogId = urlText
								.substring(urlText.indexOf("=") + 1);
						$http.get(
								'http://localhost:' + location.port
										+ '/BlogBookMiddleware/getBlog/'
										+ editBlogId).then(function(response) {
							$scope.editBlogInfo = response.data;
							delete $scope.editBlogInfo.createdDate;
						});
					}

					$scope.updateBlog = function() {
						$http.post(
								'http://localhost:' + location.port
										+ '/BlogBookMiddleware/updateBlog',
								$scope.editBlogInfo).then(function(response) {
							console.log("Blog updated.");
							$location.path("/blogList");
						});
					}

					$scope.getBlogList = function() {
						$http.get(
								'http://localhost:' + location.port
										+ '/BlogBookMiddleware/getBlogList')
								.then(function(response) {
									$scope.blogList = response.data;
									console.log("Blog list retrieved.");
								});
					}

					$scope.approveBlog = function(blogId) {
						$http.get(
								'http://localhost:' + location.port
										+ '/BlogBookMiddleware/approveBlog/'
										+ blogId).then(function(response) {
							console.log("Blog approved");
						});
					}

					$scope.rejectBlog = function(blogId) {
						$http.get(
								'http://localhost:' + location.port
										+ '/BlogBookMiddleware/rejectBlog/'
										+ blogId).then(function(response) {
							console.log("Blog rejected");
						});
					}

					$scope.deleteBlog = function(blogId) {
						$http.get(
								'http://localhost:' + location.port
										+ '/BlogBookMiddleware/deleteBlog/'
										+ blogId).then(function(response) {
							console.log("Blog deleted");
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
											+ $cookieStore.get("showBlogId"))
									.then(function(response) {
										$scope.blogDetail = response.data;
									});
						}
					}

					$scope.likeClick = function() {
						// check if there is an existing like by user
						$http
								.get(
										'http://localhost:'
												+ location.port
												+ '/BlogBookMiddleware/getBlogLike/'
												+ $cookieStore
														.get("showBlogId")
												+ '/'
												+ $rootScope.currentUser.username)
								.then(
										function(response) {
											var existingLike = response.data;
											if (!existingLike) {
												// add new like
												var blogLike = {
													'blogId' : $cookieStore
															.get("showBlogId"),
													'username' : $rootScope.currentUser.username
												};
												$http
														.post(
																'http://localhost:'
																		+ location.port
																		+ '/BlogBookMiddleware/addBlogLike',
																blogLike);
												// increment blog like value
												$http
														.get('http://localhost:'
																+ location.port
																+ '/BlogBookMiddleware/incrementLike/'
																+ $cookieStore
																		.get("showBlogId"));
												// check if there is an existing
												// dislike by user
												$http
														.get(
																'http://localhost:'
																		+ location.port
																		+ '/BlogBookMiddleware/getBlogDislike/'
																		+ $cookieStore
																				.get("showBlogId")
																		+ '/'
																		+ $rootScope.currentUser.username)
														.then(
																function(
																		response) {
																	var existingDislike = response.data;
																	if (existingDislike) {
																		// delete
																		// blog
																		// dislike
																		$http
																				.get('http://localhost:'
																						+ location.port
																						+ '/BlogBookMiddleware/deleteBlogDislike/'
																						+ existingDislike.blogDislikeId);
																		// decrement
																		// blog
																		// dislikes
																		// value
																		$http
																				.get('http://localhost:'
																						+ location.port
																						+ '/BlogBookMiddleware/decrementDislike/'
																						+ $cookieStore
																								.get("showBlogId"));
																	}
																});
											} else {
												// delete existing like
												$http
														.get('http://localhost:'
																+ location.port
																+ '/BlogBookMiddleware/deleteBlogLike/'
																+ existingLike.blogLikeId);
												// decrement blog like value
												$http
														.get('http://localhost:'
																+ location.port
																+ '/BlogBookMiddleware/decrementLike/'
																+ $cookieStore
																		.get("showBlogId"));
											}
										});
					};

					$scope.dislikeClick = function() {
						// check if there is an existing dislike by user
						$http
								.get(
										'http://localhost:'
												+ location.port
												+ '/BlogBookMiddleware/getBlogDislike/'
												+ $cookieStore
														.get("showBlogId")
												+ '/'
												+ $rootScope.currentUser.username)
								.then(
										function(response) {
											var existingDislike = response.data;
											if (!existingDislike) {
												// add new dislike
												var blogDislike = {
													'blogId' : $cookieStore
															.get("showBlogId"),
													'username' : $rootScope.currentUser.username
												};
												$http
														.post(
																'http://localhost:'
																		+ location.port
																		+ '/BlogBookMiddleware/addBlogDislike',
																blogDislike);
												// increment blog dislike value
												$http
														.get('http://localhost:'
																+ location.port
																+ '/BlogBookMiddleware/incrementDislike/'
																+ $cookieStore
																		.get("showBlogId"));
												// check if there is an existing
												// like by user
												$http
														.get(
																'http://localhost:'
																		+ location.port
																		+ '/BlogBookMiddleware/getBlogLike/'
																		+ $cookieStore
																				.get("showBlogId")
																		+ '/'
																		+ $rootScope.currentUser.username)
														.then(
																function(
																		response) {
																	var existingLike = response.data;
																	if (existingLike) {
																		// delete
																		// blog
																		// like
																		$http
																				.get('http://localhost:'
																						+ location.port
																						+ '/BlogBookMiddleware/deleteBlogLike/'
																						+ existingLike.blogLikeId);
																		// decrement
																		// blog
																		// likes
																		// value
																		$http
																				.get('http://localhost:'
																						+ location.port
																						+ '/BlogBookMiddleware/decrementLike/'
																						+ $cookieStore
																								.get("showBlogId"));
																	}
																});
											} else {
												// delete existing dislike
												$http
														.get('http://localhost:'
																+ location.port
																+ '/BlogBookMiddleware/deleteBlogDislike/'
																+ existingDislike.blogDislikeId);
												// decrement blog dislike value
												$http
														.get('http://localhost:'
																+ location.port
																+ '/BlogBookMiddleware/decrementDislike/'
																+ $cookieStore
																		.get("showBlogId"));
											}
										});
					};
				});

function blogMenuSwitch(tabId) {
	var tabPaneAllBlogs = document.getElementById("tabPane-AllBlogs");
	var tabPaneLikedBlogs = document.getElementById("tabPane-LikedBlogs");
	var tabPaneYourBlogs = document.getElementById("tabPane-YourBlogs");
	switch (tabId) {
	case "tab-AllBlogs":
		tabPaneLikedBlogs.style.display = "none";
		tabPaneYourBlogs.style.display = "none";
		tabPaneAllBlogs.style.display = "";
		break;
	case "tab-LikedBlogs":
		tabPaneYourBlogs.style.display = "none";
		tabPaneAllBlogs.style.display = "none";
		tabPaneLikedBlogs.style.display = "";
		break;
	case "tab-YourBlogs":
		tabPaneLikedBlogs.style.display = "none";
		tabPaneAllBlogs.style.display = "none";
		tabPaneYourBlogs.style.display = "";
		break;
	}
}