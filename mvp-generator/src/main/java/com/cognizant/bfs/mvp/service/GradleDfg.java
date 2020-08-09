package com.company.bfs.mvp.service;

import java.io.File;

public class GradleDfg extends BaseDfg implements MvpGenerator {

	public GradleDfg(final File configFile, final String appPort) {
		super(configFile, appPort);
	}

	@Override
	public File generate() {
		return null;
	}

}