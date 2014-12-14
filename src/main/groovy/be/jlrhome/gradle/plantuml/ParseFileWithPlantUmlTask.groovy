//from http://forums.gradle.org/gradle/topics/accessing_the_buildscript_classpath
//and from http://forums.gradle.org/gradle/topics/how_to_get_absolute_path_of_a_file_declared_in_dependencies
//and https://gradle.org/docs/current/userguide/custom_tasks.html
class ParseFileWithPlantUmlTask extends DefaultTask {
  String plantumlJarPath
  @OutputDirectory
  File destDir
  @InputFiles
  List<File> sources

  @TaskAction
  def parseFiles(IncrementalTaskInputs inputs) {
    project.buildscript.configurations.classpath.each {
      String absolutePath = it.getAbsolutePath();
      if (absolutePath.contains("plantuml-")) {
        plantumlJarPath = absolutePath
      }
    }
    if (plantumlJarPath == null) {
      throw new RuntimeException("Could not find plant uml jar.");
    }

    String execPlantUml = 'java -jar ' + plantumlJarPath + ' '
    String execPlantUmlSuffix = ' -o ' + destDir
    inputs.outOfDate {
      String execGeneratePlantUml = execPlantUml + it.file.getAbsolutePath() + execPlantUmlSuffix
      execGeneratePlantUml.execute();
      getLogger().debug('executed ' + execGeneratePlantUml)
    }
  }
}
