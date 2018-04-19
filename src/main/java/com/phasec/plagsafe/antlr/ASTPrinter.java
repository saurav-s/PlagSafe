package com.phasec.plagsafe.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import com.phasec.plagsafe.antlr.generated.Python3Parser;

/**
 * Responsible for printing an AST
 * Referred from: Federico Tomassetti's blog 
 * URL: https://tomassetti.me/parsing-any-language-in-java-in-5-minutes-using-antlr-
 * for-example-python/
 * @author Tridiv
 *
 */
public class ASTPrinter {

	// member variable
	private boolean ignoringWrappers = true;

    public void astString(RuleContext ctx, StringBuilder sb) {
        buildASTString(ctx, sb, 0);
    }

    private void buildASTString(RuleContext ctx, StringBuilder sb, int indentation) {
        boolean toBeIgnored = ignoringWrappers
                && ctx.getChildCount() == 1
                && ctx.getChild(0) instanceof ParserRuleContext;
        if (!toBeIgnored) {
            String ruleName = Python3Parser.ruleNames[ctx.getRuleIndex()];
            for (int i = 0; i < indentation; i++) {
                sb.append("  ");
            }
            sb.append(ruleName);
        }
        for (int i=0;i<ctx.getChildCount();i++) {
            ParseTree element = ctx.getChild(i);
            if (element instanceof RuleContext) {
                buildASTString((RuleContext)element, sb,indentation + (toBeIgnored ? 0 : 1));
            }
        }
    }

}
