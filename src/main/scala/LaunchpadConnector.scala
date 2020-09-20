import javax.sound.midi._
import de.sciss.midi.{Message, NoteOn}

object LaunchpadConnector {

  val launchpadInDevice = MidiSystem.getMidiDevice(
    MidiSystem.getMidiDeviceInfo.find(_.getName.contains("MIDIIN2")).get
  )

  val launchpadOutDevice = MidiSystem.getMidiDevice(
    MidiSystem.getMidiDeviceInfo.find(_.getName.contains("MIDIOUT2")).get
  )

  val transmitter = launchpadInDevice.getTransmitter
  val receiver = launchpadOutDevice.getReceiver

  def init = {
    transmitter.setReceiver(receiver)
    launchpadInDevice.open()
    launchpadOutDevice.open()
  }

  def printMessages = {
    val midiPrinter = new MidiPrinter
    transmitter.setReceiver(midiPrinter)
    val message = NoteOn(0, 11, 5)
    println(message.toString())
    receiver.send(message.toJava, -1)
  }

  def receiveMessages = {
    val synth = MidiSystem.getSynthesizer
    val synthReceiver = synth.getReceiver
    val transmitter = launchpadInDevice.getTransmitter
    transmitter.setReceiver(synthReceiver)
  }

  def printAvailableDevices() = {
    val deviceInfos: Array[MidiDevice.Info] = MidiSystem.getMidiDeviceInfo
    val deviceNames: Array[String] = deviceInfos.map(d => d.getName)
    deviceNames.foreach(println(_))
  }

  def printLaunchpadDeviceInfos() = {
    val deviceInfos: Array[MidiDevice.Info] = MidiSystem.getMidiDeviceInfo
    deviceInfos
      .filter(_.getName.contains("LPX"))
      .foreach(info =>
        println(
          "Name: " + info.getName + " Desc: " + info.getDescription + " Vendor: " + info.getVendor + " Version: " + info.getVersion
        )
      )
  }

}
