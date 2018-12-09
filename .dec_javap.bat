@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  decompile command script for Windows
@rem
@rem ##########################################################################

@rem 遅延環境変数(!i!)の有効化
setlocal ENABLEDELAYEDEXPANSION

pause

set DT=%date%
set TM=%time: =0%
set LOGNAME=%yyyy%-%mm%-%dd%_%hh%%mi%%ss%_dec_javap
set CLASS_ROOT=foundation\out\production\classes

@rem decompile target class list
set VAR_1=com.mickey305.foundation.v4.lang.math.AbstractNumberTable
set VAR_2=com.mickey305.foundation.v4.lang.math.SquareMatrix
set VAR_3=com.mickey305.foundation.v3.lang.ref.SecureObjectTaskManager

@rem foreach
set i=1
:BEGIN
call set it=%%VAR_!i!%%
if defined it (

  @rem decompile result output
  javap -c %CLASS_ROOT%\!it! > .log\%LOGNAME%_!it!.txt

  set /A i+=1
  goto :BEGIN
)

endlocal

