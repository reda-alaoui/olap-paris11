package query.test;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import query.OlapQuery;
import query.utility.OlapQueryXMLLoader;
/**
 * Test {@link OlapQueryXMLLoader}
 * @author Sylvestre
 */
public class OlapQueryXMLLoaderTest {

	private OlapQuery query;

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testOlapQueryXMLLoader() {
		new OlapQueryXMLLoader(new File("xml/sample_query.xml"));
	}

	@Test
	public void testGetQuery() {
		OlapQueryXMLLoader query = new OlapQueryXMLLoader(new File("xml/sample_query.xml"));
		query.getQuery();
	}

}
