package main.java.com.pradeep.hackerearth.codingchallenge.paypal_android_may2020;

import java.util.Scanner;

public class PayPalAndroidChallenge {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // read the number of test cases
        int testCasesCount = Integer.parseInt(scanner.nextLine());
        System.out.println("testCasesCount = " + testCasesCount);

        int[] arrLens = new int[testCasesCount];
        int[][] inputArrays = new int[testCasesCount][];

        // read the arrayLength and array elements for each test case
        for (int i = 0; i < testCasesCount; i++) {
            // read the array length
            arrLens[i] = Integer.parseInt(scanner.nextLine());

            // read the array elements
            String[] strArr = scanner.nextLine().split(" ");
            // allocate memory for 1d array inside 2d array
            inputArrays[i] = new int[strArr.length];

            // convert string array to integer array
            for (int j = 0; j < strArr.length; j++) {
                inputArrays[i][j] = Integer.parseInt(strArr[j]);
            }
        }

        // calculate output for each test case and print it
        /* Problem Statement
          Example-1:- Input = [3, 2, 4, 1, 5]
          calculating number of sub-arrays possible for element 3, curIndex = 0
                1-element sub arrays [3]             count = 1
                2-element sub arrays -> not possible
                                                TotalCount = 1
          calculating number of sub-arrays possible for element 2, curIndex = 1
                1-element sub arrays  [2]             count = 1
                2-element sub arrays [3, 2], [2, 4]   count = 2
                3-element sub arrays [3,2,4]          count = 1
                                                 TotalCount = 4
          calculating number of sub-arrays possible for element 4, curIndex = 2
                1-element sub arrays [4]             count = 1
                2-element sub arrays -> not possible
                                                TotalCount = 1
          calculating number of sub-arrays possible for element 1, curIndex = 3
                1-element sub arrays  [1]                               count = 1
                2-element sub arrays [4, 1], [1, 5]                     count = 2
                3-element sub arrays [2, 4, 1], [4, 1, 5]               count = 2
                4-element sub arrays [3, 2, 4, 1], [2, 4, 1, 5]         count = 2
                5-element sub arrays [3, 2, 4, 1, 5]                    count = 1
                                                                   TotalCount = 8
            Output = [ 1 4 1 8 ]
        */

        /* My Approach

         Identify the beforeIndex and afterIndex for the curArrElement,
         Find possible numbers count before current element and possible numbers count
         after the current element. Calculate how many sub-array combinations are possible and
        that becomes the count for the curArrElement
        Example-1:-  Indexes = 0, 1, 2, 3, 4
                         arr =[3, 2, 4, 1, 5]
               calculating number of sub-arrays possible for element 1, curIndex = 3

               step-1 :- find beforeIndex and afterIndex
               step-2 :- find count of numbers between beforeIndex and currentIndex, and find
                        count of numbers between curIndex and afterIndex.
               step-3 :- calculate possibleSubArrays for current element using above values.

               beforeIndex = 0, afterIndex = 4
               numCountBeforeIndex = curIndex - beforeIndex
                                           =  3 - 0
                                           =  3
              numCountAfterIndex = afterIndex - curIndex
                                         =  4  - 3
                                         =  1
           numOfPossibleSubArrays = (numCountBeforeIndex+1)*(numCountAfterIndex+1)
                                  = (3+1)*(1+1)
                                  =  8
        */

        for (int i = 0; i < testCasesCount; i++) {
            int count = 1;
            for (int curIndex = 0; curIndex < arrLens[i]; curIndex++) {
                int curArrElement = inputArrays[i][curIndex];
                int beforeIndex = 0, afterIndex = 0;

                for (beforeIndex = curIndex - 1; beforeIndex >= 0 &&
                        (inputArrays[i][beforeIndex] > curArrElement); beforeIndex--)
                    ; // empty for loop
                for (afterIndex = curIndex + 1; afterIndex < arrLens[i] &&
                        (inputArrays[i][afterIndex] > curArrElement); afterIndex++)
                    ; // empty for loop

                beforeIndex++;
                afterIndex--;
                int numCountBeforeIndex = curIndex - beforeIndex;
                int numCountAfterIndex = afterIndex - curIndex;
                count = (numCountBeforeIndex + 1) * (numCountAfterIndex + 1);
                // count of sub arrays for each element in the array
                System.out.print(count + " ");
            }
            // new line after output of every test case
            System.out.println();
        }
    }
}