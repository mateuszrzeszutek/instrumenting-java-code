plugins {
  java
  alias(libs.plugins.shadow)
}

dependencies {
  implementation(libs.bytebuddy)
}

tasks {
  jar {
    manifest {
      attributes("Premain-Class" to "io.rzeszut.instrumentingjavacode.javaagent.MyJavaagent")
    }
  }
}