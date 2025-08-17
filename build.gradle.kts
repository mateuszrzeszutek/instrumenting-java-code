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
}