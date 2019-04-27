CS 541 - Game Development for Mobile Platforms {Spring 2019}

Project 5 - Android Application : Grocery List


	1. Developed a client-server android application using PHP, MySQL, WampServer and Java
	2. Tools Used: Android Studio, MySQL Workbench.
	

Application Details:-	

    
    1. Before starting Grocery List app admin will add list of all users to MySQL database.	
	2. Grocery List app user can perform two tasks add and edit.
	3. User can view and edit his\her and other users grocery list.
	4. Basic idea of application is to manage common list of grocery items for students staying in same home/apartment.


Application Workflow:-
    
	
	1. This application is based on client-server architecture. Android application is a client and it sends get/post requests to server.
    2. Backend is developed using PHP and MySQL. Based on get/edit request from client PHP code sends query to MySQL database.
    3. Result of a query is then converted into a JSON format and sent back to a client. 
    4. On client side we parse the JSON response and display it on a user interface. 	