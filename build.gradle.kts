plugins {
    kotlin("jvm") version "1.8.20" // AtCoderのjvmは1.8.20
    application
}

group = "ryochansq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0") // assertSoftlyが使いたかったので
    testImplementation("org.jsoup:jsoup:1.17.2") // AtCoderの入出力例取得や提出に使う
    testImplementation("io.github.cdimascio:dotenv-kotlin:6.4.1") // AtCoderの認証情報を環境変数から取得するために使う
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}
