import org.scalatest.funsuite.AnyFunSuite

class Q3Test extends AnyFunSuite{

  test("Q3.LongestRuntoCSV") {
    Q3.LongestRuntoCSV("Q3_ans.csv")

    val output = scala.io.Source.fromFile("Q3_ans.csv").mkString
    assert(output.contains("Passenger ID, Longest Run")) //check headers print correctly
  }

  test("Longest runs are printed in descending order") {
    val fileContents = scala.io.Source.fromFile("Q3_ans.csv").getLines.toList
    val longestRuns = fileContents.tail.map(_.split(",")(1).toInt)

    // Check that the longest run of the first tuple is greater than the longest runs of the other tuples
    assert(longestRuns.head > longestRuns.tail.max)
  }
}
