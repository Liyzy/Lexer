package lexicalanalyzer;

import java.util.LinkedHashMap;

public class LexicalToken {
    private LinkedHashMap<String, Integer> LexicalTokenMap;

    /**
     * TODO 初始化词法记号表
     */
    LexicalToken() {
        LexicalTokenMap = new LinkedHashMap<>();
        LexicalTokenMap.put("#", 0);
        LexicalTokenMap.put("for", 1);
        LexicalTokenMap.put("if", 2);
        LexicalTokenMap.put("then", 3);
        LexicalTokenMap.put("else", 4);
        LexicalTokenMap.put("while", 5);
        LexicalTokenMap.put("do", 6);
        LexicalTokenMap.put("ID", 10);  // 一般标识符
        LexicalTokenMap.put("NUM", 11);  // 数字
        LexicalTokenMap.put("+", 13);
        LexicalTokenMap.put("-", 14);
        LexicalTokenMap.put("*", 15);
        LexicalTokenMap.put("/", 16);
        LexicalTokenMap.put(":", 17);
        LexicalTokenMap.put(":=", 18);
        LexicalTokenMap.put("<", 20);
        LexicalTokenMap.put("<>", 21);
        LexicalTokenMap.put("<=", 22);
        LexicalTokenMap.put(">", 23);
        LexicalTokenMap.put(">=", 24);
        LexicalTokenMap.put("=", 25);
        LexicalTokenMap.put(";", 26);
        LexicalTokenMap.put("(", 27);
        LexicalTokenMap.put(")", 28);
        LexicalTokenMap.put("until", 29);
        LexicalTokenMap.put("int", 30);
        LexicalTokenMap.put("input", 31);
        LexicalTokenMap.put("output", 32);
    }

    /**
     * TODO 获得词法记号表
     * @return 词法记号表LexicalTokenMap
     */
    public LinkedHashMap<String, Integer> getLexicalTokenMap() {
        return LexicalTokenMap;
    }

    /**
     * TODO 根据给定的词法单元，返回对应的词法记号
     * @param word 词法单元
     * @return 此法记号token
     */
    public int getToken(String word) {
        if (word == null | word.equals(""))
            return -1;
        if(!LexicalTokenMap.keySet().contains(word))
            return -1;
        return LexicalTokenMap.get(word);
    }

    /**
     * TODO 判断一个标识符是否为关键字
     * @param word 标识符
     * @return boolean值
     */
    public static boolean isKeyWord(String word) {
        if (word == null | word.equals(""))
            return false;
        String[] keyWord = {"int", "for", "if", "then", "else",
                "do", "while", "until", "input", "output"};
        for (String aWord : keyWord)
            if (word.equals(aWord))
                return true;
        return false;
    }
}
