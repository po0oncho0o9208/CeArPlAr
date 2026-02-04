package com.centrodeartes.cearplar;


import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

    public class FirebaseNotifications extends FirebaseMessagingService {
        @Override
        public void onMessageReceived( RemoteMessage remoteMessage) {

            Log.d("FCM_DEBUG", "onMessageReceived ejecutado");

            if (remoteMessage.getData().isEmpty()) {
                Log.d("FCM_DEBUG", "Mensaje sin data");
                return;
            }

            String titulo = remoteMessage.getData().get("titulo");
            String mensaje = remoteMessage.getData().get("mensaje");
            String clase = remoteMessage.getData().get("clase");

            Log.d("FCM_DEBUG", "titulo: " + titulo);
            Log.d("FCM_DEBUG", "mensaje: " + mensaje);
            Log.d("FCM_DEBUG", "clase: " + clase);

            mostrarNotificacion(titulo, mensaje, clase);
        }

        private void mostrarNotificacion(String titulo, String mensaje, String clase) {

            Intent intent = new Intent(this, PantallaPrincipal.class);
            intent.putExtra("titulo", titulo);
            intent.putExtra("mensaje", mensaje);
            intent.putExtra("clase", clase);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, "canal_data")
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle(titulo)
                            .setContentText(mensaje)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setPriority(NotificationCompat.PRIORITY_HIGH);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            NotificationManagerCompat.from(this).notify((int) System.currentTimeMillis(), builder.build());
        }
    }