#!/usr/bin/env bash

if [[ $# -ne 2 ]]; then
  echo "指定された引数は$#個です。" 1>&2
  echo "実行するには2個の引数が必要です。" 1>&2
  exit 1
fi

COMPILE_JDK=$1
TEST_JDK=$2

# compile
jdk_switcher use ${COMPILE_JDK}
echo ${JAVA_HOME} ; javac -version ; java -version
./gradlew clean compileJava compileTestJava --stacktrace --info
if [[ $? -ne 0 ]]; then
  exit 1
fi

# test
jdk_switcher use ${TEST_JDK}
echo ${JAVA_HOME} ; javac -version ; java -version
./gradlew check -x compileJava --stacktrace --info
if [[ $? -ne 0 ]]; then
  exit 1
fi

exit 0
