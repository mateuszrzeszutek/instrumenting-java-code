plugins {
  application
}

dependencies {
  implementation("org.aspectj:aspectjweaver:1.9.24")
  implementation("org.springframework:spring-aop:6.2.10")
}

application {
  mainClass = "io.rzeszut.instrumentingjavacode.aspectj.AspectJCodeGen"
}
