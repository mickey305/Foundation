@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  javadoc make script for Windows
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

@REM UTF-8
chcp 65001

call %TMP_LIB_COMMON%\datetime.bat
set LOGNAME=%yyyy%-%mm%-%dd%_%hh%%mi%%ss%_create_javadoc.txt
set LOGFILE_PATH=%TMP_THIS_DIR%\..\..\.log\%LOGNAME%

call %TMP_LIB_COMMON%\func console ######## Javadoc作成処理を開始します...
pause
call %TMP_LIB_COMMON%\func console [[######## make javadoc task start]] >> %LOGFILE_PATH% 2>&1
call %TMP_LIB_COMMON%\func console logfile name: %LOGNAME%

call %TMP_LIB_COMMON%\func console [[path]] : %PATH%                    >> %LOGFILE_PATH% 2>&1
call %TMP_LIB_COMMON%\func console [[java home]] : %JAVA_HOME%          >> %LOGFILE_PATH% 2>&1
call %TMP_LIB_COMMON%\func console [[java version]]                     >> %LOGFILE_PATH% 2>&1
java -version                                                           >> %LOGFILE_PATH% 2>&1
call %TMP_LIB_COMMON%\func console [[javac version]]                    >> %LOGFILE_PATH% 2>&1
javac -version                                                          >> %LOGFILE_PATH% 2>&1

@rem ##########################################################################
@rem  gradle script
@rem ##########################################################################

@rem set OPTION=
set OPTION=%*

@rem ##########################################################################
@rem  Javadoc作成
@rem ##########################################################################
call %TMP_LIB_COMMON%\func console 1: Javadocの作成 - 開始
call %TMP_LIB_COMMON%\func console [[1. task invoke]] >> %LOGFILE_PATH% 2>&1
call %TMP_PROJECT_ROOT%\gradlew.bat                   ^
  foundation:javadoc                                  ^
  -x foundation:compileJava                           ^
  %OPTION%                                            >> %LOGFILE_PATH% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 1: [E] create javadoc command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call %TMP_LIB_COMMON%\func console 1: [I] create javadoc command success status="%ERRORLEVEL%"
)
call %TMP_LIB_COMMON%\func console 1: Javadocの作成 - 終了

call %TMP_LIB_COMMON%\func console ######## Javadoc作成処理が正常終了しました...
call %TMP_LIB_COMMON%\func console [[######## make javadoc all task success]] >> %LOGFILE_PATH% 2>&1

timeout 60

endlocal

