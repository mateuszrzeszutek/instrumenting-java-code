plugins {
  application
}

dependencies {
  implementation(libs.h2)
}

application {
  mainClass = "io.rzeszut.instrumentingjavacode.proxy.ProxyCodeGen"
}
