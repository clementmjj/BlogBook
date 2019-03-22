myApp
		.controller(
				"ForumCommentController",
				function($scope, $http, $location, $rootScope, $cookieStore,
						$compile) {

					$scope.forumId = $cookieStore.get("showForumId");

					$scope.forumComment = {
						'forumId' : $scope.forumId,
						'commentMessage' : '',
						'username' : ''
					};
					$scope.forumCommentEdit = {
						'forumId' : $scope.forumId,
						'commentMessage' : '',
						'username' : $rootScope.currentUser.username
					};

					$scope.addForumComment = function() {
						$scope.forumComment.username = $rootScope.currentUser.username;
						$http.post(
								'http://localhost:' + location.port
										+ '/BlogBookMiddleware/addForumComment',
								$scope.forumComment).then(function(response) {
							console.log('Forum comment added');
							console.log(response.data);
						});
					}

					$scope.getForumCommentList = function(forumId) {
						$http
								.get(
										'http://localhost:'
												+ location.port
												+ '/BlogBookMiddleware/getForumCommentList/'
												+ forumId)
								.then(function(response) {
									$scope.forumCommentList = response.data;
									console.log($scope.forumCommentList);
								});
					}

					$scope.deleteForumComment = function(forumCommentId) {
						$http
								.get(
										'http://localhost:'
												+ location.port
												+ '/BlogBookMiddleware/deleteForumComment/'
												+ forumCommentId).then(
										function(response) {
											console.log("Forum comment "
													+ forumCommentId
													+ " deleted");
										});
					}

					$scope.editForumComment = function(forumCommentId) {
						var commentCell = document.getElementById("commentCell"
								+ forumCommentId);
						var existingComment = document
								.getElementById("forumComment" + forumCommentId);
						var btn_editSaveComment = document
								.getElementById("editCommentBtn"
										+ forumCommentId);
						var btn_closeEdit = document
								.getElementById("btn_closeEditComment"
										+ forumCommentId);
						if (btn_editSaveComment.getAttribute("value") == "Save") {
							$scope.forumCommentEdit.commentId = forumCommentId;
							$http
									.post(
											'http://localhost:'
													+ location.port
													+ '/BlogBookMiddleware/editForumComment',
											$scope.forumCommentEdit)
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
																"forumComment"
																		+ forumCommentId);
												btn_editSaveComment
														.setAttribute("value",
																"Edit");
												commentCell
														.removeChild(btn_closeEdit);
											});
						} else {
							var editCommentBoxElements = document
									.querySelectorAll("textarea[ng-model^='forumCommentEdit.commentMessage']");
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
									"forumCommentEdit.commentMessage");

							commentCell.replaceChild(txtArea_editComment,
									existingComment);
							txtArea_editComment.setAttribute("id",
									"forumComment" + forumCommentId);
							btn_closeEdit = document.createElement("button");
							btn_closeEdit.setAttribute("id",
									"btn_closeEditComment" + forumCommentId);
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
																"forumComment"
																		+ forumCommentId);
												btn_editSaveComment
														.setAttribute("value",
																"Edit");
												commentCell
														.removeChild(btn_closeEdit);
											});
							commentCell.appendChild(btn_closeEdit);
							btn_editSaveComment.setAttribute("value", "Save");
							$compile(txtArea_editComment)($scope);
							$scope.forumCommentEdit.commentMessage = existingComment.innerHTML;
						}

					}

				});