#Android - Badge View

Add a **Badge** to your bottom navigation(similar to iOS) with this custom view
This is strictly for in-app notification badges. If you are looking for app icon notification badges, head to the [Notifications class](https://developer.android.com/reference/android/app/Notification.html)

![The Badge View being used for tabs in bottom navigation](https://flic.kr/p/JGm6vJ)
###Installation
1. Add the _attrs.xml_ file to the values folder
2. Add this line to the parent layout  
xmlns:custom="http://schemas.android.com/apk/res-auto"
3. Add the package _BadgeView.java_ to your source project in order to use the package

###Usage
**_Note: It extends the image view class therefore it can make use of image view attributes too_**

It can be done in the following preferable ways:

1. Add the custom _BadgeView_ to the layout

    ```xml
<com.vijay.BadgeView
                    android:id="@+id/badge_view5"
                    android:layout_width="80dp"
                    android:layout_height="56dp"
                    android:layout_weight="2"
                    android:paddingRight="8dp"
                    android:src="@drawable/ic_view_headline_black_24dp"
                    custom:badgeColor="#b71c1c"
                    custom:badgeLabel="11"
                    custom:labelColor="#FFFFFF" />
    ```
By giving required values to the attributes _badgeColor, badgeLabel, labelColor_. These values will decide
the badges' color and label and also the labels' color.

2. Inflate view from layout into destination layout or tabs(For bottom navigation)

    ```java
LayoutInflater inflater = LayoutInflater.from(this);
View view = inflater.inflate(mLayout, null);
BadgeView badgeView =(BadgeView) view.findViewById(mElement);
badgeView.setImageResource(imgRes);
badgeView.setBadgeColor("#FFFFFF");
badgeView.setLabelColor("#FFFFFF");
badgeView.setLabelText("XYZ");
    ```

    Inflate the view on a fragement tab or a tab layout in order to use the custom view.
