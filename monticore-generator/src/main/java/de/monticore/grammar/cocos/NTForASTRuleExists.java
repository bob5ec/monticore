/*
 * ******************************************************************************
 * MontiCore Language Workbench
 * Copyright (c) 2015, MontiCore, All rights reserved.
 *
 * This project is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this project. If not, see <http://www.gnu.org/licenses/>.
 * ******************************************************************************
 */

package de.monticore.grammar.cocos;

import java.util.Map;

import de.monticore.grammar.grammar._ast.ASTASTRule;
import de.monticore.grammar.grammar._ast.ASTMCGrammar;
import de.monticore.grammar.grammar._cocos.GrammarASTMCGrammarCoCo;
import de.monticore.languages.grammar.MCGrammarSymbol;
import de.monticore.languages.grammar.MCRuleSymbol;
import de.se_rwth.commons.logging.Log;

/**
 * Checks that nonterminal names start lower-case.
 *
 * @author KH
 */
public class NTForASTRuleExists implements GrammarASTMCGrammarCoCo {
  
  public static final String ERROR_CODE = "0xA4021";
  
  public static final String ERROR_MSG_FORMAT = " There must not exist an AST rule for the nonterminal %s" +
          " because there exists no production defining %s.";
  
  @Override
  public void check(ASTMCGrammar a) {
    MCGrammarSymbol grammarSymbol = (MCGrammarSymbol) a.getSymbol().get();
    boolean prodFound = false;
    for(ASTASTRule astrule : a.getASTRules()){
      if(grammarSymbol.getRuleWithInherited(astrule.getType()) == null){
        for(Map.Entry entry : grammarSymbol.getRulesWithInherited().entrySet()){
          MCRuleSymbol rs = (MCRuleSymbol) entry.getValue();
            if (astrule.getType().equals(rs.getType().getName())) {
              prodFound = true;
              break ;
          }
        }
        if (!prodFound) {
          Log.error(String.format(ERROR_CODE + ERROR_MSG_FORMAT, astrule.getType(), astrule.getType()),
              astrule.get_SourcePositionStart());
        }
      }
    }
  }
}
