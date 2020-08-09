package com.company.bfs.mvp;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import com.company.bfs.mvp.service.MvpGenerator;
import com.company.bfs.mvp.service.MvpFactory;

@SpringBootApplication
public class DockerfileGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerfileGeneratorApplication.class, args);

		// Validate Input
		if (null == args || args.length < 1 || args.length > 2) {
			System.err.println("Usage: dfg <project base location> [<applicaiton port>]");
			return;
		}
		final String prjBasePath = StringUtils.trimWhitespace(args[0]);
		if (StringUtils.isEmpty(prjBasePath)) {
			System.err.println("Usage: dfg <project base location>");
			return;
		}
		String appPort = null;
		if (args.length == 2) {
			appPort = StringUtils.trimWhitespace(args[1]);
		}

		// Detect the project base directory
		File prjBaseDir = new File(prjBasePath);
		if (!prjBaseDir.isDirectory()) {
			System.err.println("No such directory : " + prjBasePath);
			return;
		}

		final MvpGenerator dfGenerator = MvpFactory.getDfGenerator(prjBaseDir, appPort);
		File dockerFile = null;
		if (null != dfGenerator) {
			dockerFile = dfGenerator.generate();
		}
		if (null == dockerFile) {
			System.err.println("Error generating docker file for this project");
		} else {
			System.out.println("Dockerfile generated successfully.");
		}

		final MvpGenerator cicdGenerator = MvpFactory.getCicdGenerator(prjBaseDir, appPort);
		File cicdFile = null;
		if (null != cicdGenerator) {
			cicdFile = cicdGenerator.generate();
		}
		if (null == cicdFile) {
			System.err.println("Error generating pipeline file for this project");
		} else {
			System.out.println("pipeline.yml file generated successfully.");
		}

		final MvpGenerator deploymentGenerator = MvpFactory.getDeploymentGenerator(prjBaseDir, appPort);
		File deploymentFile = null;
		if (null != deploymentGenerator) {
			deploymentFile = deploymentGenerator.generate();
		}
		if (null == deploymentFile) {
			System.err.println("Error generating deployment file for this project");
		} else {
			System.out.println("k8s deployment file generated successfully.");
		}
	}

}
