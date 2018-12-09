@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  release command script for Windows
@rem
@rem ##########################################################################
setlocal

pause

set DT=%date%
set TM=%time: =0%
set LOGNAME=%yyyy%-%mm%-%dd%_%hh%%mi%%ss%_release_bintray.txt

echo "logfile name: %LOGNAME%"

@rem -------------- BINTRAY_USERNAME : bintray username
@rem -------------- BINTRAY_KEY : bintray secretkey
@rem gradlew.bat clean
@rem gradlew.bat build
call gradlew.bat bintrayUpload "-PbintrayUser=%BINTRAY_USERNAME%" "-PbintrayKey=%BINTRAY_KEY%" ^
    -PdryRun=false > .log\%LOGNAME%  2>&1

endlocal
