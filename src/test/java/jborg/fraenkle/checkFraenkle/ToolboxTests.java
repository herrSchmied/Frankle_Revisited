package jborg.fraenkle.checkFraenkle;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;



public class ToolboxTests
{

    @Test
    public void testingGetBasis() throws FraenkleException
    {
        Set<Set<Integer>> basisAndFam = Toolbox.getMinimalExpandingBasis(0, 4);
        assert(Toolbox.isFamily(basisAndFam));
        
        Set<Set<Integer>> fam = Toolbox.createFamily(basisAndFam);
        
        assert(fam.equals(basisAndFam));
        
        Set<Set<Integer>> basis = Toolbox.getBasis(fam);
        
        assert(fam.equals(basis));
        
        basis = Toolbox.getMaximalExpandingBasis(0, 4);
        
        fam = Toolbox.createFamily(basis);
        
        System.out.println(basis);
        System.out.println(fam.size()+fam.toString());
    }
}
