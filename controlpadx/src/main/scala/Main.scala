import de.sciss.synth._
import ugen._
import Ops._

object Main extends App {
  val cfg = Server.Config()
  cfg.program = "/usr/bin/scsynth"
  // runs a server and executes the function
  // when the server is booted, with the
  // server as its argument 
  Server.run(cfg) { s =>
    s.dumpOSC()
    // play is imported from package de.sciss.synth.
    // it provides a convenience method for wrapping
    // a synth graph function in an `Out` element
    // and playing it back.
    play {
      val f = LFSaw.kr(0.4).mulAdd(24, LFSaw.kr(Seq(8, 7.23)).mulAdd(3, 80)).midiCps
      CombN.ar(SinOsc.ar(f) * 0.04, 0.2, 0.2, 4)
    }
  }
}

