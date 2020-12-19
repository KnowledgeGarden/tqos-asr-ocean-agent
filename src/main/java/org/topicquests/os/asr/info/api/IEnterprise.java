/**
 * Copyright 2020, TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.os.asr.info.api;

import com.tinkerpop.blueprints.Vertex;

import net.minidev.json.JSONObject;

/**
 * 
 * @author jackpark
 *
 */
public interface IEnterprise extends Vertex {

	JSONObject getJSONObject();

}
