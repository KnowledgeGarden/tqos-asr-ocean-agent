/**
 * Copyright 2020, TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.os.asr.info.api;

import java.util.Map;

import org.topicquests.hyperbrane.api.IWordGram;
import org.topicquests.support.api.IResult;

import com.tinkerpop.blueprints.Edge;

import org.topicquests.os.asr.info.InformationEnvironment;
import org.topicquests.os.asr.wordgram.api.IWordGramAgentModel;
import org.topicquests.pg.api.IPostgresConnection;

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
	public static final String
		WG_WG_LABEL		= "WG-WGArc",
		WG_TP_LABEL		= "WG-TupleArc",
		WG_WG_TR_LABEL	= "TripleArc",
		AU_DOC_LABEL	= "AU-DocArc",
		AU_EMP_LABEL	= "AU-EmpArc",
		WG_DOC_Label	= "WG-DocArc";
	
	void setEnvironment(InformationEnvironment env); 

	// we pick up wordgram API from the parent
	
	//////////////////////////
	// Nodes - Vertices
	//////////////////////////
	
	
	ITriple createTripleNode(String id, String label);
	
	IResult getTriple(String id);
	
	
	/**
	 * Creates a WordGram ID from {@ code label}
	 * @param label
	 * @return
	 */
	IAgent createInstitutionNode(String label);
	
	IResult getInstitutionNode(String id);
	
	// links instead public IRelation createRelationNode(String id, String label, String type);
	
	IDocument createDocumentNode(String id, String label, String docId);
	
	IResult getDocumentNode(String id);
		
	//////////////////////////
	// Links - Edges
	//////////////////////////
	
	/**
	 * This creates an edge
	 * @param wgFromId
	 * @param wgToId
	 * @param sentenceId
	 * @return
	 */
	Edge connectWordGramsBySentenceId(String wgFromId, String wgToId, String sentenceId);
	
	Edge connectWordGramToTupleBySentenceId(String wgFromId, String tpToId, String sentenceId);
	
	/**
	 * 
	 * @param auFromId
	 * @param docToId
	 * @return can return {@code null} if connection exists
	 */
	Edge connectAuthorToDocument(String auFromId, String docToId);
	
	/**
	 * 
	 * @param auFromId
	 * @param empToId
	 * @return can return {@code null} if connection exists
	 */
	Edge connectAuthorToInstitution(String auFromId, String empToId);
	
	Edge connectAuthorToFunder(String auFromId, String funToId); //???
	Edge connectDocumentToFunder(String docFromId, String funToId); //???
	Edge connectEmployerFunder(String empFromId, String funToId); // ???

	/**
	 * Wire up keywords to their documents
	 * @param keyWordId
	 * @param documentId
	 * @return can return {@code null} if connection exists
	 */
	Edge connectKeyWordGramToDocument(String keyWordId, String documentId);
	
	Edge connectKeyWordGramToDocument(IPostgresConnection conn, String keyWordId, String documentId, IResult r) throws Exception ;
	//////////////////////
	// Query
	//////////////////////
	
	IResult findByLabel(String label);
	
	IResult findByKeyValuePair(String key, String value);
}
