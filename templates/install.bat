SET ANDROID_STUDIO_PATH=D:\Android\Android Studio Canary
SET DESTINATION=%ANDROID_STUDIO_PATH%\plugins\android\lib\templates\other\Modular\Injector\

xcopy /s /y "injector" "%DESTINATION%"

echo "Installation completed."
pause