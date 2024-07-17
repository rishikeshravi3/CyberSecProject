document.getElementById('unsecureLogin').addEventListener('click', function() {
	// Action for unsecure login
	document.getElementById('loginForm').action = "/dashboardUnsecure";
	document.getElementById('loginForm').method = "GET"; // Ensure the method is set to GET
	document.getElementById('loginForm').submit();
});

document.getElementById('secureLogin').addEventListener('click', function() {
	// Action for secure login
	document.getElementById('loginForm').action = "/dashboardSecure";
	document.getElementById('loginForm').method = "GET"; // Ensure the method is set to GET
	document.getElementById('loginForm').submit();
});