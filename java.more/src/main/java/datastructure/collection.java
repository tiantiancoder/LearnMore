package datastructure;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class collection {
	public static void main(String args[]){
		
		Map<String,String> a=new HashMap<String, String>();
		
		a.put("a", "apple");
		a.put("b", "bed");
		a.put("c", "cute");
		Set<String> s=a.keySet();
		Iterator<String> t=s.iterator();
		while(t.hasNext()){
			System.out.println(t.next());
		}
	}
}
