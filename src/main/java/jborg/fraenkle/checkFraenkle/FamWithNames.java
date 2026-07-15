package jborg.fraenkle.checkFraenkle;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FamWithNames
{

	public final Set<Set<Integer>> fam;
	public final Map<String, Set<Integer>> mapOfNames;

	public FamWithNames(Set<Set<Integer>> fam, Map<String, Set<Integer>> mapOfNames) throws FraenkleException
	{
		
		if(!Toolbox.isFamily(fam))throw new FraenkleException("Input for Fam is no Fam.");
		if(fam.size()!=mapOfNames.size())throw new FraenkleException("map does not match Fam.");

		for(Set<Integer> member: mapOfNames.values())
		{
			if(!fam.contains(member))throw new FraenkleException("map does not match Fam.");
		}

		this.fam = fam;
		this.mapOfNames = mapOfNames;
	}
	
	public Set<Integer> abundantElements()
	{

		Set<Integer> head = Toolbox.getHead(fam);

		List<Integer> list = head.stream().filter((i)->
		{
			try
			{
				return Toolbox.isAbundant(i, fam);
			}
			catch (FraenkleException e)
			{
				e.printStackTrace();
			}
		
			return false;
		}).toList();

		Set<Integer> set = new HashSet<>(list);

		return set;
	}
}