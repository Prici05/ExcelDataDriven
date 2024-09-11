package testcases;

import java.io.IOException;
import java.util.ArrayList;

public class Check {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		excelDataDriven data = new excelDataDriven();
		ArrayList<String> array = data.getData("Login");
		
		System.out.println("Size of array: " + array.size());
		
		
		System.out.println(array.get(0));
		System.out.println(array.get(1));
		System.out.println(array.get(2));
		System.out.println(array.get(3));

	}

}
