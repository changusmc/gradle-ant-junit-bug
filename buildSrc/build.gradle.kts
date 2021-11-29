plugins {
    kotlin("jvm") version "1.5.30"
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.ant:ant-junit:1.10.12")
    implementation("junit:junit:4.12")
}
