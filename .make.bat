@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  make script for Windows
@rem
@rem ##########################################################################

setlocal

@REM UTF-8
chcp 65001

call datetime.bat
set LOGNAME=%yyyy%-%mm%-%dd%_%hh%%mi%%ss%_make.txt

call func console ######## モジュール再ビルドバッチ処理を開始します...
pause
call func console [[######## make script task start]] >> .log\%LOGNAME% 2>&1

for /f "usebackq tokens=*" %%i IN (`.echo_path.bat`) DO @set CMD_THIS_PATH=%%i
set TMP_PROJECT_ROOT_DIR=%CMD_THIS_PATH%
set TMP_PRODUCTION_MODULE_NAME=foundation
set TMP_JAVA_SRC_ROOT_DIR=src\main\java

call func console logfile name: %LOGNAME%

call func console [[path]] : %PATH%           >> .log\%LOGNAME% 2>&1
call func console [[java home]] : %JAVA_HOME% >> .log\%LOGNAME% 2>&1
call func console [[java version]]            >> .log\%LOGNAME% 2>&1
java -version                                 >> .log\%LOGNAME% 2>&1
call func console [[javac version]]           >> .log\%LOGNAME% 2>&1
javac -version                                >> .log\%LOGNAME% 2>&1

call func console [[project root]] - %TMP_PROJECT_ROOT_DIR%            >> .log\%LOGNAME% 2>&1
call func console [[production module]] - %TMP_PRODUCTION_MODULE_NAME% >> .log\%LOGNAME% 2>&1
call func console [[java source root]] - %TMP_JAVA_SRC_ROOT_DIR%       >> .log\%LOGNAME% 2>&1
set TMP_ARGS=%TMP_PROJECT_ROOT_DIR%\%TMP_PRODUCTION_MODULE_NAME%\%TMP_JAVA_SRC_ROOT_DIR%
call func console [[auto generate java source args]] - %TMP_ARGS%      >> .log\%LOGNAME% 2>&1

@rem ##########################################################################
@rem  gradle script
@rem ##########################################################################

@rem set OPTION=--stacktrace --info --debug
set OPTION=

@rem ##########################################################################
@rem  実行バイナリのクリア
@rem ##########################################################################
call func console 1: 実行バイナリのクリア - 開始
call func console [[1. task invoke]] >> .log\%LOGNAME% 2>&1
call gradlew.bat                                      ^
  foundation:clean                                    ^
  maintenance:clean                                   ^
  sample:clean                                        ^
  %OPTION% >> .log\%LOGNAME% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call func console 1: [E] clean command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call func console 1: [I] clean command success status="%ERRORLEVEL%"
)
call func console 1: 実行バイナリのクリア - 終了

@rem ##########################################################################
@rem  メンテナンスモジュールのビルド
@rem ##########################################################################
call func console 2: メンテナンスモジュールのビルド - 開始
call func console [[2. task invoke]] >> .log\%LOGNAME% 2>&1
call gradlew.bat                                      ^
  maintenance:compileJava                             ^
  maintenance:compileTestJava                         ^
  %OPTION% >> .log\%LOGNAME% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call func console 2: [E] maintenance build command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call func console 2: [I] maintenance build command success status="%ERRORLEVEL%"
)
call func console 2: メンテナンスモジュールのビルド - 終了

@rem ##########################################################################
@rem  メンテナンスモジュールのテスト
@rem ##########################################################################
call func console 3: メンテナンスモジュールのテスト - 開始
call func console [[3. task invoke]] >> .log\%LOGNAME% 2>&1
call gradlew.bat                                      ^
  maintenance:check -x compileJava                    ^
  %OPTION% >> .log\%LOGNAME% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call func console 3: [E] maintenance test command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call func console 3: [I] maintenance test command success status="%ERRORLEVEL%"
)
call func console 3: メンテナンスモジュールのテスト - 終了

@rem ##########################################################################
@rem  メンテナンスモジュールの実行
@rem ##########################################################################
call func console 4: メンテナンスモジュールの実行 - 開始
call func console [[4. task invoke]] >> .log\%LOGNAME% 2>&1
call gradlew.bat                                      ^
  maintenance:run -Pargs="%TMP_ARGS%" -x compileJava  ^
  %OPTION% >> .log\%LOGNAME% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call func console 4: [E] maintenance run command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call func console 4: [I] maintenance run command success status="%ERRORLEVEL%"
)
call func console 4: メンテナンスモジュールの実行 - 終了

@rem ##########################################################################
@rem  メインモジュール・サンプルモジュールのビルド
@rem ##########################################################################
call func console 5: メインモジュール・サンプルモジュールのビルド - 開始
call func console [[5. task invoke]] >> .log\%LOGNAME% 2>&1
call gradlew.bat                                      ^
  foundation:compileJava                              ^
  foundation:compileTestJava                          ^
  sample:compileJava                                  ^
  sample:compileTestJava                              ^
  %OPTION% >> .log\%LOGNAME% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call func console 5: [E] foundation,sample build command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call func console 5: [I] foundation,sample build success status="%ERRORLEVEL%"
)
call func console 5: メインモジュール・サンプルモジュールのビルド - 終了

@rem ##########################################################################
@rem  メインモジュールのテスト
@rem ##########################################################################
call func console 6: メインモジュールのテスト - 開始
call func console [[6. task invoke]] >> .log\%LOGNAME% 2>&1
call gradlew.bat                                      ^
  foundation:check -x compileJava                     ^
  %OPTION% >> .log\%LOGNAME% 2>&1
if not "%ERRORLEVEL%"  == "0" (
    call func console 6: [E] foundation test command failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call func console 6: [I] foundation test command success status="%ERRORLEVEL%"
)
call func console 6: メインモジュールのテスト - 終了

call func console ######## モジュール再ビルドバッチ処理が正常終了しました...
call func console [[######## make script all task success]] >> .log\%LOGNAME% 2>&1

timeout 60

endlocal
