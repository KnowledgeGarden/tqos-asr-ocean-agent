/**
 * Copyright 2020, TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package iodevtests;

import org.topicquests.os.asr.info.InformationEnvironment;
import org.topicquests.os.asr.info.api.IInfoOcean;

/**
 * @author jackpark
 *
 */
public class IOTestRoot {
	protected InformationEnvironment environment;
	protected IInfoOcean dsl;
	/**
	 * 
	 */
	public IOTestRoot() {
		environment = new InformationEnvironment();
		dsl = environment.getDSL();
	}

}
