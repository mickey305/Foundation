@echo off

@REM UTF-8
chcp 65001

pause

setlocal

@rem this batch file placed directory path
set TMP_THIS_DIR=%~dp0
set TMP_THIS_DIR=%TMP_THIS_DIR:~0,-1%
@rem path info
set TMP_LIB_COMMON=%TMP_THIS_DIR%\..\.utility-scripts\common
set TMP_LIB_TOOL=%TMP_THIS_DIR%\..\.utility-scripts\lib-tools
set TMP_LIB_TOOL_VENDOR=%TMP_THIS_DIR%\..\.utility-scripts\lib-tools-vendor
set TMP_PROJECT_ROOT=%TMP_THIS_DIR%\..
set TMP_PROJECT_FOUNDATION_BUILD=%TMP_PROJECT_ROOT%\foundation\build
set TMP_PROJECT_SAMPLE=%TMP_THIS_DIR%
set TMP_PROJECT_SAMPLE_SRC=%TMP_PROJECT_SAMPLE%\src
set TMP_PROJECT_SAMPLE_BUILD=%TMP_PROJECT_SAMPLE%\build
set TMP_JAR=foundation-build20200209213534.jar
set TMP_JAR_PATH=%TMP_PROJECT_FOUNDATION_BUILD%\jar-out\%TMP_JAR%
@rem build and run target-class
set TMP_JAVA_MAIN_CLASSNAME=Main4Se7

cd %TMP_PROJECT_ROOT%

mkdir "%TMP_PROJECT_SAMPLE%\build\javac-out" > NUL 2>&1

::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::
:: JDK 1.7 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::

:: 1. compile
call %TMP_LIB_TOOL_VENDOR%\setjdk 1.7
if not "%ERRORLEVEL%"  == "0" (
    call func console 1: [E] jdk switch failed status="%ERRORLEVEL%"
    pause
    exit
)
call javac -J-Dfile.encoding=UTF-8 -encoding UTF-8 -Xlint:none ^
      -sourcepath "%TMP_PROJECT_SAMPLE_SRC%\main\java" ^
      -d "%TMP_PROJECT_SAMPLE_BUILD%\javac-out" ^
      -cp "%TMP_PROJECT_FOUNDATION_BUILD%\dependencies\compile\*;%TMP_PROJECT_SAMPLE_SRC%\main\resources\*;%TMP_PROJECT_SAMPLE_SRC%\main\java\*;%TMP_JAR_PATH%" ^
      "%TMP_PROJECT_SAMPLE_SRC%\main\java\%TMP_JAVA_MAIN_CLASSNAME%.java"
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
call java -classpath "%TMP_PROJECT_SAMPLE_BUILD%\javac-out;%TMP_PROJECT_FOUNDATION_BUILD%\dependencies\compile\*;%TMP_JAR_PATH%" ^
     "%TMP_JAVA_MAIN_CLASSNAME%"
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
