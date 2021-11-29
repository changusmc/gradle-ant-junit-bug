import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest
import org.apache.tools.ant.taskdefs.optional.junit.XMLJUnitResultFormatter
import junit.framework.Test
import junit.framework.TestResult
import java.io.File
import java.io.FileOutputStream

class MyJUnitFormatter(junitTestName: String, outputJunitFile: File) {
    private val formatter = XMLJUnitResultFormatter()
    private val jUnitTest = JUnitTest(junitTestName)
    private val outputStream = FileOutputStream(outputJunitFile)

    init {
        formatter.setOutput(outputStream)
    }

    fun startTestSuite() {
        formatter.startTestSuite(jUnitTest)
    }

    fun endTestSuite() {
        formatter.endTestSuite(jUnitTest)
        outputStream.close()
    }

    fun addBuildFailure(error: String) {
        jUnitTest.setCounts(
            jUnitTest.runCount() + 1,
            jUnitTest.failureCount() + 1,
            jUnitTest.errorCount()
        )
        formatter.addError(BuildFailure(), BuildFailedException(error))
    }
}

class BuildFailure : Test {
    override fun run(testResult: TestResult?) {
    }

    override fun countTestCases(): Int = 1
}


class BuildFailedException(message: String?) : Error(message) {
    // disable stack trace generation, we just want to print the error message
    override fun fillInStackTrace(): Throwable {
        return this
    }
}
