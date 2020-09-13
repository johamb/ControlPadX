import javax.sound.midi.MidiMessage
import de.sciss.midi._

class MidiPrinter extends javax.sound.midi.Receiver {
  override def send(message: MidiMessage, timeStamp: Long): Unit = {
    message.getMessage.foreach(println(_))
    println("")
  }

  override def close(): Unit = println("close")
}
