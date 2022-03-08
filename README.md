# automatic-irrigation-system
Automatic Irrigation System

Requirements:
1. Java 8
2. MySQL
3. Spring STS / Eclipse

Steps to configure the project:
1. Create a database 'automatic-irrigation-system' in MySQL.
2. Replace database url, username & password in 'application.properties' file.
3. Build the project.
4. Run the project as SpringBoot application.
5. Open Swagger for Api documentation (http://localhost:8082/ais/swagger-ui.html)

Steps for automatic irrigation:
1. Create a plot. (http://localhost:8082/ais/swagger-ui.html#/plot-controller/createPlotUsingPOST)
2. Configure the plot. (http://localhost:8082/ais/swagger-ui.html#/plot-irrigation-controller/configurePlotUsingPOST)
3. Irrigate the plot. (http://localhost:8082/ais/swagger-ui.html#/plot-irrigation-controller/irrigatePlotUsingPUT)
4. After this the plot will automatically irrigated from time-to-time via schedulers.


NOTE: For testing the Alert System, plotId-1 is set to always fail the irrigation.

For any assistance contact me on:
Wasim.Shaikh@xebia.com
