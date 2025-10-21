 #DSA #DataStructure #Java #HashSet #CheatSheet #CompetitiveProgramming #Set

>  The `HashSet` is the go-to data structure for maintaining a collection of **unique** elements with **O(1)** average-case time complexity for add, remove, and contains operations. It is your primary tool for detecting duplicates and performing fast membership checks.

---

> [!note] 📖 The Core Analogy: The Exclusive Party Bouncer
> A `HashSet` is like a bouncer at an exclusive party with a magical, high-tech clipboard.
> -   **The Bouncer's Clipboard (The `HashSet`):** This is the list of guests.
> -   **`set.add("Alice")` (A guest arrives):** When "Alice" arrives, the bouncer uses a magical pen (the hash function) to instantly find the spot on their clipboard where Alice's name should be. If the spot is empty, they write her name down and let her in. If her name is already there, they know she's a duplicate (already inside) and simply ignore her.
> -   **`set.contains("Bob")` (Checking the guest list):** You ask the bouncer if "Bob" is at the party. They don't scan the whole list. They use their magical pen to instantly jump to Bob's designated spot on the clipboard to check. This is an incredibly fast lookup.
> -   **No Order:** The bouncer writes names down wherever the magical pen tells them to. The list on the clipboard is in no predictable order.

---

## ⚙️ How it Works Under the Hood
A `HashSet` is actually powered by a `HashMap`! The elements you add to the set are stored as the **keys** of the underlying `HashMap`. The value is just a constant placeholder object. This is why its performance characteristics are identical to a `HashMap`.

## 🧱 Basics
| Concept | Example | Description |
| :--- | :--- | :--- |
| **Import** | `import java.util.*;` | Needed for `HashSet` |
| **Declare** | `Set<Integer> set = new HashSet<>();` | Use the `Set` interface for good practice. |
| **Add Element** | `set.add(5);` | Adds the element. Returns `false` if it's a duplicate. |
| **Remove Element** | `set.remove(5);` | Removes the element if it exists. Returns `true` if removed. |
| **Check Existence** | `set.contains(5);` | Returns `true` or `false`. This is the primary use case. |
| **Size** | `set.size();` | Returns the number of unique elements. |
| **Clear** | `set.clear();` | Removes all elements from the set. |
| **Empty Check** | `set.isEmpty();` | Returns `true` if the set is empty. |

> [!success] 🚀 The 4 Core Operations You'll Use 95% of the Time:
> -   `set.add(x);`
> -   `set.contains(x);`
> -   `set.remove(x);`
> -   `set.size();`

---

## 🕓 Time Complexity
| Operation | Average Case | Worst-Case (Rare) |
| :--- | :---: | :---: |
| `add()`, `remove()`, `contains()` | **O(1)** | O(n) |
| Iteration (over `n` elements) | O(n) | O(n) |
> [!tip] In practice, for competitive programming, you can and should always assume `HashSet` operations are **O(1)**.

---

## 🧩 Typical CP Use Cases & Patterns

### 1️⃣ Check for Duplicates in an Array
The most common pattern. The `.add()` method's return value is a superpower.
```java
Set<Integer> set = new HashSet<>();
for (int x : arr) {
    // .add() returns false if the element is already in the set.
    if (!set.add(x)) {
        return true; // Duplicate found!
    }
}
return false;
```

### 2️⃣ Store and Count Unique Elements
A concise way to find the number of unique items.
```java
// Given an array of Integers (not int[])
Integer[] arr = {1, 2, 2, 3, 1};
Set<Integer> uniqueElements = new HashSet<>(Arrays.asList(arr));
System.out.println(uniqueElements.size()); // Output: 3
```

### 3️⃣ Set Operations: Intersection, Union, Difference
These methods modify the set in-place. Remember to create a copy first if you need to preserve the original.
```java
Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3));
Set<Integer> b = new HashSet<>(Arrays.asList(2, 3, 4));

// Intersection (elements common to both)
Set<Integer> intersect = new HashSet<>(a);
intersect.retainAll(b); // intersect is now {2, 3}

// Union (all unique elements from both)
Set<Integer> union = new HashSet<>(a);
union.addAll(b); // union is now {1, 2, 3, 4}

// Difference (elements in 'a' but not in 'b')
Set<Integer> diff = new HashSet<>(a);
diff.removeAll(b); // diff is now {1}
```

### 4️⃣ Fast Lookup Table
Perfect for problems where you need to quickly check if an item has been seen or belongs to a specific group.
```java
// Example: "Happy Number" problem
Set<Integer> seen = new HashSet<>();
int n = 19;
while (n != 1 && !seen.contains(n)) {
    seen.add(n);
    n = getNext(n); // Calculate sum of squares of digits
}
return n == 1;
```

---

## 🔄 Iterating
| Pattern | Example | Notes |
| :--- | :--- | :--- |
| **For-each Loop** | `for (int x : set) { ... }` | The simplest way. **Order is not guaranteed.** |
| **Iterator** | `Iterator<Integer> it = set.iterator(); while(it.hasNext()){ int x = it.next(); ... }` | Useful if you need to `it.remove()` during iteration. |
| **Lambda (Java 8+)** | `set.forEach(x -> { ... });` | Concise alternative. |

---

## 🔒 Variants of `Set` & Important Notes

| `Set` Type | When to Use in CP | Key Feature | Complexity |
| :--- | :--- | :--- | :---: |
| **`HashSet`** | **Default choice.** Fastest. | **Unordered.** | **O(1)** |
| **`LinkedHashSet`** | When you need to maintain **insertion order**. | Iterates in the order elements were added. | O(1) |
| **`TreeSet`** | When you need the elements to be **sorted**. | Always iterates in natural sorted order. | **O(log n)** |

> [!warning] Using Custom Objects in a HashSet
> If you want to store your own custom objects (e.g., `class Pair`) in a `HashSet`, you **MUST** correctly implement the `equals()` and `hashCode()` methods in your custom class. If you don't, the set will not be able to correctly identify duplicate objects.

---

> [!summary] Key Takeaways
> - `HashSet` is your go-to for **uniqueness** and **fast membership testing**.
> - It is backed by a `HashMap`, giving it **O(1)** average time complexity for core operations.
> - The `add()` method's boolean return value is a powerful shortcut for detecting duplicates.
> - If you need order, use `LinkedHashSet` (insertion order) or `TreeSet` (sorted order), but be aware of the performance trade-offs with `TreeSet`.