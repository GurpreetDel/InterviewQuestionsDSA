@echo off
echo Compiling SortedSquares and SortedSquaresTest...
javac -d . SortedSquares.java SortedSquaresTest.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)

echo.
echo Running SortedSquaresTest...
java com.boot.SortedSquaresTest

echo.
echo Test execution completed.
pause