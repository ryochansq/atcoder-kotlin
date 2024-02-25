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
        assertSoftly {
            questions.size shouldBeGreaterThan 0
            questions.forEachIndexed { index, question ->
                val testIO = TestIO()
                testIO.setInput(question.input)
                Solver(testIO).solve()
                val actual = testIO.getOutput()
                val expected = question.answer
                val result = if (actual == expected) "✅" else "❌"
                println("Test${index + 1}: $result")
                actual shouldBe expected
            }
        }

        // 提出
        atcoder.submit("src/main/kotlin/Main.kt")
        println("\n提出完了 ✅\n")
    }
}
