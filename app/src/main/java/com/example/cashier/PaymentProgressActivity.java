package com.example.cashier;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class PaymentProgressActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_PAYMENT_METHODS_ACTIVITY = "com.example.cashier.paymentMethodsActivity_MESSAGE";

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_progress);
        progressBar = findViewById(R.id.progressBar);
        new PaymentProgressTask().execute();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Cashier notifications";
            String description = "Notifications for the Cashier app";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("100", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showSuccessfulPaymentNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "100")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Cashier app")
                .setContentText("The payment has been successful!");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(100, mBuilder.build());
    }

    private class PaymentProgressTask extends AsyncTask<String, Integer, Void> {

        protected Void doInBackground(String... urls) {
            for (int i = 0; i < 100; i++) {
                SystemClock.sleep(50);
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        protected void onPostExecute(Void result) {
            showSuccessfulPaymentNotification();
            Intent intent = new Intent(PaymentProgressActivity.this, BillActivity.class);
            intent.putExtra(EXTRA_MESSAGE_PAYMENT_METHODS_ACTIVITY, "card");
            startActivity(intent);
        }
    }
}