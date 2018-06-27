SET ANDROID_STUDIO_PATH=D:\Android\Android Studio Canary

SET CORE_DESTINATION=%ANDROID_STUDIO_PATH%\plugins\android\lib\templates\other\ModularCore\
SET INJECTOR_DESTINATION=%ANDROID_STUDIO_PATH%\plugins\android\lib\templates\other\ModularInjector\

xcopy /s /y "injector" "%CORE_DESTINATION%"
xcopy /s /y "injector" "%INJECTOR_DESTINATION%"

echo "Installation completed."
pause