@echo off
echo 正在设置 Gradle Wrapper...

REM 创建 gradle/wrapper 目录（如果不存在）
if not exist "gradle\wrapper" mkdir "gradle\wrapper"

REM 下载 gradle-wrapper.jar
echo 下载 gradle-wrapper.jar...
powershell -Command "Invoke-WebRequest -Uri 'https://github.com/gradle/gradle/raw/v7.0.2/gradle/wrapper/gradle-wrapper.jar' -OutFile 'gradle/wrapper/gradle-wrapper.jar'"

if exist "gradle\wrapper\gradle-wrapper.jar" (
    echo Gradle Wrapper 设置完成！
    echo 现在可以使用 gradlew.bat 命令进行构建了。
) else (
    echo 下载失败，请检查网络连接或手动下载 gradle-wrapper.jar
)

pause