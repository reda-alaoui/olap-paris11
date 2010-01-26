/**
 * 
 */
package query.implementation;

import java.util.List;

import query.Composition;
import query.FunctionReference;
import query.OlapQuery;
import query.Pairing;
import query.PathExpression;
import query.Projection;
import query.QueryFactory;
import query.utility.PathExpressionValidationException;
import query.utility.PathExpressionValidator;
import schema.Attribute;
import schema.DataSchema;
import schema.Function;

/**
 * Implementation of QueryFactory
 * @see query.QueryFactory
 */
public class QueryFactoryImpl implements QueryFactory {
	
	/**
	 * The schema on which apply search validation
	 */
	private DataSchema schema;
	
	public QueryFactoryImpl(DataSchema schema){
		this.schema = schema;
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#composition(query.PathExpression, query.PathExpression)
	 */
	@Override
	public Composition composition(PathExpression p1, PathExpression p2) throws PathExpressionValidationException {
		Composition composition = CompositionImpl.createComposition(p1, p2);
		
		if(! new PathExpressionValidator(composition, schema).getValidation()) 
			throw new PathExpressionValidationException(composition);
		
		return composition;
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#function(java.lang.String)
	 */
	@Override
	public FunctionReference function(String name) throws PathExpressionValidationException {
		Function f = schema.getFunctionByName(name);
		FunctionReference funcRef = new FunctionReferenceImpl(f);
		
		if(! new PathExpressionValidator(funcRef, schema).getValidation()) 
			throw new PathExpressionValidationException(funcRef);
		
		return funcRef;
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#olapQuery(query.PathExpression, query.PathExpression, query.QueryFactory.AggregationFunction)
	 */
	@Override
	public OlapQuery olapQuery(PathExpression c, PathExpression m,
			AggregationFunction op) throws PathExpressionValidationException {
		OlapQuery query = OlapQueryImpl.createOlapQuery(c, m, op);
		
		if(! new PathExpressionValidator(query, schema).getValidation()) 
			throw new PathExpressionValidationException(query);
		
		return query;
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#pairing(query.PathExpression, query.PathExpression)
	 */
	@Override
	public Pairing pairing(PathExpression p1, PathExpression p2) throws PathExpressionValidationException {
		Pairing pairing = PairingImpl.createPairing(p1, p2);
		
		if(! new PathExpressionValidator(pairing, schema).getValidation()) 
			throw new PathExpressionValidationException(pairing);
		
		return pairing;
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#projection(query.PathExpression, java.util.List)
	 */
	@Override
	public Projection projection(List<Attribute> selectList, List<Attribute> domain) throws PathExpressionValidationException {
		Projection projection = ProjectionImpl.createProjection(selectList, domain);
		
		if(! new PathExpressionValidator(projection, schema).getValidation()) 
			throw new PathExpressionValidationException(projection);
		
		return projection;
	}

}
