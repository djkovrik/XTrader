<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="${screenPackageName}">

    <application>

        <activity
            android:name="${screenPackageName}.view.${screenClass}"
            android:launchMode="singleTop"
            android:theme="@style/AppTheme">
            <#if isLauncher == true>
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </#if>

        </activity>

    </application>

</manifest>