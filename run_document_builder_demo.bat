@echo off
echo Compiling and running Document Builder Pattern Demo...
javac -d target\classes src\main\java\com\boot\designpatterns\builder\document\*.java
java -cp target\classes com.boot.designpatterns.builder.document.DocumentClient
pause