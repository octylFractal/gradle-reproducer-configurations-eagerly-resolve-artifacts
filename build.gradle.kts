import java.util.concurrent.atomic.*

plugins {
    java
}

repositories {
    mavenCentral()
}

configurations.register("unrelated")

dependencies {
    "unrelated"("com.google.guava:guava:31.1-jre")
}

val jarRealized = AtomicBoolean()

tasks.named("jar") {
    jarRealized.set(true)
}

tasks.register("verifyNotRealized") {
    configurations.getByName("unrelated").resolve()
    if (jarRealized.get()) {
        error("jar should not be realized!")
    } else {
        println("jar was not realized :yay:")
    }
}
