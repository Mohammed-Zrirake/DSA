#Algorithm #StringManipulation #Parsing #CoreConcept #InterviewPattern

>  Solve parsing problems where an element's value depends on the *next* element by iterating through the sequence while "peeking" ahead. This allows you to apply special rules (like subtraction in Roman numerals) in a simple, elegant way.

---

> [!note] 📖 The Core Analogy: The Train Ticket Inspector
> Imagine an inspector checking tickets on a train.
> -   **The Inspector (The Algorithm):** Moves from one passenger to the next (iterates through the string).
> -   **The Passengers (The Characters):** Each has a ticket with a value.
> -   **The Special Rule (The Subtractive Case):** Some passengers have a "Family Pass" ticket. This ticket's value is only understood in the context of the person sitting next to them.
>
> **The Process:**
> 1.  The inspector gets to a passenger with a "Parent" ticket.
> 2.  Before processing it, they **look ahead** to the next passenger.
> 3.  **If the next passenger has a "Child" ticket:** The inspector knows this is a "Family Pass" deal. They process the two as a special pair (the subtractive rule, e.g., `IV`).
> 4.  **If the next passenger has another "Adult" ticket:** The inspector knows the current "Parent" ticket is just a standard adult fare and processes it normally (the additive rule, e.g., `VI`).
>
> The inspector's ability to "peek" at the next passenger is the key to correctly interpreting the current one.

---

## 🤔 The Problem: Context-Dependent Values

The core challenge with Roman numerals is that they are read left-to-right, but their value is not always additive. The value of a symbol changes if it is followed by a larger symbol.

-   `VI` = 5 + 1 = 6 (Additive)
-   `IV` = 5 - 1 = 4 (Subtractive)

A simple loop that just adds up the values would incorrectly calculate `IV` as 6.

## 💡 The Solution: Iterate and Peek

This solution cleverly handles the subtractive rule with a single, general principle instead of hardcoding every special case (`IV`, `IX`, `XL`, `XC`, `CD`, `CM`).

### The Logic (Step-by-Step)
1.  **Map Symbols to Values:** Create a `Map` for a clean, O(1) lookup of each Roman symbol's value.
2.  **Iterate from Left to Right:** Loop through the string from the first character to the last.
3.  **Look Ahead:** For each character, get its value and peek at the *next* character's value.
4.  **Apply the Rule:**
    -   If the current symbol's value is **less than** the next symbol's value, it's a subtractive case. **Subtract** the current value from the total.
    -   Otherwise (if the current value is greater or equal, or if it's the last character in the string), it's an additive case. **Add** the current value to the total.

### Code Example (Roman to Integer)

```typescript
function romanToInt(s: string): number {
  const romanMap = new Map<string, number>([
    ['I', 1],
    ['V', 5],
    ['X', 10],
    ['L', 50],
    ['C', 100],
    ['D', 500],
    ['M', 1000],
  ]);

  let total = 0;

  for (let i = 0; i < s.length; i++) {
    const currentValue = romanMap.get(s[i])!;

    // Peek ahead if we are not at the last character
    if (i + 1 < s.length) {
      const nextValue = romanMap.get(s[i + 1])!;

      // Check for the subtractive rule
      if (currentValue < nextValue) {
        total -= currentValue;
      } else {
        // Otherwise, it's an additive case
        total += currentValue;
      }
    } else {
      // This is the last character, so it must be additive
      total += currentValue;
    }
  }

  return total;
}

// Usage:
console.log(romanToInt("III"));      // Output: 3
console.log(romanToInt("LVIII"));   // Output: 58
console.log(romanToInt("MCMXCIV")); // Output: 1994
```

## 🤔 When to Use This Pattern

This "lookahead" or "peeking" pattern is extremely useful in problems where you are parsing a string or array and an element's interpretation depends on the **subsequent** element(s). Consider it when:

-   You are parsing a **custom-formatted string** with special combination rules.
-   The problem involves sequences where an element can **modify the value or meaning of its predecessor**.
-   You need to handle **exceptions or special pairs** within a sequence (like `IV` being an exception to the standard additive rule).
-   A simple left-to-right accumulation or reduction is **insufficient** to get the correct result.

---

> [!summary] Key Takeaways
> - **The Problem:** Simple iteration fails when an element's meaning is altered by the element that follows it.
> - **The Solution:** Iterate through the sequence while always "peeking" one step ahead.
> - **The Logic:** Use a simple conditional (`if current < next`) to decide whether to apply a special rule (e.g., subtract) or the standard rule (e.g., add).
> - **The Application:** This is a powerful pattern for parsing and interpreting any sequence with context-dependent rules.