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
MVVM 패턴을 공부하는데 적절한 자료를 찾기 어려웠고 자세히 공부해볼 겸 만들게 되었습니다.
프로젝트를 실행시키면 동일한 화면을 보실 수 있습니다.

💡왼쪽은 Room으로 로컬에서 가져온 데이터를 보여주고 오른쪽은 Retrofit으로 서버에서 가져온 데이터를 보여줍니다.
💡Coroutine을 사용해 비동기 통신을 하였습니다.
🔥단어를 꾹 누르면 데이터가 삭제됩니다.
```

# 🛠 Used Tech

 - `Kotlin`
 - `JetPack`
 - `AAC`
 - `ViewModel`
 - `LiveData`
 - `Room DB`
 - `Retrofit`
 - `Coroutine`

# 🏭 Architecture

- `MVVM Repository`

# ⚙️ Setting

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
