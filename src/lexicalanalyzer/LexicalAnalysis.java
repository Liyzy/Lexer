package lexicalanalyzer;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalysis {
    /**
     *
     * @param srcCode 一行代码
     */
    public void getWordMap(String srcCode) {
        // 如果是空串或者为null或者仅有空格，则返回null
        if (srcCode == null | srcCode.equals("") | srcCode.trim().equals(""))
            return;

        String regex_ID = "[a-zA-Z]([a-zA-Z0-9])*";  // 匹配标识符，包括关键字和一般标识符
        String regex_NUM = "[0-9][0-9]*";  // 匹配数字
        Pattern pattern;
        Matcher matcher;

        LexicalToken lextok = new LexicalToken();
        while (!srcCode.isEmpty()) {
            String firstChar = srcCode.substring(0, 1);  // 读取字符串的第一个字符
            String type = getCharType(firstChar);
            switch (type) {
                case "LETTER":
                    pattern = Pattern.compile(regex_ID);
                    matcher = pattern.matcher(srcCode);
                    if (matcher.lookingAt()) {
                        String result = matcher.group();
                        if (LexicalToken.isKeyWord(result)) {
                            int token = lextok.getToken(result);
                            System.out.printf("<%d,%s>  ", token, result);
                        } else {
                            int token = lextok.getToken("ID");
                            System.out.printf("<%d,%s>  ", token, result);
                        }
                    }
                    srcCode = srcCode.substring(matcher.end());
                    break;
                case "NUMBER":
                    pattern = Pattern.compile(regex_NUM);
                    matcher = pattern.matcher(srcCode);
                    if (matcher.lookingAt()) {
                        String result = matcher.group();
                        int token = lextok.getToken("NUM");
                        System.out.printf("<%d,%s>  ", token, result);
                    }
                    srcCode = srcCode.substring(matcher.end());
                    break;
                case "BLANK":
                    srcCode = srcCode.substring(1);
                    break;
                case "SPECIAL":
                    if (srcCode.length() > 1) {
                        String secondChar = srcCode.substring(1, 2);
                        String result;
                        LinkedHashMap tokenMap = lextok.getLexicalTokenMap();
                        Set set = tokenMap.keySet();
                        result = firstChar + secondChar;
                        if (getCharType(secondChar).equals("SPECIAL") && set.contains(result)) {
                                int token = lextok.getToken(result);
                                System.out.printf("<%d,%s>  ", token, result);
                                srcCode = srcCode.substring(2);
                        }else {
                            result = firstChar;
                            int token = lextok.getToken(result);
                            System.out.printf("<%d,%s>  ", token, result);
                            srcCode = srcCode.substring(1);
                        }
                    } else {  // 字符串中只有一个字符时
                        int token = lextok.getToken(srcCode);
                        System.out.printf("<%d,%s>  ", token, srcCode);
                        srcCode = srcCode.substring(1);
                    }
                    break;
            }
        }
    }

    public static String getCharType(String str) {
        String regex_Letter = "[a-zA-Z]";
        String regex_Number = "[0-9]";
        String regex_Blank = "\\s";
        Pattern pattern;

        pattern = Pattern.compile(regex_Letter);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find())
            return "LETTER";

        pattern = Pattern.compile(regex_Number);
        matcher = pattern.matcher(str);
        if (matcher.find())
            return "NUMBER";

        pattern = Pattern.compile(regex_Blank);
        matcher = pattern.matcher(str);
        if (matcher.find())
            return "BLANK";

        return "SPECIAL";
    }

    public void printWordMap(String fileName) throws IOException {
        ReadSrcCode rsc = new ReadSrcCode(fileName);
        LinkedList srcCodeList = rsc.getSrcCodeList();
        for (int i = 1; i < srcCodeList.size() + 1; i++) {
            System.out.printf("line %d:", i);
            getWordMap((String) srcCodeList.get(i - 1));
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        LexicalAnalysis lexer = new LexicalAnalysis();
        lexer.printWordMap("D:\\Program Files\\java\\java Projects\\LexicalAnalyzer\\src.txt");
    }
}
