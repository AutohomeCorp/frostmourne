package com.autohome.frostmourne.monitor.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * expression parser only for && and ||
 *
 * @author Aping
 * @since 2022/5/4 20:47
 */
public class ExpressionParser {

    public static final String TRUE = "true";

    public static final String FALSE = "false";

    enum Op {
        /**
         * and
         */
        AND("&&"),

        /**
         * or
         */
        OR("||");

        private final String operator;

        Op(String operator) {
            this.operator = operator;
        }

        public String getOperator() {
            return operator;
        }

        public static Op getByOperator(String operator) {
            if (operator == null || operator.length() == 0) {
                return null;
            }
            for (Op op : Op.values()) {
                if (op.getOperator().equalsIgnoreCase(operator)) {
                    return op;
                }
            }
            return null;
        }
    }

    private static final Map<String, Integer> OPERATOR_MAP;

    static {
        OPERATOR_MAP = new HashMap<>();
        OPERATOR_MAP.put("(", 1);
        OPERATOR_MAP.put(")", 1);
        OPERATOR_MAP.put("&&", 2);
        OPERATOR_MAP.put("||", 3);
    }

    /**
     * 计算表达式
     */
    public static boolean calculate(String expression, Map<String, String> eventMd5, Map<String, String> oldEventMd5) {
        Stack<String> stack = new Stack<>();
        ArrayList<String> tokens = generateReversePolish(getTokens(expression));
        if (tokens.size() == 1){
            stack.push( equalsMd5(tokens.get(0), eventMd5, oldEventMd5));
        }else {
            for (String token : tokens) {
                // 如果是操作符则入栈
                if (!OPERATOR_MAP.containsKey(token)) {
                    stack.push(token);
                } else {
                    String right = equalsMd5(stack.pop(), eventMd5, oldEventMd5);
                    String left = equalsMd5(stack.pop(), eventMd5, oldEventMd5);
                    stack.push(operate(left, right, token).toString());
                }
            }
        }
        if (stack.size() == 1) {
            return TRUE.equals(stack.pop());
        }
        return false;
    }

    private static ArrayList<String> getTokens(String pattern) {
        String[] splitArr = pattern.trim().split(" ");
        ArrayList<String> tokens = new ArrayList<>();
        for (String split : splitArr) {
            String trim = split.trim();
            if (StringUtils.isNotBlank(trim)) {
                tokens.add(trim);
            }
        }
        return tokens;
    }

    private static String equalsMd5(String key, Map<String, String> eventMd5, Map<String, String> oldEventMd5) {
        if (eventMd5.get(key) == null && oldEventMd5.get(key) == null) {
            return TRUE;
        }
        if (eventMd5.get(key) == null || oldEventMd5.get(key) == null) {
            return FALSE;
        }
        if (eventMd5.get(key).equals(oldEventMd5.get(key))) {
            return TRUE;
        }

        return FALSE;
    }

    private static ArrayList<String> generateReversePolish(ArrayList<String> tokens) {
        Stack<String> operatorStack = new Stack<>();
        Stack<String> numberStack = new Stack<>();
        for (String token : tokens) {
            // 如果是操作数，直接插入到操作数stack
            if (!OPERATOR_MAP.containsKey(token)) {
                numberStack.push(token);
            } else {
                if (operatorStack.empty()) {
                    operatorStack.push(token);
                } else {
                    // 如果是右括号 则要找到左括号 并且一次入栈到 操作数栈
                    if (")".equals(token)) {
                        String popToken;
                        while (!"(".equals(popToken = operatorStack.pop())) {
                            numberStack.push(popToken);
                        }
                        continue;
                    }
                    String preOperator = operatorStack.peek();
                    // 如果之前的操作符是( ，则不用比较优先级 当前操作符直接入栈
                    if ("(".equals(preOperator)) {
                        operatorStack.push(token);
                    }
                    // 比较操作符优先级， 如果该操作符优先级大于等于 ， 则直接入栈
                    else if (OPERATOR_MAP.get(token) <= OPERATOR_MAP.get(preOperator)) {
                        operatorStack.push(token);
                    }
                    // 如果该操作符优先级 小于， 则在将该操作符如栈之前 要把栈顶操作符 弹出 插入 操作数栈
                    else {
                        numberStack.push(operatorStack.pop());
                        operatorStack.push(token);
                    }
                }
            }
        }
        while (!operatorStack.empty()) {
            numberStack.push(operatorStack.pop());
        }
        String[] array = numberStack.toArray(new String[] {});
        return new ArrayList<>(Arrays.asList(array));
    }

    private static Boolean operate(String left, String right, String op) {
        Op operator = Op.getByOperator(op);
        if (operator == null) {
            return false;
        }
        switch (operator) {
            case AND:
                if (!TRUE.equals(left)) {
                    return false;
                }
                return TRUE.equals(right);
            case OR:
                if (TRUE.equals(left)) {
                    return true;
                }
                return TRUE.equals(right);
            default:
                return false;
        }
    }

    public static void main(String[] args) {

        Map<String, String> metricData = new HashMap<>();
        metricData.put("a", "a");
        metricData.put("b", "b");
        metricData.put("c", "c");

        Map<String, String> oldMetricData = new HashMap<>();
        oldMetricData.put("a", "a");
        oldMetricData.put("b", "b");
        oldMetricData.put("c", "diff");

        System.out.println(calculate("b && c", metricData, oldMetricData));
    }
}
