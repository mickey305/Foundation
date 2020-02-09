@echo off

@REM UTF-8
chcp 65001

pause

setlocal

@rem this batch file placed directory path
set TMP_THIS_DIR=%~dp0
set TMP_THIS_DIR=%TMP_THIS_DIR:~0,-1%
@rem path info
set TMP_LIB_COMMON=%TMP_THIS_DIR%\..\common
set TMP_LIB_TOOL=%TMP_THIS_DIR%
set TMP_LIB_TOOL_VENDOR=%TMP_THIS_DIR%\..\lib-tools-vendor
set TMP_PROJECT_ROOT=%TMP_THIS_DIR%\..\..

cd %TMP_PROJECT_ROOT%

set TMP_PROJECT_BUILD=%TMP_PROJECT_ROOT%\foundation\build
set TMP_PROJECT_SRC=%TMP_PROJECT_ROOT%\foundation\src

::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::
:: JDK 10 ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::
call %TMP_LIB_TOOL_VENDOR%\setjdk 10
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 1: [E] jdk switch failed status="%ERRORLEVEL%"
    pause
    exit
)
call %TMP_PROJECT_ROOT%\gradlew foundation:compileJava foundation:collectDependenciesJar
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 1: [E] prepare failed status="%ERRORLEVEL%"
    pause
    exit
)

dir /s /b /A-D "%TMP_PROJECT_SRC%\main\java" > "%TMP_PROJECT_BUILD%\all-java-files.txt"
mkdir "%TMP_PROJECT_BUILD%\javac-out" > NUL 2>&1

::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::
:: JDK 1.7 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::

:: 1. compile
call %TMP_LIB_TOOL_VENDOR%\setjdk 1.7
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 1: [E] jdk switch failed status="%ERRORLEVEL%"
    pause
    exit
)
for /f %%a in (%TMP_PROJECT_ROOT%\foundation\build\all-java-files.txt) do (
    call javac -J-Dfile.encoding=UTF-8 -encoding UTF-8 -Xlint:none ^
          -sourcepath "%TMP_PROJECT_SRC%\main\java" ^
          -d "%TMP_PROJECT_BUILD%\javac-out" ^
          -cp "%TMP_PROJECT_BUILD%\dependencies\compile\*;%TMP_PROJECT_SRC%\main\resources\*;%TMP_PROJECT_SRC%\main\java\*" ^
          %%a
    if not "%ERRORLEVEL%"  == "0" (
        call %TMP_LIB_COMMON%\func console 1: [E] compile failed status="%ERRORLEVEL%"
        pause
        exit
    ) else (
        call %TMP_LIB_COMMON%\func console %%a compiled
    )
)

:: 2. copy of resources
xcopy /e /q /y "%TMP_PROJECT_SRC%\main\resources" "%TMP_PROJECT_BUILD%\javac-out"
del /q %TMP_PROJECT_BUILD%\javac-out\*.gitignore

:: 3. archive
mkdir "%TMP_PROJECT_BUILD%\jar-out" > NUL 2>&1
call %TMP_LIB_COMMON%\datetime.bat
set JAR_NAME=foundation-build%yyyy%%mm%%dd%%hh%%mi%%ss%.jar
set JAR_LIST_NAME=foundation-build%yyyy%%mm%%dd%%hh%%mi%%ss%.jar.list.txt
call jar -J-Dfile.encoding=UTF-8 -cf %TMP_PROJECT_BUILD%\jar-out\%JAR_NAME% -C "%TMP_PROJECT_BUILD%\javac-out" "."
:: output
call jar -tf %TMP_PROJECT_BUILD%\jar-out\%JAR_NAME% > %TMP_PROJECT_BUILD%\jar-out\%JAR_LIST_NAME%
if not "%ERRORLEVEL%"  == "0" (
    call %TMP_LIB_COMMON%\func console 1: [E] jar failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call %TMP_LIB_COMMON%\func console JDK1.7 build-cli Success
)

pause

endlocal
