# kotlin-jetpack-example
[DataBinding-kotlin]

*레이아웃 및 바인딩 수식
https://developer.android.com/topic/libraries/data-binding/expressions?hl=ko

예시
android:text="@{String.valueOf(index + 1)}"
android:visibility="@{age > 13 ? View.GONE : View.VISIBLE}"
android:transitionName='@{"image_" + id}'
