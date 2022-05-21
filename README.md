# Somer Config for new Project(2022/5/21)
## Plugins

```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
}
```
## Add View Binding in Android block

```
  buildFeatures{
        viewBinding true
    }
```

## Dependency (Module app)

```
dependencies {
    //Paging
    def paging_version3 = "3.1.1"
    implementation "androidx.paging:paging-runtime-ktx:$paging_version3"

    def gson_converter = "2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:$gson_converter"
    def retrofit2_version = "2.9.0"
    implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
    def OKHTTP_version = "4.9.3"
    implementation("com.squareup.okhttp3:okhttp:$OKHTTP_version")

    def kotlin_coroutines = "1.5.2"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_coroutines"


    def coroutines_version = "2.4.1"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$coroutines_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$coroutines_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$coroutines_version"


    //Hilt_Dagger
    def hilt_version = "2.38.1"
    implementation "com.google.dagger:hilt-android:$hilt_version"
    kapt "com.google.dagger:hilt-compiler:$hilt_version"

    // viewModel library
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"

    //hilt viewModel integration library
    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    kapt 'androidx.hilt:hilt-compiler:1.0.0'

    // by viewModels() extension for activity and fragment
    implementation "androidx.activity:activity-ktx:1.4.0"
    implementation "androidx.fragment:fragment-ktx:1.4.1"

    //Coil
    implementation("io.coil-kt:coil:2.1.0")

    // Room
    def room_version = "2.4.2"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
    // optional - Test helpers
    testImplementation "androidx.room:room-testing:$room_version"
    // optional - Paging 3 Integration
    implementation "androidx.room:room-paging:2.5.0-alpha01"

    //Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation "com.squareup.retrofit2:converter-moshi:2.4.0"

    def multidex_version = "2.0.1"
    implementation "androidx.multidex:multidex:$multidex_version"

    //Others
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.6.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
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
## Sync Now 
> Happy Coding
