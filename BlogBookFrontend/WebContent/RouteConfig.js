var myApp = angular.module("myApp", [ "ngRoute", "ngCookies" ]);
myApp.config(function($routeProvider) {
	$routeProvider.when("/login", {
		templateUrl : "Pages/login.html",
	}).when("/register", {
		templateUrl : "Pages/register.html",
	}).when("/aboutus", {
		templateUrl : "Pages/aboutus.html",
	}).when("/blog", {
		templateUrl : "Pages/Blog/Blog.html",
	}).when("/addBlog", {
		templateUrl : "Pages/Blog/AddBlog.html",
	}).when("/blogList", {
		templateUrl : "Pages/Blog/BlogList.html",
	}).when("/showBlog", {
		templateUrl : "Pages/Blog/BlogDetail.html",
	}).when("/manageBlog", {
		templateUrl : "Pages/Blog/ManageBlog.html",
	}).when("/editBlog", {
		templateUrl : "Pages/Blog/EditBlog.html",
	}).when("/forum", {
		templateUrl : "Pages/Forum/Forum.html",
	}).when("/addForum", {
		templateUrl : "Pages/Forum/AddForum.html",
	}).when("/forumList", {
		templateUrl : "Pages/Forum/ForumList.html",
	}).when("/manageForum", {
		templateUrl : "Pages/Forum/ManageForum.html",
	}).when("/editForum", {
		templateUrl : "Pages/Forum/EditForum.html",
	}).when("/showForum", {
		templateUrl : "Pages/Forum/ForumDetail.html",
	}).when("/profilePic", {
		templateUrl : "Pages/User/ProfilePic.html",
	}).when("/showUser", {
		templateUrl : "Pages/User/User.html",
	}).when("/userHome", {
		templateUrl : "Pages/User/UserHome.html",
	}).when("/notifications", {
		templateUrl : "Pages/User/Notifications.html",
	}).when("/friends", {
		templateUrl : "Pages/Friend/Friend.html",
	}).when("/chat", {
		templateUrl : "Pages/Chat/Chat.html",
	}).when("/job", {
		templateUrl : "Pages/Job/Job.html",
	}).when("/addJob", {
		templateUrl : "Pages/Job/AddJob.html",
	}).when("/jobList", {
		templateUrl : "Pages/Job/JobList.html",
	}).when("/showJob", {
		templateUrl : "Pages/Job/JobDetail.html",
	}).when("/manageJob", {
		templateUrl : "Pages/Job/ManageJob.html",
	}).when("/editJob", {
		templateUrl : "Pages/Job/EditJob.html",
	}).when("/index2", {
		templateUrl : "index2.html"
	});
});

myApp.run(function($rootScope, $cookieStore) {
	$rootScope.portNo=location.port;
	if ($rootScope.currentUser == undefined) {
		$rootScope.currentUser = $cookieStore.get('userDetails');
	}
});