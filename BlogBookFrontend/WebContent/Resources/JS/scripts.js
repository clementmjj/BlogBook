function leftNavMenuSwitch(tabId) {
	var blogMenu = document.getElementById("blog-menu");
	var forumMenu = document.getElementById("forum-menu");
	var jobMenu = document.getElementById("job-menu");
	switch (tabId) {
	case "tab-blogs":
		jobMenu.setAttribute("style", "display: none !important");
		forumMenu.setAttribute("style", "display: none !important");
		blogMenu.removeAttribute("style");
		break;
	case "tab-forums":
		blogMenu.setAttribute("style", "display: none !important");
		jobMenu.setAttribute("style", "display: none !important");
		forumMenu.removeAttribute("style");
		break;
	case "tab-jobs":
		blogMenu.setAttribute("style", "display: none !important");
		forumMenu.setAttribute("style", "display: none !important");
		jobMenu.removeAttribute("style");
		break;
	}
}