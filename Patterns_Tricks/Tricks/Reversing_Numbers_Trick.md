#Algorithm #Math #CoreConcept #InterviewPattern #NumberManipulation

>  Reverse an integer mathematically by repeatedly "peeling off" the last digit using the **modulo operator (`% 10`)** and adding it to a new reversed number that is shifted left by multiplying by 10. This avoids string conversion and is highly efficient.

---

> [!note] 📖 The Core Analogy: The Coin Counter
> Imagine you have a stack of numbered coins (`123`). You want to reverse their order.
> -   **Your Left Hand (The Original Number):** Starts holding the stack `123`.
> -   **Your Right Hand (The Reversed Number):** Starts empty.
>
> **The Process:**
> 1.  **Peel Off:** You take the top coin (`3`) from your left hand. (This is `123 % 10`).
> 2.  **Add to Right:** You place the `3` in your right hand. Your right hand is now `3`.
> 3.  **Discard:** You discard the `3` from the original stack. Your left hand now holds `12`. (This is `123 / 10`).
> 4.  **Peel Off:** You take the top coin (`2`) from your left hand. (This is `12 % 10`).
> 5.  **Shift and Add:** To add the `2`, you first shift the `3` over to the "tens" place (by multiplying by 10, so `3 * 10 = 30`), then add the new coin `2`. Your right hand is now `32`.
> 6.  **Discard:** Your left hand now holds `1`. (This is `12 / 10`).
> 7.  **Repeat:** Peel off `1`, shift `32` to `320`, add `1`, and your right hand now holds `321`. Your left hand is empty, and the process is complete.

---

## ⚙️ The Trick: Breaking Down the Logic

The core of this solution is a `while` loop that dismantles the original number and builds the reversed one.

Let's reverse the number `123`. We start with `original = 123` and `reversed = 0`.

| Iteration | `original` (Before) | `lastDigit = original % 10` | `reversed = reversed * 10 + lastDigit` | `original = Math.floor(original / 10)` | `original` (After) |
| :--- | :---: | :---: | :--- | :--- | :---: |
| **1** | `123` | `3` | `(0 * 10) + 3 = 3` | `Math.floor(12.3) = 12` | `12` |
| **2** | `12` | `2` | `(3 * 10) + 2 = 32` | `Math.floor(1.2) = 1` | `1` |
| **3** | `1` | `1` | `(32 * 10) + 1 = 321` | `Math.floor(0.1) = 0` | `0` |

The loop terminates when `original` becomes `0`, and the final answer is `321`.

### Code Example (The "Palindrome Number" Problem)

This pattern is perfectly suited to check if a number is a palindrome.

```typescript
function isPalindrome(x: number): boolean {
  // Edge Case: Negative numbers are not palindromic.
  // Also, any number ending in 0 (and is not 0 itself) cannot be a palindrome.
  if (x < 0 || (x % 10 === 0 && x !== 0)) {
    return false;
  }

  let reversedNumber = 0;
  let originalNumber = x;

  while (originalNumber > 0) {
    const lastDigit = originalNumber % 10;
    reversedNumber = reversedNumber * 10 + lastDigit;
    originalNumber = Math.floor(originalNumber / 10);
  }

  // A number is a palindrome if it equals its reverse.
  return x === reversedNumber;
}

// Usage:
console.log(isPalindrome(121));  // Output: true
console.log(isPalindrome(-121)); // Output: false
console.log(