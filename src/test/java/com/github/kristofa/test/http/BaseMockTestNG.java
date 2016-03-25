/**
 * Copyright 2011 <jharlap@gitub.com> Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.github.kristofa.test.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.annotations.*;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * @author thomasmodeneis
 */
public abstract class BaseMockTestNG {

    protected static final String UTF_8 = "UTF-8";
    protected static MockHttpServer server;
    protected static SimpleHttpResponseProvider responseProvider;
    protected HttpClient client;
    protected static String baseUrl;

    @BeforeClass
    public void beforeSuite() throws IOException {
        responseProvider = new SimpleHttpResponseProvider();
        server = new MockHttpServer(0, responseProvider);
        final int port = server.start();
        assertTrue(port != -1);
        baseUrl = "http://localhost:" + server.getPort();
    }

    @AfterClass
    public void afterSuite() throws IOException {
        server.stop();
    }

    @BeforeMethod
    public void beforeClass() throws Exception {
        client = new DefaultHttpClient();
        responseProvider.reset();
    }
}