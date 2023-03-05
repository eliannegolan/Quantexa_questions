import java.io.PrintWriter

object Q3 {

  def LongestRuntoCSV(Output_q3: String): Unit = {

    //date from file that program is reading from
    val data = scala.io.Source.fromFile("flightData.csv").getLines.toList

    //case class models immutable data
    case class FlightData(passengerId: String, from: String, to: String)

    //object of case class, remove header and extract passengerId, from and to columns
    val flightsField = data.tail.map(_.split(",")).map(p => FlightData(p(0), p(2), p(3)))

    //filtering passengers who flew from OR to the uk, removing duplicates
    val ukPass = flightsField.filter(f => f.from == "uk" || f.to == "uk").map(_.passengerId).distinct

    //this is a list of tuples
    val destByPassenger = flightsField
      //filtering for flights of passengers who have been in uk
      .filter(f => ukPass.contains(f.passengerId))
      //groups by passenger id to get each country per passenger
      .groupBy(_.passengerId)
      //foldLeft allows removal of adjacent duplicate destinations, before : uk fr fr be be uk, after : uk fr be uk
      .mapValues(_.flatMap(f => List(f.from, f.to)).foldLeft(List.empty[String]) { (acc, x) =>
        if (acc.isEmpty || acc.last != x) acc :+ x
        else acc
        //converting to a list of tuples of elements passengerid, dest1,dest2,dest3, etc
      }.mkString(",")).toList

    //Will print result to output csv file
    val q3 = new PrintWriter(Output_q3)

    //Printing header of solution
    q3.println("Passenger ID, Longest Run")


    val SortedResults = destByPassenger.flatMap {

      case (id, dest) => {

        //had a tuple now list of strings representing destinations
        val destList = dest.split(",").toList
        //getting indices of strings containing uk within the list
        val ukInd = destList.zipWithIndex.filter(_._1 == "uk").map(_._2)

        val maxDest = ukInd match {
          // handles empty or one-element list
          case Nil | _ :: Nil => Nil
          // for  >=2 element lists, ie lists where uk appears at least twice, getting difference between indices
          // also subtracting one from difference to account for country gap in between and not including uk
          case _ => ukInd.sliding(2).map { case Seq(a, b) => b - a }.map(_ - 1).toList
        }
        //discard empty lists
        if (maxDest.nonEmpty) {
          //getting largest difference to get longest run
          val biggestGap = maxDest.max
          //returns tuple that allows for getting longest run in descending order
          Some((id, biggestGap))
        } else {
          None
        }
      }
      //sorting  second element(biggest gap) of tuples in descending order
    }.sortBy(_._2)(Ordering[Int].reverse)

    SortedResults.foreach {
      case (id, biggestGap) => {
        //printing result
       q3.println(f"$id,$biggestGap")
      }
    }
    q3.close()

  }



}
