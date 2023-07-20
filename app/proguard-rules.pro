# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# Ktor specific rules
-dontwarn io.ktor.**
-keep class io.ktor.** { *; }
-keepclassmembers class io.ktor.** { *; }

# If you're using kotlinx.serialization for JSON serialization/deserialization
-dontwarn kotlinx.serialization.**
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class kotlinx.serialization.** { *; }

# If you're using kotlinx.coroutines for asynchronous programming
-dontwarn kotlinx.coroutines.**
-keep class kotlinx.coroutines.** { *; }
-keepclassmembers class kotlinx.coroutines.** { *; }

# If you're using kotlinx.atomicfu for atomic operations
-dontwarn kotlinx.atomicfu.**
-keep class kotlinx.atomicfu.** { *; }
-keepclassmembers class kotlinx.atomicfu.** { *; }

# Hilt-related classes
-keep class androidx.hilt.** { *; }
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Hilt annotation classes
-keepclasseswithmembers class * {
    @dagger.hilt.* <methods>;
}

# Kotlin coroutines
-keep class kotlin.coroutines.Continuation { *; }
-keep class kotlin.coroutines.CoroutineContext { *; }
-keep class kotlinx.coroutines.CoroutineExceptionHandler { *; }
-keep class kotlinx.coroutines.Dispatchers { *; }
-keep class kotlinx.coroutines.Job { *; }
-keep class kotlinx.coroutines.flow.** { *; }

# Keep ViewModel classes and their methods and fields
-keep class * extends androidx.lifecycle.ViewModel {
    <init>();
}

# Keep UseCase classes and their methods
-keep class com.example.kinetic.domain.use_case.** {
    <init>();
    public *;
}

# Keep Domain folder
-keep class com.example.kinetic.domain.** {*;}

# Keep Data folder
-keep class com.example.kinetic.data.** {*;}

# Keep Constants folder
-keep class com.example.kinetic.constants.** {*;}

-dontwarn groovy.lang.GroovyObject
-dontwarn groovy.lang.MetaClass
-dontwarn java.lang.management.ManagementFactory
-dontwarn javax.management.InstanceNotFoundException
-dontwarn javax.management.MBeanRegistrationException
-dontwarn javax.management.MBeanServer
-dontwarn javax.management.MalformedObjectNameException
-dontwarn javax.management.ObjectInstance
-dontwarn javax.management.ObjectName
-dontwarn javax.naming.Context
-dontwarn javax.naming.InitialContext
-dontwarn javax.naming.NamingException
-dontwarn javax.servlet.ServletContainerInitializer
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.codehaus.groovy.reflection.ClassInfo
-dontwarn org.codehaus.groovy.runtime.BytecodeInterface8
-dontwarn org.codehaus.groovy.runtime.ScriptBytecodeAdapter
-dontwarn org.codehaus.groovy.runtime.callsite.CallSite
-dontwarn org.codehaus.groovy.runtime.callsite.CallSiteArray
-dontwarn org.codehaus.janino.ClassBodyEvaluator
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE
-dontwarn sun.reflect.Reflection