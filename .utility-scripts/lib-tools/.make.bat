@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  make script for Windows
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
set LOGNAME=%yyyy%-%mm%-%dd%_%hh%%mi%%ss%_make.txt
set LOGFILE_PATH=%TMP_THIS_DIR%\..\..\.log\%LOGNAME%

call %TMP_LIB_COMMON%\func console ######## モジュール再ビルドバッチ処理を開始します...
pause
call %TMP_LIB_COMMON%\func console [[######## make script task start]] >> .log\%LOGNAME% 2>&1

@rem for /f "usebackq tokens=*" %%i IN (`.echo_path.bat`) DO @set CMD_THIS_PATH=%%i
set TMP_PROJECT_ROOT_DIR=%TMP_PROJECT_ROOT%
set TMP_PRODUCTION_MODULE_NAME=foundation
set TMP_JAVA_SRC_ROOT_DIR=src\main\java

call %TMP_LIB_COMMON%\func console logfile name: %LOGNAME%

call %TMP_LIB_COMMON%\func console [[path]] : %PATH%                                    >> %LOGFILE_PATH% 2>&1
call %TMP_LIB_COMMON%\func console [[java home]] : %JAVA_HOME%                          >> %LOGFILE_PATH% 2>&1
call %TMP_LIB_COMMON%\func console [[java version]]                                     >> %LOGFILE_PATH% 2>&1
java -version                                                                           >> %LOGFILE_PATH% 2>&1
call %TMP_LIB_COMMON%\func console [[javac version]]                                    >> %LOGFILE_PATH% 2>&1
javac -version                                                                          >> %LOGFILE_PATH% 2>&1

call %TMP_LIB_COMMON%\func console [[project root]] - %TMP_PROJECT_ROOT_DIR%            >> %LOGFILE_PATH% 2>&1
call %TMP_LIB_COMMON%\func console [[production module]] - %TMP_PRODUCTION_MODULE_NAME% >> %LOGFILE_PATH% 2>&1
call %TMP_LIB_COMMON%\func console [[java source root]] - %TMP_JAVA_SRC_ROOT_DIR%       >> %LOGFILE_PATH% 2>&1
set TMP_ARGS=%TMP_PROJECT_ROOT_DIR%\%TMP_PRODUCTION_MODULE_NAME%\%TMP_JAVA_SRC_ROOT_DIR%
call %TMP_LIB_COMMON%\func console [[auto generate java source args]] - %TMP_ARGS%      >> %LOGFILE_PATH% 2>&1

@rem ##########################################################################
@rem  gradle script
@rem ##########################################################################

@rem set OPTION=--stacktrace --info --debug
@rem set OPTION=
set OPTION=%*

@rem ##########################################################################
@rem  実行バイナリのクリア
@rem ##########################################################################
call %TMP_LIB_COMMON%\func console 1: 実行バイナリのクリア - 開始
call %TMP_LIB_COMMON%\func console [[1. task invoke]] >> %LOGFILE_PATH% 2>&1
call %TMP_PROJECT_ROOT%\gradlew.bat                   ^
  foundation:clean                                    ^
  maintenance:clean                                   ^
  sample:clean                                        ^
  %OPTION%                                            >> %LOGFILE_PATH% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 1: [E] clean command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call %TMP_LIB_COMMON%\func console 1: [I] clean command success status="%ERRORLEVEL%"
)
call %TMP_LIB_COMMON%\func console 1: 実行バイナリのクリア - 終了

@rem ##########################################################################
@rem  メンテナンスモジュールのビルド
@rem ##########################################################################
call %TMP_LIB_COMMON%\func console 2: メンテナンスモジュールのビルド - 開始
call %TMP_LIB_COMMON%\func console [[2. task invoke]] >> %LOGFILE_PATH% 2>&1
call %TMP_PROJECT_ROOT%\gradlew.bat                   ^
  maintenance:compileJava                             ^
  maintenance:compileTestJava                         ^
  %OPTION%                                            >> %LOGFILE_PATH% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 2: [E] maintenance build command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call %TMP_LIB_COMMON%\func console 2: [I] maintenance build command success status="%ERRORLEVEL%"
)
call %TMP_LIB_COMMON%\func console 2: メンテナンスモジュールのビルド - 終了

@rem ##########################################################################
@rem  メンテナンスモジュールのテスト
@rem ##########################################################################
call %TMP_LIB_COMMON%\func console 3: メンテナンスモジュールのテスト - 開始
call %TMP_LIB_COMMON%\func console [[3. task invoke]] >> %LOGFILE_PATH% 2>&1
call %TMP_PROJECT_ROOT%\gradlew.bat                   ^
  maintenance:check -x compileJava                    ^
  %OPTION%                                            >> %LOGFILE_PATH% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 3: [E] maintenance test command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call %TMP_LIB_COMMON%\func console 3: [I] maintenance test command success status="%ERRORLEVEL%"
)
call %TMP_LIB_COMMON%\func console 3: メンテナンスモジュールのテスト - 終了

@rem ##########################################################################
@rem  メンテナンスモジュールの実行
@rem ##########################################################################
call %TMP_LIB_COMMON%\func console 4: メンテナンスモジュールの実行 - 開始
call %TMP_LIB_COMMON%\func console [[4. task invoke]] >> %LOGFILE_PATH% 2>&1
call %TMP_PROJECT_ROOT%\gradlew.bat                   ^
  maintenance:run -Pargs="%TMP_ARGS%" -x compileJava  ^
  %OPTION%                                            >> %LOGFILE_PATH% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 4: [E] maintenance run command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call %TMP_LIB_COMMON%\func console 4: [I] maintenance run command success status="%ERRORLEVEL%"
)
call %TMP_LIB_COMMON%\func console 4: メンテナンスモジュールの実行 - 終了

@rem ##########################################################################
@rem  メインモジュール・サンプルモジュールのビルド
@rem ##########################################################################
call %TMP_LIB_COMMON%\func console 5: メインモジュール・サンプルモジュールのビルド - 開始
call %TMP_LIB_COMMON%\func console [[5. task invoke]] >> %LOGFILE_PATH% 2>&1
call %TMP_PROJECT_ROOT%\gradlew.bat                   ^
  foundation:compileJava                              ^
  foundation:compileTestJava                          ^
  sample:compileJava                                  ^
  sample:compileTestJava                              ^
  %OPTION%                                            >> %LOGFILE_PATH% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 5: [E] foundation,sample build command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call %TMP_LIB_COMMON%\func console 5: [I] foundation,sample build success status="%ERRORLEVEL%"
)
call %TMP_LIB_COMMON%\func console 5: メインモジュール・サンプルモジュールのビルド - 終了

@rem ##########################################################################
@rem  メインモジュールのテスト
@rem ##########################################################################
call %TMP_LIB_COMMON%\func console 6: メインモジュールのテスト - 開始
call %TMP_LIB_COMMON%\func console [[6. task invoke]] >> %LOGFILE_PATH% 2>&1
call %TMP_PROJECT_ROOT%\gradlew.bat                   ^
  foundation:check -x compileJava                     ^
  %OPTION%                                            >> %LOGFILE_PATH% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 6: [E] foundation test command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call %TMP_LIB_COMMON%\func console 6: [I] foundation test command success status="%ERRORLEVEL%"
)
call %TMP_LIB_COMMON%\func console 6: メインモジュールのテスト - 終了

call %TMP_LIB_COMMON%\func console ######## モジュール再ビルドバッチ処理が正常終了しました...
call %TMP_LIB_COMMON%\func console [[######## make script all task success]] >> %LOGFILE_PATH% 2>&1

timeout 60

endlocal

