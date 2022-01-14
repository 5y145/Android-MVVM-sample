<div align="center">

<br>

# Android-MVVM-sample

<p>
<img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=Android&logoColor=white"/>
<img src="https://img.shields.io/badge/Kotlin-3178C6?style=flat-square&logo=Kotlin&logoColor=white"/>
</p>
 
JetPack을 활용한 MVVM repository 패턴 프로젝트의 예제입니다.

<img src="https://user-images.githubusercontent.com/95672282/147233986-50ba83c8-b03f-4247-9c65-83579690431b.gif" width="25%" height="25%">

</div>

<br><br>

# Skill

 - `Kotlin`
 - `JetPack`
 - `AAC`
 - `View Binding`
 - `Data Binding`
 - `ViewModel`
 - `LiveData`
 - `Room`
 - `Retrofit`
 - `Coroutine`

# Architecture

`MVVM Repository`

# Setting

manifest
```
<application
    ....
    android:usesCleartextTraffic="true"
</application>

...
<uses-permission android:name="android.permission.INTERNET" />
```

gradle.app
```
plugins {
    ...
    id 'kotlin-kapt'
}
```

```
android {
    ...
    viewBinding {
        enabled = true
    }
    dataBinding {
        enabled = true
    }
}
```

```
// RecyclerView
implementation "androidx.recyclerview:recyclerview:1.2.1"

// Retrofit
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.squareup.okhttp3:okhttp:4.9.0'

// ViewModel
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0'

// Livedata
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.4.0"

// Room
implementation 'androidx.room:room-ktx:2.4.0'
implementation 'androidx.room:room-runtime:2.4.0'
kapt 'androidx.room:room-compiler:2.4.0'

// Coroutine
api "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2"
api "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
```
