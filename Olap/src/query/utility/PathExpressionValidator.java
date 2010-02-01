/**
 * 
 */
package query.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import query.*;
import schema.Attribute;
import schema.DataSchema;

/**
 * Validates a {@link PathExpression}
 * @author Reda
 */
public class PathExpressionValidator {
	
	private final PathExpression expression;
	private final boolean validation;
	
	/**
	 * @param expression The PathExpression to validate
	 * @see PathExpression
	 * @see DataSchema
	 */
	public PathExpressionValidator(PathExpression expression){
		this.expression = expression;
		this.validation = validate();
	}
	
	/**
	 * @return True if tested expression is valid, False if not.
	 */
	public boolean getValidation(){
		return validation;
	}
	
	/**
	 * @return True if tested expression is valid, False if not
	 */
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
		
		return true;
	}
	
	/**
	 * Validates a {@link Composition}
	 * @return True if the tested composition is valid, False if not
	 */
	private boolean validateComposition(){
		// Verifies that left operand's domain equals right operand's range 
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
		
		System.out.println("Compostion is not valid: left operand's domain doesn't equal right operand's range");
		return false;
	}
	
	/**
	 * Validates a {@link Pairing}
	 * @return True if the tested pairing is valid, False if not
	 */
	private boolean validatePairing(){
		// Verifies that left operand's domain equals right operand's domain
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
		
		System.out.println("Pairing is not valid: left operand's domain doesn't equal right operand's domain");
		return false;
	}
	
	/**
	 * Validates a {@link Projection}
	 * @return True if the tested projection is valid, False if not
	 */
	private boolean validateProjection(){
		// Verifies that domain contains range
		Projection projection_exp = (Projection) expression;
		Iterator<Attribute> selectedAttribute = projection_exp.getRange();
		Iterator<Attribute> genuine_range = projection_exp.getDomain();
		Attribute att;
		boolean found;
		
		while(selectedAttribute.hasNext()){
			att = selectedAttribute.next();
			found=false;
			while(genuine_range.hasNext() && !found){
				if(att.equals(genuine_range.next())) found = true;
			}
			if(!found){
				System.out.println("Projection is not valid: domain doesn't contain range");
				return false;
			}
		}
		
		return true;
	}
}
