package com.rr.lazylist.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromTaskEmotion(value: TaskEmotion?): String? = value?.name

    @TypeConverter
    fun toTaskEmotion(value: String?): TaskEmotion? = value?.let { enumValueOf<TaskEmotion>(it) }

    @TypeConverter
    fun fromTaskCategory(value: TaskCategory): String = value.name

    @TypeConverter
    fun toTaskCategory(value: String): TaskCategory = enumValueOf(value)

    @TypeConverter
    fun fromRecurringPattern(value: RecurringPattern?): String? = value?.name

    @TypeConverter
    fun toRecurringPattern(value: String?): RecurringPattern? = value?.let { enumValueOf<RecurringPattern>(it) }

    @TypeConverter
    fun fromTaskAction(value: TaskAction): String = value.name

    @TypeConverter
    fun toTaskAction(value: String): TaskAction = enumValueOf(value)

    @TypeConverter
    fun fromAchievementType(value: AchievementType): String = value.name

    @TypeConverter
    fun toAchievementType(value: String): AchievementType = enumValueOf(value)

    @TypeConverter
    fun fromMoodType(value: MoodType): String = value.name

    @TypeConverter
    fun toMoodType(value: String): MoodType = enumValueOf(value)
}

