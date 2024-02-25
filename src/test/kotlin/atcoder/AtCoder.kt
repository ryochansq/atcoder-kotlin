package atcoder

import io.github.cdimascio.dotenv.dotenv
import org.jsoup.Jsoup
import java.io.File
import java.net.URLDecoder.decode

data class Question(
    val input: String,
    val answer: String
)

class AtCoder(private val url: String) {
    private val env = dotenv()
    private val session = Jsoup.newSession()

    fun getQuestions(): List<Question> {
        retry(::login) ?: throw Exception("ログイン失敗")
        val doc = session.url(url).get()
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

    fun submit(codePath: String) {
        retry(::login) ?: throw Exception("ログイン失敗")
        val contestUrl = Regex("^.*(?=/tasks)").find(url)?.value ?: throw Exception("contestUrl取得失敗")
        val taskScreenName = Regex("(?<=tasks/).*$").find(url)?.value ?: throw Exception("taskScreenName取得失敗")
        val submitUrl = "$contestUrl/submit"
        val kotlinId = "5004"
        val sourceCode = File(codePath).readText()

        retry {
            val csrfToken = getCsrfToken(submitUrl)
            session
                .url(submitUrl)
                .data(
                    mapOf(
                        "data.LanguageId" to kotlinId,
                        "data.TaskScreenName" to taskScreenName,
                        "csrf_token" to csrfToken,
                        "sourceCode" to sourceCode
                    )
                )
                .followRedirects(true)
                .post()
        } ?: throw Exception("提出失敗")
    }

    private fun getCsrfToken(url: String): String {
        session.url(url).get()
        val token = Regex("(?<=csrf_token%3A).*(?=%00)")
            .find(session.cookieStore().cookies.toString())
            ?: throw Exception("csrfトークンがmatchしない")
        return decode(token.value)
    }

    private fun login() {
        val loginUrl = "https://atcoder.jp/login"
        val csrfToken = getCsrfToken(loginUrl)
        val username = env.get("USERNAME")
        val password = env.get("PASSWORD")
        session
            .url(loginUrl)
            .header("content-type", "application/x-www-form-urlencoded")
            .data(
                mapOf(
                    "csrf_token" to csrfToken,
                    "username" to username,
                    "password" to password
                )
            )
            .followRedirects(true)
            .post()
    }

    private fun retry(func: () -> Any): Boolean? {
        var count = 7
        while (count > 0) {
            try {
                func()
                return true
            } catch (_: Exception) {
                count--
                Thread.sleep(500)
            }
        }
        return null
    }
}
