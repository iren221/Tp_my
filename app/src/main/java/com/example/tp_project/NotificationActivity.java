package com.example.tp_project;



//import static androidx.activity.compose.ComponentActivityKt.setContent;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.compose.runtime.MutableState;
//import androidx.core.app.ComponentActivity;
//import androidx.core.app.NotificationCompat;
//import androidx.core.content.ContextCompat;
//
//import kotlin.jvm.internal.Intrinsics;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.provider.Settings;

public class NotificationActivity extends AppCompatActivity {

    public String id_channel = "pet_channel";
    public String name_channel = "pet_channel_name";
    private int PERMISSION_REQUEST_CODE = 123;
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
//            //тут пишем что делать если разрешение дано
//            showNotification();
//
//        } else {
//            // Permission not granted, request it
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
//        }


    }
    // Проверка наличия разрешения на получение уведомлений
    private boolean isNotificationPermissionGranted() {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        return notificationManagerCompat.areNotificationsEnabled();
    }
    // Запрос разрешения на получение уведомлений
    private void requestNotificationPermission() {
        if (!isNotificationPermissionGranted()) {
            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            startActivityForResult(intent, REQUEST_NOTIFICATION_PERMISSION);
        }
    }
    // Обработка результата запроса разрешения
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (isNotificationPermissionGranted()) {
            // Разрешение получено
                showNotification();
            } else {
            // Разрешение не получено
                Intent recommend = new Intent(NotificationActivity.this, AnimalDetails.class);
                startActivity(recommend);
            }
        }
    }
    private void showNotification() {
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Hello world")
                .setContentText("This is a description")
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            // Permission granted, load image
//                showNotification();
//            } else {
//                Intent recommend = new Intent(NotificationActivity.this, AnimalDetails.class);
//                startActivity(recommend);
//                // Разрешение не дано, тут можно ошибку выдать
//            }
//        }
//    }

}

//        setContent(() -> NotificationPermissionsTheme.INSTANCE.getContent(this));
//    }
//

//
//    public static class NotificationPermissionsTheme {
//        public static final NotificationPermissionsTheme INSTANCE = new NotificationPermissionsTheme();
//
//        private NotificationPermissionsTheme() {}
//
//        public final void getContent(ComponentActivity activity) {
//            Intrinsics.checkNotNullParameter(activity, "activity");
//            ActivityMainBinding binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
//            View root = binding.getRoot();
//            Context context = root.getContext();
//            boolean hasNotificationPermission = false;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                hasNotificationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
//            }
//            MutableState<Boolean> mutableStateOf = MutableStateKt.mutableStateOf(hasNotificationPermission);
//            Column column = new Column(root.getContext());
//            column.setVerticalArrangement(Arrangement.CENTER);
//            column.setHorizontalAlignment(Layout.Alignment.CENTER_HORIZONTALLY);
//            Button button = new Button(column.getContext());
//            button.setText("Request permission");
//            button.setOnClickListener(view -> {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                    activity.registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
//                        mutableStateOf.setValue(isGranted);
//                        return null;
//                    }).launch(Manifest.permission.POST_NOTIFICATIONS);
//                }
//            });
//            column.add(button);
//            Button button2 = new Button(column.getContext());
//            button2.setText("Show notification");
//            button2.setOnClickListener(view -> {
//                if (mutableStateOf.getValue()) {
//                    showNotification();
//                }
//            });
//            column.add(button2);
//            setContent(column);
//        }
//
//    }
//}

//addAction - переход от уведомления на активити