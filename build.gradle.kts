plugins {
  java
}

subprojects {
  apply<JavaPlugin>()

  repositories {
    mavenCentral()
  }

  java {
    toolchain {
      languageVersion = JavaLanguageVersion.of(24)
    }
  }

  dependencies {
    val libs = rootProject.libs
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}