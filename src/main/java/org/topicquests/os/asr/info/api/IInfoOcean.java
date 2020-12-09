/**
 * Copyright 2020, TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.os.asr.info.api;

import java.util.Map;

import org.topicquests.hyperbrane.api.IWordGram;
import org.topicquests.support.api.IResult;
import org.topicquests.os.asr.info.InformationEnvironment;
import org.topicquests.os.asr.wordgram.api.IWordGramAgentModel;

/**
 * @author jackpark
 * </p>
 * This is a model API - a kind of DSL - which allows to create
 * graph nodes (vertices) of varying types, including:<br/>
 * <ol><li>WordGram</li>
 * <li>Triple</li>
 * <li>Person</li>
 * <li>Employer</li>
 * <li>Funder</li>
 * <li>Document</li>
 * <li>Relation</li></ol></p>
 * <p>Given link types (see below), we may only need one kind of <em>enterprise</em>
 * node type, and establish its role through the links to/from it</p>
 * <p>As a DSL, this needs to include API to create and maintain those
 * nodes, and to wire them into a graph with a variety of typed edges, such as</br>
 * <ol><li>SentenceLink</li>
 * <li>AuthorLink</li>
 * <li>TripleLink</li>
 * <li>DocumentLink</li>
 * <li>HypernymLink</li>
 * <li>HyponymLink</li>
 * <li>SynonymLink</li>
 * <li>EmployerLink</li>
 * <li>FunderLink</li>
 * <li>(various) CoherenceRelation (types)</p>
 * <p>Coherence Relations allow OpenSherlock to wander around in that graph
 * doing process like:</br>
 * <ol><li>merge authors</li>
 * <li>refine triples - merge when synonyms are used</li>
 * <li>Discover coherence relations among triples and wire them up</li></ol></p>
 * <p>A typical coherence relation is <em>DisagreesWith</em></p>
 * 
 */
public interface IInfoOcean extends IWordGramAgentModel {

	//public IWordGram createWordGramNode(String id, String text, String sentenceId, String lexType);
	
	//public IResult addWordGramSentenceId(String id, String sentenceId);
	
	//public IResult addWordGramTopicLocator(String id, String topicLocator);
	
	//public IResult addWordGramLexType(String id, String lexType);
	
	//public IResult addWordGramProperties(String id, Map<String, Object> properties);
	
	//public IResult getWordGram(String id);
	
	//public IResult getThisWordGram(String id);
	
	ITriple createTripleNode(String id, String label);
	
	IPerson createPersonNode(String id, String label);
	
	IEnterprise createEnterpriseNode(String id, String label);
	
	// links instead public IRelation createRelationNode(String id, String label, String type);
	
	IDocument createDocumentNode(String id, String label, String docId);
	
	void setEnvironment(InformationEnvironment env); 
}
