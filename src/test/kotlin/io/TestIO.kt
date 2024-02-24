package io

import IOHandler

class TestIO : IOHandler {
    private var input: MutableList<String> = mutableListOf()
    private var cursor = 0
    private var output = ""
    fun setInput(value: String) = input.addAll(value.split("\n"))
    fun getOutput() = output.trim()
    override fun readLine() = input[cursor++]
    override fun print(out: Any) {
        output += out
    }

    override fun println(out: Any) {
        print(out)
        output += "\n"
    }
}
