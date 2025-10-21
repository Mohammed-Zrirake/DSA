 #DSA #DataStructure #Java #String #CheatSheet #CompetitiveProgramming

>  The `String` class is Java's standard for an **immutable** sequence of characters, packed with useful methods for searching and manipulation. For any situation requiring frequent modifications (like building a string in a loop), always use the mutable `StringBuilder` for optimal performance.

---

> [!note] 📖 The Core Analogy: The Stone Tablet vs. The Clay Tablet
> Understanding the difference between `String` and `StringBuilder` is crucial.
> -   **`String` is a message carved in stone (Immutable):** Once you've written "abc," you cannot change it. If you want to add a "d," you must take a brand new stone and carve "abcd" from scratch. This is a slow and wasteful process if you're making many changes.
> -   **`StringBuilder` is a clay tablet (Mutable):** This is your draft space. You can easily add characters, delete them, or insert them in the middle without starting over. It's fast and efficient for building and modifying your message. Once you are completely finished, you can "fire" the clay into a final, permanent stone tablet by calling `.toString()`.

---

In Java, `String` is a fundamental class, not a primitive type. Its most important characteristic is **immutability**, which means once a `String` object is created, its value cannot be changed.

## ✅ Pros and ❌ Cons of `String`

### Pros
✔️ **Predictable & Safe:** Because they are immutable, `String` objects are thread-safe and can be shared without risk of modification.
✔️ **Easy to Use:** The class is packed with a rich set of built-in methods.

### Cons
❌ **Inefficient Modifications:** Every modification (like concatenation in a loop) creates a *new* `String` object, which can lead to poor performance and high memory usage.

---

## ☕ Java `String` Method Cheat Sheet

### 🧱 Basic Info / Properties
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `length()` | Returns the length of the string | `"abc".length() -> 3` |
| `charAt(int index)` | Access a specific character | `"abc".charAt(1) -> 'b'` |
| `toCharArray()` | Convert to a character array | `"abc".toCharArray()` |

### 🧩 Comparison & Equality
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `equals(Object obj)` | Compare for exact equality | `"abc".equals("abc") -> true` |
| `equalsIgnoreCase(String another)` | Case-insensitive comparison | `"AbC".equalsIgnoreCase("abc") -> true` |
| `compareTo(String another)` | Lexicographical (dictionary order) comparison | `"abc".compareTo("abd") -> < 0` |
| `compareToIgnoreCase(...)` | Case-insensitive lexicographical compare | `"AbC".compareToIgnoreCase("abc") -> 0` |

### ✂️ Substring & Character Extraction
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `substring(int beginIndex)` | Substring from `beginIndex` to the end | `"abcdef".substring(2) -> "cdef"` |
| `substring(int begin, int end)` | Substring from `begin` (inclusive) to `end` (exclusive) | `"abcdef".substring(2, 5) -> "cde"` |

### 🔠 Case Conversion
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `toLowerCase()` | Convert all characters to lowercase | `"ABC".toLowerCase() -> "abc"` |
| `toUpperCase()` | Convert all characters to uppercase | `"abc".toUpperCase() -> "ABC"` |

### ⚙️ Searching
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `indexOf(char c)` | First index of a character | `"banana".indexOf('a') -> 1` |
| `indexOf(String str)` | First index of a substring | `"banana".indexOf("na") -> 2` |
| `indexOf(String str, int from)` | Search for a substring from a starting index | `"banana".indexOf("na", 3) -> 4` |
| `lastIndexOf(char c)` | Last index of a character | `"banana".lastIndexOf('a') -> 5` |
| `contains(CharSequence seq)` | Returns `true` if substring is present | `"abc".contains("b") -> true` |
| `startsWith(String prefix)` | Check if the string begins with a prefix | `"hello".startsWith("he") -> true` |
| `endsWith(String suffix)` | Check if the string ends with a suffix | `"hello".endsWith("lo") -> true` |

### 🧼 Modification / Cleanup
> [!warning] Remember Immutability
> All these methods return a **new** `String` object. They do not modify the original.

| Method | Purpose | Example |
| :--- | :--- | :--- |
| `trim()` | Remove leading/trailing whitespace | `" abc ".trim() -> "abc"` |
| `replace(char old, char new)` | Replace all occurrences of a character | `"abac".replace('a', 'x') -> "xbxc"` |
| `replace(CharSequence target, Char...`| Replace all occurrences of a substring | `"aabb".replace("aa", "bb") -> "bbbb"` |
| `replaceAll(String regex, String r)` | Regex-based replacement | `"a1b2".replaceAll("\\d", "") -> "ab"` |
| `replaceFirst(String regex, String r)` | Replace only the first regex match | `"123123".replaceFirst("1", "X") -> "X23123"` |

### 🪓 Splitting & Joining
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `split(String regex)` | Split into a `String[]` array by a delimiter | `"a,b,c".split(",") -> ["a","b","c"]` |
| `split(String regex, int limit)` | Split with a limit on the number of splits | `"a,b,c".split(",", 2) -> ["a", "b,c"]` |
| `String.join(CharSequence d, ...)` | *Static method* to join an iterable with a delimiter | `String.join("-", List.of("a","b","c")) -> "a-b-c"` |

### 🔁 Building & Reversing (with `StringBuilder`)
> [!danger] The Immutability Trap & The `StringBuilder` Solution
> Never build a string in a loop using `+` or `+=`. This creates a new `String` object on every single iteration and is extremely slow. **Always use `StringBuilder` for string construction.**

| `StringBuilder` Method | Purpose | Example |
| :--- | :--- | :--- |
| `new StringBuilder("abc")` | Create a mutable string builder | `StringBuilder sb = new StringBuilder("abc");` |
| `.append(...)` | Add to the end (very fast) | `sb.append("xyz");` |
| `.insert(int offset, String str)` | Insert a string at a specific index | `sb.insert(1, "X"); // "aXbc"` |
| `.delete(int start, int end)` | Delete a substring | `sb.delete(1, 3); // "ac"` |
| `.reverse()` | Reverse the contents of the builder | `sb.reverse(); // "cba"` |
| `.toString()` | Convert the final result back to an immutable `String` | `String final = sb.toString();` |

### 🧮 Utility / Conversions
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `String.valueOf(...)` | Convert a primitive (int, char, etc.) to a string | `String.valueOf(42) -> "42"` |
| `Integer.parseInt(String)` | Convert a string to an `int` | `Integer.parseInt("123") -> 123` |
| `Long.parseLong(String)` | Convert a string to a `long` | `Long.parseLong("999999999")` |
| `Character.getNumericValue(char)` | Convert a character digit to an `int` | `Character.getNumericValue('9') -> 9` |

### 💡 Rare but Handy
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `matches(String regex)` | Check if the *entire* string matches a regex | `"123".matches("\\d+") -> true` |
| `intern()` | Places the string in the JVM's string pool | `"abc".intern()` (Rarely used in CP) |
| `isEmpty()` | Check if `length() == 0` | `"".isEmpty() -> true` |
| `isBlank()` (Java 11+) | Check if empty or contains only whitespace | `"   ".isBlank() -> true` |
| `repeat(int count)` (Java 11+) | Repeat the string `count` times | `"ab".repeat(3) -> "ababab"` |

---

> [!success] ⚡ Most Frequently Used in Competitive Programming
> If you want a shortlist of what you’ll use 90% of the time, master these:
> - `length()`
> - `charAt()`
> - `substring()`
> - `toCharArray()` (for faster iteration)
> - `indexOf()`
> - `split()`
> - `equals()`
> - `startsWith()` / `endsWith()`
> - `replace()`
> - **`StringBuilder`** (for all string construction)
> - `Integer.parseInt()` / `Long.parseLong()`
> - `String.valueOf()`