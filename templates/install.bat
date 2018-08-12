SET ANDROID_STUDIO_PATH=D:\Android\Android Studio

SET CORE_DESTINATION=%ANDROID_STUDIO_PATH%\plugins\android\lib\templates\other\ModularCore\
SET UICORE_DESTINATION=%ANDROID_STUDIO_PATH%\plugins\android\lib\templates\other\ModularUiCore\
SET INJECTOR_DESTINATION=%ANDROID_STUDIO_PATH%\plugins\android\lib\templates\other\ModularInjector\

xcopy /s /y "core" "%CORE_DESTINATION%"
:: xcopy /s /y "uicore" "%UICORE_DESTINATION%"
:: xcopy /s /y "injector" "%INJECTOR_DESTINATION%"

echo "Installation completed."
pause