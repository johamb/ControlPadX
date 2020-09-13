import java.util

import javax.sound.midi._

class Launchpad extends MidiDevice {
  override def getDeviceInfo: MidiDevice.Info = ???

  override def open(): Unit = ???

  override def close(): Unit = ???

  override def isOpen: Boolean = ???

  override def getMicrosecondPosition: Long = ???

  override def getMaxReceivers: Int = ???

  override def getMaxTransmitters: Int = ???

  override def getReceiver: Receiver = ???

  override def getReceivers: util.List[Receiver] = ???

  override def getTransmitter: Transmitter = ???

  override def getTransmitters: util.List[Transmitter] = ???
}
