package bareBonesInterpreter;

import java.util.ArrayList;
import java.util.List;

import commands.*;
import parameter.Parameter;
import parameter.operations.BooleanOperation;
import parameter.operations.Operation;
import parameter.variables.*;

/** Parses commands: intermediary between {@link Command} and {@link FileReader}
 * 
 * @author Ollie Rees
 *
 */
public class CommandParser {
	
	private static VariableStore vs = new VariableStore();
	
	private static String removePreceedingSpaces(String line) {
		String regex = ("^[ \\t]+");
		return line.replaceAll(regex, "");
	}
	
	private static String removeProceedingSpaces(String line) {
		String regex = "\\s+\\Z";
		return line.replaceAll(regex, "");
	}
	
	public static Command getNextCommand() {
		String nextLine = FileParser.readNextLine();
		if(nextLine != null)
			return CommandParser.determineCommand(nextLine);
		return null;
	}
	
	/** Get the command title from the line 
	 * 
	 * @param cmdLine the line with the command title
	 * @return the command title as it is.
	 */
	private static String getCommandString(String cmdLine) {
		
		//check if there are any arguments
		int startIndex = cmdLine.indexOf("(");
		if(startIndex == -1)
			return null;
		
		//replace spaces and turn command to lower case
		String cmdStr = cmdLine.substring(0, startIndex);
		cmdStr = cmdStr.replaceAll("\\s", "");
		cmdStr = cmdStr.toLowerCase();
		return cmdStr;
	}
	
	/** Gets the parameters of the command, split by the comma.
	 * 
	 * @param cmdLine the line being checked
	 * @return the parameters as {@link Variable variables}
	 */
	public static List<Parameter> getParameters(String cmdLine) {
		List<Parameter> parameters = new ArrayList<>();
		
		String[] params = getParameterStrings(cmdLine);
		
		for (String para  : params) 
			parameters.add(CommandParser.determineParameter(para));
		
		return parameters;
	}
	
	private static String[] getParameterStrings(String cmdLine) {
		//get the arguments in the parentheses
		if(cmdLine.indexOf(")") == -1)
			return null;
		String paramStr = cmdLine.substring(cmdLine.indexOf("("), cmdLine.indexOf(")"));
		paramStr = paramStr.replace("(", "");

		//split by the comma
		return paramStr.split(",");
	}
	
	/** Determines the {@link Variable} type based on the contents of the String
	 * 
	 * @param parameter is the string being checked
	 * @return the Variable according to the parameter's features.
	 */
	public static Parameter determineParameter(String parameter) {
		
		//string formatting: remove preceeding and proceeding spaces.
		parameter = removePreceedingSpaces(parameter) == null ? parameter : removePreceedingSpaces(parameter);
		parameter = removeProceedingSpaces(parameter) == null ? parameter : removeProceedingSpaces(parameter);
		
		//is a variable
		if(vs.getVariable(parameter) != null) {
			return vs.getVariable(parameter);
		}
		
		//is a number
		try {
			Number value = Double.parseDouble(parameter);
			return new NumberVariable(null, value);
		} catch(NumberFormatException e) {}
		
		//is boolean
		if(parameter.equals("TRUE")) 
			return new BooleanVariable(null, true);
		
		if(parameter.equals("FALSE")) 
			return new BooleanVariable(null, false);
		
		//check if parameter is an infix operation
		Operation infixOperator;
		if((infixOperator = CommandParser.determineInfixOperation(parameter)) != null) {
			return infixOperator;
		}
		
		//is string
		String strVal = parameter.replaceAll("\"", "");
		if(!strVal.equals(""))
			return new StringVariable(null, strVal);
		
		return null;
	}
	
	/** Determines any commands with infix operators (aka {@link Operation})
	 * 
	 * @param parameters the parameters of the operation
	 * @return the {@link Operation} 
	 */
	private static Operation determineInfixOperation(String parameter) {
		/*Check boolean infix */
		for(String op : BooleanOperation.getBooleanOperationValues())
			if(parameter.contains(op))
				return new BooleanOperation(parameter, op);
			
		//if it wasn't an operation
		return null;
	}
	
	/** Invokes a Command with parameters based on the contents of the line being read.
	 *  The line can be blank or have an invalid command.
	 * 
	 * @param cmdLine the line with the command
	 * @return the {@link Command} that is being invoked in the line. If no command is being invoked, or the command is invalid, then null is returned.
	 */
	public static Command determineCommand(String cmdLine) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		String cmd = CommandParser.getCommandString(cmdLine);
		if(cmd == null)
			return null;
		
		List<Parameter> parameters = CommandParser.getParameters(cmdLine);	
		
		switch(cmd) {
			case "set": 
				return new Set(parameters);
			case "get":
				return new Get(parameters);
			case "add":
				return new Add(parameters);
			case "sub":
				return new Sub(parameters);
			case "if":
				return new If(parameters);
			case "while":
				return new WhileLoop(parameters);
			case "end":
				return new End(parameters);
			default:
				return null;
		}
	}
	
	public static VariableStore getVariableStore() {
		return vs;
	}
	
}