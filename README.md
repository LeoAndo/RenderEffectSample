# RenderEffectSample
RenderEffect sample (Android 12 +)

[日本語版README](https://github.com/LeoAndo/RenderEffectSample/blob/main/readme/README_JP.md)

# Overview
Since Android 12, the view blur function has been added to the Android API, so it is the API verification app.

# API
[RenderEffect](https://developer.android.com/reference/android/graphics/RenderEffect)<br>
[View#setRenderEffect](https://developer.android.com/reference/android/view/View#setRenderEffect(android.graphics.RenderEffect))<br>
[setBackgroundBlurRadius](https://developer.android.com/reference/android/view/Window#setBackgroundBlurRadius(int))<br>
`setBackgroundBlurRadius` is used for Window content.<br>
**Note that the following Exception will occur if `0.0` is specified for the arguments radiusX and radiusY of`View#setRenderEffect`.**<br>
```
java.lang.IllegalArgumentException: nativePtr is null
 ```

# View blur functions

- Using the `setRenderEffect(android.graphics.RenderEffect)` API, you can set the blur in any view.
  - [link](https://youtu.be/D2cU_itNDAI?t=848)
- You can also blur what is behind the Window content
  - [link](https://youtu.be/D2cU_itNDAI?t=871)

# capture

<img src="https://user-images.githubusercontent.com/16476224/119225374-51d1c300-bb3e-11eb-8ed7-9770f6f70b99.gif" width=320 />

# refs
[android-12-developer-preview-2](https://android-developers.googleblog.com/2021/03/android-12-developer-preview-2.html)
