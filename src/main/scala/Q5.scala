
import java.io.PrintWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
//Bonus attempt
object Q5 {
//inputs of function : minimum number of flights, date range
  def flownTogether( atLeastNtimes: Int, from: LocalDate, to: LocalDate, Output_q5: String): Unit =List[((Int, Int), Int)] {
    //Accessing file
    val fileData = scala.io.Source.fromFile("flightData.csv").getLines.toList
    //Creating a case class because immutable
    case class FlightData(passengerId: Int, flightId: Int, date: LocalDate)
    // object of the case class : tail removes header and field is object with passengerId,flightId and date of flight
    val field = fileData.tail.map(_.split(",")).map(p => FlightData(p(0).toInt, p(1).toInt, LocalDate.parse(p(2))))

    val PassPairs = field
      // filter by date range
      .filter(flight => flight.date.isAfter(from) && flight.date.isBefore(to))
      //grouping by flightId
      .groupBy(_.flightId)
      //return iterable of values of map representing each flightId
      .values
      //
      .flatMap { flights =>
        flights
          //gets passengers from each flight
          .map(_.passengerId)
          //takes all possible combinations of passengers per flights
          .combinations(2)
          .map {
            // ensures no reps of combinations
            case List(pass1, pass2) => if (pass1 < pass2) (pass1, pass2)
            else (pass2, pass1)
          }
        //groups pair of passId
      }.groupBy(identity)
      //counts flights together for each pair of passengers
      .mapValues(_.size)
      //filters desired mimium number of flights together
      .filter { case (_, count) => count >= atLeastNtimes }
      .toList





}




}
