@echo off
echo Running Excel Demo...
call mvn compile exec:java -Dexec.mainClass="com.boot.ExcelDemo"
echo Demo completed.
pause