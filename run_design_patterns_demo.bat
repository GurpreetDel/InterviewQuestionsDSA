@echo off
echo ===== Running Design Patterns Demos =====

echo.
echo ===== Singleton Pattern Demo =====
echo.
javac -d target\classes src\main\java\com\boot\designpatterns\singleton\*.java
java -cp target\classes com.boot.designpatterns.singleton.SingletonDemo

echo.
echo ===== Builder Pattern Demo =====
echo.
javac -d target\classes src\main\java\com\boot\designpatterns\builder\*.java
java -cp target\classes com.boot.designpatterns.builder.BuilderDemo

echo.
echo ===== All Demos Completed =====
pause