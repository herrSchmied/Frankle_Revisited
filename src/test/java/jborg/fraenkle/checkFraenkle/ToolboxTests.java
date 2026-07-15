package jborg.fraenkle.checkFraenkle;

import java.util.Arrays;
import java.util.HashSet;
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
        assert(fam.size()==(int)Math.pow(2, basis.size())-1);

        Set<Set<Integer>> basisFound = Toolbox.getBasis(fam);
        
        assert(basis.equals(basisFound));
        
        System.out.println(basisFound);
        
        Set<Integer> mem1 = new HashSet<>(Arrays.asList(0,1,2,3));
        Set<Integer> mem2 = new HashSet<>(Arrays.asList(4));
        Set<Integer> mem3 = new HashSet<>(Arrays.asList(5,6));
        Set<Integer> mem4 = new HashSet<>(Arrays.asList(7,8));
        Set<Integer> mem5 = new HashSet<>(Arrays.asList(7));
        Set<Integer> mem6 = new HashSet<>(Arrays.asList(8));

        basis = new HashSet<>(Arrays.asList(mem1, mem2, mem3, mem4, mem5, mem6));
        fam = Toolbox.createFamily(basis);
        
        Set<Set<Integer>> realBasis = new HashSet<>(Arrays.asList(mem1, mem2, mem3, mem5, mem6));
        basisFound = Toolbox.getBasis(fam);
        
        assert(basisFound.equals(realBasis));
        
        
        basis = Toolbox.getChainBasisWithGaps(10, 4);
        System.out.println(basis);
        fam = Toolbox.createFamily(basis);
        System.out.println("Fam Size: " + fam.size());
        
        basis = Toolbox.getChainBasis(0, 11);
        System.out.println(basis);
        fam = Toolbox.createFamily(basis);
        System.out.println("Fam Size: " + fam.size());    
        System.out.println("Fam: " + fam);
        
        
        for(Integer member: Toolbox.getHead(fam))
        {
        	System.out.println("Member of Members:" + member);
        	System.out.println("Abundance Rate: " + Toolbox.howAbundant(member, fam));
        	System.out.println("Is Abundant: " + Toolbox.isAbundant(member, fam));
        }
        
       	System.out.println("Head: " + Toolbox.getHead(fam));

    }
}
