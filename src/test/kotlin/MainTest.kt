import atcoder.AtCoder
import io.TestIO
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class MainTest {
    private val atcoder = AtCoder(Solver(TestIO()).url)

    @Test
    fun main() {
        // 問題取得
        val questions = atcoder.getQuestions()

        // テスト
        println()
        assertSoftly {
            questions.size shouldBeGreaterThan 0
            questions.forEachIndexed { index, question ->
                val testIO = TestIO()
                testIO.setInput(question.input)
                Solver(testIO).solve()
                val expected = question.answer
                val actual = testIO.getOutput()
                val result = if (expected == actual) "✅" else "❌"
                println("Test${index + 1}: $result")
                expected shouldBe actual
            }
        }

        // 提出
        atcoder.submit("src/main/kotlin/Main.kt")
        println("\n提出完了 ✅\n")
    }
}
