import io.rzeszut.instrumentingjavacode.compiletime.MyCompileTimePlugin

plugins {
  java
  id("net.bytebuddy.byte-buddy-gradle-plugin")
}

byteBuddy {
  transformation {
    plugin = MyCompileTimePlugin::class.java
  }
}