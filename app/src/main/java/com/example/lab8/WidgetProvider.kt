package com.example.lab8

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.d(TAG, "onUpdate: ids=${appWidgetIds.joinToString()}")
        for (appWidgetId in appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        Log.d(TAG, "onReceive: action=${intent.action}")

        if (intent.action == ACTION_UPDATE_CLICK) {
            Log.d(TAG, "ACTION_UPDATE_CLICK received!")
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )
            Log.d(TAG, "appWidgetId from intent = $appWidgetId")
            if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                updateWidget(context, appWidgetManager, appWidgetId)
            } else {
                Log.e(TAG, "Invalid appWidgetId, cannot update")
            }
        }
    }

    private fun updateWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        Log.d(TAG, "updateWidget called for id=$appWidgetId")

        val views = RemoteViews(context.packageName, R.layout.widget_layout)

        // Обновляем текст с текущим временем
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        views.setTextViewText(R.id.widgetText, "Обновлено: $currentTime")
        Log.d(TAG, "Time set to: $currentTime")

        // Кнопка "Обновить" — фиксированный requestCode = 0
        val intent = Intent(context, WidgetProvider::class.java).apply {
            action = ACTION_UPDATE_CLICK
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0, // фиксированный requestCode
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        views.setOnClickPendingIntent(R.id.widgetButton, pendingIntent)
        Log.d(TAG, "PendingIntent set for button")

        // Заголовок открывает приложение
        val openAppIntent = Intent(context, MainActivity::class.java)
        val openAppPendingIntent = PendingIntent.getActivity(
            context,
            0,
            openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        views.setOnClickPendingIntent(R.id.widgetTitle, openAppPendingIntent)

        // Обновляем виджет
        appWidgetManager.updateAppWidget(appWidgetId, views)
        Log.d(TAG, "Widget updated")
    }

    companion object {
        private const val TAG = "WidgetDebug"
        const val ACTION_UPDATE_CLICK = "com.example.lab8widget.ACTION_UPDATE_CLICK"
    }
}