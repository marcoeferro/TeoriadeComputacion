package tc.AutomataFinitoNoDeterministico;

import java.util.*;

public class RegexToAfnAlt {

	public Queue<Character> StringtoQueue(String cadena) {
		Queue<Character> cola = new LinkedList<>();
		
		for(Character ch : cadena.toCharArray()) {
			cola.offer(ch);
		}
		cola.offer('#');
		/*while(!cola.isEmpty()) {
			System.out.println(cola.poll());
		}
		System.out.println(cola.isEmpty());*/
		
		return cola;
	}
	
	public AFN parseExpression(Queue<Character> regex) {
		if(regex.isEmpty()) throw new IllegalArgumentException("Expresion vacia!");
		Character c = regex.poll();
		switch(c) {
			case '*':
				AFN afn = parseExpression(regex); //pasamos la cola sin el *
				return new AFNUtils().kleeneStar(afn);
			case '|':
				AFN afn1 = parseExpression(regex);
				AFN afn2 = parseExpression(regex);
				return new AFNUtils().union(afn1, afn2);
			case '.':
				AFN afn3 = parseExpression(regex);
				AFN afn4 = parseExpression(regex);
				return new AFNUtils().concatenate(afn3, afn4);
			case '(':
				AFN afn5 = parseExpression(regex);
				do {
					c=regex.poll();
					if(regex.isEmpty()) throw new IllegalArgumentException("Falta un parentesis de cierre!");
				} while(c!=')');
				return afn5;
			case '#':
				return new AFN('#');
			default:
				return new AFN(c);
		}
	}
}
