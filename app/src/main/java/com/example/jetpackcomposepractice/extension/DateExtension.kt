package com.example.jetpackcomposepractice.extension

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.chrono.JapaneseChronology
import java.time.chrono.JapaneseDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


val ServerDateFormat = "yyyy-MM-dd"

/**
 * Date format : "yyyy-MM-dd HH:mm:ss"
 */
val ServerDateTimeFormat = "yyyy-MM-dd HH:mm:ss"

val Date.hour: Int
    get() = Calendar.getInstance().apply { time = this@hour }.get(Calendar.HOUR_OF_DAY)

val Date.min: Int
    get() = Calendar.getInstance().apply { time = this@min }.get(Calendar.MINUTE)

/**
 * Pattern: yyyy-MM-dd HH:mm:ss
 */
fun Date.formatToServerDateTimeDefaults(): String{
    val sdf= SimpleDateFormat(ServerDateTimeFormat, Locale.getDefault())
    return sdf.format(this)
}

fun Date.formatToTruncatedDateTime(): String{
    val sdf= SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
    return sdf.format(this)
}

/***
 * Default date time format yyyy年MM月dd日　EEEE
 */
fun Date.simpleDateTimeFormatStringForDisplay() : String {
    return "yyyy年M月d日　EEEE"
}

/**
 *  Pattern: yyyy年MM月dd日　EEEE
 */
fun Date.formatToLongDateForDisplay(): String{
    return SimpleDateFormat(simpleDateTimeFormatStringForDisplay(), Locale.getDefault()).format(this)
}

/**
 *  Pattern: yyyy年MM月dd日
 */
fun Date.formatToDateForDisplay(): String{
    return SimpleDateFormat( "yyyy年MM月dd日", Locale.getDefault()).format(this)
}

/**
 * Pattern: yyyy-MM-dd
 */
fun Date.formatToServerDateDefaults(): String{
    return SimpleDateFormat(ServerDateFormat, Locale.getDefault()).format(this)
}

/**
 * Pattern: HH:mm:ss
 */
fun Date.formatToServerTimeDefaults(): String{
    val sdf= SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: dd/MM/yyyy HH:mm:ss
 */
fun Date.formatToViewDateTimeDefaults(): String{
    val sdf= SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: dd/MM/yyyy
 */
fun Date.formatToViewDateDefaults(): String{
    val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: HH:mm:ss
 */
fun Date.formatToViewTimeDefaults(): String{
    val sdf= SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: HH:mm
 */
fun Date.formatToViewTimeDefaultExceptSec(): String{
    val sdf= SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Add field date to current date
 */
fun Date.add(field: Int, amount: Int): Date {
    Calendar.getInstance().apply {
        time = this@add
        add(field, amount)
        return time
    }
}

fun Date.addYears(years: Int): Date {
    return add(Calendar.YEAR, years)
}
fun Date.addMonths(months: Int): Date {
    return add(Calendar.MONTH, months)
}
fun Date.addDays(days: Int): Date {
    return add(Calendar.DAY_OF_MONTH, days)
}
fun Date.addHours(hours: Int): Date {
    return add(Calendar.HOUR_OF_DAY, hours)
}
fun Date.addMinutes(minutes: Int): Date {
    return add(Calendar.MINUTE, minutes)
}
fun Date.addSeconds(seconds: Int): Date {
    return add(Calendar.SECOND, seconds)
}

//@ExperimentalTime
/**
 * Get the date diff in days between start and end date
 * @param startDate string start date
 * @param endDate string end date
 */
//fun getDateDiff(format: SimpleDateFormat, startDate: String, endDate: String): Long {
//    return try {
//        DurationUnit.DAYS.convert(
//            format.parse(endDate).time - format.parse(startDate).time,
//            DurationUnit.MILLISECONDS
//        )
//    } catch (e: Exception) {
//        e.printStackTrace()
//        0
//    }
//}

//Japan Date format

//Change Date Format(String to Date and Date to Strong)
@SuppressLint("SimpleDateFormat")
fun convertStringToDateAndDateToString(dateValue:String){
    val date=  dateValue
    val dfStringToData = SimpleDateFormat("yyyy-MM-dd")
    val dfInDate = SimpleDateFormat("yyyy年MM月dd日")
    val parseDate = dfStringToData.parse(date)
    val dfDateToString = parseDate?.let { dfInDate.format(it) }

}


//Japan Date Format(With Era)
@RequiresApi(Build.VERSION_CODES.O)
fun japaneseDateWithEraFormat(currentDate :String){
    val value=  currentDate
    val japaneseEraDtf = DateTimeFormatter.ofPattern("GGGGy年M月d日")
        .withChronology(JapaneseChronology.INSTANCE)
        .withLocale(Locale.JAPAN)
    val gregorianDate: LocalDate = LocalDate.parse(value)
    val japaneseDate: JapaneseDate = JapaneseDate.from(gregorianDate)
//japaneseDate.format(japaneseEraDtf)
}


//Instant to Date format
val now = Date()

@RequiresApi(Build.VERSION_CODES.O)
val newDate = now.toInstant()
@RequiresApi(Build.VERSION_CODES.O)
val yesterday = newDate.minus(2, ChronoUnit.DAYS)

//println(newDate)
//println(yesterday)

//Use with date（Date Extension）
//val yesterday = Date().addDays(-1).formatToViewDateDefaults()