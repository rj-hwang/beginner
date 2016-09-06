package com.beginner.maven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dragon 2016-09-04
 */
public class M2Utils {
	private final static Logger logger = LoggerFactory.getLogger(M2Utils.class);

	public void log() {
		logger.warn("M2Utils.log");
	}
}