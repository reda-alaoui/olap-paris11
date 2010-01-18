/**
 * 
 */
package schema.utility;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import schema.DataSchema;
import schema.implementation.DataSchemaImpl;

/**
 * @author Reda
 *
 */
public class DataSchemaXMLLoader {
	
	private final DataSchema schema;
	
	public DataSchemaXMLLoader(File xml_file){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		DataSchemaHandler schema_handler = new DataSchemaHandler();
		
		try {
			parser.parse(xml_file, schema_handler);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		schema = new DataSchemaImpl(schema_handler.getName(),
				schema_handler.getAttribute_list(),
				schema_handler.getFunction_list());

	}
	
	public DataSchema getSchema(){
		return schema;
	}

}
