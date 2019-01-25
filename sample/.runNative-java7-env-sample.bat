@echo off

@REM UTF-8
chcp 65001

pause

setlocal

mkdir ".\build\javac-out" > NUL 2>&1

::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::
:: JDK 1.7 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::

:: 1. compile
call ..\setjdk 1.7
if not "%ERRORLEVEL%"  == "0" (
    call func console 1: [E] jdk switch failed status="%ERRORLEVEL%"
    pause
    exit
)
call javac -J-Dfile.encoding=UTF-8 -encoding UTF-8 -Xlint:none ^
      -sourcepath ".\src\main\java" ^
      -d ".\build\javac-out" ^
      -cp "..\foundation\build\dependencies\compile\*;.\src\main\resources\*;.\src\main\java\*;..\foundation\build\jar-out\foundation-build20190125234308.jar" ^
      ".\src\main\java\Main4Se7.java"
if not "%ERRORLEVEL%"  == "0" (
    echo 1: [E] compile failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    echo compiled
)

:: 2. run
echo java run!
echo #######################################################################################################
call java -classpath ".\build\javac-out;..\foundation\build\dependencies\compile\*;..\foundation\build\jar-out\foundation-build20190125234308.jar" ^
     "Main4Se7"
echo #######################################################################################################
if not "%ERRORLEVEL%"  == "0" (
    echo 1: [E] java failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    echo JDK1.7 run-cli Success
)

pause

endlocal
