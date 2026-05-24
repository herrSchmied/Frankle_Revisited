package jborg.fraenkle.checkFraenkle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Toolbox
{

	public static boolean isFamily(Set<Set<Integer>> V) throws FraenkleException
	{
		if(V==null)throw new FraenkleException("Can't test null.");

		for(Set<Integer> s: V)
		{
			Set<Integer> s1 = new HashSet<>();
			s1.addAll(s);
			
			for(Set<Integer> s2: V)
			{
				s1.addAll(s2);
				if(!V.contains(s1))return false;
			}
		}
		
		return true;
	}
	
	public static Set<Set<Integer>> createFamily(Set<Set<Integer>> origin) throws FraenkleException
	{

		if(origin==null)throw new FraenkleException("Can't create from null.");

		Set<Set<Integer>> creation = new HashSet<>();

		for(Set<Integer> s: origin)
		{

			creation.add(s);

			Set<Integer> s1 = new HashSet<>();
			s1.addAll(s);

			for(Set<Integer> s2: origin)
			{
				s1.addAll(s2);
				creation.add(s1);
			}
		}
		
		assert(isFamily(creation));

		return creation;
	}
	
	public static int getSizeOfSmallestMember(Set<Set<Integer>> fam) throws FraenkleException
	{
		if(fam==null)throw new FraenkleException("Family can't be null");

		int smallest = getHead(fam).size();
		
		for(Set<Integer> s: fam)
		{
			if(s.size()<smallest)smallest=s.size();
		}

		return smallest;
	}

	public static Set<Set<Integer>> getAllOfSizeN(int size, Set<Set<Integer>> fam)
	{
		Set<Set<Integer>> output = new HashSet<>();
		
		for(Set<Integer> s: fam)
		{
			if(s.size()==size)output.add(s);
		}

		return output;
	}
	
	public static Set<Set<Integer>> getBasis(Set<Set<Integer>> fam, Set<Set<Integer>> basis) throws FraenkleException
	{
		
		if(basis==null)throw new FraenkleException("Basis can't be null");
		if(fam==null)throw new FraenkleException("Family can't be null");
		
		
		Set<Set<Integer>> tmpFam = createFamily(basis);
		
		if(tmpFam.equals(fam))return basis;
	
		Set<Set<Integer>> rest = metaSetMinusMetaSet(fam, tmpFam);
		
		int min = getSizeOfSmallestMember(rest);
		basis.addAll(getAllOfSizeN(min, rest));
		
		basis.addAll(getBasis(fam, basis));
		
		return basis;

	}
	
	
	public static Set<Set<Integer>> metaSetMinusMetaSet(Set<Set<Integer>> a, Set<Set<Integer>> b)
	{
		Set<Set<Integer>> difference = new HashSet<>();
		
		for(Set<Integer> set: a)
		{
			if(!b.contains(set))difference.add(set);
		}
		
		return difference;
	}
	
	public static boolean isInMetaSet(int e, Set<Set<Integer>> metaSet)
	{
		
		for(Set<Integer> s: metaSet)if(s.contains(e))return true;
		
		return false;
	}
	
	public static boolean isAbundant(int e, Set<Set<Integer>> fam) throws FraenkleException
	{
		if(fam==null)throw new FraenkleException("Family can't be null");

		return howAbundant(e, fam)>=0.5;
	}

	public static double howAbundant(int e, Set<Set<Integer>> fam) throws FraenkleException
	{

		if(fam==null)throw new FraenkleException("Family can't be null");

		if(fam.isEmpty())return 0.0;

		int cnt = 0;
		for(Set<Integer> set: fam)
		{
			if(set.contains(e))cnt++;
		}

		return (double)fam.size()/(double)cnt;
	}
		
	public static Set<Set<Integer>> getMinimalExpandingBasis(int min, int max) throws FraenkleException
	{
		
    	if(min>max) throw new FraenkleException("Min can't be larger than Max.");

    	Set<Set<Integer>> output = new HashSet<>();
    	
    	Set<Integer> formerMember = new HashSet<>();
    	
    	for(int n=min;n<=max;n++)
    	{
    		
    		Set<Integer> currentMember = new HashSet<>();
    		if(n==min)
    		{
    			formerMember.add(min);
    			output.add(formerMember);
    		}
    		else
    		{
    			currentMember.addAll(formerMember);
    			currentMember.add(n);
    			output.add(currentMember);
    			formerMember = currentMember;
    		}
    	}
    
    	assert(isFamily(output));

    	return output;
	}
	
	public static Set<Set<Integer>> getMaximalExpandingBasis(int min, int max) throws FraenkleException
	{
    	if(min>max) throw new FraenkleException("Min can't be larger than Max.");
    	
    	Set<Set<Integer>> output = new HashSet<>();
    
    	for(int n=min;n<=max;n++)
    	{
    		Set<Integer> famMember = new HashSet<>();
    		
    		famMember.add(n);
    		output.add(famMember);
    	}
    	
    	
    	return output;

	}
    public static Set<Set<Integer>> getStarBasis(int omnipresent, int min, int max) throws FraenkleException
    {
    	
    	if(omnipresent>=min&&omnipresent<=max) throw new FraenkleException("Omnipresent Element can't be in Range.");
    	if(min>max) throw new FraenkleException("Min can't be larger than Max.");

    	Set<Set<Integer>> output = new HashSet<>();
    
    	for(int n=min;n<=max;n++)
    	{
    		Set<Integer> famMember = new HashSet<>();
    		
    		famMember.add(omnipresent);
    		famMember.add(n);
    		
    		output.add(famMember);
    	}

    	return output;
    }
    
    public static Set<Set<Integer>> getChainBasis(int min, int max) throws FraenkleException
    {
    	
    	if(min>max) throw new FraenkleException("Min can't be larger than Max.");

    	Set<Set<Integer>> output = new HashSet<>();

     	for(int n=min;n<max;n++)
    	{

     		Set<Integer> famMember = new HashSet<>();

    		famMember.add(n);
    		famMember.add(n+1);

    		output.add(famMember);
    	}

    	return output;
    }
    
    public static Set<Set<Integer>> getRingBasis(int min, int max) throws FraenkleException
    {
    	
    	if(min>max) throw new FraenkleException("Min can't be larger than Max.");

    	Set<Set<Integer>> output = new HashSet<>();

    	for(int n=min;n<max;n++)
    	{
    		
    	
    	   	Set<Integer> famMember = new HashSet<>();

    	   	famMember.add(n);
    		famMember.add(n+1);

    		output.add(famMember);
    	}
    	
       	Set<Integer> famMember = new HashSet<>();
	   	famMember.add(max);
		famMember.add(min);

		output.add(famMember);

    	return output;
    }
    
    public static Set<Integer> getHead(Set<Set<Integer>> fam)
    {
    	
    	int size = 0;
    	Set<Integer> largest = new HashSet<>();
    	for(Set<Integer> famMember: fam)
    	{
    		if(famMember.size()>size)
    		{
    			largest.clear();
    			largest.addAll(famMember);
    		}
    	}
    	
    	return largest;
    }
    
    public static Map<Integer, Double> mapOfAbundanceRates(Set<Set<Integer>> fam) throws FraenkleException
    {

    	if(fam==null)throw new FraenkleException("Can't rate content of null.");

    	Map<Integer, Double> map = new HashMap<>();
    	
    	Set<Integer> head = getHead(fam);
    	
    	for(Integer e: head)
    	{
    		
    		double abundanceRate = howAbundant(e, fam);
    		map.put(e, abundanceRate);
    	}

		return map;
    }
}