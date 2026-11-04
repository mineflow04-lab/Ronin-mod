#!/usr/bin/env bash
# One-click build for Linux/macOS. Needs JDK 21 on PATH and internet access.
set -e
cd "$(dirname "$0")"
if ! java -version 2>&1 | grep -q 'version "21'; then
  echo "Java 21 not found. Install Temurin JDK 21 (https://adoptium.net) and run again."; exit 1
fi
mkdir -p .tools
if [ ! -x .tools/gradle/bin/gradle ]; then
  echo "Downloading Gradle 8.10.2..."
  curl -L -o .tools/gradle.zip https://services.gradle.org/distributions/gradle-8.10.2-bin.zip
  unzip -q .tools/gradle.zip -d .tools && mv .tools/gradle-8.10.2 .tools/gradle && rm .tools/gradle.zip
fi
.tools/gradle/bin/gradle build --no-daemon
cp build/libs/roninpath-*.jar .
echo "DONE: $(ls roninpath-*.jar)"
