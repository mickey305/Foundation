@echo off

:: ==========================================================
:: Windows(64bit) batch file tor set environment variables
:: JAVA_HOME and PATH for Oracle JDK or OpenJDK.
:: 
:: For Oracle JDK, search JDK installed path from Windows registory.
:: For OpenJDK, search JDK installed path from directory name under
:: OPENJDK_BASE directory specified in this batch file. (needs to
:: customize your configuration)
::
:: Oracle JDK ����� OpenJDK �̊��ݒ�o�b�`�t�@�C���B
::
:: - Oracle JDK�̏ꍇ�́AWindows 64bit OS�̃��W�X�g���ɓo�^����Ă���
::   JDK�̏����������A���ϐ�JAVA_HOME�����PATH��ݒ肷��B
:: - OpenJDK�̏ꍇ�́A�{�o�b�`�t�@�C���Ŏw�肵��OPENJDK_BASE�f�B���N�g����
::   ���ɂ��� jdk-<�o�[�W�����ԍ�> �̖��O�̃f�B���N�g����T���A
::   ���ϐ�JAVA_HOME�����PATH��ݒ肷��B
::
:: Usage:
::   setjdk {/? | [openjdk] [32] [<JDK Version>] }
::   /?   show usage only
::   openjdk        set for OpenJDK  
::   32             use 32bit JDK
::   <JDK Version>  use this version of JDK
::
:: 2018/10/21 Ver. 0.4.1 by TAKAHASHI,Toru  fix calling setjdk_misc
::
:: URL: https://gist.github.com/torutk/27f55630516286dd1478bbd8b032bc8a
:: ==========================================================

if "%1"=="/?" goto option_error

setlocal
set JAVA_HOME=
set JDK_VERSION=
set JDK_KEYPATH_OLD="HKLM\SOFTWARE\JavaSoft\Java Development Kit"
set JDK_KEYPATH_NEW="HKLM\SOFTWARE\JavaSoft\JDK"
set JDK_KEYPATH=
:: Modify base directory for your configuration
set OPENJDK_BASE=C:\tools

:: ----------------------------------------------------------
:: parsing command-line options and dispatching
:: 
:: �R�}���h���C���I�v�V�����̉�͂Ə����U�蕪��
:: ----------------------------------------------------------

:: ----------------------------------------------------------
:: when specified OpenJDK, set flag and shift command-line arguments.
::
:: openjdk���w�肳�ꂽ�ꍇ�̏���
:: ----------------------------------------------------------
if "%1"=="openjdk" (
   set OPENJDK=1
   @shift
)

:: ----------------------------------------------------------
:: when specified 32bit JDK, set flag and shift command-line arguments.
::
:: 32bit�ł��w�肳�ꂽ�ꍇ�̏���
:: ----------------------------------------------------------
set JDK_32BIT=0
if "%1"=="32" (
   set JDK_32BIT=1
   @shift
)

:: ----------------------------------------------------------
:: JDK 8 version number is 1.8 in registory, so replace 8 with 1.8
:: 
:: JDK 8�̏ꍇ�A���W�X�g�����1.8�Ƀo�[�W��������u��������B
:: ����ȊO�́A�R�}���h���C���Ŏw�肵���o�[�W�����������̂܂܎g�p����B
:: ----------------------------------------------------------
if "%1"=="8" (
    set JDK_VERSION=1.8
) else (
    set JDK_VERSION=%1
)

:: ----------------------------------------------------------
:: OpenJDK don't use registory, so set JAVA_HOME and jump to set_env
:: 
:: OpenJDK �̏ꍇ�A���W�X�g�����g�p���Ȃ��̂ŁAJAVA_HOME��OpenJDK�̃p�X
:: ��ݒ肵�Aset_env�փW�����v����B
:: ----------------------------------------------------------
if "%OPENJDK%"=="1" (
   set JAVA_HOME=%OPENJDK_BASE%\jdk-%JDK_VERSION%
   goto set_env
)

:: ----------------------------------------------------------
:: JDK 8 or before uses the following key (JDK 8�܂ł̓��W�X�g���̃L�[����)
::   HKLM\Software\JavaSoft\Java Development Kit
::
:: JDK 9 or after uses the following key (JDK 9����̓��W�X�g���̃L�[����)
::   HKLM\Software\JavSoft\JDK
:: ----------------------------------------------------------
if "%JDK_VERSION:~0,2%"=="1." (
    set JDK_KEYPATH=%JDK_KEYPATH_OLD%
    goto search_jdk
) else if not "%JDK_VERSION%"=="" (
    set JDK_KEYPATH=%JDK_KEYPATH_NEW%
    goto search_jdk
)
    
:: ----------------------------------------------------------
:: If no command-line option, search for JDK9+, then JDK8-,
:: additionally 32bit JDK9+, 32bit JDK8- from Windows registory.
:: Use the first found JDK.
:: 
:: �R�}���h���C���I�v�V�����w�肪�Ȃ��ꍇ�A�f�t�H���g��JDK�o�[�W��������������B
:: 64bit OS �ł́A32bit�� JDK �����邩�����ׂ�B
:: ----------------------------------------------------------
:: 64bit JDK 9+�̌���
call :search_jdk_path %JDK_KEYPATH_NEW%
if not "%JAVA_HOME%"=="" (
  echo JDK version is not specified, so using CurrentVersion=%JDK_VERSION%
  goto set_env
)
:: 64bit JDK 8-�̌���
call :search_jdk_path %JDK_KEYPATH_OLD%
if not "%JAVA_HOME%"=="" (
  echo JDK version is not specified, so using CurrentVersion=%JDK_VERSION%
  goto set_env
)

:search_jdk_32
if %JDK_32BIT%==0 set JDK_32BIT=1
:: 32bit JDK 9+�̌���
call :search_jdk_path %JDK_KEYPATH_NEW%
if not "%JAVA_HOME%"=="" (
  echo JDK version is not specified, so using CurrentVersion=%JDK_VERSION%
  goto set_env
)
:: 32bit JDK 8-�̌���
call :search_jdk_path %JDK_KEYPATH_OLD%
if not "%JAVA_HOME%"=="" (
  echo JDK version is not specified, so using CurrentVersion=%JDK_VERSION%
  goto set_env
)
:: ������Ȃ�������G���[�\�����I��
goto error_exit

:: ----------------------------------------------------------
:: search the JAVA_HOME under the specified registory key.
:: �w�肳�ꂽ�L�[�̉��ɂ���JDK�̃p�X(JAVA_HOME)��T���B
:: ----------------------------------------------------------
:search_jdk
:: echo [DEBUG]:serch_jdk JDK_KEYPATH=%JDK_KEYPATH%
call :search_jdk_path %JDK_KEYPATH%
if "%JAVA_HOME%"=="" goto jdk_error
goto set_env

:: ----------------------------------------------------------
:: append JAVA_HOME to PATH
:: ��������JAVA_HOME�̉���bin��PATH�ɑO�ǉ����I����
:: ----------------------------------------------------------
:set_env
endlocal & set JAVA_HOME=%JAVA_HOME%
echo set JAVA_HOME=%JAVA_HOME%
PATH=%JAVA_HOME%\bin;%PATH%
if not exist "%JAVA_HOME%" (
   echo JAVA_HOME %JAVA_HOME% is not exist.
   goto error_exit
)
echo append %JAVA_HOME%\bin to PATH
goto end

:: ----------------------------------------------------------
:: show usage when miss specifing command-line
::
:: �����̎w���莞�̏����\��
:: ----------------------------------------------------------
:option_error
echo Usage: %0 { /? ^| [openjdk] [32] [^<jdk version^>] }
echo   openjdk - Use OpenJDK 
echo   32 - Setting 32bit JDK
echo   ^<jdk version^> : 8  - JDK 8 Current Version
echo                 1.8  - JDK 8 Current Version
echo           1.8.0_152  - JDK 8u152
echo                   9  - JDK 9
echo               9.0.4  - JDK 9.0.4
echo                  10  - JDK 10
echo                  11  - JDK 11
echo                   :     :
goto error_exit

:: ----------------------------------------------------------
:: show error message when missing JDK
:: 
:: JDK��������Ȃ������ꍇ�̃G���[�\��
:: ----------------------------------------------------------
:jdk_error
if %JDK_32BIT%==1 set IS32=32bit
echo Cannot find installed JDK for version=%JDK_VERSION% %IS32%
echo;
goto error_exit

:: ----------------------------------------------------------
:: show error message, and installed JDK version as registory key
:: ----------------------------------------------------------
:error_exit
echo Installed JDKs in registory 64bit are as follows

reg query "HKLM\SOFTWARE\JavaSoft\JDK" /f * /k 2>nul
reg query "HKLM\SOFTWARE\JavaSoft\Java Development Kit" /f * /k 2>nul
echo;
echo Installed JDKs in registory 32bit are as follows

reg query "HKLM\SOFTWARE\JavaSoft\JDK" /f * /k /reg:32 2>nul
reg query "HKLM\SOFTWARE\JavaSoft\Java Development Kit" /f * /k /reg:32 2>nul
echo;
echo OpenJDK directory is
dir %JAVA_HOME%\..
endlocal
exit /b

:: ----------------------------------------------------------
:: function search_jdk_path <registory key>
:: get JDK path from Windows registory with specified registory key
:: and set to environment variable JAVA_HOME.
::
:: �֐� search_jdk_path <���W�X�g���[�L�[>
:: �w�肵�����W�X�g���[�L�[���猻�o�[�W������JDK�p�X���擾��
:: ���ϐ�JAVA_HOME�ɐݒ肷��
:: ----------------------------------------------------------
:search_jdk_path
:: echo [DEBUG]:search_jdk_path KEY=%1
:: echo [DEBUG]:search_jdk_path JDK_VERSION=%JDK_VERSION%
:: 32bit�p���W�X�g�������I�v�V����
if %JDK_32BIT%==1 set REG32=/reg:32
:: echo [DEBUG]:search_jdk_path REG32=%REG32%
:: get CurrentVersion value from JDK key and set to JDK_VERSION
:: ���W�X�g������JDK�̒l�̖��OCurrentVersion�����l��JDK_VERSION�ɐݒ�
if "%JDK_VERSION%"=="" (
    for /f "skip=2 tokens=2*" %%A in (
        'reg query %1 /v CurrentVersion %REG32% 2^>nul'
    ) do set JDK_VERSION=%%B
)
if "%JDK_VERSION%"=="" exit /b
:: get subkeys includes specified version and set to JDK_VERSION_KEY
:: JDK_VERSION��JDK�̃��W�X�g���L�[���擾
for /f "delims=" %%A in (
    'reg query %1 /f %JDK_VERSION% /k %REG32% 2^>nul ^| findstr /R ^HKEY_LOCAL.*\\%JDK_VERSION%'
) do set JDK_VERSION_KEY=%%A
:: echo [DEBUG]:search_jdk_path JDK_VERSION_KEY=%JDK_VERSION_KEY%
if "%JDK_VERSION_KEY%"=="" exit /b
:: get JavaHome value from the specified key and set to JAVA_HOME
:: ���W�X�g���L�[�̒l�̖��OJavaHome�����l��JAVA_HOME�ɐݒ�
for /f "skip=2 tokens=2*" %%A in (
    'reg query "%JDK_VERSION_KEY%" /v JavaHome %REG32%'
) do set JAVA_HOME=%%B
:: echo [DEBUG]:search_jdk_path JAVA_HOME=%JAVA_HOME%
exit /b

:: ----------------------------------------------------------
:: when normally exit, call additional batch 'setjdk_misc.bat' if exist.
::
:: ����I�����A�ǉ��o�b�`�t�@�C��'setjdk_misc.bat'�����݂���ΌĂяo���B
:: ----------------------------------------------------------
:end
if exist %~dp0%setjdk_misc.bat call setjdk_misc.bat
