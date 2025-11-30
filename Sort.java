/*
 * 1. Buat program Java yang mengurutkan data dari terbesar ke terkecil
 * menggunakan Merge Sort, Tentukan sendiri nilai untuk variabel (misal): a1,
 * a2, a3, a4, a5, a6, a7, a8 (minimal 8 elemen). Kemudian buat analisa kinerja
 * algoritma tersebut menurut anda?  (45 Poin)
 * 
 * 2. Buat program Java yang mengurutkan data dari terbesar ke terkecil
 * menggunakan Counting Sort, Tentukan sendiri nilai untuk variabel (misal): a1,
 * a2, a3, a4, a5, a6, a7, a8 minimal 8 elemen). Kemudian buat analisa kinerja
 * algoritma tersebut menurut anda? (40 Poin)
 */

import java.util.Arrays;

class MergeSort {
  private static int[] merge(int[] arr1, int[] arr2) {
    int[] temp = new int[arr1.length + arr2.length];

    int current1 = 0;
    int current2 = 0;
    int current3 = 0;

    while (current1 < arr1.length && current2 < arr2.length) {
      if (arr1[current1] < arr2[current2]) {
        temp[current3++] = arr1[current1++];
      } else {
        temp[current3++] = arr2[current2++];
      }
    }

    while (current1 < arr1.length) {
      temp[current3++] = arr1[current1++];
    }

    while (current2 < arr2.length) {
      temp[current3++] = arr2[current2++];
    }

    return temp;
  }

  void sort(int[] arr) {
    if (arr.length > 1) {
      int[] firstHalf = new int[arr.length / 2];
      System.arraycopy(arr, 0, firstHalf, 0, arr.length / 2);
      sort(firstHalf);

      int[] secondHalf = new int[arr.length - (arr.length / 2)];
      System.arraycopy(arr, arr.length / 2, secondHalf, 0, secondHalf.length);
      sort(secondHalf);

      int[] tempArr = merge(firstHalf, secondHalf);
      System.arraycopy(tempArr, 0, arr, 0, tempArr.length);
    }
  }
}

class CountingSort {
  void sort(int[] arr) {
    if (arr == null || arr.length < 1)
      return;

    int max = arr[0];
    for (int i = 0; i < arr.length; i++) {
      if (max < arr[i]) {
        max = arr[i];
      }
    }

    int[] count = new int[max + 1];

    for (int num : arr) {
      count[num]++;
    }

    for (int i = 0; i <= max; i++) {
      if (i > 0) {
        count[i] += count[i - 1];
      }
    }

    int[] output = new int[arr.length];

    for (int i = arr.length - 1; i >= 0; i--) {
      output[count[arr[i]] - 1] = arr[i];

      count[arr[i]]--;
    }

    System.arraycopy(output, 0, arr, 0, output.length);

  }
}

public class Sort {
  public static void main(String[] args) {
    int[] random = { 10, 2, 7, 3, 5, 1, 9, 6 };

    long startTime = System.nanoTime();
    // MergeSort sortByMerge = new MergeSort();
    // sortByMerge.sort(random);

    CountingSort sortByCount = new CountingSort();
    sortByCount.sort(random);
    System.out.println(Arrays.toString(random));
    long endTime = System.nanoTime();

    double convertToMili = (double) (endTime - startTime) / 1_000_000.00;

    System.out.println("Time complexity: " + convertToMili + "ms");
  }
}