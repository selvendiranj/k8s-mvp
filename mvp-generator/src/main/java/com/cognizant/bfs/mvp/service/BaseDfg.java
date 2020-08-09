package com.company.bfs.mvp.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BaseDfg {

	protected File configFile;
	protected Map<String, String> contentMap;
	protected String appPort;

	public BaseDfg(final File configFile, final String appPort) {
		this.configFile = configFile;
		this.appPort = appPort;
		this.contentMap = new HashMap<>();
	}

}