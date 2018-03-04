call runcrud.bat
if "%ERRORLEVEL%" =="0" goto runchrome
echo .
echo RUNCRUD has errors
goto fail

:runchrome
start chrome "http://localhost:8080/crud/v1/task"
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.