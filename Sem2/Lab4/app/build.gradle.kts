plugins {
    application
    alias(libs.plugins.lombok)
    id("maven-publish") 
}

group = "ru.gitmaxlla.itandp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.spring.framework.context)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "ru.gitmaxlla.itandp.Main"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "ru.gitmaxlla.itandp"
	    artifactId = "gradle-app"
	    version = "1.0-SNAPSHOT"

	    from(components["java"])
	}
    }
}
