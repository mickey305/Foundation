@echo off
goto %1
exit /b

@rem console std output
:console
    setlocal

    @rem argument is %2~%n
    shift
    set TARG=
    :check
        if "%1"=="" goto final
        set TARG=%TARG% %1
        shift
        goto check
    :final

    call datetime.bat
    @rem console output
    echo [%yyyy%-%mm%-%dd% %hh%:%mi%:%ss%.%sss%]%TARG%

    endlocal
exit /b

pause
