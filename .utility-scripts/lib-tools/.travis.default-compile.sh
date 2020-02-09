#!/usr/bin/env bash

# this batch file placed directory path
# shellcheck disable=SC2164
# shellcheck disable=SC2046
TMP_THIS_DIR=$(cd $(dirname "$0"); pwd)
# path info
# shellcheck disable=SC2034
TMP_LIB_COMMON=$TMP_THIS_DIR/../common
# shellcheck disable=SC2034
TMP_LIB_TOOL=$TMP_THIS_DIR
# shellcheck disable=SC2034
TMP_LIB_TOOL_VENDOR=$TMP_THIS_DIR/../lib-tools-vendor
# shellcheck disable=SC2034
TMP_PROJECT_ROOT=$TMP_THIS_DIR/../..

cd "$TMP_PROJECT_ROOT"

if [[ $# -ne 0 ]]; then
  echo "指定された引数は$#個です。" 1>&2
  echo "実行するには0個の引数が必要です。" 1>&2
  exit 1
fi

# compile
echo "=============== compile info start ==============="
echo "${JAVA_HOME}" ; javac -version ; java -version
echo "=============== compile info end ==============="
"$TMP_PROJECT_ROOT"/gradlew clean compileJava compileTestJava # --stacktrace --info
# shellcheck disable=SC2181
if [[ $? -ne 0 ]]; then
  exit 1
fi

exit 0
