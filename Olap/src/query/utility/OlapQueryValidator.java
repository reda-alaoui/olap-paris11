/**
 * 
 */
package query.utility;

import java.util.Iterator;

import query.OlapQuery;
import query.PathExpression;
import query.QueryFactory.AggregationFunction;
import schema.Attribute;
import schema.DataSchema;
import schema.Attribute.DataType;

/**
 * @author Reda
 *
 */
public class OlapQueryValidator {
	
	private final OlapQuery query;
	private final DataSchema schema;
	private final boolean validation;
	
	public OlapQueryValidator(OlapQuery query, DataSchema schema){
		this.query = query;
		this.schema = schema;
		this.validation = validate();
	}
	
	private boolean validate(){
		return isAggregateDataTypeCompatible() && isSourcesEqualsRoot();
	}
	
	private boolean isAggregateDataTypeCompatible(){
		// Verifies that aggregate is compatible with range(measure) DataType
		AggregationFunction aggregate = query.getAggregate();
		
		if(aggregate == AggregationFunction.AVG || aggregate == AggregationFunction.SUM){
			PathExpression measure = query.getMeasure();
			Iterator<Attribute> range_iterator = measure.getRange();
			
			while(range_iterator.hasNext()){
				if(range_iterator.next().getDataType() == DataType.STRING ){ 
					System.out.println("Aggregation function is not compatible with the measure");
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isSourcesEqualsRoot(){
		// Verifies that source(classifier) = source(measure) = root
		
		Iterator<Attribute> att_iterator = schema.getAttributeIterator();
		Attribute root=null;
		boolean root_found = false;
		while(att_iterator.hasNext() && !root_found){
			root = att_iterator.next();
			if(!schema.getFunctionsByRange(root).hasNext()) root_found = true;
		}
		
		Iterator<Attribute> classifier_domain = query.getClassifier().getDomain();
		if(classifier_domain.hasNext()){
			if(!classifier_domain.next().equals(root)){
				System.out.println("Source(classifier) doesn't equal root");
				return false;
			}
			if(classifier_domain.hasNext()){
				System.out.println("Source(classifier) doesn't equal root");
				return false;
			}
		}
		
		Iterator<Attribute> measure_domain = query.getMeasure().getDomain();
		if(measure_domain.hasNext()){
			if(!measure_domain.next().equals(root)){
				System.out.println("Source(measure) doesn't equal root");
				return false;
			}
			if(measure_domain.hasNext()){
				System.out.println("Source(measure) doesn't equal root");
				return false;
			}
		}
		
		return true;
	}
	
	public boolean getValidation(){
		return validation;
	}
	

}
