/**
 * Copyright 2020, TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.os.asr.info.api;

import com.tinkerpop.blueprints.Vertex;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 * <p>A Triple is a single vertex which contains information
 * on three other nodes:<br/>
 * <ol><li>a subject</li>
 * <li>a predicate</li>
 * <li>anobject</li></ol></p>
 * 
 */
public interface ITriple extends Vertex {

	JSONObject getJSONObject();
	
	/**
	 * A subject can be either a simple node, or it can be
	 * a triple
	 * @param id
	 * @param label
	 */
	void setSubject(String id, String label);
	
	
	String getSubjectId();
	
	void setPredicate(String id, String label);
	
	String getPredicateId();
	
	/**
	 * An object can be either a simple node, or a triple
	 * @param id
	 * @param label
	 */
	void setObject(String id, String label);
	
	String getObjectId();

}
