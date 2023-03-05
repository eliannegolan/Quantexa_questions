
import java.io.{File, PrintWriter}

object Q1 {

  def FlightsperMonthtoCSV(Output : String): Unit = {


    //getting information from csv file ie rows
    val fileData = scala.io.Source.fromFile("flightData.csv").getLines.toList
    //creating a case class to make sure flightdata is immmutable
    case class FlightData(flightId: String, date: String)
    //field extracts the flightID and date columns from whole dataset
    val field = fileData.tail.map(_.split(",")).map(c => FlightData(c(1).toString, c(4)))

    //List containing strings representing different months
    val MonthStrings = List("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
    val sortedField = field.sortBy(_.date)

    //this will create an output file
    val q1 = new PrintWriter(Output)
    //printing headers of cvs file
    q1.println("Month, Number of Flights")

    //Iterating over the individual month strings
    MonthStrings.foreach(month => {
      //filtering the dates that contain the given month string in its 5th a 6th element : yyyy-mm-dd
      //extracting flightId for rows that meet the condition
      //distinct removes duplicates leaving only unique flight Ids
      //getting size of flightIds = number of flights
      val flightIds = sortedField.filter(f => f.date.slice(5, 7) == month).map(_.flightId).distinct
      val flightNum =  flightIds.size
      q1.println(s"${month.toInt},$flightNum")
    })

    q1.close()


  }
}
