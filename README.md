@echo off
setlocal enabledelayedexpansion

for /f "delims=" %%a in ('wmic os get localdatetime ^| find "."') do (
    set datetime=%%a
)

REM Extraer los componentes de la fecha y la hora
set "year=!datetime:~0,4!"
set "month=!datetime:~4,2!"
set "day=!datetime:~6,2!"
set "hour=!datetime:~8,2!"
set "minute=!datetime:~10,2!"
set "second=!datetime:~12,2!"

REM Formar la fecha en formato ddmmyy
set "formatted_date=!day!!month!!year:~-2!"

echo Fecha en formato ddmmyy: !formatted_date!

pause
