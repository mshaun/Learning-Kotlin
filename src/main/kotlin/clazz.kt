class Name {
    var lastName: String = "" // (instance) field
    var firstName: String = "" // (instance) field
}

class Date {
    var year: Int = 2023
        private set

    var month: Int = 1
        private set

    var day: Int = 10
        private set

    private fun copy(): Date {
        val newDate = Date()
        newDate.year = this.year
        newDate.month = this.month
        newDate.day = this.day

        return newDate
    }

    fun addDay(days: Int): Date {
        val copied = this.copy()
        copied.addByDay(days)
        return copied
    }

    fun addMonth(months: Int): Date {
        // `this` is immutable
        val copied = this.copy()
        copied.addByMonth(months)

        return copied
    }

    fun addYear(years: Int): Date {
        val copied = this.copy()
        copied.addYear(years)
        return copied
    }

    fun addByDay(days: Int): Date {
        this.day += days
        while (this.day > numOfDays(this.year, this.month)) {
            this.day -= numOfDays(this.year, this.month)
            this.addByMonth(1)
        }
        while (this.day < 1) {
            this.addByMonth(-1)
            this.day += numOfDays(this.year, this.month)
        }
        return this
    }

    fun addByMonth(months: Int): Date {

        this.month += months

        while (this.month > 12) {
            this.addByYear(1)
            this.month -= 12
        }
        while (this.month < 1) {
            this.addByYear(-1)
            this.month += 12
        }
        return this
    }

    fun addByYear(years: Int): Date {
        val yearSum: Int = this.year + years
        this.year = yearSum
        return this
    }

    fun println() {
        println("$year/$month/$day")
    }
}

/**
 * ==
 * !=
 *
 * ===
 * !==
 *
 */

fun identifyLeapYear(years: Int): Boolean { //check that is the year is leapYear or not
    val leapYear: Boolean =
        when {
            ((years % 4 == 0) && (years % 100 !== 0) || (years % 400 == 0)) -> true
            else -> false
        }
    return leapYear
}

fun changeLeapYearDays(
    leapYear: Boolean,
    daysOfMonth: Array<Int>
): Array<Int> { //if the date's year is leapYear, change the days of the february
//    if (leapYear == true) {
    if (leapYear) {
        daysOfMonth[1] = 29
    } else {
        daysOfMonth[1] = 28
    }
    return daysOfMonth
}

fun adjustDaysOfMonth(year: Int, daysOfMonth: Array<Int>): Array<Int> {
    changeLeapYearDays(
        identifyLeapYear(year),
        daysOfMonth
    ) //if the date's year is leapYear, change the days of the february
    return daysOfMonth
}

val DAYS_OF_MONTH: Array<Int> =
    arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)  //days of every month


fun numOfDays(year: Int, month: Int): Int {
    adjustDaysOfMonth(year, DAYS_OF_MONTH)
    return DAYS_OF_MONTH[month - 1]
}

//fun addMonth(date: Date, months: Int): Date {
//    date.month += months
//    while (date.month > 12) {
//        addYear(date, 1)
//        date.month -= 12
//    }
//
//    while (date.month < 1) {
//        addYear(date, -1)
//        date.month += 12
//    }
//    return date
//
////    val monthSum: Int = date.month + months
////    if (monthSum > 12) {
////        addYear(date, monthSum / 12)
////        when {
////            monthSum % 12 == 0 -> return date
////            else -> date.month = monthSum % 12
////        }
////    } else if (monthSum < 1) {
////        // 1. 假設帶進來的參數date的年月日是正常的
////        //      -> 1 <= date.month <= 12
////        // 根據1. , 調整後的月份monthSum若小於1, 代表 months < 0
////        if (months > -12) {
////            // -12 < months < 0 -> 最多向year借1位 (因monthSum < 1 -> 一定要向year借1位)
////            addYear(date, -1)
////            // a. 1 <= date.month <= 12
////            // b. -12 < months < 0
////            // (a. + b.) -> -11 < monthSum < 1
////            date.month = monthSum + 12
////        } else {
////            if (months % 12 == 0) {
////                addYear(date, months / 12)
////            } else if (date.month + (months % 12) > 0) {
////                addYear(date, months / 12)
////                date.month += months % 12
////            } else {                                          //date.month + months%12 < 0
////                addYear(date, months / 12 - 1)
////                date.month += months % 12 + 12
////            }
////        }
////    } else {
////        date.month = monthSum
////        return date
////    }
////    return date
//}
//
//fun addYear(date: Date, years: Int): Date {
//    val yearSum: Int = date.year + years
//    date.year = yearSum
//    return date
//}
//
//fun addDate(date: Date, years: Int, months: Int, days: Int): Date {
//    date.day += days
//    while (date.day > numOfDays(date.year, date.month)) {
//        date.day -= numOfDays(date.year, date.month)
//        addMonth(date, 1)
//    }
//    while (date.day < 1) {
//        addMonth(date, -1)
//        date.day += numOfDays(date.year, date.month)
//    }
//    //    // Adjust right before numOfDays()
//////    adjustDaysOfMonth(date.year, daysOfMonth)
////
////    ////if date's year is leapYear, change the days of the february
////    var daySum: Int = date.day + days // Sum of the date.day and days
////
////    if (daySum > numOfDays(date.year, date.month)) {
////        while (daySum > numOfDays(date.year, date.month)) {
////            val numOfDays = numOfDays(date.year, date.month)
////            daySum -= numOfDays
////            addMonth(date, 1)
////            //if the date's year is leapYear, change the days of the february
////        }
////        date.day = daySum
////        return date
////    } else if (daySum < 1) {
////        while (daySum < 1) {
////            addMonth(date, -1)
////
////            val numOfDays = numOfDays(date.year, date.month)
////            daySum += numOfDays  //[lastMonth-1] and add -1 month
////            //if the date's year is leapYear, change the days of the february
////        }
////        date.day = daySum
////        return date
////    } else {
////        date.day = daySum
////        return date
////    }
//    addMonth(date, months)
//    addYear(date, years)
//    return date
//}

fun main() {



    val dateA: Date = Date()
    dateA.println()
//    addMonth(dateA, 12)
    val dateB = dateA.addMonth(12)
//    dateA.addByMonth(12)
//    dateA.getYear()

    println(dateA)
    dateB.println()
//    addMonth(dateA, 23) //: BUG

    /*
2023-1-10
2023-3-11
     */
    dateA.addByDay(60)

    println(dateA)

}

private fun println(dateA: Date) {
    dateA.println()
//    println("${dateA.year}-${dateA.month}-${dateA.day}")
}


