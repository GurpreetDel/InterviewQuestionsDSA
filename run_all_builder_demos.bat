@echo off
echo ===== Running All Builder Pattern Demos =====

echo.
echo ===== Aircraft Builder Pattern Demo =====
echo.
javac -d target\classes src\main\java\com\boot\designpatterns\builder\aircraft\*.java
java -cp target\classes com.boot.designpatterns.builder.aircraft.AircraftClient

echo.
echo ===== Document Builder Pattern Demo =====
echo.
javac -d target\classes src\main\java\com\boot\designpatterns\builder\document\*.java
java -cp target\classes com.boot.designpatterns.builder.document.DocumentClient

echo.
echo ===== Person Builder Pattern Demo =====
echo.
javac -d target\classes src\main\java\com\boot\designpatterns\builder\*.java
java -cp target\classes com.boot.designpatterns.builder.BuilderDemo

echo.
echo ===== All Demos Completed =====
pause