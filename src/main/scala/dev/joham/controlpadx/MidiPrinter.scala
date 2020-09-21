package dev.joham.controlpadx

import com.typesafe.scalalogging.Logger
import de.sciss.midi.Message
import javax.sound.midi.MidiMessage
import dev.joham.controlpadx.utils.MidiMessageToString.midiMessageToString

import scala.util.{Failure, Success, Try}

class MidiPrinter extends javax.sound.midi.Receiver {

  val logger = Logger("dev.joham.controlpadx.MidiPrinter")

  private var open: Boolean = true
  def isOpen = open

  override def send(message: MidiMessage, timeStamp: Long): Unit = {
    if (message.getMessage()(1) == 11) close()
    val convertedMessage: Try[Message] = Try(Message.fromJava(message))
    convertedMessage match {
      case Success(value) => println(value.toString)
      case Failure(exception) =>
        exception.getStackTrace
          .map(stacktraceElement => stacktraceElement.toString)
          .foreach(logger.error(_))
        println(midiMessageToString(message))
    }
    println(midiMessageToString(message))
  }

  override def close(): Unit = {
    open = false
  }
}
