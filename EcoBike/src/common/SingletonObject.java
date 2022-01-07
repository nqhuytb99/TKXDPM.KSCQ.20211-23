package common;

import java.util.Stack;

import views.screen.BaseScreenHandler;

public class SingletonObject {
	private static volatile SingletonObject instance;
	 
    private SingletonObject() {}
 
    public static SingletonObject getInstance() {
        if (instance == null) {
            synchronized (SingletonObject.class) {
                                if (instance == null) {
                    instance = new SingletonObject();
                }
            }
        }
        return instance;
    }
    
    private Stack<BaseScreenHandler> stack = new Stack<>();
    private SearchQueryInfomation searchQueryInfomation = null;
    
    public BaseScreenHandler getPreviousScreen() {
    	if(stack.isEmpty()) return null;
    	return stack.pop();
    }
    
    public boolean addScreen(BaseScreenHandler screen) {
    	if(screen == null) return false;
    	stack.add(screen);
    	return true;
    }
    
    public void clearScreen() {
    	stack.clear();
    }

	public SearchQueryInfomation getSearchQueryInfomation() {
		return searchQueryInfomation;
	}

	public void setSearchQueryInfomation(SearchQueryInfomation searchQueryInfomation) {
		this.searchQueryInfomation = searchQueryInfomation;
	}
}
