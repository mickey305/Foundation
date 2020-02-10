@echo off

@REM UTF-8
chcp 65001

setlocal

@rem this batch file placed directory path
set TMP_THIS_DIR=%~dp0
set TMP_THIS_DIR=%TMP_THIS_DIR:~0,-1%
@rem path info
set TMP_LIB_COMMON=%TMP_THIS_DIR%\..\.utility-scripts\common
set TMP_LIB_TOOL=%TMP_THIS_DIR%\..\.utility-scripts\lib-tools
set TMP_LIB_TOOL_VENDOR=%TMP_THIS_DIR%\..\.utility-scripts\lib-tools-vendor
set TMP_PROJECT_ROOT=%TMP_THIS_DIR%\..
set TMP_PROJECT_FOUNDATION_BUILD=%TMP_PROJECT_ROOT%\foundation\build
set TMP_PROJECT_SAMPLE=%TMP_THIS_DIR%
set TMP_PROJECT_SAMPLE_SRC=%TMP_PROJECT_SAMPLE%\src
set TMP_PROJECT_SAMPLE_BUILD=%TMP_PROJECT_SAMPLE%\build
@rem set TMP_JAR=foundation-0.6.41-beta.jar
set TMP_JAR=foundation-0.6.51.jar
set TMP_JAR_PATH=%TMP_PROJECT_FOUNDATION_BUILD%\libs\%TMP_JAR%

set TMP_TARGET_JSH_PATH=%TMP_PROJECT_SAMPLE_SRC%\main\jsh\sample.jsh

call jshell -J-Dfile.encoding=utf8 "%TMP_TARGET_JSH_PATH%" --class-path "%TMP_JAR_PATH%"

@REM pause

endlocal

