import javax.sound.midi.MidiMessage
import de.sciss.midi.Message
import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success, Try}

class MidiPrinter extends javax.sound.midi.Receiver with LazyLogging {

  var isOpen: Boolean = true

  override def send(message: MidiMessage, timeStamp: Long): Unit = {
    if (message.getMessage()(1) == 11) close()
    val convertedMessage: Try[Message] = Try(Message.fromJava(message))
    convertedMessage match {
      case Success(value)     => println(value.toString)
      case Failure(exception) => logger.error(exception.getMessage)
    }
    println("")
  }

  override def close(): Unit = {
    isOpen = false
    println("close")
  }
}
