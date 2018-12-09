@echo off
setlocal

@rem this batch file placed directory path
set TMP_THIS_DIR=%~dp0
echo %TMP_THIS_DIR:~0,-1%

endlocal
