import java.io.*;
import java.util.*;

public class textCheckTimeCount {
    public static ArrayList fileRead(String writing){
            String text;
            ArrayList<String> list = new ArrayList<String>();
        try {
            FileReader reader = new FileReader( writing );
            BufferedReader in = new BufferedReader( reader );
            // Read file line by line
            while((text = in.readLine()) != null){
                Scanner scanner = new Scanner(text);
                while (scanner.hasNext()){
                    list.add(scanner.next());
                }
            }
        } catch( FileNotFoundException e ) {
            System.out.println( e );
        } catch ( IOException e ) { 
            System.out.println( e );
        }
        return list;
    }
    static int comparisons = 0;
    static int moves = 0;
    static ArrayList<Long> time = new ArrayList<Long>();
    public static void merge(String[] l_arr, String[] r_arr, String[] arr, int l_size, int r_size){
		int i = 0;
		int l = 0;
		int r = 0;
		//While both l and r are greater than 0
		while(l<l_size && r<r_size){
            //Compare the words to see which comes first
			if(l_arr[l].compareTo(r_arr[r]) < 0){
				arr[i++] = l_arr[l++];
                comparisons += 1;
                moves += 1;
                
			} else {
				arr[i++] = r_arr[r++];
                comparisons +=1;
                moves += 1;
			}
		}
        //input rest of left array
		while(l<l_size){
			arr[i++] = l_arr[l++];
            moves += 1;
		}
        //input rest of right array
		while(r<r_size){
			arr[i++] = r_arr[r++];
            moves += 1;
		}
        
	}
	public static String[] mergeSort(String [] arr, int len)
	{
        //Check if array size is < 2. If is, do nothing
		if (len < 2){
			return arr;
		}
		// Makes an array with the correct number of slots
		int mid = len / 2;
		String [] l_arr = new String[mid];
		String [] r_arr = new String[len - mid];
		// Divide the words between arrays
		int k = 0;
		for(int i = 0; i < len; ++i){
			if(i < mid){
				l_arr[i] = arr[i];
			} else {
				r_arr[k] = arr[i];
				k = k + 1;
			}
		}
		//Recursively call function untill fully split
		mergeSort(l_arr,mid);
		mergeSort(r_arr, len-mid);
		//combining arrays again
		merge(l_arr, r_arr, arr, mid, len-mid);
        if(moves%100 == 0){
            time.add(System.nanoTime());
        }
        return arr;
  	}

    public static ArrayList grammatic(ArrayList content){
        //Convert ArrayList to String
        ArrayList<String> nextList = new ArrayList<String>();
        //Iterate through each word to remove punctuationa and convert to lower case
        for (int i = 0; i < content.size(); i++) { 	
            String text = content.get(i).toString();
            text = text.replaceAll("[^a-zA-Z ]", "").toLowerCase();
            nextList.add(text);
        }
        return nextList;
    }

    static int binSearch(String[] arr, String word)
        {
            int l = 0;
            int r = arr.length - 1;
            //Loop untill eithe mid == answer or no answer
            while (l <= r) {
                int m = l + (r - l) / 2;
                int val = word.compareTo(arr[m]);
                if (val == 0)
                    return m;
                // Remove Left half
                if (val > 0)
                    l = m + 1;
                // Remove right half
                else
                    r = m - 1;
            }
            return -1;
        }

    public static void main(String [] args){
        //Start by reading google file and convert to ArrayList
        ArrayList google = fileRead("google-10000-english-no-swears.txt");

        //Remove punctuation from Input
        @SuppressWarnings("unchecked")
        List<String> newList = grammatic(google);
        //Convert ArrayList to String Array
        String[] array = newList.toArray(new String[0]);
        // Use merge sort to alphaatise
        array = mergeSort(array, array.length);

        //Read Input file
        ArrayList input = fileRead("input219.txt");
        //Remove Punctuation
        @SuppressWarnings("unchecked")
        List<String> newListIn = grammatic(input);
        //Convert ArrayList to String Array
        String[] arrayIn = newListIn.toArray(new String[0]);
        //Iterate over every word using binary search, if found keep, else remove rkd from Array List
        for(String word : arrayIn){
            int res = binSearch(array, word);
            if(res == -1){
                newListIn.remove(word);
            } else{
                continue;
            }
        }

        // Convert ArrayList to String array 
        arrayIn = newListIn.toArray(new String[0]);
        // Run merge sort
        // Clear the timings ArrayList
        time.clear();
        // Start timer
        long startTime = System.nanoTime();
        arrayIn = mergeSort(arrayIn, arrayIn.length);
        long endTime = System.nanoTime();
        //Convert back to ArrayList
        List<String> list = new ArrayList<String>(Arrays.asList(arrayIn));
        ArrayList<Long> count = new ArrayList<Long>();
        // Subtract start time from each 100 words to find the time it took
        for(int i = 0; i<time.size();i++){
            count.add(time.get(i)-startTime);
        }
        System.out.println(list);
        System.out.println("\n" + "The number of comparisons was: " + comparisons);
        System.out.println("The number of moves was: " + moves);
        System.out.println("The Start time was " + startTime + " and the End time was " + endTime + " making the total time taken : " + (endTime-startTime) + " nanoseconds");
        System.out.println("The times for the every 100 words sorted are as follows");
        for(int i = 0; i<count.size();i++){         
            System.out.print( count.get(i) + " nanoseconds " + "\n");
        }
    }
}