import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm")
    id("org.jmailen.kotlinter")
}

group = "com.seregil13"
version = "1.0.0-alpha01"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testCompile("junit", "junit", "4.12")
//    implementation("com.ironsource.aura.dslint:dslint-annotations:1.0.3")
}

//tasks.withType<KotlinCompile>() {
//    kotlinOptions.apiVersion = "1.5"
//    kotlinOptions.languageVersion = "1.5"
//}
