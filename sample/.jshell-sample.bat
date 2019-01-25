@echo off

@REM UTF-8
chcp 65001

setlocal

call jshell -J-Dfile.encoding=utf8 ".\src\main\jsh\sample.jsh" --class-path "..\foundation\build\libs\foundation-0.6.25-beta.jar"

@REM pause

endlocal

