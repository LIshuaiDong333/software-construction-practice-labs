# Software Construction Practice Labs

Android and Java exercises for a software construction practice course.

- `lab2/SurfaceViewDemo`: SurfaceView example.
- `lab5/HandlerDemo`: Handler example.
- `lab5/SQLiteDemo`: SQLite read/write example.
- `lab6/HTTPDemo`: HTTP client, Android server project, and Java server module.
- `lab6/SocketDemo`: socket client, Android server project, and Java server module.

The local directories for experiments 1, 3, and 4 contained reports only, so no source files from those experiments are published. Each example retains its own Gradle Kotlin build files, version catalog, Android manifest, Java sources, tests, and UI resources.

Open the selected project in a compatible Android Studio installation. Original Gradle and Android plugin versions are preserved. Local SDK paths, Gradle caches, APKs, compiled classes, and bundled JAR files (including Gradle wrapper JARs) are excluded. Restore the Gradle wrapper with a compatible local Gradle installation before using `gradlew`, or configure the IDE to use local Gradle. Network client examples require the corresponding server and environment configuration.

## Publication scope

This repository is a reviewed export of local coursework. Word/PDF reports, archive files, personal records, credentials, local IDE settings, generated binaries, and original Git history are excluded. Original source bytes and asset files are retained except for the documented database privacy substitutions. The root `.gitignore` uses an explicit upload allowlist; review new files before adding them.
