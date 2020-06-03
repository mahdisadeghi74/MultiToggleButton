# MultiToggleButton
A optional toggle button

[![Platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Version](https://jitpack.io/v/mahdisadeghi74/MultiToggleButtonSupport.svg)](https://jitpack.io/#mahdisadeghi74/MultiToggleButtonSupport)

#### `This library same toggle button but you can use many drawable or text for change it`:

#### Step 1
Add the JitPack repository to your build file
Add it in your root `build.gradle` at the end of repositories:

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### Step 2
Add the JitPack repository to your build file
Add the dependency:

in `kotlin`
```groovy
implementation 'com.github.mahdisadeghi74.MultiToggleButton:multitogglebutton:1.1.2'
```

in `java androidx`
```groovy
implementation 'com.github.mahdisadeghi74.MultiToggleButton:multitogglebutton-java:1.1.2'
```

in `java appCompatSupport`
```groovy
implementation 'com.github.mahdisadeghi74:MultiToggleButtonSupport:1.1.4'

```

## Redaing
add multitogglebutton to layout
```xml
    <com.dependencies.buria.multitogglebutton.MultiToggleButton
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:text="@string/app_name"
        android:id="@+id/tgba"
        app:textSize="20sp"
        app:textStyle="bold"
        app:buttonPadding="16dp"
        app:toggleButtonSize="40dp"
        app:toggleButtonTint="@color/colorAccent"
        app:textColor="@color/colorPrimary"/>
```

### kotlin code:

```kotlin
tgba.addToggleDrawables(R.drawable.ic_launcher_background, R.drawable.ic_android)
tgba.addToggleDrawables(R.drawable.ic_launcher_foreground)

tgba.setDefaultPosition(2)
tgba.setOnItemChangeListener { resourceId, position -> 
    Toast.makeText(this, "$resourceId : $position", Toast.LENGTH_SHORT).show()
}
```

you can add `text` instead image:
```kotlin
tgba.addToggleTexts("first", "Second")

tgba.setOnTextChangeListener { text, position ->
        Toast.makeText(this, "$text : $position", Toast.LENGTH_SHORT).show()
}
```

### java code:
java code is same kotlin code
