#Depricated For java
# Some Config for new Project(2022/7/27)
# Lets setup XML(without Compose) Dependency

## Plugins

```
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
    //id("com.google.devtools.ksp") version "1.6.10-1.0.2"
}
```

## Add View Binding in Android block

```
//if you use viewBinding -> jetpack compose not need
  buildFeatures{
        viewBinding true
    }
```

## Dependency (Module Project)

```
//Add Inside buildscript {} Scope->
//Add All Class Path
    dependencies {
        classpath "com.google.dagger:hilt-android-gradle-plugin:2.41"
        // Firebase SDK
        //classpath 'com.google.gms:google-services:4.3.13'
    }
```

## Dependency (Module app)

```
dependencies {
    //Others
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.6.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'


    def lifecycle_version = "2.5.0-rc01"
    def room_version = "2.4.2"
    def paging_version3 = "3.1.1"
    def gson_converter = "2.9.0"
    def retrofit2_version = "2.9.0"
    def OKHTTP_version = "4.9.3"
    def kotlin_coroutines = "1.5.2"
    def hilt_version = "2.38.1"
    def multidex_version = "2.0.1"



    //Room
    implementation "androidx.room:room-ktx:$room_version"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
             // optional - Test helpers
    testImplementation "androidx.room:room-testing:$room_version"
            // optional - Paging 3 Integration
    implementation "androidx.room:room-paging:2.5.0-alpha01"
    
       // To use Kotlin Symbolic Processing (KSP)
    //ksp("androidx.room:room-compiler:$roomVersion")

    //Paging3
    implementation "androidx.paging:paging-runtime-ktx:$paging_version3"
    implementation "androidx.paging:paging-runtime:$paging_version3"

    //ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    // LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

    //Hilt_Dagger
    implementation "com.google.dagger:hilt-android:$hilt_version"
    kapt "com.google.dagger:hilt-compiler:$hilt_version"

    //Kotlin Coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_coroutines"


    //Retrofit
    implementation "com.squareup.retrofit2:converter-gson:$gson_converter"
    implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
    implementation "com.squareup.retrofit2:converter-moshi:$retrofit2_version"
    implementation("com.squareup.okhttp3:okhttp:$OKHTTP_version")


    //Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")


    //hilt viewModel integration library
    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    kapt 'androidx.hilt:hilt-compiler:1.0.0'

    // by viewModels() extension for activity and fragment
    implementation "androidx.activity:activity-ktx:1.4.0"
    implementation "androidx.fragment:fragment-ktx:1.4.1"

    //Coil
    implementation("io.coil-kt:coil:2.1.0")

    implementation "androidx.multidex:multidex:$multidex_version"


}
```

## Add Hilt plugin (X->Deprecated Classpath) in pluginManagement block

```
  resolutionStrategy {
        eachPlugin {
            def hilt_version = "2.38.1"
            if( requested.id.id == 'dagger.hilt.android.plugin') {
                useModule("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
            }
        }
    }
```


# Now Lets setup Jetpack Compose Dependency

## Dependency (Module Project)-> JetPack Compose
```
buildscript {
    ext {
        compose_version = '1.1.1'
        hilt_version = '2.41'
        room_version = "2.4.2"
        retrofitVersion = '2.9.0'
        kotlin_version = "1.6.10"
        accompanist_version = '0.20.2'
        lifecycle_version = "2.5.0-rc01"
        material3_version = '1.0.0-alpha09'
        ktx_coroutine_version = '1.6.1'
        camerax_version = '1.0.1'
    }

    dependencies {
        classpath "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"
        // Firebase SDK
        classpath 'com.google.gms:google-services:4.3.13'
    }

}

plugins {
    id 'com.android.application' version '7.1.2' apply false
    id 'com.android.library' version '7.1.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.6.10' apply false
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

```

## Dependency (Module app)-> JetPack Compose
```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
    id "org.jlleitschuh.gradle.ktlint" version "10.3.0"
    id 'com.google.gms.google-services'
}

android {
    compileSdk 31

    defaultConfig {
        applicationId "jp.cognivision.cpbmobile"
        minSdk 29
        targetSdk 31
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary true
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += [
                        "room.schemaLocation":"$projectDir/schemas".toString(),
                        "room.incremental":"true",
                        "room.expandProjection":"true"]
            }
        }
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    buildFeatures {
        compose true
        viewBinding true
    }
    composeOptions {
        kotlinCompilerExtensionVersion compose_version
    }
    packagingOptions {
        resources {
            excludes += '/META-INF/{AL2.0,LGPL2.1}'
        }
    }
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach { dependsOn() }
}

dependencies {

 implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.8.0'
    implementation 'androidx.appcompat:appcompat:1.4.2'
    implementation 'com.google.android.material:material:1.6.1'
    implementation "androidx.compose.ui:ui:$compose_version"
    implementation "androidx.compose.material:material:$compose_version"
    implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.0'
    implementation 'androidx.activity:activity-compose:1.5.0'
    testImplementation 'junit:junit:4.12'
    debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"

    //material 3
    implementation "androidx.compose.material3:material3:$material3_version"

    //Materials Icons
    implementation "androidx.compose.material:material-icons-extended:$compose_version"


    // Compose dependencies
    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha01"
    implementation "androidx.hilt:hilt-navigation-compose:1.0.0"


    //Room
    implementation "androidx.room:room-ktx:$room_version"
    implementation "androidx.room:room-runtime:$room_version"
    debugImplementation "androidx.compose.ui:ui-test-manifest:$compose_version"
    kapt "androidx.room:room-compiler:$room_version"

    // ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    // LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

    //retrofit
    implementation "com.squareup.retrofit2:retrofit:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    //moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation 'com.squareup.retrofit2:converter-moshi:2.9.0'

    //Ok Http
    implementation "com.squareup.okhttp3:okhttp:4.9.3"
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.0'

    //dagger hilt
    implementation "com.google.dagger:hilt-android:$hilt_version"
    kapt "com.google.dagger:hilt-compiler:$hilt_version"
    kapt "androidx.hilt:hilt-compiler:1.0.0"


    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.3.0")

    //kotlin Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0'

    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$ktx_coroutine_version"

    // android ktx
    implementation 'androidx.activity:activity-ktx:1.5.0'

    //hilt viewmodel
    // implementation 'androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03'

    //pager
    implementation "com.google.accompanist:accompanist-pager:$accompanist_version"
    implementation "com.google.accompanist:accompanist-pager-indicators:$accompanist_version"

    //navigation
    implementation("androidx.navigation:navigation-compose:2.5.0")

    // Coil Image Dependency
    implementation("io.coil-kt:coil-compose:2.1.0")

    //LiveData
    implementation("androidx.compose.runtime:runtime:1.3.0-alpha01")
    implementation("androidx.compose.runtime:runtime-livedata:1.3.0-alpha01")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.3.0-alpha01")

    //Gson
    implementation 'com.google.code.gson:gson:2.9.0'

    // Accompanist (Jetpack Compose Flow Layouts)
    implementation "com.google.accompanist:accompanist-flowlayout:0.15.0"
    // Accompanist (Jetpack Compose Pager, indicator)
    implementation "com.google.accompanist:accompanist-pager:0.24.6-alpha"
    implementation "com.google.accompanist:accompanist-swiperefresh:0.24.6-alpha"

    // Accompanist (System UI Controller)
    implementation 'com.google.accompanist:accompanist-systemuicontroller:0.17.0'
    implementation 'com.google.accompanist:accompanist-insets:0.17.0'

    //Camera
    implementation "androidx.camera:camera-core:$camerax_version"
    implementation "androidx.camera:camera-camera2:$camerax_version"
    implementation "androidx.camera:camera-lifecycle:$camerax_version"
    implementation 'androidx.camera:camera-view:1.0.0-alpha28'

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.0-rc01")

    // Preferences DataStore
    implementation "androidx.datastore:datastore-preferences:1.0.0"


    // Local unit tests
    testImplementation "androidx.test:core:1.4.0"
    testImplementation "junit:junit:4.13.2"
    testImplementation "androidx.arch.core:core-testing:2.1.0"
    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:$ktx_coroutine_version"
    testImplementation "com.squareup.okhttp3:mockwebserver:4.9.1"
    testImplementation "io.mockk:mockk:1.10.5"
    // Mockk
    androidTestImplementation "io.mockk:mockk-android:1.10.5"
    debugImplementation "androidx.compose.ui:ui-test-manifest:1.3.0-alpha01"

    // Instrumentation tests
    androidTestImplementation 'com.google.dagger:hilt-android-testing:2.41'
    kaptAndroidTest "com.google.dagger:hilt-android-compiler:$hilt_version"
    androidTestImplementation "junit:junit:4.13.2"
    androidTestImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:$ktx_coroutine_version"
    androidTestImplementation "androidx.arch.core:core-testing:2.1.0"
    //Truth Dependency
    androidTestImplementation "com.google.truth:truth:1.1.3"
    testImplementation "com.google.truth:truth:1.1.3"

    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test:core-ktx:1.4.0'
    androidTestImplementation "com.squareup.okhttp3:mockwebserver:4.9.1"
    androidTestImplementation "io.mockk:mockk-android:1.10.5"
    androidTestImplementation 'androidx.test:runner:1.4.0'
    androidTestImplementation 'com.linkedin.dexmaker:dexmaker-mockito:2.28.3'

    //Testing Dependency
    testImplementation 'junit:junit:'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    testImplementation "com.google.truth:truth:1.1.3"




```

## Sync Now

> Happy Coding
