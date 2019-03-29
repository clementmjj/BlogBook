myApp
		.controller(
				"BlogCommentController",
				function($scope, $http, $location, $rootScope, $cookieStore,
						$compile) {

					$scope.blogId = $cookieStore.get("showBlogId");

					$scope.blogComment = {
						'blogId' : $scope.blogId,
						'commentMessage' : '',
						'username' : ''
					};
					$scope.blogCommentEdit = {
						'blogId' : $scope.blogId,
						'commentMessage' : '',
						'username' : $rootScope.currentUser.username
					};

					$scope.addBlogComment = function() {
						$scope.blogComment.username = $rootScope.currentUser.username;
						$http.post(
								'http://localhost:' + location.port
										+ '/BlogBookMiddleware/addBlogComment',
								$scope.blogComment).then(function(response) {
							console.log('Blog comment added.');
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
									console.log("Blog comments retrieved.")
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
											console.log("Blog comment deleted.");
										});
					}

					$scope.editBlogComment = function(blogCommentId) {
						var commentCell = document.getElementById("commentCell"
								+ blogCommentId);
						var existingComment = document
								.getElementById("blogComment" + blogCommentId);
						var btn_editSaveComment = document
								.getElementById("editCommentBtn"
										+ blogCommentId);
						var btn_closeEdit = document
								.getElementById("btn_closeEditComment"
										+ blogCommentId);
						if (btn_editSaveComment.getAttribute("value") == "Save") {
							$scope.blogCommentEdit.commentId = blogCommentId;
							$http
									.post(
											'http://localhost:'
													+ location.port
													+ '/BlogBookMiddleware/editBlogComment',
											$scope.blogCommentEdit)
									.then(
											function(response) {
												console.log(response.data);
												var p_comment = document
														.createElement("p");
												p_comment.innerHTML = (response.data).commentMessage;
												commentCell.replaceChild(
														p_comment,
														existingComment);
												p_comment
														.setAttribute(
																"id",
																"blogComment"
																		+ blogCommentId);
												btn_editSaveComment
														.setAttribute("value",
																"Edit");
												commentCell
														.removeChild(btn_closeEdit);
											});
						} else {
							var editCommentBoxElements = document
									.querySelectorAll("textarea[ng-model^='blogCommentEdit.commentMessage']");
							var closeEditBoxButtons = document
									.querySelectorAll("button[id^='btn_closeEditComment']");
							closeEditBoxButtons.forEach(function(item) {
								item.click();
							});
							var txtArea_editComment = document
									.createElement("textarea");
							txtArea_editComment.setAttribute("name",
									"editCommentMessage");
							txtArea_editComment.setAttribute("ng-model",
									"blogCommentEdit.commentMessage");

							commentCell.replaceChild(txtArea_editComment,
									existingComment);
							txtArea_editComment.setAttribute("id",
									"blogComment" + blogCommentId);
							btn_closeEdit = document.createElement("button");
							btn_closeEdit.setAttribute("id",
									"btn_closeEditComment" + blogCommentId);
							btn_closeEdit.innerHTML = "x";
							btn_closeEdit
									.addEventListener(
											"click",
											function() {
												var p_comment = document
														.createElement("p");
												p_comment.innerHTML = existingComment.innerHTML;
												commentCell.replaceChild(
														p_comment,
														txtArea_editComment);
												p_comment
														.setAttribute(
																"id",
																"blogComment"
																		+ blogCommentId);
												btn_editSaveComment
														.setAttribute("value",
																"Edit");
												commentCell
														.removeChild(btn_closeEdit);
											});
							commentCell.appendChild(btn_closeEdit);
							btn_editSaveComment.setAttribute("value", "Save");
							$compile(txtArea_editComment)($scope);
							$scope.blogCommentEdit.commentMessage = existingComment.innerHTML;
						}

					}

				});