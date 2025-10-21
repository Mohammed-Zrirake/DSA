
tags: #DSA #DataStructure #Java #HashMap #CheatSheet #CompetitiveProgramming

>  The `HashMap` is the competitive programmer's ultimate tool for **O(1)** average-case lookups, insertions, and deletions. It's the go-to data structure for any problem involving frequency counting, mapping, or grouping.

---

> [!note] 📖 The Core Analogy: The Magical Post Office
> A `HashMap` is like a magical post office with an infinite number of mailboxes.
> -   **The Key (The Person's Name):** This is the unique identifier you use, like "John Smith."
> -   **The Hash Function (The Magical Sorter):** When a letter for "John Smith" arrives, a magical sorter instantly knows which specific mailbox (`#3491`) belongs to him. It doesn't have to search through all the mailboxes.
> -   **The Value (The Contents of the Mailbox):** This is the actual mail—the data you are storing.
> -   **`map.put("John", mail)`:** You give the letter to the sorter, and it instantly places it in John's box.
> -   **`map.get("John")`:** You ask for John's mail, and the sorter instantly retrieves it from his box.
> -   **Hash Collisions (The Rare Problem):** On very rare occasions, the sorter might get confused and assign "John Smith" and "Jane Doe" to the same mailbox. It then has to do a quick manual check inside that one box to find the right person's mail. This is why the worst-case is O(n), but it's so rare in practice that we almost always assume O(1).

---

## ☕ Java HashMap — Competitive Programming Cheatsheet

### 🧱 Basics
| Concept | Example | Notes |
| :--- | :--- | :--- |
| **Import** | `import java.util.*;` | `HashMap` is in `java.util` |
| **Declare** | `HashMap<Key, Val> map = new HashMap<>();` | e.g., `HashMap<String, Integer> map;` |
| **Add / Update** | `map.put("abc", 10);` | Inserts or replaces the value if the key exists. |
| **Get Value** | `map.get("abc");` | Returns the value, or `null` if the key is not found. |
| **Check Key** | `map.containsKey("abc");` | Boolean check, O(1). **Prefer this over `map.get(k) != null`**. |
| **Check Value** | `map.containsValue(10);` | Boolean check, but it's slow—**O(n)**. Avoid in CP. |
| **Remove** | `map.remove("abc");` | Deletes the key-value pair. |
| **Size** | `map.size();` | Returns the number of key-value pairs. |
| **Clear** | `map.clear();` | Deletes all pairs. |
| **Empty Check** | `map.isEmpty();` | Boolean check. |

### ⚙️ Most Commonly Used in CP
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `getOrDefault(key, default)` | Returns the value for a key, or a default value if the key is absent. **Essential for frequency counting.** | `int count = map.getOrDefault(ch, 0);` |
| `putIfAbsent(key, value)` | Adds the key-value pair only if the key is not already present. | `map.putIfAbsent("x", 1);` |
| `replace(key, value)` | Replaces the value for a key only if the key already exists. | `map.replace("x", 5);` |
| `replace(key, oldV, newV)` | Replaces only if the key is mapped to the `oldValue`. | `map.replace("x", 5, 10);` |
| `remove(key, value)` | Removes the entry for a key only if it is currently mapped to the specified value. | `map.remove("x", 10);` |

### 🔄 Iterating
| Pattern | Example |
| :--- | :--- |
| **Keys Only** | `for (KeyType key : map.keySet()) { ... }` |
| **Values Only** | `for (ValueType val : map.values()) { ... }` |
| **Entries (Key + Value)** | `for (Map.Entry<Key, Val> entry : map.entrySet()) { Key k = entry.getKey(); Val v = entry.getValue(); }` |
| **Lambda-style (Java 8+)** | `map.forEach((key, value) -> { ... });` |

### 🧩 Typical CP Use Cases

#### 1. Frequency Map (Counting Characters/Numbers)
The most common use case.
```java
HashMap<Character, Integer> freq = new HashMap<>();
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

#### 2. Mapping Values to Their First Index
Useful for problems where you need to find the first occurrence of an element.
```java
HashMap<Integer, Integer> firstIndex = new HashMap<>();
for (int i = 0; i < arr.length; i++) {
    firstIndex.putIfAbsent(arr[i], i);
}
```

#### 3. Grouping Elements
Use `computeIfAbsent` to initialize a list for a new key.
```java
// Group numbers by their remainder when divided by 3
HashMap<Integer, List<Integer>> groups = new HashMap<>();
for (int x : arr) {
    groups.computeIfAbsent(x % 3, k -> new ArrayList<>()).add(x);
}
```

---

## ⚡ Advanced Shortcuts & Compute Methods
> [!success] These methods are extremely useful for writing concise and efficient code for counting and summing.

| Method | Description | Example (Frequency Count) |
| :--- | :--- | :--- |
| `compute(key, (k, v) -> ...)` | Computes a new value based on the old one. | `map.compute(c, (k, v) -> (v == null) ? 1 : v + 1);` |
| `computeIfAbsent(key, k -> ...)` | If key is missing, computes and inserts a value. | `map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);` |
| `computeIfPresent(key, (k, v) -> ...)`| If key is present, computes a new value. | `map.computeIfPresent(key, (k, v) -> v + 1);` |
| `merge(key, value, (oldV, newV) -> ...)` | Combines a new value with an existing one. **Extremely powerful.** | `map.merge(ch, 1, Integer::sum);` |

---

## 🕓 Time Complexities
| Operation | Average Case | Worst-Case (Rare) |
| :--- | :---: | :---: |
| `put`, `get`, `remove`, `containsKey`| **O(1)** | O(n) (due to hash collisions) |
| Iteration (over all `n` entries) | O(n) | O(n) |

## 🔒 Variants of `Map`
| Map Type | When to Use in CP | Key Feature |
| :--- | :--- | :--- |
| **`HashMap`** | **Default choice.** Fastest for general use. | **Unordered.** |
| **`LinkedHashMap`** | When you need to maintain the **insertion order** of keys. | Iterates in the order keys were inserted. |
| **`TreeMap`** | When you need the keys to be **sorted**. | Slower (O(log n) operations), but keys are always in natural or custom sorted order. |

## 💡 Extra CP Tricks

### Sorting a HashMap by Value
You can't sort a `HashMap` directly. You must convert its entries to a `List` and then sort the list.
```java
// Get the entry set and put it into a list
List<Map.Entry<String, Integer>> entryList = new ArrayList<>(freqMap.entrySet());

// Sort the list by value in descending order
entryList.sort((a, b) -> b.getValue() - a.getValue());

// To sort by key: a.getKey().compareTo(b.getKey())
```

---

> [!success] 🚀 Most Used in Contests
> You will use these 95% of the time. Master them.
> -   `map.put(k, v);`
> -   `map.get(k);`
> -   **`map.getOrDefault(k, default);`** (Extremely common)
> -   `map.containsKey(k);`
> -   `map.remove(k);`
> -   `map.size();`
> -   `for (Map.Entry<K,V> entry : map.entrySet()) { ... }`