set ProjectPath=%~dp0
cd %ProjectPath%
set p=%PATH%
java -javaagent:"%ProjectPath%libAllure\aspectjweaver-1.8.10.jar" -classpath "%ProjectPath%bin;%ProjectPath%libAllure\*;%ProjectPath%libExtentV5\*;%ProjectPath%libLog4J\*;%ProjectPath%libSelenium\*;%ProjectPath%libReportNG\*;%ProjectPath%libWebDriverManager\*" org.testng.TestNG "%ProjectPath%bin\runNopCommerceTest.xml"
allure serve allure-results
pause