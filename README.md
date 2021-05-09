# Counter
This is a android widget for counter.

## How to use
### Step 1. 
Add the JitPack repository to your build file.

Add it in your root build.gradle at the end of repositories:
```gradle
 allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
  ```
### Step 2.
Add the dependency
```gradle
dependencies {
	  implementation 'com.github.bhavin121:Counter:0.1'
}
```

### Step 3.
Add to UI
```xml
<com.example.counterwidget.Counter
        android:id="@+id/counter"
        android:layout_width="160dp"
        android:layout_height="70dp"
        app:textColor="@color/white"
        app:cornerRadius="10dp"
        android:layout_centerInParent="true"/>
```

## Properties
### 1. Shadow
```xml
<com.example.counterwidget.Counter
        android:id="@+id/counter"
        android:layout_width="160dp"
        android:layout_height="70dp"
        ...
        android:shadowColor="@color/black"
        app:shadowRadius="5dp"
        app:shadowDy="2dp"
        app:shadowDx="2dp"
        />
```
### 2. Corner Radius
```xml
<com.example.counterwidget.Counter
        android:id="@+id/counter"
        android:layout_width="160dp"
        android:layout_height="70dp"
        ...
        app:cornerRadius="10dp"
        />
```
### 3. Colors
* Button Color
```xml
app:buttonColor="@color/black"
```
* Text Color
```xml
app:textColor="@color/white"
```

### 4. Padding
```xml
<com.example.counterwidget.Counter
        android:id="@+id/counter"
        android:layout_width="160dp"
        android:layout_height="70dp"
        ...
        app:contentPadding="10dp"
        app:contentPaddingLeft="5dp"
        app:contentPaddingRight="5dp"
        app:contentPaddingTop="5dp"
        app:contentPaddingBottom="5dp"
        />
```
## Java Implementation
### 1. OnChangeListener
Trigger when there is a change in counter value.
```java
counter.setOnChangeListener(new OnChangeListener() {
      @Override
      public void onChange(int value) {
         // Your Code Here
      }
 });
```
### 2. OnZeroListener
Trigger when the counter value is become zero for the first time.
```java
counter.setOnZeroListener(new OnZeroListener() {
    @Override
    public void onZero() {
       // Your Code Here
    }
});
```
### 3. Getting Counter Value
```java
int val = counter.getCounterValue();
```
