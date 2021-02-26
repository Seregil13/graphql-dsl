import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm")
    id("org.jmailen.kotlinter")
    `maven-publish`
}

group = "com.seregil13"
version = "1.0.0-alpha01"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testCompile("junit", "junit", "4.12")
}

publishing {
    publications {
        create<MavenPublication>("default") {
            groupId = "com.seregil13"
            artifactId = "graphql-dsl"
            version = "1.0.0-alpha01"
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/seregil13/graphql-dsl")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
