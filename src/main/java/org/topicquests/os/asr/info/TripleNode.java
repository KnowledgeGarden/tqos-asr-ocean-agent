/*
 * Copyright 2020 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.os.asr.info;

import java.util.Set;

import org.topicquests.os.asr.info.api.ITriple;
import org.topicquests.os.asr.wordgram.WordGramEnvironment;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.VertexQuery;
import com.tinkerpop.blueprints.impls.sql.SqlGraph;
import com.tinkerpop.blueprints.impls.sql.SqlVertex;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public class TripleNode implements ITriple {
	private WordGramEnvironment environment;
	private SqlGraph graph;
	private SqlVertex data;
	private static final long serialVersionUID = 1L;
	private Object synchObject;
	private boolean _isNew = false;

	/**
	 * @param v
	 * @param env
	 */
	public TripleNode(SqlVertex v, WordGramEnvironment env) {
		data = v;
		//label is set later
		environment = env;
		environment.logDebug("TripleNode- "+data);
		graph = environment.getSqlGraph();
		synchObject = new Object();
	}

	@Override
	public Iterable<Edge> getEdges(Direction direction, String... labels) {
		return data.getEdges(direction, labels);
	}

	@Override
	public Iterable<Vertex> getVertices(Direction direction, String... labels) {
		return data.getVertices(direction, labels);
	}

	@Override
	public VertexQuery query() {
		return data.query();
	}

	@Override
	public Edge addEdge(String label, Vertex inVertex) {
		return data.addEdge(label, inVertex);
	}

	@Override
	public <T> T getProperty(String key) {
		return data.getProperty(key);
	}

	@Override
	public Set<String> getPropertyKeys() {
		return data.getPropertyKeys();
	}

	@Override
	public void setProperty(String key, Object value) {
		data.setProperty(key, value);
	}

	@Override
	public <T> T removeProperty(String key) {
		return data.removeProperty(key);
	}

	@Override
	public void remove() {
		data.remove();
	}

	@Override
	public Object getId() {
		return data.getId();
	}

	@Override
	public JSONObject getJSONObject() {
		return data.getData();
	}

	@Override
	public void setSubject(String id, String label) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSubjectId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPredicate(String id, String label) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPredicateId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObject(String id, String label) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getObjectId() {
		// TODO Auto-generated method stub
		return null;
	}	
}
