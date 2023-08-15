# SFTP



@echo off
set "CarpetaParaComprimir=C:\Users\risla\Desktop\web"
set "RutaDestino=C:\Users\risla\Documents\compresor"

for /f "tokens=1-3 delims=/ " %%a in ('date /t') do (
    set "day=%%a"
    set "month=%%b"
    set "year=%%c"
)

rem Formatear año a yy (dos últimos dígitos)
set "year=%year:~-2%"

set "nombreApp=app%day%%month%%year%"
echo Nombre de la aplicación: %nombreApp%

set "NombreArchivo=app%day%%month%%year%.zip"

echo Comprimiendo la carpeta...
powershell.exe -command "Compress-Archive -Path '%CarpetaParaComprimir%' -DestinationPath ('%RutaDestino%\%NombreArchivo%')"

echo Carpeta comprimida exitosamente en la ubicación '%RutaDestino%\%NombreArchivo%'.

rem Eliminar archivos en el directorio de origen
echo Limpiando el directorio de origen...
del /q "%CarpetaParaComprimir%\*.*"

echo Directorio de origen limpiado exitosamente.


pause
