@echo off

@REM UTF-8
chcp 65001

pause

setlocal

::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::
:: JDK 10 ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::
call .\setjdk 10
if not "%ERRORLEVEL%"  == "0" (
    call func console 1: [E] jdk switch failed status="%ERRORLEVEL%"
    pause
    exit
)
call .\gradlew foundation:compileJava foundation:collectDependenciesJar
if not "%ERRORLEVEL%"  == "0" (
    call func console 1: [E] prepare failed status="%ERRORLEVEL%"
    pause
    exit
)

dir /s /b /A-D ".\foundation\src\main\java" > ".\foundation\build\all-java-files.txt"
mkdir ".\foundation\build\javac-out" > NUL 2>&1

::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::
:: JDK 1.7 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++::

:: 1. compile
call .\setjdk 1.7
if not "%ERRORLEVEL%"  == "0" (
    call func console 1: [E] jdk switch failed status="%ERRORLEVEL%"
    pause
    exit
)
for /f %%a in (.\foundation\build\all-java-files.txt) do (
    call javac -J-Dfile.encoding=UTF-8 -encoding UTF-8 -Xlint:none ^
          -sourcepath ".\foundation\src\main\java" ^
          -d ".\foundation\build\javac-out" ^
          -cp ".\foundation\build\dependencies\compile\*;.\foundation\src\main\resources\*;.\foundation\src\main\java\*" ^
          %%a
    if not "%ERRORLEVEL%"  == "0" (
        call func console 1: [E] compile failed status="%ERRORLEVEL%"
        pause
        exit
    ) else (
        call func console %%a compiled
    )
)

:: 2. copy of resources
xcopy /e /q /y ".\foundation\src\main\resources" ".\foundation\build\javac-out"
del /q .\foundation\build\javac-out\*.gitignore

:: 3. archive
mkdir ".\foundation\build\jar-out" > NUL 2>&1
call datetime.bat
set JAR_NAME=foundation-build%yyyy%%mm%%dd%%hh%%mi%%ss%.jar
set JAR_LIST_NAME=foundation-build%yyyy%%mm%%dd%%hh%%mi%%ss%.jar.list.txt
call jar -J-Dfile.encoding=UTF-8 -cf .\foundation\build\jar-out\%JAR_NAME% -C ".\foundation\build\javac-out" "."
:: output
call jar -tf .\foundation\build\jar-out\%JAR_NAME% > .\foundation\build\jar-out\%JAR_LIST_NAME%
if not "%ERRORLEVEL%"  == "0" (
    call func console 1: [E] jar failed status="%ERRORLEVEL%"
    pause
    exit
) else (
    call func console JDK1.7 build-cli Success
)

pause

endlocal
