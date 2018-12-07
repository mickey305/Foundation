#!/usr/bin/env bash

if [[ $# -ne 0 ]]; then
  echo "指定された引数は$#個です。" 1>&2
  echo "実行するには0個の引数が必要です。" 1>&2
  exit 1
fi

# compile
echo ${JAVA_HOME} ; javac -version ; java -version
./gradlew clean compileJava compileTestJava --stacktrace --info
if [[ $? -ne 0 ]]; then
  exit 1
fi

exit 0