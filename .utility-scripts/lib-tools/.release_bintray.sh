#!/bin/sh

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

logname=$(date +%Y-%m-%d_%H%M%S)_release_bintray.txt
LOGFILE_PATH=$TMP_THIS_DIR/../../.log/$logname

# ---------- BINTRAY_USERNAME : bintray username
# ---------- BINTRAY_KEY : bintray secretkey
# "$TMP_PROJECT_ROOT"/gradlew clean
# "$TMP_PROJECT_ROOT"/gradlew build
"$TMP_PROJECT_ROOT"/gradlew bintrayUpload -PbintrayUser="${BINTRAY_USERNAME}" -PbintrayKey="$BINTRAY_KEY" -PdryRun=false > "$LOGFILE_PATH"
