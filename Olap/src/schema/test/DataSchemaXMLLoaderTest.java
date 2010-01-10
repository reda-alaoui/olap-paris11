/**
 * 
 */
package schema.test;

import java.io.File;
import java.io.IOException;

import org.jdom.JDOMException;
import org.junit.Test;

import schema.utility.DataSchemaXMLLoader;


/**
 * @author Reda
 *
 */
public class DataSchemaXMLLoaderTest {
	/**
	 * Test method for {@link schema.utility.DataSchemaXMLLoader#DataSchemaXMLLoader(java.io.File)}.
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	@Test
	public void testDataSchemaXMLLoader() throws JDOMException, IOException {
		new DataSchemaXMLLoader("S",new File("test.xml"));
	}

	/**
	 * Test method for {@link schema.utility.DataSchemaXMLLoader#getSchema()}.
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	@Test
	public void testGetSchema() throws JDOMException, IOException {
		DataSchemaXMLLoader schema = new DataSchemaXMLLoader("S",new File("test.xml"));
		schema.getSchema();
		
	}
}
