#DSA #DataStructure #Java #Stack #CheatSheet #CompetitiveProgramming #LIFO

>  The Stack is a fundamental **Last-In, First-Out (LIFO)** data structure. For competitive programming in Java, you should almost always use the `ArrayDeque` class to implement a stack, as it is faster and more modern than the legacy `Stack` class.

---

> [!note] 📖 The Core Analogy: A Stack of Plates
> A stack is exactly like a stack of plates in a cafeteria.
> -   **`push()` (Placing a plate):** You can only add a new plate to the **top** of the stack.
> -   **`pop()` (Taking a plate):** You can only take a plate from the **top**. You can't pull one from the middle. The plate you take is the last one that was placed.
> -   **`peek()` (Looking at the top plate):** You can look at the top plate to see what it is without removing it.
> -   **`isEmpty()` (Is the stack empty?):** You can see if there are any plates left.

---

## ✅ The Recommended Implementation: `ArrayDeque`
While Java has a `java.util.Stack` class, it is a legacy class that is synchronized (thread-safe, which adds unnecessary overhead for CP) and extends `Vector`. The modern, preferred, and **faster** replacement for a stack in single-threaded contexts like competitive programming is `ArrayDeque`.

**Setup:**
```java
import java.util.ArrayDeque;
import java.util.Deque;

// Use the Deque interface, implemented by ArrayDeque
Deque<Integer> stack = new ArrayDeque<>();
```

| `Deque` Method | Stack Equivalent | Purpose | Example |
| :--- | :--- | :--- | :--- |
| `push(e)` | `push(e)` | Add element to the top | `stack.push(10);` |
| `pop()` | `pop()` | Remove and return top element | `int top = stack.pop();` |
| `peek()` | `peek()` | Return top element without removing | `int top = stack.peek();` |
| `isEmpty()` | `isEmpty()` | Check if stack is empty | `if (stack.isEmpty()) { ... }` |
| `size()` | `size()` | Get number of elements | `int count = stack.size();` |

> [!success] 🚀 The 4 Core Operations You'll Use 95% of the Time:
> -   `stack.push(x);`
> -   `stack.pop();`
> -   `stack.peek();`
> -   `stack.isEmpty();`

---

## 🕓 Time Complexity
For `ArrayDeque` used as a stack, all core operations are amortized constant time.
| Operation | Complexity |
| :--- | :---: |
| `push()` | **O(1)** |
| `pop()` | **O(1)** |
| `peek()` | **O(1)** |
| `isEmpty()` | **O(1)** |
| `search()` (`Stack` only) | O(n) |

---

## 🧩 Typical CP Use Cases & Patterns

### 1️⃣ Balanced Parentheses / Brackets
The canonical use case for a stack.
```java
Deque<Character> stack = new ArrayDeque<>();
for (char c : s.toCharArray()) {
    if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
    } else {
        if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
            return false;
        }
    }
}
return stack.isEmpty();
```

### 2️⃣ Next Greater / Smaller Element
A classic pattern that processes an array from right to left (or left to right) to find the next element with a certain property.
```java
// Find the next greater element for each item in an array
int[] arr = {4, 5, 2, 10, 8};
int[] nextGreater = new int[arr.length];
Deque<Integer> stack = new ArrayDeque<>();

for (int i = arr.length - 1; i >= 0; i--) {
    // While stack is not empty and top is smaller than current element, pop it
    while (!stack.isEmpty() && stack.peek() <= arr[i]) {
        stack.pop();
    }
    // If stack is empty, no greater element; otherwise, the top is the answer
    nextGreater[i] = stack.isEmpty() ? -1 : stack.peek();
    // Push current element for subsequent items to check against
    stack.push(arr[i]);
}
```

### 3️⃣ Monotonic Stack (Increasing or Decreasing)
A powerful variation of the "Next Greater Element" pattern. A monotonic stack maintains a strictly increasing or decreasing order of elements. It's used in problems like "Largest Rectangle in Histogram," "Daily Temperatures," etc. The key is often to store **indices** instead of values.
```java
// Maintain a stack of indices of elements in increasing order
Deque<Integer> stack = new ArrayDeque<>();
for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
        int height = arr[stack.pop()];
        int width = stack.isEmpty() ? i : i - stack.peek() - 1;
        // ... calculate area or other logic ...
    }
    stack.push(i);
}
```

### 4️⃣ Evaluate Postfix / Reverse Polish Notation
A stack is perfect for evaluating postfix expressions.
```java
Deque<Integer> stack = new ArrayDeque<>();
for (String token : expression) {
    if (isOperator(token)) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(applyOperator(a, b, token));
    } else {
        stack.push(Integer.parseInt(token));
    }
}
return stack.pop();
```

### 5️⃣ Simulation, Undo & Backtracking
Use a stack to simulate processes that require "undoing" steps or exploring paths in a DFS-like manner.
```java
// Example: Simulating a text editor with an undo function
Deque<String> history = new ArrayDeque<>();
String currentState = "";

// User types "hello"
history.push(currentState); // Save ""
currentState = "hello";

// User types " world"
history.push(currentState); // Save "hello"
currentState = "hello world";

// User hits undo
currentState = history.pop(); // currentState is now "hello"
```

---

## 🔄 Iterating Through a Stack
How you iterate depends on whether you want to preserve the stack.

| Pattern | Example | Notes |
| :--- | :--- | :--- |
| **Destructive (Top to Bottom)** | `while (!stack.isEmpty()) { process(stack.pop()); }` | This is the most common pattern. It processes elements in LIFO order and **empties the stack**. |
| **Non-Destructive (Top to Bottom)** | `for (Integer item : stack) { process(item); }` | This uses the `Deque`'s iterator. It processes from **top to bottom** and **preserves the stack**. |

> [!warning] `Stack` vs `ArrayDeque` Iteration Order
> The legacy `Stack` class's iterator is bottom-to-top, which is counter-intuitive. `ArrayDeque`'s iterator is top-to-bottom, which is what you'd typically expect. Another reason to always use `ArrayDeque`.

---

> [!summary] Key Takeaways
> - A stack is a **LIFO** data structure.
> - **Always use `Deque<T> stack = new ArrayDeque<>();`** for better performance in competitive programming.
> - Master the four core operations: `push`, `pop`, `peek`, `isEmpty`.
> - The most powerful patterns involve maintaining a **monotonic stack** (often of indices) to solve problems like "Next Greater Element" and "Largest Rectangle in Histogram."