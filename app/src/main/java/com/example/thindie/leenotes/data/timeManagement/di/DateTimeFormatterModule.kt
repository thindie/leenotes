package com.example.thindie.leenotes.data.timeManagement.di

import com.example.thindie.leenotes.data.timeManagement.util.TimePatterns
import dagger.Module
import dagger.Provides
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
internal class DateTimeFormatterModule {

    @Provides
    @Singleton
    @Named("custom")
    fun provideDateTimeFormatterCustom(
        timePattern: TimePatterns,
        locale: Locale,
    ): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(timePattern.patternCustom(), locale)
    }

    @Provides
    @Singleton
    @Named("day")
    fun provideDateTimeFormatterDay(timePattern: TimePatterns, locale: Locale): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(timePattern.patternDay(), locale)
    }

    @Provides
    @Singleton
    @Named("dayFull")
    fun provideDateWeekDay(timePattern: TimePatterns, locale: Locale): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(timePattern.patternDay(), locale)
    }

    @Provides
    @Singleton
    @Named("daySimpleNumber")
    fun provideSimpleDay(timePattern: TimePatterns, locale: Locale): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(timePattern.patternSimpleDay(), locale)
    }

    @Provides
    @Singleton
    @Named("datePicker")
    fun provideSpecialFormatDay(timePattern: TimePatterns, locale: Locale): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(timePattern.patternSpecialFormat(), locale)
    }

    @Provides
    @Singleton
    @Named("dayShort")
    fun provideDateTimeFormatterDayShort(
        timePattern: TimePatterns,
        locale: Locale,
    ): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(timePattern.patternDayShort(), locale)
    }

    @Provides
    @Singleton
    @Named("monthTitle")
    fun provideDateTimeFormatterMonthTitle(
        timePattern: TimePatterns,
        locale: Locale,
    ): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(timePattern.patternMonthFull(), locale)
    }

    @Provides
    @Singleton
    @Named("iso")
    fun provideDateTimeFormatterIso(): DateTimeFormatter {
        return DateTimeFormatter.ISO_DATE_TIME
    }

}