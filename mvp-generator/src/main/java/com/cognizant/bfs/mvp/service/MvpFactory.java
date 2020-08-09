package com.company.bfs.mvp.service;

import java.io.File;

import com.company.bfs.mvp.constants.TechStack;

public class MvpFactory {

	private MvpFactory() {
		// no instance class
	}

	public static MvpGenerator getDfGenerator(final File projectBaseDir, final String appPort) {
		File configFile = null;
		MvpGenerator dfGenerator = null;
		configFile = getConfigFile(projectBaseDir, TechStack.JAVA_MAVEN);
		if (configFile.exists()) {
			dfGenerator = new MavenDfg(configFile, appPort);
		}
		configFile = getConfigFile(projectBaseDir, TechStack.JAVA_GRADLE);
		if (configFile.exists()) {
			dfGenerator = new GradleDfg(configFile, appPort);
		}
		configFile = getConfigFile(projectBaseDir, TechStack.NODE);
		if (configFile.exists()) {
			dfGenerator = new NodeDfg(configFile, appPort);
		}
		if (null == dfGenerator) {
			System.err.println("Tech stack not detected or supported in " + projectBaseDir.getAbsolutePath());
		}
		return dfGenerator;
	}

	public static MvpGenerator getCicdGenerator(final File projectBaseDir, final String appPort) {
		return new PipelineGenerator(projectBaseDir, projectBaseDir.getName(), appPort);
	}

	public static MvpGenerator getDeploymentGenerator(final File projectBaseDir, final String appPort) {
		return new DeploymentGenerator(projectBaseDir, projectBaseDir.getName(), appPort);
	}

	private static File getConfigFile(final File projectBaseDir, final TechStack techStack) {
		return new File(projectBaseDir.getPath() + File.separatorChar + techStack.getConfigFile());
	}

}
