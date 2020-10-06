@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  decompile command script for Windows
@rem
@rem ##########################################################################

@rem decompile target class list
set VAR_1=com.mickey305.foundation.v3.gen.R
set VAR_2=com.mickey305.foundation.v3.lang.math.MathFun
set VAR_3=com.mickey305.foundation.v3.lang.ref.SecureObjectTaskManager
set VAR_4=com.mickey305.foundation.v3.util.AbstractCryptoManager
set VAR_5=com.mickey305.foundation.v3.util.Assert
set VAR_6=com.mickey305.foundation.v3.util.ByteConverter
set VAR_7=com.mickey305.foundation.v3.util.CollectionUtil
set VAR_8=com.mickey305.foundation.v3.util.DateUtil
set VAR_9=com.mickey305.foundation.v3.util.Regexp
set VAR_10=com.mickey305.foundation.v3.util.ResFile
set VAR_11=com.mickey305.foundation.v3.util.collections.LRUMap
set VAR_12=com.mickey305.foundation.v3.util.collections.LRUSet
set VAR_13=com.mickey305.foundation.v3.util.concurrent.AutoUnlock
set VAR_14=com.mickey305.foundation.v3.util.concurrent.InstanceHasOneTransactionTemplate
set VAR_15=com.mickey305.foundation.v3.util.concurrent.NaturalInstanceId
set VAR_16=com.mickey305.foundation.v3.util.concurrent.NaturalObjectHashId
set VAR_17=com.mickey305.foundation.v4.lang.math.AbstractNumberTable
set VAR_18=com.mickey305.foundation.v4.lang.math.MathConst
set VAR_19=com.mickey305.foundation.v4.lang.math.SquareMatrix
set VAR_20=com.mickey305.foundation.v3.lang.ConsoleSnatcher
set VAR_21=com.mickey305.foundation.v3.util.ArrayUtil
set VAR_22=com.mickey305.foundation.v4.lang.math.handler.AbstractMatrixHandler

@REM UTF-8
chcp 65001

@rem 遅延環境変数(!i!)の有効化
setlocal ENABLEDELAYEDEXPANSION

@rem this batch file placed directory path
set TMP_THIS_DIR=%~dp0
set TMP_THIS_DIR=%TMP_THIS_DIR:~0,-1%
@rem path info
set TMP_LIB_COMMON=%TMP_THIS_DIR%\..\common
set TMP_LIB_TOOL=%TMP_THIS_DIR%
set TMP_LIB_TOOL_VENDOR=%TMP_THIS_DIR%\..\lib-tools-vendor
set TMP_PROJECT_ROOT=%TMP_THIS_DIR%\..\..

cd %TMP_PROJECT_ROOT%

call %TMP_LIB_COMMON%\func console ######## Javadecompile実行処理を開始します...

pause

call %TMP_LIB_COMMON%\datetime.bat
set LOGNAME=%yyyy%-%mm%-%dd%_%hh%%mi%%ss%_dec_javap
set LOGFILE_PATH=%TMP_THIS_DIR%\..\..\.log\%LOGNAME%
set CLASS_ROOT=%TMP_PROJECT_ROOT%\foundation\build\classes\java\main

@rem foreach
set i=1
:BEGIN
call set it=%%VAR_!i!%%
if defined it (
  call %TMP_LIB_COMMON%\func console %i%: Javadecの実行 - 開始
  call %TMP_LIB_COMMON%\func console %i%: class root - %CLASS_ROOT%
  call %TMP_LIB_COMMON%\func console %i%: target package - !it!
  call %TMP_LIB_COMMON%\func console [[%i%. task invoke start]] >> %LOGFILE_PATH%_[%i%]!it!.txt 2>&1

  @rem decompile result output
  javap -c -cp %CLASS_ROOT% !it! >> %LOGFILE_PATH%_[%i%]!it!.txt 2>&1

  if not "!ERRORLEVEL!" == "0" (
      call %TMP_LIB_COMMON%\func console %i%: [E] build javadec command failed status="!ERRORLEVEL!"
      pause
      exit
  ) else (
      call %TMP_LIB_COMMON%\func console %i%: [I] build javadec command success status="!ERRORLEVEL!"
  )
  call %TMP_LIB_COMMON%\func console %i%: Javadecの実行 - 終了
  call %TMP_LIB_COMMON%\func console [[%i%. task invoke end]] >> %LOGFILE_PATH%_[%i%]!it!.txt 2>&1
  
  
  set /A i+=1
  goto :BEGIN
)

call %TMP_LIB_COMMON%\func console ######## Javadecompile実行処理が正常終了しました...

timeout 60
endlocal

