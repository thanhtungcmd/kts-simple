import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.6.21"
    id("maven-publish")
}

group = "com.tungbt"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/thanhtungcmd/kts-simple")
            credentials {
                username = "thanhtungcmd"
                password = "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"
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    /** Audit */
    implementation("org.springframework.boot:spring-boot-starter-aop")
    /** Security */
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    /** PDF */
    implementation("org.apache.poi:poi:3.10-FINAL")
    implementation("fr.opensagres.xdocreport:org.apache.poi.xwpf.converter.core:1.0.6")
    implementation("fr.opensagres.xdocreport:org.apache.poi.xwpf.converter.pdf:1.0.6")
    implementation("fr.opensagres.xdocreport:fr.opensagres.xdocreport.itext.extension:2.0.0")
    implementation("itext:itext:1.3.1")
    implementation("org.apache.poi:poi-ooxml-schemas:3.10-FINAL")
    /** REST */
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.google.code.gson:gson:2.9.1")
    // other
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation(kotlin("stdlib-jdk8"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}