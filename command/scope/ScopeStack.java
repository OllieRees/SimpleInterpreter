package command.scope;

import java.util.Stack;

public class ScopeStack {
	
	private static Stack<ScopedCommand> scopeStack = new Stack<>();
	
	public static void pushQueue(ScopedCommand queue) {
		scopeStack.push(queue);
	}
	
	public static ScopedCommand popQueue() {
		return scopeStack.pop();
	}
	
	public static ScopedCommand peekQueue() {
		if(scopeStack.isEmpty())
			return null;
		return scopeStack.peek();
	}
	
}
