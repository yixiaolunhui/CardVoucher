# CardVoucher
卡券常用的锯齿布局

效果图:

##1、水平
         <com.dalong.cardvoucherlayout.CardVoucherLayout
                android:layout_width="match_parent"
                android:layout_height="130dp"
                android:gravity="center"
                android:layout_marginTop="20dp"
                android:layout_marginLeft="20dp"
                android:layout_marginRight="20dp"
                android:background="@color/colorAccent"
                app:CardVoucher_Orientation="horizontal"
                app:CardVoucher_Radius="15"
                app:CardVoucher_Spacing="15"
                app:CardVoucher_Color="@color/white">
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:text="Hello World!"
                    android:textSize="40sp"
                    android:textColor="@color/white"
                    android:gravity="center"
                    app:layout_constraintBottom_toBottomOf="@+id/activity_main"
                    app:layout_constraintLeft_toLeftOf="@+id/activity_main"
                    app:layout_constraintRight_toRightOf="@+id/activity_main"
                    app:layout_constraintTop_toTopOf="@+id/activity_main" />
            </com.dalong.cardvoucherlayout.CardVoucherLayout>

![image](https://github.com/dalong982242260/CardVoucher/blob/master/img/cardvoucher1.png)

##1、竖直
         <com.dalong.cardvoucherlayout.CardVoucherLayout
                android:layout_width="match_parent"
                android:layout_height="130dp"
                android:gravity="center"
                android:layout_marginTop="20dp"
                android:layout_marginLeft="20dp"
                android:layout_marginRight="20dp"
                android:background="@color/colorAccent"
                app:CardVoucher_Orientation="vertical"
                app:CardVoucher_Radius="15"
                app:CardVoucher_Spacing="15"
                app:CardVoucher_Color="@color/white">
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:text="Hello World!"
                    android:textSize="40sp"
                    android:textColor="@color/white"
                    android:gravity="center"
                    app:layout_constraintBottom_toBottomOf="@+id/activity_main"
                    app:layout_constraintLeft_toLeftOf="@+id/activity_main"
                    app:layout_constraintRight_toRightOf="@+id/activity_main"
                    app:layout_constraintTop_toTopOf="@+id/activity_main" />
            </com.dalong.cardvoucherlayout.CardVoucherLayout>

![image](https://github.com/dalong982242260/CardVoucher/blob/master/img/cardvoucher2.png)

##1、水平和竖直
        <com.dalong.cardvoucherlayout.CardVoucherLayout
                android:layout_width="match_parent"
                android:layout_height="130dp"
                android:gravity="center"
                android:layout_marginTop="20dp"
                android:layout_marginLeft="20dp"
                android:layout_marginRight="20dp"
                android:background="@color/colorAccent"
                app:CardVoucher_Orientation="all"
                app:CardVoucher_Radius="15"
                app:CardVoucher_Spacing="15"
                app:CardVoucher_Color="@color/white">
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:text="Hello World!"
                    android:textSize="40sp"
                    android:textColor="@color/white"
                    android:gravity="center"
                    app:layout_constraintBottom_toBottomOf="@+id/activity_main"
                    app:layout_constraintLeft_toLeftOf="@+id/activity_main"
                    app:layout_constraintRight_toRightOf="@+id/activity_main"
                    app:layout_constraintTop_toTopOf="@+id/activity_main" />
            </com.dalong.cardvoucherlayout.CardVoucherLayout>

![image](https://github.com/dalong982242260/CardVoucher/blob/master/img/cardvoucher3.png)