-dontwarn retrofit2.Platform$Java8
-keep class com.sedsoftware.data.entity.** { *; }
-keep class com.sedsoftware.domain.entity.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepattributes Exceptions