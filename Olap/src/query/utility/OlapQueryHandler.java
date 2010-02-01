package query.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import query.OlapQuery;
import query.PathExpression;
import query.QueryFactory.AggregationFunction;
import query.implementation.CompositionImpl;
import query.implementation.FunctionReferenceImpl;
import query.implementation.PairingImpl;
import query.implementation.ProjectionImpl;
import schema.Attribute;
import schema.DataSchema;

/**
 * Handles an xml file validating the {@link OlapQuery} DTD
 * @author Julien.C
 */
public class OlapQueryHandler extends DefaultHandler{
	
	public static enum OlapPathExpressionOperator {FUNCTION, PAIRING, COMPOSITION, PROJECTION};
	
	private PathExpression classifier;
	
	private PathExpression measure;
	
	private AggregationFunction aggregate;
	
	private String classifierId;
	
	private String measureId;
	
	/**
	 * The current projection node.
	 * null if not on a Projection node
	 */
	private Object[] currentProjection;
	
	/**
	 * The current projection id.
	 * null if not on a Projection node
	 */
	private String currentProjectionId;

	/**
	 * All PathExpression Elements map
	 */
	private HashMap<String,Object[]> map;

	/**
	 * Are we process a domain node ?
	 */
	private boolean isDomain;

	/**
	 * Current Olap schema
	 */
	private DataSchema schema;
	
	public OlapQueryHandler(DataSchema schema){
		this.schema = schema;
		map = new HashMap<String, Object[]>();
		isDomain = false;
	}
	
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

	@SuppressWarnings("unchecked")
	private PathExpression construct(String opId) {
		
		Object[] objTab = map.get(opId);
		OlapPathExpressionOperator type = (OlapPathExpressionOperator) objTab[0];
		PathExpression retPath = null;
		PathExpression leftOp = null;
		PathExpression rightOp = null;
		
		switch (type) {
			case FUNCTION: 
				retPath = FunctionReferenceImpl.createFunctionReference(schema.getFunctionByName((String) objTab[1]));
				break;
			case COMPOSITION: 
				leftOp = construct((String) objTab[1]);
				rightOp = construct((String) objTab[2]);
				retPath = CompositionImpl.createComposition(leftOp, rightOp);
				break;
			case PAIRING: 
				leftOp = construct((String) objTab[1]);
				rightOp = construct((String) objTab[2]);
				retPath = PairingImpl.createPairing(leftOp, rightOp);
				break;
			case PROJECTION: 
				List<String> domainList = (List<String>) objTab[1];
				List<String> rangeList = (List<String>) objTab[2];
				
				ArrayList<Attribute> domainAttList = new ArrayList<Attribute>();
				ArrayList<Attribute> rangeListAttList = new ArrayList<Attribute>();
				
				for (String attName : domainList) {
					domainAttList.add(schema.getAttributeByName(attName));
				}
				for (String attName : rangeList) {
					rangeListAttList.add(schema.getAttributeByName(attName));
				}
				
				retPath = ProjectionImpl.createProjection(domainAttList, rangeListAttList);
				break;
		}
		
		return retPath;
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
