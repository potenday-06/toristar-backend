package com.workthis.toristar.common.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateConverter {

    public static String convertToRelativeDate(LocalDate date) {
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(date, today);

        if (daysBetween == 0) {
            return "오늘";
        } else if (daysBetween == 1) {
            return "1일 전";
        } else if (daysBetween < 7) {
            return daysBetween + "일 전";
        } else if (daysBetween < 14) {
            return "일주일 전";
        } else if (daysBetween < 21) {
            return "2주일 전";
        } else if (daysBetween < 30) {
            return "3주일 전";
        } else if (daysBetween < 60) {
            return "한 달 전";
        } else if (daysBetween < 90) {
            return "두 달 전";
        } else {
            return date.toString(); // YYYY-MM-DD 형식으로 반환
        }
    }

    public static String formatDateWithDay(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.KOREA);
        String formattedDate = date.format(formatter);
        String dayOfWeek = getKoreanDayOfWeek(date.getDayOfWeek());
        return formattedDate + "(" + dayOfWeek + ")";
    }

    private static String getKoreanDayOfWeek(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> "월";
            case TUESDAY -> "화";
            case WEDNESDAY -> "수";
            case THURSDAY -> "목";
            case FRIDAY -> "금";
            case SATURDAY -> "토";
            case SUNDAY -> "일";
        };
    }
}
