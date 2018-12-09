@echo off

@REM UTF-8
chcp 65001

setlocal

:keyinput
set answer=
set /p answer="「モジュール再ビルドバッチ処理」をデバッグモードで実行します(y/n)？  : %answer%"
if "%answer%"=="y" (
  call .make.bat --debug
) else if "%answer%"=="n" ( 
  echo キャンセルされました
  timeout 10
  exit /b 1
) else (
  echo 入力が誤っています
  goto keyinput
)

endlocal

