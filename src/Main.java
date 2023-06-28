import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputscanner = new Scanner(System.in);
        System.out.println("Sample Input: ");
        System.out.print("Enter the number of elements in the array: ");
        int size = inputscanner.nextInt();
        int[] array = new int[size];

        System.out.println("Enter the elements in the array:");
        for (int i =0; i <= size-1; i++){
            int elementInput = inputscanner.nextInt();
            array[i] = elementInput;
        }

        System.out.print("Enter the search key:");
        int keyInput = inputscanner.nextInt();

        System.out.println("\nSample Output:");
        System.out.println("Using Linear Search:");
        //long linearStartTime = System.nanoTime();
        int linearAnswer = linearSearch(array, keyInput);
        //long linearEndTime   = System.nanoTime();
        //long linearTotalTime = linearEndTime - linearStartTime;
        if (linearAnswer != -1){
            System.out.println("Search key FOUND at index " + linearAnswer + ".");
        } else {
            System.out.println("Search key NOT FOUND");
        }
        //System.out.println("Running time of linear algorithm is: " + linearTotalTime+ "\n");
        System.out.println("\nUsing Interpolation Search:");

        try {
            //long interpolationStartTime = System.nanoTime();
            int interpolationAnswer = interpolationSearch(array, keyInput);
            //long interpolationEndTime = System.nanoTime();
            if (interpolationAnswer != -1){
                System.out.println("Search key FOUND at index " + interpolationAnswer + ".");
            } else {
                System.out.println("Search key NOT FOUND");
            }
        }catch (Exception e){
            System.out.println("Search key NOT FOUND");
        }

        //long interpolationTotalTime = interpolationEndTime - interpolationStartTime;
        //System.out.println("Running time of interpolation algorithm is: " + interpolationTotalTime+ "");


    }

    public static int linearSearch(int[] array, int key){
        for (int i =0; i <= array.length-1; i++){               //O(n)
            if (array[i] == key){                               //O(1)
                return i;                                       //O(1)
            }
        }
        return -1;                                              //O(1)
    }

    // Run time complexity for linear search = O(n) + O(1) + O(1) + O(1) = O(n+3)

    // Best case run time complexity = O(1), happens when key is found in first element of search
    // Average case run time complexity = O(n+1/2), happens when key is found between first and last element
    // Worst case run time complexity =  O(n), happens when key is found in last element of search

    public static int optimizedLinearSearch(int[] array, int key){
        Arrays.sort(array);                                     //O(n log n)
        int l = 0;                                              //O(1)
        int r = array.length - 1;                               //O(1)
        if (l <= r){                                            //O(1)
            int mid = l + (r-l)/2;
            if (key == array[mid]){                             //O(1)
                return mid;                                     //O(1)
            }
            if (key < array[mid]){                              //O(1)
                for (int i =0; i <= mid-1; i++){                //O(n/2)
                    if (array[i] == key){                               //O(1)
                        return i;                                       //O(1)
                    }
                }
            }
            if (key > array[mid]){                              //O(1)
                for (int i =mid+1; i <= array.length - 1; i++){ //O(n/2)
                    if (array[i] == key){                               //O(1)
                        return i;                                       //O(1)
                    }
                }
            }

        }
        return -1;                                                      //O(1)
    }

    // Run time complexity for linear search = O(n) + O(1) + O(1) + O(1) = O(n+3)

    // Best case run time complexity = O(1), happens when key is found in middle element of search
    // Average case run time complexity = O(n log n), happens when key is found not in the middle element of the searched array or last element in the subdivided array.
    // Worst case run time complexity =  O(n), happens when key is found in last element in the subdivided array

    public static int interpolationSearch(int[] array, int key){
        //sort algorithm
        Arrays.sort(array);                                     //O(n log n)
        int l = 0;                                              //O(1)
        int r = array.length - 1;                               //O(1)

        while (l < r){                                                  //O(n)
            int pos = (key - array[l]) / (array[r] -array[l]);          //O(1)
            int mid = l + (((r-l) * pos));                              //O(1)
            if (key == array[mid]){                                     //O(1)
                return mid;                                             //O(1)
            }
            if (key < array[mid]){                                      //O(1)
                r = pos -1;                                             //O(1)
            }
            if (key > array[mid]){                                      //O(1)
                l = pos +1;                                             //O(1)
            }
        }
        return -1;                                              //O(1)
    }
}

// Run time complexity = O(n log n) + O(1) + O(1) + (O(n) + O(1)+ O(1)+ O(1)+ O(1)+ O(1)+ O(1)+ O(1)+ O(1)) + O(1) = O(n log n) + O(1) + O(1) +(O(n) + O(8)) + O(1) = O(n log n) + O(n) + O(11)

// Best case run time complexity = O(1), happens when key is found in middle element of search
// Average case run time complexity = O(lg (lg n)), happens when key is found within few subdivisions of the searched array (excluding the last sub-division)
// Worst case run time complexity = O(n), happens when key is found in last sub-division of searched array

