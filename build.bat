@echo off
setlocal

:: Set paths
set SRC_DIR=src
set BIN_DIR=bin
set JAR_NAME=Fungorium.jar
set MANIFEST_FILE=manifest.txt

:: Create bin directory if it doesn't exist
if not exist %BIN_DIR% mkdir %BIN_DIR%

:: Compile Java files
echo Compiling Java files...
javac -d %BIN_DIR% %SRC_DIR%\*.java
if %ERRORLEVEL% neq 0 (
    echo Compilation failed!
    exit /b %ERRORLEVEL%
)

:: Create JAR with manifest
echo Creating JAR file...
jar cvfm %JAR_NAME% %MANIFEST_FILE% -C %BIN_DIR% .

echo Build successful! JAR file created: %JAR_NAME%

endlocal
