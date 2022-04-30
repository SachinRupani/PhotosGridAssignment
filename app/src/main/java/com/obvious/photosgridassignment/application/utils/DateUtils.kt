package com.obvious.photosgridassignment.application.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
const val DISPLAY_DATE_FORMAT = "MMM d, yyyy"

/**
 * Extension function to convert string date
 * to [Date] object
 * @param dateFormat Format of the date eg. yyyy-MM-dd
 * @return [Date] object of the input date string
 */
fun String.toDateOrNull(dateFormat: String = DEFAULT_DATE_FORMAT): Date? {
    val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.ENGLISH)
    return simpleDateFormat.parse(this)
}

/**
 * Extension function to convert one date format string
 * to another
 * @param dateFormat [String] Input Date Format
 * @param dateFormatToDisplay [String] Output Date Format
 * @return [String] Output Date String
 */
fun String.toDisplayDate(
    dateFormat: String = DEFAULT_DATE_FORMAT,
    dateFormatToDisplay: String = DISPLAY_DATE_FORMAT
): String {
    val date: Date? = this.toDateOrNull(dateFormat = dateFormat)
    val simpleDateFormat = SimpleDateFormat(dateFormatToDisplay, Locale.ENGLISH)
    return date?.let {
        simpleDateFormat.format(it)
    } ?: ""
}