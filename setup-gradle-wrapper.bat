@echo off
echo Setting up Gradle Wrapper...

REM Create gradle/wrapper directory if it doesn't exist
if not exist "gradle\wrapper" mkdir "gradle\wrapper"

REM Download gradle-wrapper.jar
echo Downloading gradle-wrapper.jar...
powershell -Command "Invoke-WebRequest -Uri 'https://github.com/gradle/gradle/raw/v7.0.2/gradle/wrapper/gradle-wrapper.jar' -OutFile 'gradle/wrapper/gradle-wrapper.jar'"

if exist "gradle\wrapper\gradle-wrapper.jar" (
    echo Gradle Wrapper setup complete!
    echo You can now use gradlew.bat commands for building.
) else (
    echo Download failed, please check network connection or manually download gradle-wrapper.jar
)

pause