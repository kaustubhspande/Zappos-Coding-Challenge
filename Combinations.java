package zappos;

import java.util.ArrayList;

public class Combinations {
	static ArrayList<ArrayList<Integer>> megaList = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> printCombination(int arr[], int n, int r)
	{
	    // A temporary array to store all combination one by one
	    int[] data = new int [r];
	 
	    // Print all combination using temprary array 'data[]'
	    combinationUtil(arr, data, 0, n-1, 0, r);
	    return megaList;
	}
	
	static ArrayList combinationUtil(int arr[], int data[], int start, int end, int index, int r)
	{
	    // Current combination is ready to be printed, print it
		ArrayList<Integer> list = new ArrayList<Integer>();
		
	    if (index == r)
	    {
	        for (int j=0; j<r; j++){
	            //System.out.print(" "+ data[j]);
	            list.add(data[j]);
	        }
	        megaList.add(list);
	        //System.out.println(megaList);
	        //System.out.println("\n");
	        return megaList;
	    }
	 
	    // replace index with all possible elements. The condition
	    // "end-i+1 >= r-index" makes sure that including one element
	    // at index will make a combination with remaining elements
	    // at remaining positions
	    for (int i=start; i<=end && end-i+1 >= r-index; i++)
	    {
	        data[index] = arr[i];
	        combinationUtil(arr, data, i+1, end, index+1, r);
	    }
		return megaList;
	    
	}
	public static void main(String[] args)
	{
	    int arr[] = {1, 2, 3, 4, 5};
	    int r = 3;
	    int n = arr.length;
	    printCombination(arr, n, r);
	}
	
}
