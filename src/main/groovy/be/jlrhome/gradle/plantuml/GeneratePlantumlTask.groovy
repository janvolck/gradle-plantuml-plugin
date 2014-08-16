package be.jlrhome.gradle.plantuml

import net.sourceforge.plantuml.GeneratedImage
import net.sourceforge.plantuml.Option
import net.sourceforge.plantuml.SourceFileReader
import net.sourceforge.plantuml.preproc.Defines
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GeneratePlantumlTask extends DefaultTask {


    boolean processingSingleFile(final File file, Option option) throws IOException, InterruptedException {

        project.logger.debug("Processing " + file.getAbsolutePath() + " to " + option.getOutputDir())

        final SourceFileReader sourceFileReader = new SourceFileReader(
                new Defines(),
                file,
                option.getOutputDir(),
                option.getConfig(),
                option.getCharset(),
                option.getFileFormatOption());

        if (option.isCheckOnly()) {
            return sourceFileReader.hasError();
        }

        return doFile(file, sourceFileReader, option);
    }

    boolean doFile(final File f, final SourceFileReader sourceFileReader, Option option) throws IOException, InterruptedException {

        project.logger.debug("GenerateImage " + f.getAbsolutePath());
        final Collection<GeneratedImage> result = sourceFileReader.getGeneratedImages();
        boolean error = false;

        for (GeneratedImage g : result) {
            project.logger.debug(g.toString() + " " + g.getDescription());
            if (sourceFileReader.hasError()) {
                error = true;
            }
        }

        if (error) {
            project.logger.error(f.getCanonicalPath());
        }

        if (error && option.isFailfastOrFailfast2()) {
            return true;
        }

        return false;
    }


    @TaskAction
    def generatePlantumlDiagrams() {
        project.logger.debug("Starting PlantUML")

        def Option option = new Option()
        option.setCheckOnly(project.plantuml.checkOnly)
        option.setFileFormat(project.plantuml.fileFormat)
        option.setFailfast(project.plantuml.failFast)
        option.setFailfast2(project.plantuml.failFast2)

        project.plantuml.sources.each { File f ->

            processingSingleFile(f, option);
        }

        project.javadoc {
            options.addStringOption('sourcepath', project.plantuml.sourcePath)
            options.docFilesSubDirs = true
        }

    }
}