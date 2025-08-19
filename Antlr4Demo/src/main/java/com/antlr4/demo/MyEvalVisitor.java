package com.antlr4.demo;

import com.antlr4.demo.gen.LabelExprParser;
import com.antlr4.demo.gen.LabelExprBaseVisitor;

import java.util.HashMap;
import java.util.Map;

public class MyEvalVisitor extends LabelExprBaseVisitor<Integer> {

    /**
     * 保存运行中的结果
     */
    Map<String, Integer> memory = new HashMap<>();

    @Override
    public Integer visitAssign(LabelExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Integer visitPrintExpr(LabelExprParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }

    @Override
    public Integer visitInt(LabelExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitId(LabelExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        return 0;
    }

    @Override
    public Integer visitMulDiv(LabelExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabelExprParser.MUL) {
            return left * right;
        } else {
            return left / right;
        }
    }

    @Override
    public Integer visitAddSub(LabelExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabelExprParser.Add) {
            return left + right;
        } else {
            return left - right;
        }
    }

    @Override
    public Integer visitParens(LabelExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}