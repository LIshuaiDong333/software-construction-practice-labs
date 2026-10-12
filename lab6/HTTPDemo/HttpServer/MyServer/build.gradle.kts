plugins {
    id("java-library")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.example.myserver.MyClass"
    }
}