package query.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import query.PathExpression;
import query.QueryFactory.AggregationFunction;
import schema.DataSchema;

/**
 * Handles an xml file validating the {@link DataSchema} DTD
 * @author Julien.C
 */
public class OlapQueryHandler extends DefaultHandler{
	
	public static enum OlapPathExpressionOperator {FUNCTION, PAIRING, COMPOSITION, PROJECTION};
	
	private PathExpression classifier;
	
	private PathExpression measure;
	
	private AggregationFunction aggregate;
	
	private String classifierId;
	
	private String measureId;
	
	private Object[] currentProjection;
	private String currentProjectionId;

	private HashMap<String,Object[]> map;

	private boolean isDomain = false;
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Parsing started");
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println("Parsing ended");
		System.out.println("Construct Object");
		
		classifier = construct(classifierId);
		measure = construct(measureId);
	}

	private PathExpression construct(String opId) {
		Object[] objTab = map.get(opId);
		OlapPathExpressionOperator type = (OlapPathExpressionOperator) objTab[0];
		
		switch (type) {
		case FUNCTION:
			
			break;

		default:
			break;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//query process
		if(qName.equals("query")){
			classifierId = attributes.getValue("classifier");
			measureId = attributes.getValue("measure");
			aggregate = AggregationFunction.valueOf(AggregationFunction.class, attributes.getValue("aggregate"));
		//function, pairing, compos, proj process
		}else if(qName.equals("projection") || qName.equals("function") || 
				qName.equals("pairing") || qName.equals("composition")){
			
			if(qName.equals("projection")){
				currentProjectionId = attributes.getValue("name");
				currentProjection = new Object[]{
						OlapPathExpressionOperator.PROJECTION,
						//domain
						new ArrayList<String>(),
						//range
						new ArrayList<String>()
						};
			}else{
				Object[] vars = null;
				if(qName.equals("function")){
					vars = new Object[]{
							OlapPathExpressionOperator.FUNCTION,
							attributes.getValue("name")
							};
				}
				else if(qName.equals("pairing")){
					vars = new Object[]{
							OlapPathExpressionOperator.PAIRING,
							attributes.getValue("leftoperand"),
							attributes.getValue("rightoperand")
							};
				}
				else if(qName.equals("composition")){
					vars = new Object[]{
							OlapPathExpressionOperator.COMPOSITION,
							attributes.getValue("leftoperand"),
							attributes.getValue("rightoperand")
							};
				}
				map.put(attributes.getValue("name"),vars);
			}
		}
		//domain or range
		else if (qName.equals("domain") || qName.equals("range")){
			if (qName.equals("domain")){
				isDomain  = true;
			}
		}
		//attributes
		else if (qName.equals("attribute")){
			List<String> colAttr;
			if (isDomain){
				colAttr = (List<String>) currentProjection[1];
			}else{
				colAttr = (List<String>) currentProjection[2];
			}
			colAttr.add(attributes.getValue("name"));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if(qName.equals("projection")){
			map.put(currentProjectionId, currentProjection);
			currentProjectionId = null;
			currentProjection = null;
		}
		
		if(qName.equals("domain")){
			isDomain = false;
		}
	}

	public PathExpression getClassifier() {
		return classifier;
	}

	public PathExpression getMeasure() {
		return measure;
	}

	public AggregationFunction getAggregate() {
		return aggregate;
	}

}
