import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun
import com.bmuschko.gradle.docker.tasks.container.*
import com.bmuschko.gradle.docker.tasks.image.*

plugins {
	id("org.springframework.boot") version "2.2.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("com.bmuschko.docker-remote-api") version "6.1.2"
	id("com.bmuschko.docker-java-application") version "6.1.2"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.register<BootRun>("startService") {
	main = "com.example.softwareengineeringlab.SoftwareEngineeringLabApplicationKt"
	classpath = sourceSets["main"].runtimeClasspath
}

val createContainer by tasks.creating(DockerCreateContainer::class) {
	val buildImage = tasks.getByName("dockerBuildImage") as DockerBuildImage
	dependsOn(buildImage)
	targetImageId { buildImage.imageId.get() }
	hostConfig.portBindings.set(listOf("80:8080"))
}

val startContainer by tasks.creating(DockerStartContainer::class) {
	dependsOn(createContainer)
	targetContainerId { createContainer.containerId.get() }
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}