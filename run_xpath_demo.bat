@echo off
echo Running XPath Demo...
call mvn compile exec:java -Dexec.mainClass="com.boot.XPathDemo"
echo Demo completed.
pause