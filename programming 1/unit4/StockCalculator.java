package unit4;

// You are working on a data analysis project where you need to process an array of opening stock prices
// containing 10 days of data as float datatype.

// Question:

// Consider a scenario where you have been given an array of integers representing the daily stock prices
// of a company for a given period. You are also provided with an ArrayList of stock prices for the same period
// You are required to implement a program that performs the following tasks:

// Calculate the average stock price:

// Write a method, calculateAveragePrice, that takes the array of stock prices as input and returns the average price of the stocks.

// Find the maximum stock price:

// Write a method, findMaximumPrice, that takes the array of stock prices as input and returns the maximum price among all the stocks. 

// Determine the occurrence count of a specific price:

// Write a method, countOccurrences, that takes the array of stock prices and a target price as input and returns
// the number of times the target price occurs in the array. 

// Compute the cumulative sum of stock prices:

// Write a method, computeCumulativeSum, that takes the ArrayList of stock prices as input and returns
// a new ArrayList containing the cumulative sum of prices at each position.

// Note:

// Assume both the array and ArrayList of stock prices is not null and contains at least one element.

// You are allowed to use loops (for, while) for array and ArrayList.

// Write the code for the above scenario, including the required methods and their implementations.

import java.util.ArrayList;
import java.util.Arrays;

class StockCalculator {

  // calculateAveragePriceArray method returns the average price of stocks in
  // array
  static float calculateAveragePriceArray(float[] arr) {
    float sum = 0;
    for (float price : arr) {
      sum += price;
    }

    return sum / arr.length;
  }

  // calculateAveragePriceArrayList method returns the average price of stocks in
  // ArrayList
  static float calculateAveragePriceArrayList(ArrayList<Float> arr) {
    float sum = 0;
    for (float price : arr) {
      sum += price;
    }
    return sum / arr.size();
  }

  // findMaximumPriceArray method returns the maximum price of stock inside array
  static float findMaximumPriceArray(float[] arr) {
    float max = arr[0];
    for (int i = 0; i < arr.length; ++i) {
      if (max < arr[i]) {
        max = arr[i];
      }
    }
    return max;
  }

  // findMaximumPriceArrayList method returns the maximum price of stock inside
  // ArrayList
  static float findMaximumPriceArrayList(ArrayList<Float> arr) {
    float max = arr.get(0);
    for (int i = 0; i < arr.size(); ++i) {
      if (max < arr.get(i)) {
        max = arr.get(i);
      }
    }
    return max;
  }

  // countOccurrencesArray method returns the count of occurence of a stock price
  // inside array
  static int countOccurrencesArray(float[] arr, float target) {
    int count = 0;
    for (float price : arr) {
      if (price == target) {
        count++;
      }
    }
    return count;
  }

  // countOccurrencesArrayList method returns the count of occurence of a stock
  // price inside ArrayList
  static int countOccurrencesArrayList(ArrayList<Float> arr, float target) {
    int count = 0;
    for (float price : arr) {
      if (price == target) {
        count++;
      }
    }
    return count;
  }

  // computeCumulativeSumArray method returns the cumulative sum of stock prices
  // inside array
  static float[] computeCumulativeSumArray(float[] arr) {
    float[] cumSum = new float[arr.length];
    float sum = 0;
    for (int i = 0; i < arr.length; ++i) {
      sum += arr[i];
      cumSum[i] = sum;
    }
    return cumSum;
  }

  // computeCumulativeSumArrayList method returns the cumulative sum of stock
  // prices inside ArrayList
  static float[] computeCumulativeSumArrayList(ArrayList<Float> arr) {
    float[] cumSum = new float[arr.size()];
    float sum = 0;
    for (int i = 0; i < arr.size(); ++i) {
      sum += arr.get(i);
      cumSum[i] = sum;
    }
    return cumSum;
  }

  public static void main(String[] args) {
    float[] stockPricesArray = { 24.5f, 26.4f, 21.2f, 23.7f, 34.1f, 31.9f, 40.8f, 50.7f, 41.2f, 50.7f, 11.6f };
    ArrayList<Float> stockPricesArrayList = new ArrayList<Float>(
        Arrays.asList(24.5f, 26.4f, 21.2f, 23.7f, 34.1f, 31.9f, 40.8f, 50.7f, 41.2f, 50.7f, 11.6f));

    System.out.println("Average price of stocks in array: " + calculateAveragePriceArray(stockPricesArray));

    System.out.println("Average price of stocks in ArrayList: " + calculateAveragePriceArrayList(stockPricesArrayList));

    System.out.println("Maximum price of stock in array: " + findMaximumPriceArray(stockPricesArray));

    System.out.println("Maximum price of stock in arrayList:" + findMaximumPriceArrayList(stockPricesArrayList));

    System.out
        .println("Count of occurences of stock with price of 50.7 in array: "
            + countOccurrencesArray(stockPricesArray, 50.7f));

    System.out.println("Count of occurences of stock with price of 50.7 in ArrayList: "
        + countOccurrencesArrayList(stockPricesArrayList, 50.7f));

    System.out.print("Cumulative Sum of Stock Prices in array: ");
    float[] cumSumArray = computeCumulativeSumArray(stockPricesArray);
    for (float sum : cumSumArray) {
      System.out.print(sum + " ");
    }

    System.out.println();

    System.out.print("Cumulative Sum of Stock Prices in ArrayList: ");
    float[] cumSumArrayList = computeCumulativeSumArrayList(stockPricesArrayList);
    for (float sum : cumSumArrayList) {
      System.out.print(sum + " ");
    }
  }
}