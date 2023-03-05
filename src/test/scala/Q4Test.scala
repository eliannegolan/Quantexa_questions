import org.scalatest.funsuite.AnyFunSuite

class Q4Test extends AnyFunSuite
{

  test("Number of flights together are printed in descending order") {
    val fileContents = scala.io.Source.fromFile("Q4_ans.csv").getLines.toList
    val NumFlightsTog = fileContents.tail.map(_.split(",")(2).toInt)

    // Check that the longest run of the first tuple is greater than the longest runs of the other tuples
    assert(NumFlightsTog.head > NumFlightsTog.tail.max)
  }



}
