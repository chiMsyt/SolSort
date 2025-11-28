import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.io.IOException;

/**
 * SolSort v2.2: Advanced Algorithm Suite.
 * Features: Robust Menu, Data Visualization, Safe Execution, and True Terminal
 * Clearing.
 */
public class SolSort {

  // ANSI Color Codes
  public static final String RESET = "\033[0m";
  public static final String CYAN = "\033[0;36m";
  public static final String BLUE = "\033[0;34m";
  public static final String RED = "\033[0;31m";
  public static final String GREEN = "\033[0;32m";
  public static final String YELLOW = "\033[0;33m";
  public static final String PURPLE = "\033[0;35m";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] currentArray = null;

    while (true) {
      // 1. Clear screen (True Clear)
      clearConsole();

      // 2. Print Header
      printAsciiArt();

      // 3. Dashboard Status
      if (currentArray != null) {
        System.out.println(BLUE + " [ STATUS: MEMORY LOADED ]" + RESET);
        System.out.println(" Size: " + currentArray.length + " integers");
        System.out.println(" Status: " + (isSorted(currentArray) ? GREEN + "Sorted" : YELLOW + "Unsorted") + RESET);
      } else {
        System.out.println(RED + " [ STATUS: MEMORY EMPTY ]" + RESET);
      }
      System.out.println("------------------------------------------------");

      // 4. Menu Options
      System.out.println("1. Input New Array (Manual)");
      System.out.println("2. Generate Random Array");
      System.out.println("3. Linear Search");
      System.out.println("4. Binary Search");
      System.out.println("5. Bubble Sort");
      System.out.println("6. Selection Sort");
      System.out.println("7. Quick Sort");
      System.out.println("8. Heap Sort");
      System.out.println(PURPLE + "9. View Full Array Details (Stats + Grid View)" + RESET);
      System.out.println(RED + "10. Clear Memory / Reset" + RESET);
      System.out.println("11. Exit");
      System.out.print(CYAN + ">> Select option: " + RESET);

      int choice = getValidInt(scanner);

      // Exit Logic
      if (choice == 11) {
        System.out.println("Exiting SolSort. Goodbye!");
        break;
      }

      // Edge Case: Prevent running algorithms on null array (Except 1, 2, 10)
      if ((choice > 2 && choice < 10) && (currentArray == null || currentArray.length == 0)) {
        System.out.println(RED + "\n[!] Error: No array loaded. Please use Option 1 or 2 first." + RESET);
        waitForEnter(scanner);
        continue;
      }

      System.out.println(); // Spacing

      // 5. Execution Logic
      switch (choice) {
        case 1:
          currentArray = inputManualArray(scanner);
          break;
        case 2:
          currentArray = generateRandomArray(scanner);
          break;
        case 3:
          performSearch(scanner, currentArray, "Linear");
          break;
        case 4:
          performBinarySearchSafe(scanner, currentArray);
          break;
        case 5:
          performSort(currentArray, "Bubble");
          break;
        case 6:
          performSort(currentArray, "Selection");
          break;
        case 7:
          performSort(currentArray, "Quick");
          break;
        case 8:
          performSort(currentArray, "Heap");
          break;
        case 9:
          viewArrayDetails(currentArray);
          break;
        case 10:
          currentArray = null;
          System.out.println(YELLOW + ">> Memory Cleared. Array reset to null." + RESET);
          break;
        default:
          System.out.println(RED + "Invalid selection." + RESET);
      }

      waitForEnter(scanner);
    }
    scanner.close();
  }

  // ============================================================
  // SEARCHING ALGORITHMS (Strict Signatures)
  // ============================================================

  /**
   * Linear Search: O(N)
   */
  public static int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == target)
        return i;
    }
    return -1;
  }

  /**
   * Binary Search: O(log N)
   * Constraint: Array must be sorted.
   */
  public static int binarySearch(int[] arr, int target) {
    int low = 0;
    int high = arr.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] == target)
        return mid;
      if (arr[mid] < target)
        low = mid + 1;
      else
        high = mid - 1;
    }
    return -1;
  }

  // ============================================================
  // SORTING ALGORITHMS (Strict Signatures)
  // ============================================================

  /**
   * Bubble Sort: O(N^2)
   */
  public static void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }

  /**
   * Selection Sort: O(N^2)
   */
  public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[minIndex])
          minIndex = j;
      }
      int temp = arr[minIndex];
      arr[minIndex] = arr[i];
      arr[i] = temp;
    }
  }

  /**
   * Quick Sort: O(N log N)
   */
  public static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
      int pi = partition(arr, low, high);
      quickSort(arr, low, pi - 1);
      quickSort(arr, pi + 1, high);
    }
  }

  private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
      if (arr[j] < pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    return i + 1;
  }

  /**
   * Heap Sort: O(N log N)
   */
  public static void heapSort(int[] arr) {
    int n = arr.length;
    for (int i = n / 2 - 1; i >= 0; i--)
      heapify(arr, n, i);
    for (int i = n - 1; i > 0; i--) {
      int temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;
      heapify(arr, i, 0);
    }
  }

  private static void heapify(int[] arr, int n, int i) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    if (left < n && arr[left] > arr[largest])
      largest = left;
    if (right < n && arr[right] > arr[largest])
      largest = right;
    if (largest != i) {
      int swap = arr[i];
      arr[i] = arr[largest];
      arr[largest] = swap;
      heapify(arr, n, largest);
    }
  }

  // ============================================================
  // UI & HELPER METHODS
  // ============================================================

  private static void printAsciiArt() {
    System.out.println(CYAN + "   _____       _  _____            _   ");
    System.out.println("  / ____|     | |/ ____|          | |  ");
    System.out.println(" | (___   ___ | | (___   ___  _ __| |_ ");
    System.out.println("  \\___ \\ / _ \\| |\\___ \\ / _ \\| '__| __|");
    System.out.println("  ____) | (_) | |____) | (_) | |  | |_ ");
    System.out.println(" |_____/ \\___/|_|_____/ \\___/|_|   \\__|" + RESET);
    System.out.println(YELLOW + "       v2.2 | Algorithms Suite" + RESET);
  }

  /**
   * Invokes the Operating System's native clear command.
   * Windows: cls
   * Mac/Linux: clear
   */
  private static void clearConsole() {
    try {
      String os = System.getProperty("os.name");

      if (os.contains("Windows")) {
        // Run cls for Windows
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        // Run clear for Mac/Linux
        // ANSI Escape code is usually safer for Java on Unix than "clear" command
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }
    } catch (Exception e) {
      // Fallback if OS command fails (e.g. inside some IDEs)
      System.out.println("\n\n\n");
    }
  }

  private static void waitForEnter(Scanner scanner) {
    System.out.println(YELLOW + "\n[Press Enter to continue...]" + RESET);
    scanner.nextLine();
    scanner.nextLine();
  }

  private static boolean isSorted(int[] arr) {
    if (arr == null || arr.length <= 1)
      return true;
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] > arr[i + 1])
        return false;
    }
    return true;
  }

  private static void viewArrayDetails(int[] arr) {
    System.out.println(PURPLE + "=== FULL ARRAY DETAILS ===" + RESET);

    int min = arr[0], max = arr[0];
    long sum = 0;
    for (int val : arr) {
      if (val < min)
        min = val;
      if (val > max)
        max = val;
      sum += val;
    }
    double avg = (double) sum / arr.length;

    System.out.println("Stats: [Min: " + min + "] [Max: " + max + "] [Avg: " + String.format("%.2f", avg) + "]");
    System.out.println("------------------------------------------------");

    for (int i = 0; i < arr.length; i++) {
      System.out.printf("[%3d]: %-5d ", i, arr[i]);
      if ((i + 1) % 5 == 0)
        System.out.println();
    }
    System.out.println();
  }

  private static void printArrayTruncated(int[] arr) {
    System.out.print("[ ");
    int limit = Math.min(arr.length, 10);
    for (int i = 0; i < limit; i++) {
      System.out.print(arr[i] + (i < limit - 1 ? ", " : ""));
    }
    if (arr.length > 10)
      System.out.print(" ... (" + (arr.length - 10) + " more)");
    System.out.println(" ]");
  }

  private static int[] inputManualArray(Scanner scanner) {
    System.out.print("Enter array size: ");
    int size = getValidInt(scanner);
    if (size < 0)
      size = 0;

    int[] arr = new int[size];
    System.out.println("Enter " + size + " integers:");
    for (int i = 0; i < size; i++) {
      System.out.print("Element " + i + ": ");
      arr[i] = getValidInt(scanner);
    }
    System.out.println(GREEN + ">> Array Loaded Successfully." + RESET);
    return arr;
  }

  private static int[] generateRandomArray(Scanner scanner) {
    System.out.print("Enter size for Random Array: ");
    int size = getValidInt(scanner);
    if (size < 0)
      size = 0;

    int[] arr = new int[size];
    Random rand = new Random();
    for (int i = 0; i < size; i++) {
      arr[i] = rand.nextInt(100) + 1;
    }
    System.out.println(GREEN + ">> Random Array of size " + size + " generated." + RESET);
    return arr;
  }

  private static void performSearch(Scanner scanner, int[] arr, String type) {
    System.out.print("Enter target value: ");
    int target = getValidInt(scanner);

    int result = -1;
    if (type.equals("Linear"))
      result = linearSearch(arr, target);

    if (result != -1)
      System.out.println(GREEN + ">> Found at index: " + result + RESET);
    else
      System.out.println(RED + ">> Value not found." + RESET);
  }

  private static void performBinarySearchSafe(Scanner scanner, int[] arr) {
    if (!isSorted(arr)) {
      System.out.println(YELLOW + ">> Warning: Array is unsorted.");
      System.out.println(">> Auto-sorting array for Binary Search..." + RESET);
      quickSort(arr, 0, arr.length - 1);
      printArrayTruncated(arr);
    }

    System.out.print("Enter target value: ");
    int target = getValidInt(scanner);
    int result = binarySearch(arr, target);

    if (result != -1)
      System.out.println(GREEN + ">> Found at index: " + result + RESET);
    else
      System.out.println(RED + ">> Value not found." + RESET);
  }

  private static void performSort(int[] arr, String type) {
    System.out.print("Before: ");
    printArrayTruncated(arr);

    long startTime = System.nanoTime();
    switch (type) {
      case "Bubble":
        bubbleSort(arr);
        break;
      case "Selection":
        selectionSort(arr);
        break;
      case "Quick":
        quickSort(arr, 0, arr.length - 1);
        break;
      case "Heap":
        heapSort(arr);
        break;
    }
    long endTime = System.nanoTime();

    System.out.print(GREEN + "After:  " + RESET);
    printArrayTruncated(arr);
    System.out.println(PURPLE + "Time taken: " + (endTime - startTime) + " ns" + RESET);
  }

  private static int getValidInt(Scanner scanner) {
    while (!scanner.hasNextInt()) {
      System.out.print(RED + "Invalid input. Please enter a number: " + RESET);
      scanner.next();
    }
    return scanner.nextInt();
  }
}