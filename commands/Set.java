package commands;

import java.util.List;

import parameter.Parameter;
import parameter.operations.Operation;
import parameter.variables.*;

public class Set extends Command {
	
	private String name;
	private Parameter valueVar;
	
	/** Sets up the variable name and value.
	 * 
	 * @param parameters which should be the name of the variable (String) followed by the value (Variable Value)
	 * @throws InvalidArgumentAmountException if there aren't exactly 2 parameters given.
	 * @throws InvalidArgumentTypeException if the name isn't a String
	 */
	public Set(List<Parameter> parameters) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		super(parameters);	
		if(parameters.size() != 2)
			throw new InvalidArgumentAmountException("set", 2);
			
		//check that, if the name variable is new, then it's value is string.
		if(((Variable) parameters.get(0)).getName() == null) {
			if( !((parameters.get(0) instanceof StringVariable)) )
				throw new InvalidArgumentTypeException("Set's name variable", "String");
		}
			
		//check that the values of A and B are the same
		else {
			if( parameters.get(0).getClass() != parameters.get(1).getClass() ) 
				throw new InvalidArgumentTypeException("For a pre-existing name variable, the values of both parameters in Set aren't the same class");
		}
		
		this.name = ((Variable) parameters.get(0)).getName() == null ? ((StringVariable) parameters.get(0)).getValue() : ((Variable) parameters.get(0)).getName();
		this.valueVar = parameters.get(1);
	}
	
	private Variable getVarType(String name, Object value) {
		if(value instanceof Number) 
			return new NumberVariable(name, (Number) value);
		if(value instanceof Boolean)
			return new BooleanVariable(name, (Boolean) value);
		if(value instanceof String)
			return new StringVariable(name, (String) value);
		return null;
	}
	
	/** Creates a new {@link Variable} based on the parameters given.
	 */
	@Override
	public void execute() {
		if(valueVar instanceof Operation) {
			((Operation) this.valueVar).assess();
			this.valueVar = (((Operation) this.valueVar).getResult());
		}
		Variable var = getVarType(name, ((Variable) valueVar).getValue());
	}
}