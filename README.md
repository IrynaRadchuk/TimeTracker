#Time Tracker
Time Tracker is an application to track employees productivity.
It allows users to choose activities and set activities duration and date in schedule.
It allows admins to see statistics and manage activities and users.

##Before Run
To start working change database root properties in the file **src/main/resources/db.properties**.
To initialize database execute script from file **src/main/resources/db/init.sql**.
To fill database with initial data execute script from file **src/main/resources/db/insert.sql**.
Versions of used  dependencies are stored in file **pom.xml**.
Run project with Tomcat servlet container plugin.
After server successfully starts, run application on *http://localhost:8080/tracker/*

##After Run
To log in as admin use credentials: 
Email --> admin@gmail.com 
Password --> Qwerty123

To log in as user use credentials: 
Email --> user@gmail.com 
Password --> Qwerty123