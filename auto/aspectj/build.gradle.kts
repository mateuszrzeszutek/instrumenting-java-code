plugins {
  application
}

dependencies {
  implementation(libs.aspectj)
  implementation(libs.spring.aop)
}

application {
  mainClass = "io.rzeszut.instrumentingjavacode.aspectj.AspectJCodeGen"
}
