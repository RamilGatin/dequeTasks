package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {

        String answer = "";
        int m = adjacencyMatrix.length;
        ArrayList<Integer> status = new ArrayList<>();
        ArrayDeque<Integer> graph = new ArrayDeque<Integer>();
        graph.add(startIndex);
        while (status.size() < m) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                if (adjacencyMatrix[i][graph.peekFirst()]) {
                    temp.add(i);
                }
            }
            Collections.sort(temp);
            graph.addAll(temp);

            if (!status.contains(graph.peekFirst())) {
                status.add(graph.peekFirst());
                answer = answer + graph.pop() + ",";
            } else {
                graph.removeFirst();
            }
        }
        return answer.substring(0, answer.length() - 1);
    }

    @Override
    public Boolean validateBrackets(String s) {
        String openingBraces = "([{";
        String closingBraces = ")]}";
        char [] input = s.toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (Character c : input) {
            if (openingBraces.contains(c.toString())) {
                stack.add(c);
                continue;
            }
            if (closingBraces.contains(c.toString())){
                if (stack.isEmpty()){
                    return false;
                }
                if (closingBraces.indexOf(c) == openingBraces.indexOf(stack.peekLast())){
                    stack.removeLast();
                }
            }
        }
        return stack.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
            int key = 0;
            ArrayList<Character> line = new ArrayList<>();
            ArrayDeque<Integer> nums = new ArrayDeque<>();
            for (int i = 0;i < s.length();i++) {
                line.add(s.charAt(i));
                if (line.get(i).isDigit(s.charAt(i))){
                    int k = Integer.parseInt(Character.toString(s.charAt(i)));
                    nums.push(k);
                } else {
                    if(key == 0){
                        key = nums.getFirst();
                        nums.pop();
                    }
                    switch(line.get(i)){
                        case '+':
                            key = nums.pop()+key;
                            break;
                        case '-':
                            key = nums.pop()-key;
                            break;
                        case '*':
                            key = nums.pop()*key;
                            break;
                        case '/':
                            key = nums.pop()/key;
                            break;
                    }
                }
            }
            return (long)key;
        }
    }

