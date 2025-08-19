package com.micrometer.demo;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import com.micrometer.demo.gen.LabelExprLexer;
import com.micrometer.demo.gen.LabelExprParser;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        // 使用自建的visitor进行遍历
        InputStream is = Main.class.getClassLoader().getResourceAsStream("expr1.txt");
        LabelExprLexer lexer = new LabelExprLexer(CharStreams.fromStream(is));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabelExprParser parser = new LabelExprParser(tokens);
        ParseTree tree = parser.prog();
        MyEvalVisitor visitor = new MyEvalVisitor();
        visitor.visit(tree);
        is.close();

        ParseTreeWalker walker = new ParseTreeWalker();
        MyEvalListener listener =  new MyEvalListener();
        walker.walk(listener, tree);
    }
}