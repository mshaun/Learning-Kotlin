// MxN matrix (immutable, mutable)
// 加、減、乘

fun copyArray(a: Array<Array<Int>>): Array<Array<Int>> {
    val b = Array(a.size) { i ->
        Array(a[i].size) { j ->
            a[i][j]
        }
    }
    return b
}

class Matrix private constructor( // primary constructor
    val numOfRows: Int = 3, // M
    val numOfCols: Int = 3, // N
    private var array: Array<Array<Int>>
) {
//    constructor() : this(3, 3) { // convenience constructor
//        // other codes
//    }

    constructor(numOfRows: Int, numOfCols: Int):
            this(
                numOfRows,
                numOfCols,
                Array(numOfRows) {
                    Array(numOfCols) { 0 }
                }
            )

    constructor(array: Array<Array<Int>>): this(numOfRows(array), numOfCols(array), copyArray(array))

    constructor(matrix: Matrix): this(matrix.numOfRows, matrix.numOfCols, copyArray(matrix.array))

//    constructor(matrix: Matrix): this(matrix.numOfRows, matrix.numOfCols) {
//        for (i in 0 until numOfRows) {
//            for (j in 0 until numOfCols) {
//                array[i][j] = matrix.array[i][j]
//            }
//        }
//    }

    init {
        require(numOfRows > 0 && numOfCols > 0) { "The numOfRows and numOfCols must be greater than zero" }

    }

    //    private var array: Array<Array<Int>> = arrayOf(arrayOf(4, 5, 6), arrayOf(4, 5, 6), arrayOf(4, 5, 6))
//    private var array: Array<Array<Int>>
//        = Array<Array<Int>>(numOfRows) { n ->
//            Array<Int>(numOfCols) { 0 } // The value that should be placed in nth position.
//

    init {
//        this.array = array
//        array = Array<Array<Int>>(numOfRows) { n ->
//            Array<Int>(numOfCols) { 0 } // The value that should be placed in nth position.
//        }
    }

    private val numOfElements: Int

    init {
        numOfElements = numOfRows * numOfCols
        for (row in 0 until numOfRows) {
            for (col in 0 until numOfCols) {
                array[row][col] = numOfElements
            }
        }
    }

    companion object {
        fun numOfRows(a: Array<Array<Int>>): Int {
            require(a.size > 0)
            return a.size
        }
        private fun numOfCols(array: Array<Array<Int>>): Int {
            val numOfCols = array[0].size
            for (a in array) {
                require(numOfCols == a.size)
            }
            return numOfCols
        }
    }



    fun get(row: Int, col: Int): Int {
        return array[row][col]
    }

    fun set(row: Int, col: Int, value: Int) {
        array[row][col] = value
    }

    private fun copy(): Matrix {
        return Matrix(this)
//        val newMatrix: Matrix = Matrix(this.numOfRows, this.numOfCols)
//        //copy this.array to newMatrix.array
//        for (i in 0 until this.numOfRows) {
//            for (j in 0 until this.numOfCols) {
//                newMatrix.array[i][j] = this.array[i][j]
//            }
//        }
//        return newMatrix
    }

    fun add(matrix: Matrix): Matrix {
        val copied: Matrix = Matrix(this)//        val newMatrix: Matrix = Matrix(this.numOfRows, this.numOfCols)
//        //copy this.array to newMatrix.array
//        for (i in 0 until this.numOfRows) {
//            for (j in 0 until this.numOfCols) {
//                newMatrix.array[i][j] = this.array[i][j]
//            }
//        }
//        return newMatrix
        copied.addBy(matrix)
        return copied
    }

    fun addBy(matrix: Matrix): Matrix { // must be the same M*N
        require(this.numOfRows == matrix.numOfRows && this.numOfCols == matrix.numOfCols)
        { "the each matrix's rows and rows has to be equal, cols and cols has too be equal too" }
        for (row in 0 until numOfRows) {
            for (col in 0 until numOfCols) {
                this.array[row][col] = this.array[row][col] + matrix.array[row][col]
            }
        }
        return this
    }

    fun sub(matrix: Matrix): Matrix {
        val copied: Matrix = Matrix(this)//        val newMatrix: Matrix = Matrix(this.numOfRows, this.numOfCols)
//        //copy this.array to newMatrix.array
//        for (i in 0 until this.numOfRows) {
//            for (j in 0 until this.numOfCols) {
//                newMatrix.array[i][j] = this.array[i][j]
//            }
//        }
//        return newMatrix
        copied.subBy(matrix)
        return copied
    }

    fun subBy(matrix: Matrix): Matrix {
        require(this.numOfRows == matrix.numOfRows && this.numOfCols == matrix.numOfCols)
        { "the each matrix's rows and rows has to be equal, cols and cols has too be equal too" }
        for (row in 0 until numOfRows) {
            for (col in 0 until numOfCols) {
                this.array[row][col] = this.array[row][col] - matrix.array[row][col]
            }
        }
        return this
    }

    fun mul(matrix: Matrix): Matrix {
        return Matrix(numOfRows, matrix.numOfCols, _mul(matrix))

        val copied: Matrix = Matrix(this)//        val newMatrix: Matrix = Matrix(this.numOfRows, this.numOfCols)
//        //copy this.array to newMatrix.array
//        for (i in 0 until this.numOfRows) {
//            for (j in 0 until this.numOfCols) {
//                newMatrix.array[i][j] = this.array[i][j]
//            }
//        }
//        return newMatrix
        copied.mulBy(matrix)
        return copied
    }

    // MxN
    // NxK
    // -> MxK
    fun mulBy(matrix: Matrix): Matrix {
        require(this.numOfCols == matrix.numOfRows)
        { "It only make sense for first matrix's columns to be equal to second matrix's rows" }
        var temp1: Int
        val forCount: Array<Array<Int>> = Array<Array<Int>>(this.numOfRows) { n -> Array<Int>(matrix.numOfCols) { 0 } }
        for (row in 0 until this.numOfRows) {
            for (col in 0 until matrix.numOfCols) {
                var temp2: Int = 0
                for (counter in 0 until this.numOfCols) {
                    temp1 = this.array[row][counter] * matrix.array[counter][col]
                    temp2 += temp1
                }
                forCount[row][col] = temp2
            }
        }
        this.array = forCount
        return this
    }

    // MxN
    // NxK
    // -> MxK
    fun _mul(matrix: Matrix): Array<Array<Int>> {
        val array = Array<Array<Int>>(numOfRows) {
            Array(matrix.numOfCols) { 0 }
        }

        // ...

        return array
    }

    fun printMatrix(): Unit {
        for (row in 0 until this.numOfRows) {
            for (col in 0 until this.numOfCols) {
                print(String.format("%3d ", this.array[row][col]))
            }
            println()
        }
    }
}


fun main() {
    val a: Matrix = Matrix(2, 3)
    val b: Matrix = Matrix(3, 4)
    a.mulBy(b)
    a.printMatrix()

//    val array = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
//    val c = Matrix(3, 3, array)
////    c.set(0, 0, 9)
//
//    array[0][0] = 9
//    c.get(0, 0) // 9
}
