
import org.scalatest._
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import Q1.FlightsperMonthtoCSV
import org.scalatest.funsuite.AnyFunSuite

import scala.Console.in

import org.scalatest.funsuite.AnyFunSuite


class Q1Test extends AnyFunSuite with Matchers {
  test("Q1.FlightsperMonthtoCSV") {
    Q1.FlightsperMonthtoCSV("Q1_ans.csv")
    val output = scala.io.Source.fromFile("Q1_ans.csv").mkString
    output shouldBe
      """Month, Number of Flights
        |1,97
        |2,73
        |3,82
        |4,92
        |5,92
        |6,71
        |7,87
        |8,76
        |9,85
        |10,76
        |11,75
        |12,94
        |""".stripMargin //counted this manually by calculating number of flightIds per month
  }


    test("Q1.monthlyFlights") {
      val output = scala.io.Source.fromFile("Q1_ans.csv").getLines.toList
      val flightCounts = output.tail.map(_.split(",")(1).toInt) //second element of csv = flight/month
      assert(flightCounts.sum == 1000)//sum of monthly flights = 1000
    }

}









