import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.matrix
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.11"

project {

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        dotnetBuild {
            id = "dotnet"
            projects = ".NetSimpleProject.sln"
            sdk = "6"
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
        matrix {
            param("parameterOne", listOf(
                value("value1"),
                value("value2")
            ))
            param("Symbols256", listOf(
                value("value2"),
                value("Value2")
            ))
            param("Parameter3", listOf(
                value("1"),
                value("2")
            ))
            param("parameter4", listOf(
                value("1"),
                value("2")
            ))
            param("parameter5", listOf(
                value("1"),
                value("2")
            ))
        }
    }
})
