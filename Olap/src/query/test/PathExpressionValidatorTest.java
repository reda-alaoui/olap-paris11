package query.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import query.Composition;
import query.FunctionReference;
import query.Pairing;
import query.Projection;
import query.implementation.CompositionImpl;
import query.implementation.FunctionReferenceImpl;
import query.implementation.PairingImpl;
import query.implementation.ProjectionImpl;
import query.utility.PathExpressionValidator;
import schema.Attribute;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.FunctionImpl;
/**
 * Test {@link PathExpressionValidator}
 * @author Reda
 *
 */
public class PathExpressionValidatorTest {
	/**
	 * A function reference (can't be invalid)
	 */
	private FunctionReference function1;
	/**
	 * A valid composition
	 */
	private Composition composition1;
	/**
	 * An invalid composition
	 */
	private Composition composition2;
	/**
	 * A valid projection
	 */
	private Projection projection1;
	/**
	 * An invalid projection
	 */
	private Projection projection2;
	/**
	 * A valid pairing
	 */
	private Pairing pairing1;
	/**
	 * An invalid pairing
	 */
	private Pairing pairing2;

	@Before
	public void setUp() throws Exception {
		// FunctionReference instantiations
		Attribute left_domain = new AttributeImpl("date", DataType.STRING);
		Attribute left_range = new AttributeImpl("month", DataType.STRING);
		Attribute right_domain = new AttributeImpl("O", DataType.ID);
		
		function1 = new FunctionReferenceImpl(
				new FunctionImpl("f1", left_domain, left_range));
		// Composition instantiations
		FunctionReference rightOperand = new FunctionReferenceImpl(
				new FunctionImpl("f", right_domain, left_domain));
		composition1 = CompositionImpl.createComposition(function1, rightOperand);
		
		composition2 = CompositionImpl.createComposition(rightOperand, function1);
		
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
		
		// Pairing instantiations
		Attribute att = new AttributeImpl("quantity", DataType.INTEGER);
		FunctionReference q = new FunctionReferenceImpl(
				new FunctionImpl("q", right_domain, att));
		
		pairing1 = PairingImpl.createPairing(composition1, q);
		pairing2 = PairingImpl.createPairing(pairing1, function1);
		
	}
	/**
	 * Test method for {@link PathExpressionValidator#PathExpressionValidator(query.PathExpression)}
	 */
	@Test
	public void testPathExpressionValidator() {
		new PathExpressionValidator(composition1);
	}
	/**
	 * Test method for {@link PathExpressionValidator#getValidation()}.</br>
	 * Function reference validation
	 */
	@Test
	public void testGetValidation1() {
		// Validation of a function reference
		assertTrue(new PathExpressionValidator(function1).getValidation());
	}
	/**
	 * Test method for {@link PathExpressionValidator#getValidation()}.</br>
	 * Composition validation
	 */
	@Test
	public void testGetValidation2() {
		// Validation of a valid composition
		assertTrue(new PathExpressionValidator(composition1).getValidation());
		// Validation of an invalid composition
		assertFalse(new PathExpressionValidator(composition2).getValidation());
	}
	/**
	 * Test method for {@link PathExpressionValidator#getValidation()}.</br>
	 * Projection validation
	 */
	@Test
	public void testGetValidation3() {
		// Validation of a valid projection
		assertTrue(new PathExpressionValidator(projection1).getValidation());
		
		// Validation of an invalid projection
		assertFalse(new PathExpressionValidator(projection2).getValidation());
	}
	/**
	 * Test method for {@link PathExpressionValidator#getValidation()}.</br>
	 * Pairing validation
	 */
	@Test
	public void testGetValidation4() {
		// Validation of a valid pairing
		assertTrue(new PathExpressionValidator(pairing1).getValidation());
		
		// Validation of an invalid pairing
		assertFalse(new PathExpressionValidator(pairing2).getValidation());
	}

}
