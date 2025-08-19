package com.micrometer.demo;

import com.micrometer.demo.gen.LabelExprParser;
import com.micrometer.demo.gen.LabelExprBaseListener;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class MyEvalListener extends LabelExprBaseListener {

    // 定义一个栈（先进后出），存放中间计算结果
    private Stack<Integer> result = new Stack<Integer>();

    private Map<String, Integer> map = new ConcurrentHashMap<>();

    @Override
    public void enterProg(LabelExprParser.ProgContext ctx) {

    }

    @Override
    public void exitProg(LabelExprParser.ProgContext ctx) {

    }

    @Override
    public void enterPrintExpr(LabelExprParser.PrintExprContext ctx) {

    }

    @Override
    public void exitPrintExpr(LabelExprParser.PrintExprContext ctx) {
        if(!result.isEmpty()) {
            System.out.println(result.pop());
        }
    }

    @Override
    public void enterAssign(LabelExprParser.AssignContext ctx) {
    }

    @Override
    public void exitAssign(LabelExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        if(!result.isEmpty()) {
            map.put(id, result.pop());
        }
    }

    @Override
    public void enterBlank(LabelExprParser.BlankContext ctx) {

    }

    @Override
    public void exitBlank(LabelExprParser.BlankContext ctx) {

    }

    @Override
    public void enterParens(LabelExprParser.ParensContext ctx) {

    }

    @Override
    public void exitParens(LabelExprParser.ParensContext ctx) {

    }

    @Override
    public void enterMulDiv(LabelExprParser.MulDivContext ctx) {

    }

    @Override
    public void exitMulDiv(LabelExprParser.MulDivContext ctx) {
        if(result.size() >=2) {
            int right = result.pop();
            int left = result.pop();
            if (ctx.op.getType() == LabelExprParser.MUL) {
                result.push(left*right);
            } else {
                result.push(left/right);
            }
        }
    }

    @Override
    public void enterAddSub(LabelExprParser.AddSubContext ctx) {

    }

    @Override
    public void exitAddSub(LabelExprParser.AddSubContext ctx) {
        if(result.size() >= 2) {
            int right = result.pop();
            int left = result.pop();
            if (ctx.op.getType() == LabelExprParser.Add) {
                result.push(left + right);
            } else {
                result.push(left + right);
            }
        }

    }

    @Override
    public void enterId(LabelExprParser.IdContext ctx) {

    }

    @Override
    public void exitId(LabelExprParser.IdContext ctx) {
        if(map.containsKey(ctx.ID().getText())) {
            result.push(map.get(ctx.ID().getText()));
        }
    }

    @Override
    public void enterInt(LabelExprParser.IntContext ctx) {

    }

    @Override
    public void exitInt(LabelExprParser.IntContext ctx) {
        result.push(Integer.valueOf(ctx.INT().getText()));
    }

}