@echo off
echo Running TestNG Tests...
call mvn clean test -DsuiteXmlFile=testng.xml
echo Tests completed.
pause