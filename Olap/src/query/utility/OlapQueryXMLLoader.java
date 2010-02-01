/**
 * 
 */
package query.utility;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import query.OlapQuery;
import query.implementation.QueryFactoryImpl;
import schema.DataSchema;

/**
 * Parse an XML document into an OlapQuery if the file is valid
 * @author Julien.C
 * @author Reda
 */
public class OlapQueryXMLLoader {
	
	private OlapQuery query;
	
	public OlapQueryXMLLoader(DataSchema schema, File xml_file){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		OlapQueryHandler query_handler = null;
		
		try {
			parser = factory.newSAXParser();
			query_handler = new OlapQueryHandler(schema);
			parser.parse(xml_file, query_handler);
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		QueryFactoryImpl q = new QueryFactoryImpl(schema);
		
		try {
			query = q.olapQuery(query_handler.getClassifier(), query_handler
					.getMeasure(), query_handler.getAggregate());
		} catch (OlapQueryValidationException e) {
			query = null;
			System.err.println("Can't parse file "+xml_file.getAbsolutePath() + ", request is not a valid olap request");
			e.printStackTrace();
		}
	}
	
	public OlapQuery getQuery(){
		return query;
	}
}
