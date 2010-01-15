/**
 * 
 */
package schema.test;

import java.io.File;
import java.io.IOException;
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
	public void testDataSchemaXMLLoader(){
		new DataSchemaXMLLoader(new File("xml/sample_schema.xml"));
	}

	/**
	 * Test method for {@link schema.utility.DataSchemaXMLLoader#getSchema()}.
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	@Test
	public void testGetSchema(){
		DataSchemaXMLLoader schema = new DataSchemaXMLLoader(new File("xml/sample_schema.xml"));
		schema.getSchema();
	}
}
