import java.io.*;
import java.util.*;

public class Solution {

    public static Integer childArraySize = 0;
    public static Integer[] childrenRank;
    public static Integer[] childCandyResults;
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner inputScanner = new Scanner(System.in);
        childArraySize = inputScanner.nextInt();
        
        childrenRank = new Integer[childArraySize];
        childCandyResults = new Integer[childArraySize];
        
        for(int i = 0; i < childArraySize; i++){
            int rank = inputScanner.nextInt();
            childrenRank[i] = rank;
            childCandyResults[i] = 0;
        }
            
        int totalCandies = 0;
        for(int i = 0; i < childArraySize; i++){
            totalCandies += GetCandies(i);
        }
        
        System.out.println(totalCandies); 
    }
    
    public static Integer GetCandies(Integer childIndex){
        //return memoized result if available
        if(childCandyResults[childIndex] != 0){
            return childCandyResults[childIndex];
        }
        
        //otherwise, calculate result
        Integer leftChildIndex = childIndex - 1;
        Integer rightChildIndex = childIndex + 1;
        Boolean hasLeftNeighbor = leftChildIndex >= 0;
        Boolean hasRightNeighbor = rightChildIndex < childArraySize;
        
        Integer leftChildRank = null;
        Integer rightChildRank = null;

        if(hasLeftNeighbor){
            leftChildRank = childrenRank[leftChildIndex];
        }
        if(hasRightNeighbor){
            rightChildRank = childrenRank[rightChildIndex];
        }

        Integer myRank = childrenRank[childIndex];

        Integer result;

        if(!hasLeftNeighbor){
            if(myRank > rightChildRank){
                result = GetCandies(rightChildIndex) + 1;
            }
            else{
                result = 1;
            }

        }
        else if(!hasRightNeighbor){
            if(myRank > leftChildRank){
                result =  GetCandies(leftChildIndex) + 1;
            }
            else{
                result =  1;
            }
        }
        else{
            if(myRank > leftChildRank && myRank > rightChildRank){
                int left = GetCandies(leftChildIndex);
                int right = GetCandies(rightChildIndex);
                result = Math.max(left,right) + 1;
            }
            else if(myRank > leftChildRank){
                int left = GetCandies(leftChildIndex);
                result = left + 1;
            }
            else if(myRank > rightChildRank){
                int right = GetCandies(rightChildIndex);
                result = right + 1;
            }
            else {
                result = 1;
            }
        }
        
        //store result in memo table
        childCandyResults[childIndex] = result;
        return result;
    }
}
