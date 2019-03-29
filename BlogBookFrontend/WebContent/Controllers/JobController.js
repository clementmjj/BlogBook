myApp
		.controller(
				"JobController",
				function($scope, $http, $location, $rootScope, $cookieStore) {

					$scope.job = {
						'jobTitle' : '',
						'jobContent' : '',
						'username' : '',
						'status' : '',
						'likes' : 0,
						'dislikes' : 0
					};
					$scope.jobDetail;
					$scope.editJobInfo;
					$scope.addJob = function() {
						$scope.job.username = $rootScope.currentUser.username;
						$scope.job.status = 'NA';
						$http.post(
								'http://localhost:' + location.port
										+ '/JobBookMiddleware/addJob',
								$scope.job).then(function(response) {
							console.log('Job added');
							$location.path('/jobList');
						});
					}

					$scope.showEditJob = function() {
						var urlText = window.location.href;
						var editJobId = urlText
								.substring(urlText.indexOf("=") + 1);
						$http.get(
								'http://localhost:' + location.port
										+ '/JobBookMiddleware/getJob/'
										+ editJobId).then(function(response) {
							$scope.editJobInfo = response.data;
							delete $scope.editJobInfo.createdDate;
						});
					}

					$scope.updateJob = function() {
						$http.post(
								'http://localhost:' + location.port
										+ '/JobBookMiddleware/updateJob',
								$scope.editJobInfo).then(function(response) {
							console.log("Job updated.");
							$location.path("/jobList");
						});
					}

					$scope.getJobList = function() {
						$http.get(
								'http://localhost:' + location.port
										+ '/JobBookMiddleware/getJobList')
								.then(function(response) {
									$scope.jobList = response.data;
									console.log("Job list retrieved.");
								});
					}

					$scope.approveJob = function(jobId) {
						$http.get(
								'http://localhost:' + location.port
										+ '/JobBookMiddleware/approveJob/'
										+ jobId).then(function(response) {
							console.log("Job approved");
						});
					}

					$scope.rejectJob = function(jobId) {
						$http.get(
								'http://localhost:' + location.port
										+ '/JobBookMiddleware/rejectJob/'
										+ jobId).then(function(response) {
							console.log("Job rejected");
						});
					}

					$scope.deleteJob = function(jobId) {
						$http.get(
								'http://localhost:' + location.port
										+ '/JobBookMiddleware/deleteJob/'
										+ jobId).then(function(response) {
							console.log("Job deleted");
						});
					}

					$scope.showJob = function(jobId) {
						if (jobId == undefined)
							jobId = $cookieStore.get("showJobId");
						if ($cookieStore.get("showJobId") == undefined) {
							$cookieStore.put("showJobId", jobId);
						} else if ($cookieStore.get("showJobId") != jobId) {
							$cookieStore.put("showJobId", jobId);
						} else {
							$http.get(
									'http://localhost:' + location.port
											+ '/JobBookMiddleware/getJob/'
											+ $cookieStore.get("showJobId"))
									.then(function(response) {
										$scope.jobDetail = response.data;
									});
						}
					}

					$scope.likeClick = function() {
						// check if there is an existing like by user
						$http
								.get(
										'http://localhost:'
												+ location.port
												+ '/JobBookMiddleware/getJobLike/'
												+ $cookieStore
														.get("showJobId")
												+ '/'
												+ $rootScope.currentUser.username)
								.then(
										function(response) {
											var existingLike = response.data;
											if (!existingLike) {
												// add new like
												var jobLike = {
													'jobId' : $cookieStore
															.get("showJobId"),
													'username' : $rootScope.currentUser.username
												};
												$http
														.post(
																'http://localhost:'
																		+ location.port
																		+ '/JobBookMiddleware/addJobLike',
																jobLike);
												// increment job like value
												$http
														.get('http://localhost:'
																+ location.port
																+ '/JobBookMiddleware/incrementLike/'
																+ $cookieStore
																		.get("showJobId"));
												// check if there is an existing
												// dislike by user
												$http
														.get(
																'http://localhost:'
																		+ location.port
																		+ '/JobBookMiddleware/getJobDislike/'
																		+ $cookieStore
																				.get("showJobId")
																		+ '/'
																		+ $rootScope.currentUser.username)
														.then(
																function(
																		response) {
																	var existingDislike = response.data;
																	if (existingDislike) {
																		// delete
																		// job
																		// dislike
																		$http
																				.get('http://localhost:'
																						+ location.port
																						+ '/JobBookMiddleware/deleteJobDislike/'
																						+ existingDislike.jobDislikeId);
																		// decrement
																		// job
																		// dislikes
																		// value
																		$http
																				.get('http://localhost:'
																						+ location.port
																						+ '/JobBookMiddleware/decrementDislike/'
																						+ $cookieStore
																								.get("showJobId"));
																	}
																});
											} else {
												// delete existing like
												$http
														.get('http://localhost:'
																+ location.port
																+ '/JobBookMiddleware/deleteJobLike/'
																+ existingLike.jobLikeId);
												// decrement job like value
												$http
														.get('http://localhost:'
																+ location.port
																+ '/JobBookMiddleware/decrementLike/'
																+ $cookieStore
																		.get("showJobId"));
											}
										});
					};

					$scope.dislikeClick = function() {
						// check if there is an existing dislike by user
						$http
								.get(
										'http://localhost:'
												+ location.port
												+ '/JobBookMiddleware/getJobDislike/'
												+ $cookieStore
														.get("showJobId")
												+ '/'
												+ $rootScope.currentUser.username)
								.then(
										function(response) {
											var existingDislike = response.data;
											if (!existingDislike) {
												// add new dislike
												var jobDislike = {
													'jobId' : $cookieStore
															.get("showJobId"),
													'username' : $rootScope.currentUser.username
												};
												$http
														.post(
																'http://localhost:'
																		+ location.port
																		+ '/JobBookMiddleware/addJobDislike',
																jobDislike);
												// increment job dislike value
												$http
														.get('http://localhost:'
																+ location.port
																+ '/JobBookMiddleware/incrementDislike/'
																+ $cookieStore
																		.get("showJobId"));
												// check if there is an existing
												// like by user
												$http
														.get(
																'http://localhost:'
																		+ location.port
																		+ '/JobBookMiddleware/getJobLike/'
																		+ $cookieStore
																				.get("showJobId")
																		+ '/'
																		+ $rootScope.currentUser.username)
														.then(
																function(
																		response) {
																	var existingLike = response.data;
																	if (existingLike) {
																		// delete
																		// job
																		// like
																		$http
																				.get('http://localhost:'
																						+ location.port
																						+ '/JobBookMiddleware/deleteJobLike/'
																						+ existingLike.jobLikeId);
																		// decrement
																		// job
																		// likes
																		// value
																		$http
																				.get('http://localhost:'
																						+ location.port
																						+ '/JobBookMiddleware/decrementLike/'
																						+ $cookieStore
																								.get("showJobId"));
																	}
																});
											} else {
												// delete existing dislike
												$http
														.get('http://localhost:'
																+ location.port
																+ '/JobBookMiddleware/deleteJobDislike/'
																+ existingDislike.jobDislikeId);
												// decrement job dislike value
												$http
														.get('http://localhost:'
																+ location.port
																+ '/JobBookMiddleware/decrementDislike/'
																+ $cookieStore
																		.get("showJobId"));
											}
										});
					};
				});