plugins {
    kotlin("jvm")
    id("org.jetbrains.intellij.platform.module")
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "242"
        }
    }
}

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        phpstorm("2024.2")
        bundledPlugin("com.jetbrains.php")
    }
}