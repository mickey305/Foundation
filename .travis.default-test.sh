#!/usr/bin/env bash

if [[ $# -ne 0 ]]; then
  echo "指定された引数は$#個です。" 1>&2
  echo "実行するには0個の引数が必要です。" 1>&2
  exit 1
fi

# test
echo "=============== test info start ==============="
echo ${JAVA_HOME} ; javac -version ; java -version
echo "=============== test info end ==============="
./gradlew check -x compileJava # --stacktrace --info
if [[ $? -ne 0 ]]; then
  exit 1
fi

exit 0
