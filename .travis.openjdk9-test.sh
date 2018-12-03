#!/usr/bin/env bash

if [[ $# -ne 1 ]]; then
  echo "指定された引数は$#個です。" 1>&2
  echo "実行するには1個の引数が必要です。" 1>&2
  exit 1
fi

COMPILE_JDK=$1

# compile
jdk_switcher use ${COMPILE_JDK}
echo ${JAVA_HOME} ; javac -version ; java -version
./gradlew clean compileJava compileTestJava --stacktrace --info
if [[ $? -ne 0 ]]; then
  exit 1
fi

# test
export _JAVA_OPTIONS=
mkdir -p ${HOME}/tmp
export JAVA_HOME=`./install-jdk.sh -F 9 -W ${HOME}/tmp -E | tail -n 1`
export PATH=${JAVA_HOME}/bin:$PATH
echo ${JAVA_HOME} ; javac -version ; java -version
./gradlew check -x compileJava --stacktrace --info
if [[ $? -ne 0 ]]; then
  exit 1
fi

exit 0
