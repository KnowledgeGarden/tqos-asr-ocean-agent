/**
 * Copyright 2020, TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.os.asr.info;

import org.topicquests.os.asr.info.api.IInfoOcean;
import org.topicquests.os.asr.wordgram.WordGramEnvironment;
import org.topicquests.support.RootEnvironment;

/**
 * @author jackpark
 *
 */
public class InformationEnvironment extends RootEnvironment {
	private IInfoOcean dsl = null;
	private WordGramEnvironment wordGramEnvironment;

	/**
	 */
	public InformationEnvironment() {
		super("io-config-props.xml", "logger.properties");
		wordGramEnvironment = new WordGramEnvironment("wordgram-props.xml", "logger.properties");
		dsl = new InformationOceanModel(wordGramEnvironment);
		dsl.setEnvironment(this);
	}
	
	public IInfoOcean getDSL() {
		return dsl;
	}
	
	public WordGramEnvironment getWordGramEnvironment() {
		return wordGramEnvironment;
	}


	@Override
	public void shutDown() {
		wordGramEnvironment.shutDown();

	}

}
