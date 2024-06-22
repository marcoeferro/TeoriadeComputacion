package tc.AutomataFinitoNoDeterministico;

public class REGEXtoAFN {

    public AFN parseExpression(String regex, int[] index) {
        char c = regex.charAt(index[0]);
        index[0]++;
        switch (c) {
            case '*':
                AFN afn = parseExpression(regex, index);
                return new AFNUtils().kleeneStar(afn);
            case '|':
                AFN afn1 = parseExpression(regex, index);
                AFN afn2 = parseExpression(regex, index);
                return new AFNUtils().union(afn1, afn2);
            case '.':
                AFN afn3 = parseExpression(regex, index);
                AFN afn4 = parseExpression(regex, index);
                return new AFNUtils().concatenate(afn3, afn4);
            case '(':
                AFN afn5 = parseExpression(regex, index);
                if (regex.charAt(index[0]) != ')') {
                    throw new IllegalArgumentException("Missing closing parenthesis at position " + index[0]);
                }
                index[0]++;
                return afn5;
            default:
                return new AFN(c);
        }
    }
}