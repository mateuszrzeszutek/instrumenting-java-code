plugins {
  application
}

dependencies {
  implementation("net.bytebuddy:byte-buddy:1.17.6")

  testImplementation(libs.junit.jupiter)
  testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
  useJUnitPlatform()
}
