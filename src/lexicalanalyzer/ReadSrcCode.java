package lexicalanalyzer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadSrcCode {
    private LinkedList<String> srcCodeList;  // 每一个节点为一行代码的链表

    /**
     * TODO 读取给定的源代码文件，并将其每一行作为一个链表的一个节点
     * @param fileName 源代码文件路径
     * @throws IOException
     */
    ReadSrcCode(String fileName) throws IOException {
        srcCodeList = new LinkedList<>();

        Scanner s = new Scanner(Paths.get(fileName));
        while(s.hasNextLine()){
            srcCodeList.add(s.nextLine());
        }
    }

    /**
     * TODO 获得源代码文件生成的链表
     * @return 源代码文件对应的链表
     */
    public LinkedList<String> getSrcCodeList() {
        return srcCodeList;
    }
}
