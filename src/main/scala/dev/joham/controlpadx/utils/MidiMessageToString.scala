package dev.joham.controlpadx.utils

import javax.sound.midi.MidiMessage

object MidiMessageToString {

  def midiMessageToString(message: MidiMessage): String = {
    val string = s"Status: ${message.getStatus}"
    if (message.getLength > 0) {
      midiMessageToStringRec(string, message, 0)
    } else string
  }

  private def midiMessageToStringRec(
      string: String,
      message: MidiMessage,
      index: Int
  ): String = {
    if (message.getLength < 100 && index < message.getLength)
      midiMessageToStringRec(
        string.concat(s" [${index}]: " + message.getMessage()(index).toString),
        message,
        index + 1
      )
    else string
  }

}
