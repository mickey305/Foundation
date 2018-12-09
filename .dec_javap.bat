@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  decompile command script for Windows
@rem
@rem ##########################################################################

@rem decompile target class list
set VAR_1=com.mickey305.foundation.v4.lang.math.AbstractNumberTable
set VAR_2=com.mickey305.foundation.v4.lang.math.SquareMatrix
set VAR_3=com.mickey305.foundation.v3.lang.ref.SecureObjectTaskManager

@REM UTF-8
chcp 65001

@rem 遅延環境変数(!i!)の有効化
setlocal ENABLEDELAYEDEXPANSION

call func console ######## Javadecompile実行処理を開始します...

pause

call datetime.bat
set LOGNAME=%yyyy%-%mm%-%dd%_%hh%%mi%%ss%_dec_javap
set CLASS_ROOT=foundation\build\classes\java\main

@rem foreach
set i=1
:BEGIN
call set it=%%VAR_!i!%%
if defined it (
  call func console %i%: Javadecの実行 - 開始
  call func console %i%: class root - %CLASS_ROOT%
  call func console %i%: target package - !it!
  call func console [[%i%. task invoke start]] >> .log\%LOGNAME%_[%i%]!it!.txt 2>&1

  @rem decompile result output
  javap -c -cp %CLASS_ROOT% !it! >> .log\%LOGNAME%_[%i%]!it!.txt 2>&1

  if not "!ERRORLEVEL!" == "0" (
      call func console %i%: [E] build javadec command failed status="!ERRORLEVEL!"
      pause
      exit
  ) else (
      call func console %i%: [I] build javadec command success status="!ERRORLEVEL!"
  )
  call func console %i%: Javadecの実行 - 終了
  call func console [[%i%. task invoke end]] >> .log\%LOGNAME%_[%i%]!it!.txt 2>&1
  
  
  set /A i+=1
  goto :BEGIN
)

call func console ######## Javadecompile実行処理が正常終了しました...

timeout 60
endlocal

