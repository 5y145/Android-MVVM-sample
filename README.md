<div align="center">

<br>

# Android-MVVM-sample
JetPack을 활용한 MVVM repository 패턴 프로젝트의 예제입니다.

<p>
<img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=Android&logoColor=white"/>
<img src="https://img.shields.io/badge/Kotlin-3178C6?style=flat-square&logo=Kotlin&logoColor=white"/>
</p>

![mvvm_sample](https://user-images.githubusercontent.com/95672282/146951469-9cf538fb-c50f-4e01-b93f-929fbb800f68.gif)
<img src="https://user-images.githubusercontent.com/95672282/146952254-2e6508a4-b0a1-44f7-9593-e29d7b224c62.png" width="70%" height="70%">

</div>

<br><br>

# 📝 Introduce
```
MVVM repository 패턴을 구현한 예제입니다.
설명이 필요한 부분에 주석을 달아 놓았습니다.

💡Room과 Retrofit을 사용해 데이터를 가져오는 예제입니다.
💡비동기 작업을 위해 Coroutine을 사용하였습니다.
🔥단어를 꾹 누르면 데이터가 삭제됩니다.
```

# 🛠 Used Tech

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

# 🏭 Architecture

- `MVVM Repository`

# ⚙️ Setting

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
