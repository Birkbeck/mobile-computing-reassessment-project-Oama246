@ECHO OFF
SET DIR=%~dp0
SET APP_ARGS=%*
"%DIR%\gradle\wrapper\gradle-wrapper.jar" %APP_ARGS%
