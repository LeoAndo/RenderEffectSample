# Rendereffectsample
RenderEffect sample (Android 12 +)

# アプリ概要
Android 12 から Viewのぼかし機能がAndroid APIに追加されたのでそのAPI検証アプリです。

# API
[RenderEffect](https://developer.android.com/reference/android/graphics/RenderEffect)<br>
[View#setRenderEffect](https://developer.android.com/reference/android/view/View#setRenderEffect(android.graphics.RenderEffect))

# Viewのぼかし機能

- `setRenderEffect(android.graphics.RenderEffect)` APIを使うと、どのViewでもぼかし設定を行うことが可能.
  - [link](https://youtu.be/D2cU_itNDAI?t=848)
- Windowコンテンツの背後にあるものもぼかし加工できる
  - [link](https://youtu.be/D2cU_itNDAI?t=871)

# capture

<img src="https://user-images.githubusercontent.com/16476224/119225374-51d1c300-bb3e-11eb-8ed7-9770f6f70b99.gif" width=320 />

# その他参考にした記事
[android-12-developer-preview-2](https://android-developers.googleblog.com/2021/03/android-12-developer-preview-2.html)
