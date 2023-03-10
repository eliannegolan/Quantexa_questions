import java.io.PrintWriter

object Q4
{
  def CommonFlightstoCSV(Output_q4: String): Unit = {
  //Accessing file
  val fileData = scala.io.Source.fromFile("../../../flightData.csv").getLines.toList

  //Creating a case class because immutable for functional style
  case class FlightData(passengerId: Int, flightId: Int)

  // object of the case class : tail removes header and maps
  val field = fileData.tail.map(_.split(",")).map(p => FlightData(p(0).toInt, p(1).toInt))

  val PassPairs = field
    //grouping by flightId returns (flight Id, List(passengers in flight)
    .groupBy(_.flightId)
    //return iterable of values of map
    .values

    .flatMap { flights =>
      flights
        //gets passengers from each flight
        .map(_.passengerId)
        //takes all possible combinations of passengers per flights
        .combinations(2)
        .map {
        //condition so that first id in tuple < second id ensures combinations not repeated
          case List(pass1, pass2) => if (pass1 < pass2) (pass1, pass2)
          else (pass2, pass1)
        }
      //groups pairs of passenger Ids
    }.groupBy(identity)
    // map to get pairs of passenger ids and number of flights together
    .mapValues(_.size)
    //interested in more than 3 flights together
    .filter { case (_, count) => count > 3 }
    .toList
    //sorts list in descending order
    .sortWith { case ((_, count1), (_, count2)) => count1 > count2 }

    //Will write output to csv file
    val q4 = new PrintWriter(Output_q4)
    q4.println("Passenger 1 ID, Passenger 2 ID, Number of flights together")

  PassPairs.foreach { case ((passenger1, passenger2), count) =>
   q4.println(s"$passenger1,$passenger2,$count")
  }
    q4.close()
}


}
