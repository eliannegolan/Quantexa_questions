import org.scalatest.funsuite.AnyFunSuite

class Q2Test extends AnyFunSuite {

  test("Q2.FreqFlyerstoCSV") {
    Q2.FreqFlyerstoCSV("Q2_ans.csv")


    val output = scala.io.Source.fromFile("Q2_ans.csv").mkString
    assert(output.contains("Passenger ID,Number of Flights,First Name,Last Name"))//check headers print correctly
  }

  test("NumberFreqFlyers") {
    Q2.FreqFlyerstoCSV("Q2_ans.csv")
    val numRows = scala.io.Source.fromFile("Q2_ans.csv").getLines().count(_ => true)
    assert(numRows == 101) //100 frequent flyers + header

  }
}
