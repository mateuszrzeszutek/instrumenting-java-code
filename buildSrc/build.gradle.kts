plugins {
  java
  `kotlin-dsl`

  alias(libs.plugins.bytebuddy)
}

repositories {
  gradlePluginPortal()
}

dependencies {
  implementation(gradleApi())
  implementation(libs.bytebuddy)
  implementation(plugin(libs.plugins.bytebuddy))
}

fun plugin(plugin: Provider<PluginDependency>) =
  plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }