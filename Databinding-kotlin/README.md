### [DataBinding-kotlin]  

## [목적]
DataBinding을 사용하여 GitUser의 repository를 검색하는 앱

## [사용기술]
1. DataBinding
2. ViewModel
3. Retrofit



## [참고하면 좋은정보]  
*레이아웃 및 바인딩 수식참고  
https://developer.android.com/topic/libraries/data-binding/expressions?hl=ko  

예시)  
android:text='@{"userName:" + user.name}'  
android:text="@{String.valueOf(index + 1)}"  
android:visibility="@{age > 13 ? View.GONE : View.VISIBLE}"  
android:transitionName='@{"image_" + id}'  
