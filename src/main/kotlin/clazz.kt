class Name {
    var lastName: String = "" // (instance) field
    var firstName: String = "" // (instance) field
}

class Date {
    var year: Int = 2023
    var month: Int = 1
    var day: Int = 10
}

fun identifyLeapYear(years: Int): Boolean { //check that is the year is leapYear or not
    val leapYear: Boolean =
        when {
            ((years % 4 == 0) && (years % 100 !== 0) || (years % 400 == 0)) -> true
            else -> false
        }
    return leapYear
}

fun changeLeapYearDays(leapYear: Boolean, daysOfMonth: Array<Int>): Array<Int> { //if the date's year is leapYear, change the days of the february
    if (leapYear == true) {
        daysOfMonth[1] = 29
    }
    else {
        daysOfMonth[1] = 28
    }
    return daysOfMonth
}

fun addDay(date: Date, days: Int): Date {
    var leapYear: Boolean = identifyLeapYear(date.year)  //check that is the year is leapYear or not
    var daySum: Int = date.day + days // Sum of the date.day and days
    val daysOfMonth: Array<Int> = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)  //days of every month


    changeLeapYearDays(leapYear, daysOfMonth)  ////if date's year is leapYear, change the days of the february

    if (daySum > daysOfMonth[date.month - 1]) {
        while (daySum > daysOfMonth[date.month - 1]) {
            daySum -= daysOfMonth[date.month - 1]
            addMonth(date, 1)
            leapYear = identifyLeapYear(date.year) //check that is the date's year is leapYear or not
            changeLeapYearDays(leapYear, daysOfMonth) //if the date's year is leapYear, change the days of the february
            date.day = daySum
        }
        return date
    }
    else if (daySum < 1) {
        while (daySum < 1) {
            daySum += daysOfMonth[addMonth(date,-1).month-1]  //[lastMonth-1] and add -1 month
            leapYear = identifyLeapYear(date.year) //check that is the date's year is leapYear or not
            changeLeapYearDays(leapYear, daysOfMonth) //if the date's year is leapYear, change the days of the february
        }
        date.day = daySum
        return date
    }
    else {
        date.day = daySum
        return date
    }
}

fun addMonth(date: Date, months: Int): Date {
    val monthSum: Int = date.month + months
    if (monthSum > 12) {
        addYear(date, monthSum / 12)
        when{
            monthSum%12 == 0 -> return date
            else -> date.month = monthSum % 12
        }
    }
    else if (monthSum < 1) {
        if (months > -12) {
            addYear(date, -1)
            date.month = monthSum + 12
        }
        else {
            if (months%12 == 0) {
                addYear(date, months / 12)
            }
            else if (date.month + (months % 12) > 0) {
                addYear(date, months / 12)
                date.month += months % 12
            }
            else {                                          //date.month + months%12 < 0
                addYear(date, months / 12 - 1)
                date.month += months % 12 + 12
            }
        }
    }
    else {
        date.month = monthSum
        return date
    }
    return date
}

fun addYear(date: Date, years: Int): Date {
    val yearSum: Int = date.year + years
    date.year = yearSum
    return date
}

fun addDate(date: Date, years: Int, months: Int, days: Int): Date {
    addDay(date, days)
    addMonth(date, months)
    addYear(date, years)
    return date
}


fun main() {

    val dateA: Date = Date()
    addDate(dateA, 0, 0, -100000)
    print("years: ${dateA.year}\nmonths: ${dateA.month}\ndays:${dateA.day}")

}

