/*
 * Copyright 2017 Ties BV
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package network.tiesdb.context.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.ServiceLoader;

import network.tiesdb.exception.TiesConfigurationException;

/**
 * TiesDB context factory.
 * 
 * <P>Factory of TiesDB contexts.
 * 
 * @author Anton Filatov (filatov@ties.network)
 */
public interface TiesContextFactory {

	/**
	 * Searches a suitable {@link TiesContextFactory}
	 * 
	 * @param contextTypeName
	 *            - name of the context class
	 * @return {@link TiesContextFactory} instance or null
	 */
	static TiesContextFactory getTiesContextFactory(String contextTypeName) {
		Iterator<TiesContextFactory> services = ServiceLoader.load(TiesContextFactory.class).iterator();
		while (services.hasNext()) {
			TiesContextFactory tiesContextService = services.next();
			if (tiesContextService.matchesContextType(contextTypeName)) {
				return tiesContextService;
			}
		}
		return null;
	}

	boolean matchesContextType(String contextTypeName);

	TiesContext readContext(InputStream is) throws TiesConfigurationException;

	void writeContext(OutputStream os, TiesContext contextData) throws TiesConfigurationException;
}
