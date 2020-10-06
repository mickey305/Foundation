@echo off

@REM UTF-8
chcp 65001

setlocal

@rem this batch file placed directory path
set TMP_THIS_DIR=%~dp0
set TMP_THIS_DIR=%TMP_THIS_DIR:~0,-1%
@rem path info
set TMP_LIB_COMMON=%TMP_THIS_DIR%\..\common
set TMP_LIB_TOOL=%TMP_THIS_DIR%
set TMP_LIB_TOOL_VENDOR=%TMP_THIS_DIR%\..\lib-tools-vendor
set TMP_PROJECT_ROOT=%TMP_THIS_DIR%\..\..

:keyinput
set answer=
set /p answer="「モジュール再ビルドバッチ処理」をデバッグモードで実行します(y/n)？  : %answer%"
if "%answer%"=="y" (
  call %TMP_LIB_TOOL%\.make.bat --debug
) else if "%answer%"=="n" ( 
  echo キャンセルされました
  timeout 10
  exit /b 1
) else (
  echo 入力が誤っています
  goto keyinput
)

endlocal

