apply plugin: 'com.android.library'

apply plugin: 'kotlin-android'

apply plugin: 'kotlin-kapt'

android {
    compileSdkVersion 27
    defaultConfig {
        minSdkVersion 19
        targetSdkVersion 27
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    ${getConfigurationName("implementation")} fileTree(dir: 'libs', include: ['*.jar'])

    implementation project(':${coreModuleName}')
    implementation project(':${coreUiModuleName}')

    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"

    implementation "com.android.support:appcompat-v7:27.1.1"

    <#if cicerone>
    implementation "ru.terrakok.cicerone:cicerone:3.0.0"
    </#if>

    <#if moxy>
    implementation "com.arello-mobile:moxy:1.5.3"
    implementation "com.arello-mobile:moxy-app-compat:1.5.3"
    kapt "com.arello-mobile:moxy-compiler:1.5.3"
    </#if>

    implementation "com.google.dagger:dagger:2.17"
    kapt "com.google.dagger:dagger-compiler:2.17"
}
