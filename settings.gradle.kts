rootProject.name = "mongock-test-kotlin"

pluginManagement {
    val springbootVersion = "3.0.2"
    val springDependencyManagementVersion = "1.1.0"
    val kotlinVersion = "1.7.22"

    plugins {
        id("org.springframework.boot") version springbootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
    }
}