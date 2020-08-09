package com.company.bfs.mvp.service;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.company.bfs.mvp.constants.TechStack;
import com.company.bfs.mvp.util.GeneratorUtil;

public class NodeDfg extends BaseDfg implements MvpGenerator {

	public NodeDfg(final File configFile, final String appPort) {
		super(configFile, appPort);
		if (StringUtils.isBlank(this.appPort)) {
			this.appPort = TechStack.NODE.getDefaultPort();
		}
	}

	@Override
	public File generate() {
		this.contentMap.put("appPort", this.appPort);
		// Parse the configFile and read the main file and set it in the template
		return GeneratorUtil.generateDockerFile(this.configFile.getParent(), TechStack.NODE.getTemplateName(),
				contentMap);
	}

}