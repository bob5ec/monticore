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

import java.util.List;
import java.util.Optional;

import de.monticore.grammar.grammar._ast.ASTClassProd;
import de.monticore.grammar.grammar._ast.ASTRuleReference;
import de.monticore.grammar.grammar._cocos.GrammarASTClassProdCoCo;
import de.monticore.languages.grammar.MCRuleSymbol;
import de.se_rwth.commons.logging.Log;

/**
 * Checks that nonterminals only extends abstract or normal nonterminals.
 *
 * @author KH
 */
public class NTNotExtendInterfaceOrExternalNTs implements GrammarASTClassProdCoCo {
  
  public static final String ERROR_CODE = "0xA2103";
  
  public static final String ERROR_MSG_FORMAT = " The nonterminal %s must not extend the %s nonterminal %s. " +
                                      "Nonterminals may only extend abstract or normal nonterminals.";
  
  @Override
  public void check(ASTClassProd a) {
    if (!a.getSuperRule().isEmpty()) {
      List<ASTRuleReference> superRules = a.getSuperRule();
      for(ASTRuleReference sr : superRules){
        Optional<MCRuleSymbol> ruleSymbol = a.getEnclosingScope().get().resolve(sr.getName(), MCRuleSymbol.KIND);
        if(ruleSymbol.isPresent()){
          MCRuleSymbol r = ruleSymbol.get();
          boolean isInterface = r.getType().isInterface();
          boolean isExternal =  r.getType().isExternal();
          if(isInterface || isExternal){
            Log.error(String.format(ERROR_CODE + ERROR_MSG_FORMAT, a.getName(), isInterface? "interface": "external", r.getName()),
                    a.get_SourcePositionStart());
          }
        }
      }
    }
  }

}
