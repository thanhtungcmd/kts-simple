
plugins {
	kotlin("jvm") version "1.6.21"
}

group = "com.tungbt"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":app"))
	implementation(project(":util"))
}
