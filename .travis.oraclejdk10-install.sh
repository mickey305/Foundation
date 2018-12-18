#!/usr/bin/env bash

if [[ $# -ne 0 ]]; then
  echo "指定された引数は$#個です。" 1>&2
  echo "実行するには0個の引数が必要です。" 1>&2
  exit 1
fi

# install
#export _JAVA_OPTIONS=
#mkdir -p ${HOME}/tmp
export JAVA_HOME=`./install-jdk.sh -F 10 -L BCL -E | tail -n 1`
#export PATH=${JAVA_HOME}/bin:$PATH

exit 0
