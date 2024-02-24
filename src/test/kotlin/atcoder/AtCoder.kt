package atcoder

import io.github.cdimascio.dotenv.dotenv
import org.jsoup.Jsoup

data class Question(
    val input: String,
    val answer: String
)

class AtCoder(private val url: String) {
    private val env = dotenv()
    private val username = env.get("USERNAME")
    private val password = env.get("PASSWORD")

    fun getQuestions(): List<Question> {
        val doc = Jsoup.connect(url).get()
        val inputs = doc.select("h3:containsOwn(入力例) ~ pre").map { it.text() }
        val answers = doc.select("h3:containsOwn(出力例) ~ pre").map { it.text() }
        if (inputs.size != answers.size) throw Exception("入力例と出力例の数が違う")
        return inputs.mapIndexed { index, input ->
            Question(
                input = input,
                answer = answers[index]
            )
        }
    }

    fun getCsrfToken(): String {
        // TODO: 実装する
        return ""
    }

    fun login() {
        // TODO: 実装する
    }

    fun submit(codePath: String) {
        // TODO: 実装する
    }
}
