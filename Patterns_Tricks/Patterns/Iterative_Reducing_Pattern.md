#Algorithm #StringManipulation #CoreConcept #InterviewPatter

>  Solve "longest common prefix" problems by making an optimistic assumption—that the **first string is the answer**—and then iteratively **shortening** it as you check it against every other string in the list.

---

> [!note] 📖 The Core Analogy: The Master Key Maker
> A key maker is given a bundle of different locks and is asked to create a single master key that can open all of them.
> -   **The Initial Candidate (The First String):** The key maker takes the longest, most complex key blank they have (`strs[0]`). They assume this might be the master key.
> -   **Verify Against Each Lock (The Loop):** They take this key and try it on the first lock. It works. Then they move to the second lock. It doesn't fit—it's too long.
> -   **Reduce Until it Matches (The `while` Loop):** The key maker takes the key and files a tiny bit off the end. They try it again on the second lock. Still too long. They file a bit more. They repeat this "file and try" process until the key finally turns the second lock.
> -   **The Final Result:** The key maker now has a shorter key. They take this *new, shorter key* and repeat the process with the third lock, filing it down even more if necessary. The key that remains after successfully opening every single lock is the longest possible master key.

---

## 🤔 The Problem: Finding Commonality in a Collection

The challenge in finding a longest common prefix is that you must find a prefix that is valid for *every single string* in an array. A naive approach might involve complex, multi-pointer character-by-character comparisons across all strings at once, which can be difficult to write and reason about.

## 💡 The Solution: Assume and Reduce

This pattern simplifies the problem by turning it into a process of elimination. It's also sometimes referred to as "Vertical Scanning" because it effectively checks the validity of each character position across all strings.

### The Logic (Step-by-Step)
1.  **Handle Edge Cases:** If the input array is empty, return an empty string.
2.  **Assume an Initial Candidate:** Start by optimistically assuming the *entire first string* (`strs[0]`) is the longest common prefix.
3.  **Verify Against Each Item:** Loop through the rest of the strings in the array (from the second string onwards).
4.  **Reduce Until it Matches:** For each string, enter a `while` loop. As long as the current string does **not** `startsWith()` our candidate prefix, shorten the prefix by removing its last character.
5.  **Check for No Match:** If the prefix ever becomes empty, it means there is no common prefix at all, so you can stop and return `""`.
6.  **The Survivor is the Winner:** The prefix that survives the reduction process for all the strings is the final answer.

### Code Example (Longest Common Prefix)

```typescript
function longestCommonPrefix(strs: string[]): string {
  // 1. Handle edge case of an empty array.
  if (strs.length === 0) {
    return "";
  }

  // 2. Assume the entire first string is the common prefix.
  let prefix = strs[0];

  // 3. Iterate through the rest of the strings to verify.
  for (let i = 1; i < strs.length; i++) {
    // 4. Reduce the prefix until the current string starts with it.
    while (!strs[i].startsWith(prefix)) {
      prefix = prefix.slice(0, prefix.length - 1);

      // 5. If the prefix becomes empty, there's no common prefix.
      if (prefix === "") {
        return "";
      }
    }
  }

  // 6. The prefix that survived all checks is the answer.
  return prefix;
}

// Usage:
const words = ["flower", "flow", "flight"];
console.log(longestCommonPrefix(words)); // Output: "fl"

const words2 = ["dog", "racecar", "car"];
console.log(longestCommonPrefix(words2)); // Output: ""
```

## 🤔 When to Use This Pattern

This "Iterative Reduction" approach is very effective for problems where you need to find a commonality across a collection of items. Consider using it when:

-   You are looking for a **"longest" or "shortest" common attribute** among a set of inputs (e.g., prefix, suffix, substring).
-   You can start with a **candidate solution** from one of the inputs and then check its validity against the others.
-   The problem allows you to easily **"reduce" or "shrink"** your candidate solution if it's proven invalid (e.g., removing a character from a string).
-   The constraints are built up by checking each item in a collection sequentially.

---

> [!summary] Key Takeaways
> - **The Core Logic:** Assume, Verify, Reduce.
> - **Assume:** Start with an optimistic candidate solution (e.g., the first item).
> - **Verify:** Loop through the remaining items to check the candidate's validity.
> - **Reduce:** If the candidate is invalid, systematically shrink it until it becomes valid for the current item.
> - This pattern elegantly simplifies problems that involve finding a common attribute across a collection.