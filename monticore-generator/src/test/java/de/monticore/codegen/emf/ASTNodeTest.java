/*
 * Copyright (c) 2016 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package de.monticore.codegen.emf;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.monticore.emf._ast.ASTENodePackage;
import de.monticore.emf.util.AST2ModelFiles;
import de.se_rwth.commons.logging.Log;
import de.se_rwth.commons.logging.Slf4jLog;

/**
 * TODO: Write me!
 *
 * @author (last commit) $Author$
 * @version $Revision$, $Date$
 */
public class ASTNodeTest {
  
  @BeforeClass
  public static void setup() {
    Slf4jLog.init();
    Log.enableFailQuick(false);
  }
  
  /**
   * TODO: Write me!
   * 
   * @param args
   */
  @Test
  public void testSerializing() {
    
    try {
      AST2ModelFiles.get().serializeAST(ASTENodePackage.eINSTANCE);
    }
    catch (IOException e) {
      fail("Should not reach this, but: " + e);
    }
    
  }
  
}
