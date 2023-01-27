#Test Automation Web Challenge - Destacame

###Escenarios en el Feature:
- Agregar dos productos al carrito que superen el limite de compra
- Agregar dos productos al carrito que no superen el limite de compra

###Datos Técnicos:
- Java 11
- Selenium
- TestNg
- Cucumber
- Allure (Report)

###Ejecución por Terminal:
- Test: mvn clean test / mvn test
- Reporte: mvn allure:serve (Opcional)

###Estructura:
1. Main: 

    1.1 com.destacame.config 

    1.1.1 ConfigPage: 
    - Contiene los parámetros iniciales de configuración. 
    - Se implementó la librería WebDriverManager, para automatizar el proceso de obtención del chromedriver/geckodriver, no es necesario agregarlo ni actualizarlo.
    - Manejo de métodos de Selenium.

    1.1.2 LoggerPage:
    - Manejo de Logger (Log4j) para registrar el proceso.

    1.1.3 PropertiesPage:
    - Manejo de las propiedades de ejecución como Url, browser (chrome o firefox), datos de entrada.

    1.2 com.destacame.process

    //Los procesos son manejados por separados pero en ejecución son dependientes

    1.2.1 AddProductsProcess: 
    - Métodos para el manejo de la visita a la pagina, busqueda de los productos y agregados al carrito
    
    1.2.2 PurchaseProcess: 
    - Métodos para el manejo de la visita al carrito de compras, la evaluacion del total de la compra y el proceso de compra

2. Test:

    2.1 com.destacame.defs

    //Pasos en la ejecución del proceso. Asociados a los Scenarios del Feature

    2.1.1 TotalPurchaseBelowTheLimitTest: métodos para la ejecución del proceso y test de la compra de productos con total menor al limite
    
    2.1.2 TotalPurchaseOverTheLimitTest: métodos para la ejecución del proceso y test de la compra de productos con total mayor al limite

    2.2. RunCucumberTest.java
    Definición del ejecutable (Cucumber + TestNg)

###Escenarios
* src/test/resources/scenarios.feature contiene los Escenarios del Feature en Gherkin.

###Reportes:
Se pueden obtener reportes de dos maneras:
1. Allure: mvn allure:serve una vez terminada la ejecución del test. Este reporte
muestra cada escenario ejecutado e incluye los screenshots de cada proceso.
2. Cucumber Report: reporte por defecto, es posible encontrarlo en la carpeta Target del proyecto.
No incluye screenshots.

###Screenshots:
Están configurados por defecto para incluirse en el reporte de Allure y en la carpeta
Screenshots del proyecto, a modo de backup.

###Logs:
Están configurados para registrar los procesos de la ejecución. Se registran en dos archivos,
automation.log y automation.error según el status de los que se ha registrado. Se almacenan por día
en un archivo comprimido a fin de almacenar los registros.