@echo off
goto %1
exit /b

@rem console std output
:console
    setlocal

    @rem this batch file placed directory path
    set TMP_THIS_DIR=%~dp0
    set TMP_THIS_DIR=%TMP_THIS_DIR:~0,-1%
    @rem path info
    set TMP_LIB_COMMON=%TMP_THIS_DIR%
    set TMP_LIB_TOOL=%TMP_THIS_DIR%\..\lib-tools
    set TMP_LIB_TOOL_VENDOR=%TMP_THIS_DIR%\..\lib-tools-vendor

    @rem argument is %2~%n
    shift
    set TARG=
    :check
        if "%1"=="" goto final
        set TARG=%TARG% %1
        shift
        goto check
    :final

    call %TMP_LIB_COMMON%\datetime.bat
    @rem console output
    echo [%yyyy%-%mm%-%dd% %hh%:%mi%:%ss%.%sss%]%TARG%

    endlocal
exit /b

pause
