package com.company.bfs.mvp.constants;

public enum TechStack {

	JAVA_MAVEN("MAVEN", "pom.xml", "dockerfile-java-maven-template", "8080"), 
	JAVA_GRADLE("Gradle", "build.xml", "dockerfile-java-gradle-template", "8080"),
	NODE("Node", "package.json", "dockerfile-node-template", "8080"), 
	PYTHON("Python", ".py", "dockerfile-python-template", "5000"),
	DOTNET("DotNet", ".csproj", "dockerfile-dotnet-template", "5001");

	private String tech;
	private String configFile;
	private String templateName;
	private String defaultPort;

	TechStack(final String tech, final String configFile, final String templateName, final String defaultPort) {
		this.tech = tech;
		this.configFile = configFile;
		this.templateName = templateName;
		this.defaultPort = defaultPort;
	}

	public String getTech() {
		return tech;
	}

	public String getConfigFile() {
		return configFile;
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getDefaultPort() {
		return defaultPort;
	}

}
