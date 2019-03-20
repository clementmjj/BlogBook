myApp
		.controller(
				"BlogCommentController",
				function($scope, $http, $location, $rootScope, $cookieStore) {

					$scope.blogId = $cookieStore.get("showBlogId");
					$scope.blogComment = {
						'blogId' : $scope.blogId,
						'commentMessage' : '',
						'username' : ''
					};

					$scope.newBlogComment = {
						'blogId' : $scope.blogId,
						'commentId' : '',
						'commentMessage' : '',
						'username' : $rootScope.currentUser.username
					};

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
						$http
								.get(
										'http://localhost:'
												+ location.port
												+ '/BlogBookMiddleware/getBlogCommentList/'
												+ blogId)
								.then(function(response) {
									$scope.blogCommentList = response.data;
									console.log($scope.blogCommentList);
								});
					}

					$scope.deleteBlogComment = function(blogCommentId) {
						$http
								.get(
										'http://localhost:'
												+ location.port
												+ '/BlogBookMiddleware/deleteBlogComment/'
												+ blogCommentId).then(
										function(response) {
											console.log("Blog comment "
													+ blogCommentId
													+ " deleted");
										});
					}

					$scope.editBlogComment = function(blogCommentId) {
						var btn = document.getElementById("editCommentBtn"
								+ blogCommentId);
						if (btn.getAttribute("value") == "Save") {
							$scope.newBlogComment.commentId = blogCommentId;
							$http
									.post(
											'http://localhost:'
													+ location.port
													+ '/BlogBookMiddleware/editBlogComment',
											$scope.newBlogComment).then(
											function(response) {
												console.log(response.data);
											});
						} else {
							var editBox = document.createElement("textarea");
							editBox.setAttribute("name", "editCommentMessage");
							editBox.setAttribute("ng-model",
									"newBlogComment.username");
							var parent = document.getElementById("commentCell"
									+ blogCommentId);
							var existingComment = document
									.getElementById("blogComment"
											+ blogCommentId);
							editBox.innerHTML = existingComment.innerHTML;
							parent.replaceChild(editBox, existingComment);
							editBox.setAttribute("id", "blogComment"
									+ blogCommentId);

							btn.setAttribute("value", "Save");
						}

					}

				});