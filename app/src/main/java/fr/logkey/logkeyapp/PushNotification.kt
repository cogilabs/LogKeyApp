package fr.logkey.logkeyapp


data class PushNotification(
    val data: NotificationData,
    val to: String
)