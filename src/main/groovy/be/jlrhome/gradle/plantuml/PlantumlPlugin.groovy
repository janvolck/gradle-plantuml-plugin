package be.jlrhome.gradle.plantuml

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.SourceDirectorySet
import net.sourceforge.plantuml.FileFormat;

class PlantumlPlugin implements Plugin<Project> {

    void apply(Project project) {

        project.extensions.create("plantuml", PlantumlPluginExtension, project)
        project.task('plantuml', type: GeneratePlantumlTask).dependsOn(project.compileJava)
        project.javadoc.dependsOn 'plantuml'
    }
}

class PlantumlPluginExtension {

    def String sourcePath
    def SourceDirectorySet sources
    def FileFormat fileFormat
    def boolean verbose
    def boolean failFast
    def boolean failFast2
    def boolean checkOnly
    def boolean overwrite

    PlantumlPluginExtension(Project project) {
        sources = project.sourceSets.main.java
        sourcePath = 'src/main/java'

        fileFormat = FileFormat.PNG
        verbose = false
        failFast = false
        failFast2 = false
        checkOnly = false
        overwrite = true
    }
}