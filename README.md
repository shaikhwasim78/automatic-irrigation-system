# automatic-irrigation-system
Automatic Irrigation System

Requirements:
1. Java 8
2. MySQL
3. Spring STS / Eclipse

Steps to configure the project:
1. Replace database url, username & password in 'application.properties' file.
2. Build the project.
3. Run the project as SpringBoot application.
4. Open Swagger for Api documentation (http://localhost:8082/ais/swagger-ui.html)

Steps for automatic irrigation:
1. Create a plot. (http://localhost:8082/ais/swagger-ui.html#/plot-controller/createPlotUsingPOST)
2. Configure the plot. (http://localhost:8082/ais/swagger-ui.html#/plot-irrigation-controller/configurePlotUsingPOST)
3. Irrigate the plot. (http://localhost:8082/ais/swagger-ui.html#/plot-irrigation-controller/irrigatePlotUsingPUT)
4. After this the plot will automatically irrigated from time-to-time via schedulers.


For any assistance contact me on:
Wasim.Shaikh@xebia.com
