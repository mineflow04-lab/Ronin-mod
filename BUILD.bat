@echo off
setlocal
cd /d "%~dp0"
echo ==============================================
echo   Ronin's Path - one-click build (NeoForge 1.21.1)
echo ==============================================
set "TOOLS=%~dp0.tools"
if not exist "%TOOLS%" mkdir "%TOOLS%"

rem ---------- 1. Java 21 ----------
set "HAVE_JAVA21="
if exist "%TOOLS%\jdk21\bin\java.exe" (
  set "JAVA_HOME=%TOOLS%\jdk21"
  set "PATH=%TOOLS%\jdk21\bin;%PATH%"
  set "HAVE_JAVA21=1"
)
if not defined HAVE_JAVA21 (
  where java >nul 2>nul
  if not errorlevel 1 (
    java -version 2>&1 | findstr /c:"version \"21" >nul
    if not errorlevel 1 set "HAVE_JAVA21=1"
  )
)
if not defined HAVE_JAVA21 (
  echo [1/3] Java 21 not found - downloading portable JDK 21, please wait...
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ProgressPreference='SilentlyContinue'; [Net.ServicePointManager]::SecurityProtocol=[Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://api.adoptium.net/v3/binary/latest/21/ga/windows/x64/jdk/hotspot/normal/eclipse' -OutFile '%TOOLS%\jdk.zip'"
  if errorlevel 1 goto :fail
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ProgressPreference='SilentlyContinue'; if (Test-Path '%TOOLS%\jdk_tmp') { Remove-Item -Recurse -Force '%TOOLS%\jdk_tmp' }; Expand-Archive -Force '%TOOLS%\jdk.zip' '%TOOLS%\jdk_tmp'; Get-ChildItem '%TOOLS%\jdk_tmp' | Select-Object -First 1 | Move-Item -Destination '%TOOLS%\jdk21'"
  if errorlevel 1 goto :fail
  del "%TOOLS%\jdk.zip" >nul 2>nul
  set "JAVA_HOME=%TOOLS%\jdk21"
  set "PATH=%TOOLS%\jdk21\bin;%PATH%"
) else (
  echo [1/3] Java 21 OK
)

rem ---------- 2. Gradle ----------
if not exist "%TOOLS%\gradle\bin\gradle.bat" (
  echo [2/3] Downloading Gradle 8.10.2, please wait...
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ProgressPreference='SilentlyContinue'; [Net.ServicePointManager]::SecurityProtocol=[Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.10.2-bin.zip' -OutFile '%TOOLS%\gradle.zip'"
  if errorlevel 1 goto :fail
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ProgressPreference='SilentlyContinue'; if (Test-Path '%TOOLS%\gradle_tmp') { Remove-Item -Recurse -Force '%TOOLS%\gradle_tmp' }; Expand-Archive -Force '%TOOLS%\gradle.zip' '%TOOLS%\gradle_tmp'; Get-ChildItem '%TOOLS%\gradle_tmp' | Select-Object -First 1 | Move-Item -Destination '%TOOLS%\gradle'"
  if errorlevel 1 goto :fail
  del "%TOOLS%\gradle.zip" >nul 2>nul
) else (
  echo [2/3] Gradle OK
)

rem ---------- 3. Build ----------
echo [3/3] Building the mod (the first build downloads Minecraft + NeoForge, it can take 5-10 minutes)...
call "%TOOLS%\gradle\bin\gradle.bat" build --no-daemon
if errorlevel 1 goto :fail

copy /y "build\libs\roninpath-*.jar" "%~dp0" >nul
echo.
echo ==============================================
echo  DONE! The jar is in this folder:
dir /b "%~dp0roninpath-*.jar"
echo  Copy it into the "mods" folder of your Minecraft.
echo ==============================================
pause
exit /b 0

:fail
echo.
echo ==============================================
echo  BUILD FAILED. Copy the red text above and send it to Claude.
echo ==============================================
pause
exit /b 1
