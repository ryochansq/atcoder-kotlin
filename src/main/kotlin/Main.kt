import java.io.PrintWriter

fun myBinarySearch(begin: Int, end: Int, isOk: (key: Int) -> Boolean): Int {
    var (ng, ok) = Pair(begin, end)
    while (Math.abs(ok - ng) > 1) {
        val mid = (ok + ng) / 2
        if (isOk(mid)) ok = mid
        else ng = mid
    }
    return ok
}

fun <T> List<T>.myBinarySearch(isOk: (T) -> Boolean) = myBinarySearch(-1, size) { isOk(get(it)) }
fun <T> Array<T>.myBinarySearch(isOk: (T) -> Boolean) = myBinarySearch(-1, size) { isOk(get(it)) }
val dir = listOf(-1, 0, 1, 0, -1, -1, 1, 1, -1)
fun <T> genList(n: Int, value: T) = MutableList(n) { value }
fun <T> genList2d(m: Int, n: Int, value: T) = MutableList(m) { genList(n, value) }
fun <T> genList3d(l: Int, m: Int, n: Int, value: T) = MutableList(l) { genList2d(m, n, value) }
data class P(val i: Int, val j: Int)

interface IOHandler {
    fun readLine(): String
    fun print(out: Any)
    fun println(out: Any)
}

class Stdio : IOHandler {
    private val printWriter = PrintWriter(System.out)
    override fun readLine() = readln()
    override fun print(out: Any) = printWriter.print(out)
    override fun println(out: Any) = printWriter.println(out)
    fun flush() = printWriter.flush()
}

fun main() {
    val stdio = Stdio()
    Solver(stdio).solve()
    stdio.flush()
}

class Solver(private val io: IOHandler) {
    private fun rStr() = io.readLine().trim()
    private fun rInt() = rStr().toInt()
    private fun rLong() = rStr().toLong()
    private fun rDouble() = rStr().toDouble()
    private fun rStrs() = rStr().split(" ").toMutableList()
    private fun rInts() = rStrs().map { it.toInt() }.toMutableList()
    private fun rLongs() = rStrs().map { it.toLong() }.toMutableList()
    private fun rDoubles() = rStrs().map { it.toDouble() }.toMutableList()
    private fun pr(out: Any) = io.print(out)
    private fun prln(out: Any) = io.println(out)

    fun solve() {
        val a = rInt()
        val (b, c) = rInts()
        val s = rStr()
        prln("${a + b + c} $s")
    }
}
