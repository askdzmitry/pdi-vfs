/*!
* Copyright 2010 - 2013 Pentaho Corporation.  All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

package org.apache.commons.vfs.provider.webdav.test;

import junit.framework.Test;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.FileSystemOptions;
import org.apache.commons.vfs.impl.DefaultFileSystemManager;
import org.apache.commons.vfs.provider.temp.TemporaryFileProvider;
import org.apache.commons.vfs.provider.webdav.WebdavFileProvider;
import org.apache.commons.vfs.provider.webdav.WebdavFileSystemConfigBuilder;
import org.apache.commons.vfs.test.AbstractProviderTestConfig;
import org.apache.commons.vfs.test.ProviderTestSuite;

/**
 * Test cases for the WebDAV provider.
 *
 * @author <a href="mailto:adammurdoch@apache.org">Adam Murdoch</a>
 * @version $Revision: 773234 $ $Date: 2009-05-09 11:27:59 -0400 (Sat, 09 May 2009) $
 */
public class WebdavProviderTestCase
    extends AbstractProviderTestConfig
{
    private static final String TEST_URI = "test.webdav.uri";
    public static Test suite() throws Exception
    {
        if (System.getProperty(TEST_URI) != null)
        {
            ProviderTestSuite suite = new WebdavProviderTestSuite(new WebdavProviderTestCase());
            suite.addTests(WebdavVersioningTests.class);
            return suite;
        }
        else
        {
            return notConfigured(WebdavProviderTestCase.class);
        }
    }

    /**
     * Prepares the file system manager.
     */
    public void prepare(final DefaultFileSystemManager manager)
        throws Exception
    {
        manager.addProvider("webdav", new WebdavFileProvider());
        manager.addProvider("tmp", new TemporaryFileProvider());
    }

    /**
     * Returns the base folder for tests.
     */
    public FileObject getBaseTestFolder(final FileSystemManager manager)
        throws Exception
    {
        WebdavFileSystemConfigBuilder builder =
            (WebdavFileSystemConfigBuilder)manager.getFileSystemConfigBuilder("webdav");
        final String uri = System.getProperty(TEST_URI);
        FileSystemOptions opts = new FileSystemOptions();
        builder.setRootURI(opts, uri);
        return manager.resolveFile(uri, opts);
    }
}
