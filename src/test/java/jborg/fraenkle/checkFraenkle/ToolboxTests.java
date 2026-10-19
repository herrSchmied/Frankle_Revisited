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
    
    @Test
    public void oddFamTest() throws FraenkleException, InterruptedException
    {
    	Set<Integer> A1 = new HashSet<>(Arrays.asList(1, 2, 3));
    	Set<Integer> A2 = new HashSet<>(Arrays.asList(1, 2, 3, 6, 7, 8, 9));
    	Set<Integer> A3 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9));
    	Set<Integer> A4 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    	Set<Integer> A5 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 8, 9));
    	Set<Integer> A6 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 9));
    	Set<Integer> A7 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    	Set<Integer> A8 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
    	Set<Integer> A9 = new HashSet<>(Arrays.asList(6, 7, 8, 9));
    	Set<Integer> A10 = new HashSet<>(Arrays.asList(4, 6, 7, 8, 9));
    	Set<Integer> A11 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8, 9));
    	Set<Integer> A12 = new HashSet<>(Arrays.asList(4, 5, 8, 9));
    	Set<Integer> A13 = new HashSet<>(Arrays.asList(4, 5, 6, 8, 9));
    	Set<Integer> A14 = new HashSet<>(Arrays.asList(4, 5, 6, 7));
    	Set<Integer> A15 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
    	Set<Integer> A16 = new HashSet<>(Arrays.asList(1, 6, 7, 8, 9));
    	Set<Integer> A17 = new HashSet<>(Arrays.asList(1, 4, 6, 7, 8, 9));
    	Set<Integer> A18 = new HashSet<>(Arrays.asList(1, 4, 5, 6, 7, 8, 9));
    	Set<Integer> A19 = new HashSet<>(Arrays.asList(2, 4, 5, 8, 9));
    	Set<Integer> A20 = new HashSet<>(Arrays.asList(2, 4, 5, 6, 8, 9));
    	Set<Integer> A21 = new HashSet<>(Arrays.asList(2, 4, 5, 6, 7, 8, 9));
    	Set<Integer> A22 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
    	Set<Integer> A23 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7, 8));
    	Set<Integer> A24 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9));
    	Set<Integer> A25 = new HashSet<>(Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9));
    	Set<Integer> A26 = new HashSet<>(Arrays.asList(1, 3, 4, 5, 6, 7, 8, 9));
    	Set<Integer> A27 = new HashSet<>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9));
    	
    	Set<Set<Integer>> oddFam = new HashSet<>();
    	
    	oddFam.add(A1);oddFam.add(A2);oddFam.add(A3);oddFam.add(A4);oddFam.add(A5);
    	oddFam.add(A6);oddFam.add(A7);oddFam.add(A8);oddFam.add(A9);    	
    	oddFam.add(A10);oddFam.add(A11);oddFam.add(A12);oddFam.add(A13);
    	oddFam.add(A14);oddFam.add(A15);oddFam.add(A16);oddFam.add(A17);
    	oddFam.add(A18);oddFam.add(A19);oddFam.add(A20);oddFam.add(A21);
    	oddFam.add(A22);oddFam.add(A23);oddFam.add(A24);oddFam.add(A25);
    	oddFam.add(A26);oddFam.add(A27);
    	
    	if(Toolbox.isFamily(oddFam))System.out.println("Odd fam is a Fam!");
    	else System.out.println("No Odd fam is not a Fam!");
    	
    	Set<Set<Integer>> basis = Toolbox.getBasis(oddFam);
    	
    	System.out.println("Odd fam Basis.");
    	for(Set<Integer> set: basis)System.out.println(set);
    	System.out.println("");
    	Thread.sleep(1000);

    	assert(Toolbox.createFamily(basis).equals(oddFam));
    	
    	for(int n=1;n<10;n++)
    	{
    		double a = Toolbox.howAbundant(n, oddFam);
    		System.out.println(n + " is at this rate: " + a);
    		
        	
        	Thread.sleep(1000);
    	}
    	System.out.println("");
    }
}