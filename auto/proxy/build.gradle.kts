plugins {
  application
}

dependencies {
  implementation("com.h2database:h2:2.3.232")
}

application {
  mainClass = "io.rzeszut.instrumentingjavacode.proxy.ProxyCodeGen"
}
