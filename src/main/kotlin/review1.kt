import java.nio.channels.InterruptedByTimeoutException

fun repeatPrint(c: Char, times: Int) {
    for (i in 1 .. times) {
        print(c)
    }
}
fun printStar(times: Int) {
    repeatPrint('*', times)
}
fun printSpace(times: Int){
    repeatPrint(' ',times)
}

const val star = '*'

//One 1 ---for version bad readability
fun triangleOne1(baseSide: Int) {
    for (counter1 in 1..baseSide) {
        for (counter2 in 1..counter1) {
            print(star)
        }
        println()
    }
}

//One 2 ---for version good readability
fun triangleOne2(baseSide: Int) {
    for (lineNo in 1 .. baseSide) {
        val starTimes: Int = lineNo
        printStar(starTimes)
        println()
    }
}

//one 3 ---while version
fun triangleOne3(baseSide: Int) {
    var lineNo: Int = 0
    var starTimes: Int = 0
    while (lineNo < baseSide) {
        starTimes = 0          //reset starTimes to 0
        while (starTimes < lineNo) {
            print(star)
            starTimes++

        }
        println(star)
        lineNo++
    }
}

//Two 1 ---for version bad readability
fun triangleTwo1(baseSide: Int) {
    for (counter1 in baseSide downTo 1) {
        for (counter2 in counter1 downTo 1) {
            print(star)
        }
        println()
    }
}

//Two 2 ---for version good readability
fun triangleTwo2(baseSide: Int) {
    for (lineNo in 1 .. baseSide) {
        val times = baseSide - lineNo + 1
        printStar(times)
        println()
    }
}

//Two 3 ---while version
fun triangleTwo3(baseSide: Int) {
    var lineNo: Int = 0
    var starTimes: Int = 0
    while(lineNo<baseSide) {
        starTimes = baseSide-1
        while(starTimes>lineNo) {
            print(star)
            starTimes--
        }
        println(star)
        lineNo++
    }
}

//Three 1
fun multiplicationTable1() {
    for (multiplier in 1..9) {
        for (multiplicand in 1..9) {
            val product = String.format("%2d", multiplicand * multiplier)
//            print("$multiplicand * $multiplier = $product ")
            print("$product ")
        }
        println()
    }
}

//Three 2 ---You can see the multiplicationTable that which number you type
fun multiplicationTable2() {
    println("Type a number:")
    val multiplicand = Integer.valueOf(readlnOrNull())
    for (multiplier in 1..9) {
        println("$multiplicand * $multiplier = ${multiplicand * multiplier}")
    }
}

//Four 1 bad readability
fun triangleFour1(baseSide: Int) {
    val space: Char = ' '
    for (counter1 in 1..baseSide) {
        for (spaceTimes in baseSide - 1 downTo counter1) {
            print(space)
        }
        for (starTimes in 1..counter1) {
            print(star)
        }
        println()
    }
}
//Four 2 good readability
fun triangleFour2(baseSide: Int) {
    for (lineNo in 1..baseSide) {
        val spaceTimes: Int = baseSide-lineNo
        val starTimes: Int =lineNo
        printSpace(spaceTimes)
        printStar(starTimes)
        println()
    }
}
//Five 1 base on baseSide ---bad readability
fun isoscelesTriangle1(baseSide: Int) {
    val space: Char = ' '
    for (counter1 in baseSide downTo 1 step 2) {
        for (circle2 in counter1 / 2 downTo 1) {
            print(space)
        }
        for (circle3 in baseSide - counter1 + 1 downTo 1) {
            print(star)
        }
        println()
    }
}
//five 2 base on baseSide ---good readability
fun isoscelesTriangle2(baseSide: Int) {
    for (lineNo in 1..baseSide/2+1){
        val spaceTimes: Int = baseSide/2+1 - lineNo
        val starTimes: Int = (lineNo+lineNo)-1
        printSpace(spaceTimes)
        printStar(starTimes)
        println()
    }
}
//Five 3 base on height
fun isoscelesTriangle3(height: Int) {
    for (lineNo in 1..height) {
        val spaceTimes: Int = height - lineNo
        val starTimes: Int = (lineNo + lineNo) - 1
        printSpace(spaceTimes)
        printStar(starTimes)
        println()
    }
}
//Matrix
fun matrixAddition(a: Array<Array<Int>>, b: Array<Array<Int>>): Array<Array<Int>> {

    val sum: Array<Array<Int>> = arrayOf(arrayOf(0, 0), arrayOf(0, 0))
    for (row in 0..1) {
        for(col in 0..1) {
            sum[row][col] = a[row][col] + b[row][col]
        }
    }
    return sum
}

fun matrixMultiplication(a: Array<Array<Int>>, b: Array<Array<Int>>): Array<Array<Int>> {

    val product: Array<Array<Int>> = arrayOf(arrayOf(0, 0), arrayOf(0, 0))
    for (row in 0..1) {
        for (col in 0..1) {
            for (counter in 0..1) {
                product[row][col] = product[row][col] + a[row][counter] * b[counter][col]
            }
        }
    }
    return product
}
fun print2x2Matrix(a: Array<Array<Int>>) {
    for(row in 0..1) {
        for (col in 0..1) {
            print("${a[row][col]} ")
        }
        println()
    }
}

//Six
fun arrayPlus(){
    val arrA: Array<Array<Int>> = arrayOf(arrayOf(1, 2), arrayOf(3, 4))
    val arrB: Array<Array<Int>>  = arrayOf(arrayOf(5, 6), arrayOf(7, 8))

    for (row in 0..1) {
        for (col in 0..1){
            print(String.format("%2d ",arrA[row][col] + arrB[row][col]))
        }
        println()
    }
}
//Seven
fun arrayMultiple(){
    val arrA: Array<Array<Int>>  = arrayOf(arrayOf(1, 2),
                                           arrayOf(3, 4))
    val arrB: Array<Array<Int>>  = arrayOf(arrayOf(5, 6),
                                           arrayOf(7, 8))
    val product: Array<Array<Int>>  = arrayOf(arrayOf(0, 0),
                                              arrayOf(0, 0))
    for (row in 0..1) {
        for (col in 0..1) {
            for (counter in 0..1) {
                product[row][col] = product[row][col] + arrA[row][counter] * arrB[counter][col]
            }
            print("${product[row][col]} ")
        }
        println()
    }
}
fun main() {
    val product: Array<Array<Int>> = matrixMultiplication(arrayOf(arrayOf(1, 2), arrayOf(3, 4)),arrayOf(arrayOf(5, 6), arrayOf(7, 8)))
    val sum: Array<Array<Int>> = matrixAddition(arrayOf(arrayOf(1, 2), arrayOf(3, 4)),arrayOf(arrayOf(5, 6), arrayOf(7, 8)))
    print2x2Matrix(product)
}