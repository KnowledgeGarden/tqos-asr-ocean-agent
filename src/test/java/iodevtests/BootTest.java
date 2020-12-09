/**
 * Copyright 2020, TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package iodevtests;

/**
 * @author jackpark
 *
 */
public class BootTest extends IOTestRoot {

	/**
	 * 
	 */
	public BootTest() {
		super();
		System.out.println("A "+environment.getProperties());
		environment.shutDown();
		System.exit(0);
	}

}
