#Algorithm #DataStructure #Stack #CoreConcept #InterviewPattern #Parsing

>  Use a **Stack** to solve problems involving nested, paired delimiters (like parentheses, brackets, or HTML tags). The Stack's **Last-In, First-Out (LIFO)** nature perfectly models the "last opened must be first closed" logic of nested structures

---

## 🤔 The Problem: Validating Nested Structures

The challenge with validating a string like `"{[]()}"` is ensuring two things simultaneously:
1.  Every opening character has a corresponding closing character.
2.  The pairs are closed in the correct order (e.g., `[(])` is invalid).

A simple counter can't solve this because it doesn't track the nesting order.

## 💡 The Solution: The LIFO Power of the Stack

A Stack is the ideal data structure for this because its Last-In, First-Out (LIFO) behavior mirrors how nested structures must be resolved.

### The Logic (Step-by-Step)

1.  **Initialize an empty Stack.**
2.  **Iterate through the input string, character by character.**
3.  **If it's an "Opener" (`(`, `[`, `{`):** Don't push the opener itself. Instead, push its **expected "Closer"** (`)`, `]`, `}`) onto the stack. This sets an expectation for what the next valid closing character must be.
4.  **If it's a "Closer" (`)`, `]`, `}`):**
    a. First, check if the stack is empty. If it is, this closer has no matching opener, so the string is invalid.
    b. If the stack is not empty, `pop()` the top element. This is the "expectation" we set earlier.
    c. Compare the popped element to the current closer. If they do **not** match, the nesting order is incorrect, and the string is invalid.
5.  **Final Validation:** After the loop finishes, check if the stack is **empty**.
    -   If it is, all openers were correctly matched and closed. The string is valid.
    -   If it is **not** empty, it means there are unclosed openers. The string is invalid.

### Code Example (Valid Parentheses)

```typescript
function isValid(s: string): boolean {
  const stack: string[] = [];
  const map = new Map<string, string>([
    ['(', ')'],
    ['[', ']'],
    ['{', '}'],
  ]);

  for (const char of s) {
    if (map.has(char)) {
      // It's an opening character. Push the expected closer onto the stack.
      stack.push(map.get(char)!);
    } else {
      // It's a closing character.
      // 1. If stack is empty OR 2. if the closer doesn't match the expectation...
      if (stack.length === 0 || stack.pop() !== char) {
        return false; // ...it's invalid.
      }
    }
  }

  // After the loop, the stack must be empty for the string to be valid.
  return stack.length === 0;
}

// Usage:
console.log(isValid("()")); // true
console.log(isValid("()[]{}")); // true
console.log(isValid("(]")); // false
console.log(isValid("([)]")); // false
console.log(isValid("{[]}")); // true
console.log(isValid("]")); // false
console.log(isValid("(()")); // false
```

## 🤔 When to Use This Pattern

A Stack is your go-to data structure for any problem involving **Last-In, First-Out (LIFO)** logic. You should immediately think of a stack when a problem involves:

-   **Matching Pairs:** Validating parentheses, brackets, XML/HTML tags, or any other paired delimiters.
-   **Nested Structures:** Parsing file system paths (e.g., handling `/../`), evaluating expressions with nested functions, or navigating hierarchical data.
-   **Sequences Requiring Reversal:** Whenever you need to process items in the reverse order of their appearance.
-   **Simulating Recursion:** Stacks can be used to implement recursive algorithms iteratively to avoid stack overflow errors.

---

> [!summary] Key Takeaways
> - **The Problem:** Validating nested pairs requires tracking both balance and order.
> - **The Data Structure:** A **Stack** is the perfect tool due to its LIFO nature.
> - **The Logic:**
>     - When you see an **opener**, `push` its expected **closer** onto the stack.
>     - When you see a **closer**, `pop` from the stack and check if they match.
>     - After the loop, the stack must be **empty**.
> - This is a fundamental pattern for parsing and validating any kind of nested structure.