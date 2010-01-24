/**
 * 
 */
package query.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import query.*;
import query.QueryFactory.AggregationFunction;
import schema.Attribute;
import schema.Attribute.DataType;

/**
 * @author Reda
 *
 */
public class PathExpressionValidator {
	private final PathExpression expression;
	private final boolean validation;
	
	public PathExpressionValidator(PathExpression expression){
		this.expression = expression;
		this.validation = validate();
	}
	
	public boolean getValidation(){
		return validation;
	}
	
	private boolean validate(){
		if(expression instanceof Composition ){
			return validateComposition();
		}
		else if(expression instanceof Pairing){
			return validatePairing();
		}
		else if(expression instanceof Projection){
			return validateProjection();
		}
		else if(expression instanceof OlapQuery){
			return validateOlapQuery();
		}
		else if(expression instanceof FunctionReference){
			return validateFunctionReference();
		}
		
		return false;
	}
	
	private boolean validateComposition(){
		// Verify that left operand's domain equals right operand's range 
		Composition composition_exp = (Composition) expression;
		
		Iterator<Attribute> left_iterator = composition_exp.getLeftOperand().getDomain();
		List<Attribute> left_domain = new ArrayList<Attribute>();
		while(left_iterator.hasNext()){
			left_domain.add(left_iterator.next());
		}
		
		Iterator<Attribute> right_iterator = composition_exp.getRightOperand().getRange();
		List<Attribute> right_range = new ArrayList<Attribute>();;
		while(right_iterator.hasNext()){
			right_range.add(right_iterator.next());
		}
		
		if(left_domain.equals(right_range)) return true;
		
		return false;
	}
	
	private boolean validatePairing(){
		// Verify that left operand's domain equals right operand's domain
		Pairing pairing_exp = (Pairing) expression;
		
		Iterator<Attribute> left_iterator = pairing_exp.getLeftOperand().getDomain();
		List<Attribute> left_domain = new ArrayList<Attribute>();
		while(left_iterator.hasNext()){
			left_domain.add(left_iterator.next());
		}
		
		Iterator<Attribute> right_iterator = pairing_exp.getRightOperand().getDomain();
		List<Attribute> right_domain = new ArrayList<Attribute>();;
		while(right_iterator.hasNext()){
			right_domain.add(right_iterator.next());
		}
		
		if(left_domain.equals(right_domain)) return true;
		
		return false;
	}
	
	private boolean validateProjection(){
		
		return true;
	}
	
	private boolean validateOlapQuery(){
		OlapQuery query_exp = (OlapQuery) expression;
		AggregationFunction aggregate = query_exp.getAggregate();
		
		if(aggregate != AggregationFunction.AVG && aggregate != AggregationFunction.SUM){
			return true;
		}
		
		PathExpression measure = query_exp.getMeasure();
		Iterator<Attribute> range_iterator = measure.getRange();
		
		while(range_iterator.hasNext()){
			if(range_iterator.next().getDataType() == DataType.STRING ) return false;
		}
		return true;
		
	}
	
	private boolean validateFunctionReference(){
		
		return true;
	}
}
