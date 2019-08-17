package commands;

import java.util.List;

import variables.*;

public class Set extends Command {
	
	private String name;
	private Variable valueVar;
	
	/** Sets up the variable name and value.
	 * 
	 * @param parameters which should be the name of the variable (String) followed by the value (Variable Value)
	 * @throws InvalidArgumentAmountException if there aren't exactly 2 parameters given.
	 * @throws InvalidArgumentTypeException if the name isn't a String
	 */
	public Set(List<Variable> parameters) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		super(parameters);	
		if(parameters.size() != 2)
			throw new InvalidArgumentAmountException("set", 2);
			
		//check that the if the name variable is a new variable then the value is a string
		if(parameters.get(0).getName() == null) {
			if( !(parameters.get(0).getValue() instanceof String) )
				throw new InvalidArgumentTypeException("Set's name variable", "String");
		}
			
		//check that the values of A and B are the same
		else {
			if(parameters.get(0).getValue().getClass() != parameters.get(1).getValue().getClass()) 
				throw new InvalidArgumentTypeException("For a pre-existing name variable, the values of both parameters in Set aren't the same class");
		}
		
		this.name = parameters.get(0).getName() == null ? (String) parameters.get(0).getValue() : parameters.get(0).getName();
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
		Variable var = getVarType(name, valueVar.getValue());
	}
}