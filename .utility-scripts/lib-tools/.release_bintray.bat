@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  release command script for Windows
@rem
@rem ##########################################################################
setlocal

@rem this batch file placed directory path
set TMP_THIS_DIR=%~dp0
set TMP_THIS_DIR=%TMP_THIS_DIR:~0,-1%
@rem path info
set TMP_LIB_COMMON=%TMP_THIS_DIR%\..\common
set TMP_LIB_TOOL=%TMP_THIS_DIR%
set TMP_LIB_TOOL_VENDOR=%TMP_THIS_DIR%\..\lib-tools-vendor
set TMP_PROJECT_ROOT=%TMP_THIS_DIR%\..\..

cd %TMP_PROJECT_ROOT%

pause

call %TMP_LIB_COMMON%\datetime.bat
set LOGNAME=%yyyy%-%mm%-%dd%_%hh%%mi%%ss%_release_bintray.txt
set LOGFILE_PATH=%TMP_THIS_DIR%\..\..\.log\%LOGNAME%

echo "logfile name: %LOGNAME%"

set OPTION=%*

@rem -------------- BINTRAY_USERNAME : bintray username
@rem -------------- BINTRAY_KEY : bintray secretkey
@rem %TMP_PROJECT_ROOT%\gradlew.bat clean
@rem %TMP_PROJECT_ROOT%\gradlew.bat build
call %TMP_PROJECT_ROOT%\gradlew.bat bintrayUpload "-PbintrayUser=%BINTRAY_USERNAME%" "-PbintrayKey=%BINTRAY_KEY%" ^
    %OPTION% -PdryRun=false > %LOGFILE_PATH%  2>&1

endlocal
timeout 60

