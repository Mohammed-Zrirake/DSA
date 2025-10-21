
 #DSA #DataStructure #Java #Array #CheatSheet #CompetitiveProgramming

> Java arrays are the backbone of competitive programming for storing fixed-size, sequential data. The `java.util.Arrays` class is your most powerful ally, providing highly optimized methods for sorting, searching, and manipulation that you **must** know.

---

> [!note] 📖 The Core Analogy: The Numbered Rack of Test Tubes
> An array is like a scientist's rack of test tubes, perfectly ordered and numbered.
> -   **Fixed Size & Contiguous:** The rack is manufactured with a specific number of slots, all in a neat row.
> -   **`O(1)` Access:** If you need the contents of test tube #42, you can reach for it instantly without checking any others.
> -   **The `Arrays` Class (The Lab Assistant):** This is your hyper-efficient lab assistant. Instead of you manually sorting the tubes by color, you can just tell your assistant, "Sort these," and they'll do it in a flash (`Arrays.sort()`). If you need to find a specific chemical, you can tell them, "Find 'H2O'," and they'll use an incredibly fast search method to locate it (`Arrays.binarySearch()`). Master what your assistant can do for you.

---

## ☕ Java Arrays — Competitive Programming Cheatsheet

### 🧱 Basics
| Concept / Method | Purpose | Example |
| :--- | :--- | :--- |
| `arr.length` | Get the number of elements | `int n = arr.length;` |
| `arr[i]` | Access or modify an element | `arr[2] = 10;` |
| `new int[n]` | Create a new array of size `n` (default value `0`) | `int[] a = new int[5];` |
| `int[] arr = {1, 2, 3};` | Declare and initialize in one line | — |

### ⚙️ Common Utility Methods (`java.util.Arrays`)
> [!tip] Always start with `import java.util.Arrays;`

| Method | Purpose | Example |
| :--- | :--- | :--- |
| `Arrays.sort(arr)` | Sort entire array ascending | `Arrays.sort(arr);` |
| `Arrays.sort(arr, from, to)` | Sort subarray `[from, to)` | `Arrays.sort(arr, 1, 4);` |
| `Arrays.sort(arr, Comparator)` | Custom sort for Objects (e.g., `Integer[]`, not `int[]`) | `Arrays.sort(arr, Collections.reverseOrder());` |
| `Arrays.fill(arr, val)` | Fill entire array with a value | `Arrays.fill(arr, -1);` |
| `Arrays.fill(arr, from, to, val)` | Fill part of array `[from, to)` | `Arrays.fill(arr, 0, 3, 5);` |
| `Arrays.equals(a, b)` | Check 1D array equality | `Arrays.equals(a, b)` |
| `Arrays.deepEquals(a, b)` | Check multi-dimensional array equality | `Arrays.deepEquals(matrixA, matrixB)` |
| `Arrays.copyOf(arr, len)` | Copy array, resizing if needed | `int[] b = Arrays.copyOf(a, 10);` |
| `Arrays.copyOfRange(arr, from, to)` | Copy subarray `[from, to)` into a new array | `int[] b = Arrays.copyOfRange(a, 1, 4);` |
| `Arrays.toString(arr)` | Convert 1D array to printable string | `System.out.println(Arrays.toString(a));` |
| `Arrays.deepToString(arr)` | Convert multi-dimensional array to string | `System.out.println(Arrays.deepToString(matrix));` |
| `Arrays.binarySearch(arr, key)` | **Sorted array only.** O(log n) search. | `int idx = Arrays.binarySearch(a, 42);` |

### 🌊 Useful Stream Tricks
> [!success] Streams are super useful in contests to write concise, one-line logic.

| Use Case | Example |
| :--- | :--- |
| **Sum** | `long sum = Arrays.stream(a).asLongStream().sum();` *(Use `asLongStream` to prevent overflow)*|
| **Max** | `int max = Arrays.stream(a).max().getAsInt();` |
| **Min** | `int min = Arrays.stream(a).min().getAsInt();` |
| **Average** | `double avg = Arrays.stream(a).average().getAsDouble();` |
| **Filter** | `int[] even = Arrays.stream(a).filter(x -> x % 2 == 0).toArray();` |
| **Sort Descending (Primitives)** | `int[] desc = Arrays.stream(a).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();` |

### ▦ 2D Arrays (Matrices)
| Concept | Example |
| :--- | :--- |
| **Declare** | `int[][] grid = new int[rows][cols];` |
| **Initialize** | `int[][] grid = {{1,2,3}, {4,5,6}};` |
| **Access** | `grid[i][j]` |
| **Get Dimensions** | `grid.length` (number of rows), `grid[0].length` (number of columns) |
| **Print** | `System.out.println(Arrays.deepToString(grid));` |
| **Fill with a value** | `for (int[] row : grid) { Arrays.fill(row, -1); }` |

### 🔄 Conversions
| From → To | Method |
| :--- | :--- |
| `int[]` → `List<Integer>` | `Arrays.stream(arr).boxed().collect(Collectors.toList());` or `.toList()` (Java 16+) |
| `List<Integer>` → `int[]` | `list.stream().mapToInt(Integer::intValue).toArray();` |
| `int[]` → `String` | `Arrays.toString(arr)` |
| `String[]` → `List<String>` | `Arrays.asList(strArr)` or `List.of(strArr)` |
| `List<String>` → `String[]` | `list.toArray(new String[0]);` |

---

## 💡 Common Patterns & Tricks in CP

### 1. Sorting Custom Objects or 2D Arrays
Use a lambda comparator. This is extremely common.
```java
// Sort a 2D array of pairs based on the first element, then the second.
int[][] pairs = {{3, 5}, {1, 2}, {3, 1}};
Arrays.sort(pairs, (a, b) -> {
    if (a[0] != b[0]) {
        return a[0] - b[0]; // Sort by first element
    }
    return a[1] - b[1]; // If first is same, sort by second
});
// pairs is now {{1, 2}, {3, 1}, {3, 5}}
```

### 2. Reversing an Array
-   **For Objects (`Integer[]`, not `int[]`):**
    ```java
    Integer[] arr = {1, 2, 3};
    Collections.reverse(Arrays.asList(arr)); // arr is now {3, 2, 1}
    ```
-   **For Primitives (`int[]`):** You must do it manually.
    ```java
    int[] arr = {1, 2, 3, 4};
    for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    // arr is now {4, 3, 2, 1}
    ```

### 3. Prefix Sum / Suffix Sum
A fundamental technique for answering range query problems in O(1) time after an O(n) pre-computation.
```java
// arr = {2, 4, 1, 5}
int[] prefixSum = new int[n + 1];
for (int i = 0; i < n; i++) {
    prefixSum[i + 1] = prefixSum[i] + arr[i];
}
// prefixSum = {0, 2, 6, 7, 12}
// Sum of subarray from index i to j = prefixSum[j+1] - prefixSum[i]
```

### 4. 2D Directions Array (for Grids/BFS/DFS)
Indispensable for grid traversal problems.
```java
// Up, Down, Left, Right
int[][] dirs = {{ -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};

// To get neighbors of cell (r, c):
for (int[] dir : dirs) {
    int newR = r + dir[0];
    int newC = c + dir[1];
    // check bounds and process neighbor...
}
```

### 5. Using `binarySearch` for Lower/Upper Bound
The negative return value is a superpower.
```java
// Find first element >= key (Lower Bound)
int[] arr = {2, 5, 5, 8, 12};
int key = 5;
int idx = Arrays.binarySearch(arr, key);
if (idx < 0) {
    idx = -(idx + 1); // This is the insertion point
} else {
    // Found it, but need to find the *first* occurrence
    while (idx > 0 && arr[idx - 1] == key) {
        idx--;
    }
}
// idx is now 1, the index of the first 5.
```

---

> [!success] 🚀 Most Common in Contests
> You will use these 90% of the time. Master them.
> -   `arr.length`
> -   `Arrays.sort(arr)` (and with a custom comparator)
> -   `Arrays.fill(arr, value)`
> -   `Arrays.copyOfRange(arr, from, to)`
> -   `Arrays.equals(a, b)`
> -   `Arrays.toString(arr)`
> -   `Arrays.binarySearch(arr, key)`
> -   `Arrays.stream(a).sum()` / `.max()` / `.min()`