var myApp = angular.module("myApp", [ "ngRoute", "ngCookies" ]);
myApp.config(function($routeProvider) {
	$routeProvider.when("/login", {
		templateUrl : "Pages/login.html"
	}).when("/register", {
		templateUrl : "Pages/register.html",
	}).when("/aboutus", {
		templateUrl : "Pages/aboutus.html",
	}).when("/addBlog", {
		templateUrl : "Pages/Blog/AddBlog.html",
	}).when("/blogList", {
		templateUrl : "Pages/Blog/BlogList.html"
	}).when("/showBlog/**", {
		templateUrl : "Pages/Blog/Blog.html"
	});
});

myApp.run(function($rootScope, $cookieStore) {
	if ($rootScope.currentUser == undefined) {
		$rootScope.currentUser = $cookieStore.get('userDetails');
	}
});