package com.company.bfs.mvp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GeneratorUtil {

	private static TemplateEngine staticTemplateEngine;

	@Autowired
	private TemplateEngine templateEngine;

	@PostConstruct
	public void init() {
		staticTemplateEngine = templateEngine;
	}

	public static File generateDockerFile(final String prjBasePath, final String templateName,
			final Map<String, String> contentMap) {
		final Locale locale = Locale.getDefault();
		final Context ctx = new Context(locale);
		for (Entry<String, String> entry : contentMap.entrySet()) {
			ctx.setVariable(entry.getKey(), entry.getValue());
		}
		final String dockerfileContent = staticTemplateEngine.process("docker/" + templateName, ctx);
		File dockerFile = new File(prjBasePath + File.separatorChar + "Dockerfile");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(dockerFile))) {
			writer.write(dockerfileContent);
		} catch (IOException ex) {
			log.error("Error : ", ex);
		}
		return dockerFile;
	}

	public static File generatePipelineFile(final String prjBasePath, final Map<String, String> contentMap) {
		final Locale locale = Locale.getDefault();
		final Context ctx = new Context(locale);
		for (Entry<String, String> entry : contentMap.entrySet()) {
			ctx.setVariable(entry.getKey(), entry.getValue());
		}
		final String dockerfileContent = staticTemplateEngine.process("cicd/pipeline", ctx);
		File dockerFile = new File(prjBasePath + File.separatorChar + "pipeline.yml");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(dockerFile))) {
			writer.write(dockerfileContent);
		} catch (IOException ex) {
			log.error("Error : ", ex);
		}
		return dockerFile;
	}

	public static File generateDeploymentFile(final String prjBasePath, final Map<String, String> contentMap) {
		final Locale locale = Locale.getDefault();
		final Context ctx = new Context(locale);
		for (Entry<String, String> entry : contentMap.entrySet()) {
			ctx.setVariable(entry.getKey(), entry.getValue());
		}
		final String dockerfileContent = staticTemplateEngine.process("kubernates/deployment", ctx);
		File dockerFile = new File(prjBasePath + File.separatorChar + "deployment.yml");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(dockerFile))) {
			writer.write(dockerfileContent);
		} catch (IOException ex) {
			log.error("Error : ", ex);
		}
		return dockerFile;
	}

}
