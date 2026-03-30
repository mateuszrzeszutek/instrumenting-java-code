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
      languageVersion = JavaLanguageVersion.of(25)
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