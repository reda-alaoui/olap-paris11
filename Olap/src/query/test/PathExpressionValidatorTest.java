package query.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import query.Composition;
import query.PathExpression;
import query.Projection;
import query.implementation.CompositionImpl;
import query.implementation.FunctionReferenceImpl;
import query.implementation.ProjectionImpl;
import query.utility.PathExpressionValidator;
import schema.Attribute;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.FunctionImpl;

public class PathExpressionValidatorTest {
	
	private Composition composition1;
	private Composition composition2;
	
	private Projection projection1;
	private Projection projection2;

	@Before
	public void setUp() throws Exception {
		// Composition instantiations
		Attribute left_domain = new AttributeImpl("date", DataType.STRING);
		Attribute left_range = new AttributeImpl("month", DataType.STRING);
		Attribute right_domain = new AttributeImpl("O", DataType.ID);
		
		PathExpression leftOperand = new FunctionReferenceImpl(
				new FunctionImpl("f1", left_domain, left_range));
		PathExpression rightOperand = new FunctionReferenceImpl(
				new FunctionImpl("f", right_domain, left_domain));
		
		composition1 = CompositionImpl.createComposition(leftOperand, rightOperand);
		
		composition2 = CompositionImpl.createComposition(rightOperand, leftOperand);
		
		// Projection instantiations
		List<Attribute> range = new ArrayList<Attribute>();
		range.add(left_domain);
		range.add(left_range);
		List<Attribute> domain = new ArrayList<Attribute>();
		domain.add(left_domain);
		domain.add(left_range);
		domain.add(right_domain);
		projection1 = ProjectionImpl.createProjection(range, domain);
		projection2 = ProjectionImpl.createProjection(domain, range);
		
	}

	@Test
	public void testPathExpressionValidator() {
		new PathExpressionValidator(composition1);
	}

	@Test
	public void testGetValidation() {
		// Validation of valid composition
		PathExpressionValidator validator = new PathExpressionValidator(composition1);
		assertTrue(validator.getValidation());
		
		// Validation of an invalid composition
		validator = new PathExpressionValidator(composition2);
		assertFalse(validator.getValidation());
		
		// Validation of a valid projection
		assertTrue(new PathExpressionValidator(projection1).getValidation());
		
		//Validation of an invalid projection
		assertFalse(new PathExpressionValidator(projection2).getValidation());
	}

}
