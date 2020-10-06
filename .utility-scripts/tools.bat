@echo off

@REM UTF-8
chcp 65001

setlocal

@rem this batch file placed directory path
set TMP_THIS_DIR=%~dp0
set TMP_THIS_DIR=%TMP_THIS_DIR:~0,-1%
@rem path info
@rem set TMP_LIB_COMMON=%TMP_THIS_DIR%\common
set TMP_LIB_TOOL=%TMP_THIS_DIR%\lib-tools
set TMP_LIB_TOOL_VENDOR=%TMP_THIS_DIR%\lib-tools-vendor

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
echo # 1-dbg. make batch(debug mode)
echo # 1-trc. make batch(stacktrace mode)
echo #--------------------------------------------------------------------------
echo # 9. exit
echo ###########################################################################
set /p answer="select parameter : %answer%"
if "%answer%"=="1" (
  cls
  call %TMP_LIB_TOOL%\.make.bat

) else if "%answer%"=="2" (
  cls
  call %TMP_LIB_TOOL%\.create_javadoc.bat

) else if "%answer%"=="3" (
  cls
  call %TMP_LIB_TOOL%\.dec_javap.bat

) else if "%answer%"=="4" (
  cls
  call %TMP_LIB_TOOL%\.release_bintray.bat

) else if "%answer%"=="all" (
  cls
  call %TMP_LIB_TOOL%\.make.bat
  cls
  call %TMP_LIB_TOOL%\.create_javadoc.bat
  cls
  call %TMP_LIB_TOOL%\.dec_javap.bat
  cls
  call %TMP_LIB_TOOL%\.release_bintray.bat

) else if "%answer%"=="1-dbg" (
  cls
  call %TMP_LIB_TOOL%\.make_debug.bat

) else if "%answer%"=="1-trc" (
  cls
  call %TMP_LIB_TOOL%\.make_stacktrace.bat

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

