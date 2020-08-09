package com.company.bfs.mvp.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import com.company.bfs.mvp.constants.TechStack;
import com.company.bfs.mvp.util.GeneratorUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MavenDfg extends BaseDfg implements MvpGenerator {

	public MavenDfg(final File configFile, final String appPort) {
		super(configFile, appPort);
		if (StringUtils.isBlank(this.appPort)) {
			this.appPort = TechStack.JAVA_MAVEN.getDefaultPort();
		}
	}

	@Override
	public File generate() {

		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = null;
		try {
			model = reader.read(new FileReader(this.configFile));
		} catch (IOException | XmlPullParserException e) {
			log.error("Error : ", e);
			return null;
		}
		String javaVersion = (String) model.getProperties().get("java.version");
		if (StringUtils.isBlank(javaVersion)) {
			javaVersion = "1.8";
		}
		this.contentMap.put("javaVersion", javaVersion);
		String finalName = model.getBuild().getFinalName();
		if (StringUtils.isBlank(finalName)) {
			String artifactName = model.getArtifactId();
			String artifactVersion = model.getVersion();
			finalName = artifactName + "-" + artifactVersion;
		}
		String pkg = model.getPackaging();
		if (StringUtils.isNotEmpty(pkg) && !StringUtils.equals(pkg, "pom")) {
			finalName = finalName + "." + pkg;
		}
		this.contentMap.put("finalName", finalName);
		this.contentMap.put("appPort", this.appPort);
		final String projectName = model.getName();
		this.contentMap.put("projectName", projectName);

		return GeneratorUtil.generateDockerFile(this.configFile.getParent(), TechStack.JAVA_MAVEN.getTemplateName(),
				contentMap);
	}

}