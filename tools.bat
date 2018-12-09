@echo off

@REM UTF-8
chcp 65001

setlocal

cls

:keyinput
set answer=
echo ###########################################################################
echo # input parameter list
echo ###########################################################################
echo # 1. make batch
echo # 2. create javadoc batch
echo # 3. decompile batch
echo # 4. release batch(require: load bintray access user information env)
echo #--------------------------------------------------------------------------
echo # all. 1 to 4 task invoke
echo #--------------------------------------------------------------------------
echo # 9. exit
echo ###########################################################################
set /p answer="select parameter : %answer%"
if "%answer%"=="1" (
  cls
  call .make.bat

) else if "%answer%"=="2" (
  cls
  call .create_javadoc.bat

) else if "%answer%"=="3" (
  cls
  call .dec_javap.bat

) else if "%answer%"=="4" (
  cls
  call .release_bintray.bat

) else if "%answer%"=="all" (
  cls
  call .make.bat
  cls
  call .create_javadoc.bat
  cls
  call .dec_javap.bat
  cls
  call .release_bintray.bat

) else if "%answer%"=="9" (
  exit /b 1

) else (
  cls
  echo @@@@@@ invalid input parameter @@@@@@
  echo input="%answer%"
  goto keyinput

)
cls
goto keyinput

endlocal

