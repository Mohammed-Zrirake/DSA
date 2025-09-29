#Algorithm #DataStructure #HashMap #Optimization #CoreConcept #InterviewPattern

>  Use a **HashMap** to solve "find a pair that sums to a target" problems in a single pass **(O(n))**. This pattern dramatically improves performance by trading a small amount of memory for a massive reduction in time complexity.

---

> [!note] 📖 The Core Analogy: The Matchmaker at a Dance
> Imagine a dance where everyone is looking for a partner with a specific number to add up to 10.
> -   **The Slow Way (Nested Loop):** You, as the first person (`7`), have to go and ask every other person at the dance, "Are you a `3`?" This is slow and inefficient.
> -   **The Hash Map Way (The Matchmaker):** A matchmaker stands at the door with a list (`HashMap`).
>     1.  The first person (`7`) arrives. They tell the matchmaker, "I'm looking for a `3`."
>     2.  The matchmaker checks their list. "Nope, no `3` has arrived yet."
>     3.  The matchmaker says, "Okay, but I'll add *you* to my list in case someone comes looking for a `7`." The list now contains `{ 7: ... }`.
>     4.  The next person (`2`) arrives. "I'm looking for an `8`." The matchmaker checks the list, finds no `8`, and adds `{ 2: ... }` to the list.
>     5.  The third person (`3`) arrives. "I'm looking for a `7`."
>     6.  The matchmaker checks the list. "Aha! `7` is on my list! It's a match!"
>
> This process is incredibly fast. Each person only needs to talk to the matchmaker once, and the matchmaker's lookup is instantaneous (O(1)).

---

## 🤔 The Problem: The Slow Nested Loop (O(n²))

The brute-force approach to finding a pair that sums to a target involves a nested loop. For each element, you iterate through the rest of the array to see if you can find its complement.

```typescript
// Brute-force O(n²) approach
function findPairSlowly(nums: number[], target: number): number[] | null {
  for (let i = 0; i < nums.length; i++) {
    for (let j = i + 1; j < nums.length; j++) {
      if (nums[i] + nums[j] === target) {
        return [i, j];
      }
    }
  }
  return null;
}
```
This works, but it's very inefficient for large arrays because the number of comparisons grows quadratically.

## 💡 The Solution: The Single-Pass Hash Map (O(n))

The optimized solution uses a HashMap to store the numbers we've already seen and their indices.

### The Logic (Step-by-Step)
1.  Initialize an empty HashMap (in JavaScript, a `Map` or a plain object).
2.  Loop through the array **exactly once**.
3.  Inside the loop, for the current number `num`, calculate its **complement**: `complement = target - num`.
4.  Check if this `complement` **already exists as a key** in the HashMap.
    -   **If YES:** You have found the pair! The answer is the index of the complement (which you retrieve from the map) and the index of the current number.
    -   **If NO:** Add the **current number `num`** and its index to the HashMap, so it can be found as a complement by a future number.

### Code Example (The "Two Sum" Problem)

```typescript
function twoSum(nums: number[], target: number): number[] | null {
  // The map will store: { number => index }
  const seenNumbers = new Map<number, number>();

  for (let i = 0; i < nums.length; i++) {
    const currentNum = nums[i];
    const complement = target - currentNum;

    // Check if the complement we need has been seen before
    if (seenNumbers.has(complement)) {
      // If yes, we've found our pair!
      return [seenNumbers.get(complement)!, i];
    }

    // If not, add the current number and its index to the map for future lookups
    seenNumbers.set(currentNum, i);
  }

  // If we finish the loop without finding a pair
  return null;
}

// Usage:
const nums = [2, 7, 11, 15];
const target = 9;
console.log(twoSum(nums, target)); // Output: [0, 1] (because nums[0] + nums[1] === 9)
```

## 📈 Complexity Analysis

-   **Time Complexity: O(n)**
    We iterate through the array of `n` elements exactly once. Each lookup (`.has()`) and insertion (`.set()`) in a HashMap is, on average, a constant time O(1) operation.
-   **Space Complexity: O(n)**
    In the worst-case scenario (where no pair is found until the very end, or not at all), the HashMap will store up to `n` elements.

## 🤔 When to Use This Pattern

You should immediately consider this pattern whenever a problem involves:

-   **Finding pairs of elements that sum up to a specific target** (the classic "Two Sum" problem).
-   **Checking for the existence of duplicates** in an array (if `map.has(currentNumber)`, you've found a duplicate).
-   **Finding anagrams** (use a map to store character counts and compare them).
-   Any situation where you want to **avoid a nested loop** that searches for a second element with a specific relationship (e.g., complementary, identical) to the current element.

---

> [!summary] Key Takeaways
> - **The Problem:** Finding pairs in an array with a nested loop is slow (O(n²)).
> - **The Solution:** Use a **HashMap** to store seen elements.
> - **The Logic:** In a single loop, for each element, **check if its complement exists in the map, then add the element itself to the map.**
> - **The Trade-off:** You use extra memory (O(n) space) to achieve a massive speedup (O(n) time).
> - This is a fundamental pattern for optimizing array-based problems.