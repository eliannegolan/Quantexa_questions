import java.io.PrintWriter

object Q2 {

  def FreqFlyerstoCSV(Output_q2 : String): Unit = {



    //Getting Data from csv file
    val Fdata = scala.io.Source.fromFile("flightData.csv").getLines.toList

    //Getting data from passenger csv file
    val Pdata = scala.io.Source.fromFile("passengers.csv").getLines.toList


    //Case class to model immutable data in functional style
    case class PassData(passengerId: String, firstName: String, lastName: String)

    //object of the case class, extracts first, second and third col : passId,first name, last name
    val field = Pdata.tail.map(_.split(",")).map(p => PassData(p(0), p(1), p(2)))

    //Basically extracting passengerID column from flight csv file & removing header
    val passIds = Fdata.tail.map(_.split(",")(0))

    //Count repetitions of passengerIDs
    //groupBy(identity) takes each flight ID and gets its reps
    // mapValues_.size) counts the reps
    //sortBy argument represents the count which is what is being sorted
    val reps = passIds.groupBy(identity).mapValues(_.size).toList.sortBy(-_._2)

    //getting 100 top Frequent Flyers and each count = number of flights
    val topfreqFlyers = reps.take(100)
    //Writing output to csv file
    val q2 = new PrintWriter(Output_q2)

    //printing headers of solution
    q2.println("Passenger ID,Number of Flights,First Name,Last Name")


    //For each top 100 frequent flyer
    topfreqFlyers.foreach {

      case (passId, count) =>
        //within passenger csv file find passId of frequent flyers -> becomes an object of case class
        val passenger = field.find(_.passengerId == passId)
        // extracting first and last name of passenger, getOrElse will return unknown if not a passenger
        val firstName = passenger.map(_.firstName).getOrElse("Unknown")
        //same process for lastname
        val lastName = passenger.map(_.lastName).getOrElse("Unknown")

        //print formatted solution
        q2.println(s"$passId,$count,$firstName,$lastName")

    }
    q2.close()



  }






}
