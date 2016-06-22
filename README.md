# 卡券常用的锯齿布局

##效果图:
![image](https://github.com/dalong982242260/CardVoucher/blob/master/img/6.png)

##XML Definition

        <com.dalong.cardvoucherlayout.CardVoucherLayout
                android:layout_width="match_parent"
                android:layout_height="120dp"
                android:gravity="center"
                android:layout_marginTop="20dp"
                android:layout_marginLeft="20dp"
                android:layout_marginRight="20dp"
                android:background="@color/colorAccent"
                app:CardVoucher_Orientation="vertical"
                app:CardVoucher_Shape="circle"
                app:CardVoucher_Radius="19"
                app:CardVoucher_Spacing="0"
                app:CardVoucher_Color="@color/colorPrimary">
                <include layout="@layout/item_layout"/>
            </com.dalong.cardvoucherlayout.CardVoucherLayout>

##Attributes
        
        |name|format|description|
        |:---:|:---:|:---:|
        | CardVoucher_Radius | float |设置半径
        | CardVoucher_Spacing | float |设置间距
        | CardVoucher_Color | color |设置锯齿颜色
        | CardVoucher_Orientation | integer |设置方向
        | CardVoucher_Shape | integer |设置图形


##如何使用:

        dependencies {
             compile 'com.dalong:cardvoucher:1.0.0'
        }      