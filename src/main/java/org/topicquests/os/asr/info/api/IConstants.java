/**
 * Copyright 2020, TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.os.asr.info.api;

/**
 * @author jackpark
 *
 */
public interface IConstants {
	/** Node Types */
	public static final String
		DOCUMENT_TYPE		= "DocType",
		WORDGRAM_TYPE		= "WgType",
		TRIPLE_TYPE			= "TrpType",
		PERSON_TYPE			= "PsnType",
		ENTERPRISE_TYPE		= "EntType";
		//RELATION_TYPE
	
	/** Primary Link Types */
	public static final String
		DOCUMENT_LINK		= "DocLink",
		AUTHOR_LINK			= "AthrLink",
		EMPLOYER_LINK		= "EmpLink",
		FUNDER_LINK			= "FndrLink",
		SENTENCE_LINK		= "SentLink",
		TRIPLE_LINK			= "TrplLink";
	
	public static final String
		DISAGREE_LINK		= "DisagreesWith",
		AGREE_LINK			= "AgreesWith",
		EXAMPLE_LINK		= "ExampleOf",
		PROVE_LINK			= "Proves",
		DISPROVE_LINK		= "Disproves";
		//TODO add more

}
