@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  javadoc make script for Windows
@rem
@rem ##########################################################################

setlocal

@REM UTF-8
chcp 65001

call datetime.bat
set LOGNAME=%yyyy%-%mm%-%dd%_%hh%%mi%%ss%_create_javadoc.txt

call func console ######## Javadoc作成処理を開始します...
pause
call func console [[######## make javadoc task start]] >> .log\%LOGNAME% 2>&1
call func console logfile name: %LOGNAME%

call func console [[path]] : %PATH%           >> .log\%LOGNAME% 2>&1
call func console [[java home]] : %JAVA_HOME% >> .log\%LOGNAME% 2>&1
call func console [[java version]]            >> .log\%LOGNAME% 2>&1
java -version                                 >> .log\%LOGNAME% 2>&1
call func console [[javac version]]           >> .log\%LOGNAME% 2>&1
javac -version                                >> .log\%LOGNAME% 2>&1

@rem ##########################################################################
@rem  gradle script
@rem ##########################################################################

@rem set OPTION=
set OPTION=%*

@rem ##########################################################################
@rem  Javadoc作成
@rem ##########################################################################
call func console 1: Javadocの作成 - 開始
call func console [[1. task invoke]] >> .log\%LOGNAME% 2>&1
call gradlew.bat                                      ^
  foundation:javadoc                                  ^
  -x foundation:compileJava                           ^
  %OPTION% >> .log\%LOGNAME% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call func console 1: [E] create javadoc command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call func console 1: [I] create javadoc command success status="%ERRORLEVEL%"
)
call func console 1: Javadocの作成 - 終了

call func console ######## Javadoc作成処理が正常終了しました...
call func console [[######## make javadoc all task success]] >> .log\%LOGNAME% 2>&1

timeout 60

endlocal

