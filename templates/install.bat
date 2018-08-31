SET ANDROID_STUDIO_PATH=D:\Android Studio Canary

SET CORE_DESTINATION=%ANDROID_STUDIO_PATH%\plugins\android\lib\templates\other\ModularCore\
SET INJECTOR_DESTINATION=%ANDROID_STUDIO_PATH%\plugins\android\lib\templates\other\ModularInjector\
SET PROVIDER_DESTINATION=%ANDROID_STUDIO_PATH%\plugins\android\lib\templates\other\ModularProvider\
SET SCREEN_DESTINATION=%ANDROID_STUDIO_PATH%\plugins\android\lib\templates\other\ModularScreen\

xcopy /s /y "core" "%CORE_DESTINATION%"
xcopy /s /y "injector" "%INJECTOR_DESTINATION%"
xcopy /s /y "provider" "%PROVIDER_DESTINATION%"
xcopy /s /y "screen" "%SCREEN_DESTINATION%"

echo "Installation completed."
pause