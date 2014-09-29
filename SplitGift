package zappos;

/*
 * Author: Kaustubh S Pande
 * School: Northeastern University, Boston.
 * Course & Major: Masters in Computer Science
 */

/*
 * This program has been written as a part of Zappos Code challenge question
 * for Summer Internship 2015
 * 
 */

/* ALGORITHM:
 * I  begin the program by creating maps which will be required
 * later for printing. These maps are created by parsing JSON.
 *  Function from Combinations.java class has been used to give
 *  all combinations of list of integers.
 *  Here, when user enters his budget and the number of products
 *  he wishes to gift, program is supposed to print all the values
 *  matching closely to the budget. Therefore, it is important
 *  to try all the combinations of list of integers we finally generate.
 *  So, the function printCombination from Combinations.java 
 *  accept the array, number of elements in the array and 
 *  combinations of r digits, where r is number of products user enters.
 *  These values, when given to the function printCombinations, 
 *  it returns all the combinations of numbers in the
 *  array and then they are in turn mapped to the 
 *  product name and product description they are supposed to have.
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.json.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SplitGift {
	public static void main(String[] args) throws IOException {
		Combinations comb = new Combinations();

		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your budget\n");
		int budget = in.nextInt();
		System.out.println("Please enter number of items\n");
		int noOfItems = in.nextInt();

		if (checkUserInput(budget) == false
				|| checkUserInput(noOfItems) == false) {
			System.out
					.println("Budget and Number of Items should be integer values");
		}

		String apiKey = "52ddafbe3ee659bad97fcce7c53592916a6bfd73";

		// Below URL is constructed to access API
		// API Key has been appended at the end.

		String urlStr = "http://api.zappos.com/Search?term=boots" + "&key="
				+ apiKey;
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();

		/*
		 * The first map keeps track of list of Price and Product ID given by
		 * JSON. Key value is assigned randomly. The second map keeps track of
		 * product details such as Product Name Brand Name, Price etc. These are
		 * all mapped to the product ID given by JSON.
		 */

		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Map<Integer, List<String>> productMap = new HashMap<Integer, List<String>>();

		/*
		 * gson library has been used to parse JSON here.
		 */

		JsonElement jelement = new JsonParser().parse(rd);
		JsonObject jobject = jelement.getAsJsonObject();

		String currentCount = jobject.get("currentResultCount").toString();

		currentCount = currentCount.replaceAll("^\"|\"$", "");
		int count = Integer.parseInt(currentCount);
		JsonArray jarray = jobject.getAsJsonArray("results");
		for (int i = 0; i < count; i++) {

			List<Integer> prodCatlog = new ArrayList<Integer>();
			List<String> prodDetails = new ArrayList<String>();

			jobject = jarray.get(i).getAsJsonObject();
			String productId = jobject.get("productId").toString();

			productId = productId.replaceAll("^\"|\"$", "");
			int productID = Integer.parseInt(productId);

			String result = jobject.get("price").toString();

			result = result.replaceAll("^\"|\"$", "");
			result = result.replaceAll("[$,]", "");

			float priceFloat = Float.parseFloat(result);
			int price = Math.round(priceFloat);

			String prodName = jobject.get("productName").toString();
			String productName = prodName.replaceAll("^\"|\"$", "");

			String bName = jobject.get("brandName").toString();
			String brandName = bName.replaceAll("^\"|\"$", "");

			prodDetails.add(productName);
			prodDetails.add(brandName);
			prodDetails.add(result);
			productMap.put(productID, prodDetails);

			prodCatlog.add(productID);
			prodCatlog.add(price);

			map.put(i, prodCatlog);

		}

		Set<Integer> keys = map.keySet();
		int num = keys.size();
		int[] sampleArray = new int[num];

		for (int key : keys) {

			sampleArray[key] = key;
		}

		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		list = comb.printCombination(sampleArray, num, noOfItems);
		

		ArrayList<ArrayList<Integer>> finalList = new ArrayList<ArrayList<Integer>>();
		for (int l = 0; l < list.size(); l++) {
			int total = 0;
			ArrayList<Integer> sumList = new ArrayList<Integer>();
			sumList = list.get(l);

			for (int k = 0; k < noOfItems; k++) {

				int key = sumList.get(k);

				ArrayList<Integer> tempList = (ArrayList<Integer>) map.get(key);

				total = total + tempList.get(1);

			}
			
			if (total <= budget) {
				finalList.add(sumList);
				if (finalList.size() == 0) {
					System.out
							.println("Sorry! There is no product in the given range.");
				} else {
					ArrayList<Integer> itemList = new ArrayList<Integer>();

					for (int m = 0; m < finalList.size(); m++) {

						itemList = finalList.get(m);
						for (int a = 0; a < itemList.size(); a++) {
							int key = itemList.get(a);
							ArrayList<Integer> tempListPrice = (ArrayList<Integer>) map
									.get(key);

							ArrayList<String> tempListProductDetails = (ArrayList<String>) productMap
									.get(tempListPrice.get(0));

							for (int b = 0; b < tempListProductDetails.size(); b++) {
								System.out.println("Product: "
										+ tempListProductDetails.get(0));
								System.out.println("Brand Name: "
										+ tempListProductDetails.get(1));
								System.out.println("Price: " + "$"
										+ tempListProductDetails.get(2));

							}

							System.out.println("***************************");

						}
					}
				}

			} else {
				continue;
			}
		}
	}

	private static boolean checkUserInput(int val) {

		if (Math.round(val) == val)
			return true;
		else
			return false;

	}
}
