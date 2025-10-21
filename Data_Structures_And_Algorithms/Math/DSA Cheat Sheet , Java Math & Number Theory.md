
 #DSA #Java #Math #CheatSheet #CompetitiveProgramming #NumberTheory

> Java's `Math` class provides core mathematical functions, but for competitive programming, you **must** have a ready-to-use toolkit of custom functions for number theory staples like GCD, LCM, and modular exponentiation.

---

> [!note] 📖 The Core Analogy: The Scientific Calculator
> The `java.lang.Math` class is your basic scientific calculator. It has all the standard buttons you'd expect: `sin`, `cos`, `sqrt`, `pow`, etc. It's reliable and built-in.
>
> However, a competitive programmer is like an advanced mathematician. They need more than the standard buttons. They have a second, programmable calculator (your custom utility class) with powerful, pre-written programs for complex operations like modular exponentiation (`modPow`) or finding the greatest common divisor (`gcd`). Knowing how to use both calculators effectively is key to solving a wide range of problems.

---

## 🧮 `java.lang.Math` — The Standard Library

### 🧱 Basic Functions
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `Math.abs(x)` | Absolute value | `Math.abs(-5.5) -> 5.5` |
| `Math.max(a, b)` | Maximum of two numbers | `Math.max(5, 10) -> 10` |
| `Math.min(a, b)` | Minimum of two numbers | `Math.min(5, 10) -> 5` |
| `Math.signum(x)`| Sign of a number (-1.0, 0.0, 1.0) | `Math.signum(-15) -> -1.0` |

### ✖️ Power & Roots
| Method | Purpose | Example & Notes |
| :--- | :--- | :--- |
| `Math.pow(a, b)`| `a` to the power of `b` | `Math.pow(2, 3) -> 8.0` (**Returns a `double`!**) |
| `Math.sqrt(x)` | Square root of `x` | `Math.sqrt(16) -> 4.0` |
| `Math.cbrt(x)` | Cube root of `x` | `Math.cbrt(27) -> 3.0` |
| `Math.hypot(x, y)`| Euclidean distance `√(x² + y²)` | `Math.hypot(3, 4) -> 5.0` (Avoids overflow) |

### 🔢 Rounding & Truncation
| Method | Purpose | Example |
| :--- | :--- | :--- |
| `Math.floor(x)` | Round down to nearest integer | `Math.floor(2.7) -> 2.0` |
| `Math.ceil(x)` | Round up to nearest integer | `Math.ceil(2.3) -> 3.0` |
| `Math.round(x)` | Round to nearest `long` or `int` | `Math.round(2.5) -> 3L`, `Math.round(2.4) -> 2L` |

### 🌐 Trigonometry (in Radians)
| Method | Purpose | Example & Notes |
| :--- | :--- | :--- |
| `Math.toDegrees(rad)` | Convert radians to degrees | |
| `Math.toRadians(deg)` | Convert degrees to radians | `Math.toRadians(90)` |
| `Math.sin(rad)` | Sine of an angle | `Math.sin(Math.PI / 2) -> 1.0` |
| `Math.cos(rad)` | Cosine of an angle | `Math.cos(0) -> 1.0` |
| `Math.tan(rad)` | Tangent of an angle | |
| `Math.atan2(y, x)` | Angle θ from (x, y) coordinates | The best way to calculate angles in geometry problems. |

### 📈 Exponential & Logarithms
| Method | Purpose | Example & Notes |
| :--- | :--- | :--- |
| `Math.exp(x)` | `e` to the power of `x` | |
| `Math.log(x)` | **Natural log (ln)** of `x` | `Math.log(Math.E) -> 1.0` |
| `Math.log10(x)` | Base-10 log of `x` | |
| `Math.log1p(x)` | `ln(1+x)` | More precise for very small `x`. |

---

## 🔢 CP Number Theory Toolkit (Custom Functions)
> [!danger] Essential: These are not built-in. You must write them yourself or have them in a template.

### Greatest Common Divisor (GCD) & Least Common Multiple (LCM)
```java
// GCD (Euclidean Algorithm)
static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
}

// LCM
static long lcm(long a, long b) {
    // To avoid overflow, divide before multiplying
    return (a / gcd(a, b)) * b;
}
```

### Modular Arithmetic
> [!tip] A `long` is often necessary for intermediate products to avoid overflow before taking the modulo.

```java
// Modular Exponentiation: (a^b % mod)
// Fast and avoids overflow
static long modPow(long a, long b, long mod) {
    long res = 1;
    a %= mod;
    while (b > 0) {
        if ((b & 1) == 1) res = (res * a) % mod;
        a = (a * a) % mod;
        b >>= 1; // b = b / 2
    }
    return res;
}

// Modular Multiplicative Inverse: (a^-1 % mod)
// Only works if mod is a prime number (Fermat's Little Theorem)
static long modInverse(long a, long mod) {
    return modPow(a, mod - 2, mod);
}
```

### Combinatorics
```java
// nCr (Combinations: "n choose r")
// Works for small n, without modulo
static long nCr(int n, int r) {
    if (r > n) return 0;
    if (r > n / 2) r = n - r; // Optimization
    long res = 1;
    for (int i = 1; i <= r; i++) {
        res = res * (n - i + 1) / i;
    }
    return res;
}
```
> [!warning] For large `n` with modulo arithmetic, you must precompute factorials and their modular inverses to calculate `nCr = fact[n] * invFact[r] * invFact[n-r] % mod`.

---

## ⚡ Bitwise & Miscellaneous Tricks
| Task | Trick | Notes |
| :--- | :--- | :--- |
| Check if `n` is power of 2 | `n > 0 && (n & (n - 1)) == 0` | Crucial check. |
| Count set bits (number of 1s) | `Integer.bitCount(n)` | Built-in and fast. |
| Get highest set bit | `Integer.highestOneBit(n)` | e.g., for `13 (1101)` returns `8 (1000)` |
| Floor of log₂(n) | `31 - Integer.numberOfLeadingZeros(n)` | Fast, integer-based log2. |
| Ceil of log₂(n) | `32 - Integer.numberOfLeadingZeros(n - 1)`| |
| Swap `a` and `b` without a temp var | `a ^= b; b ^= a; a ^= b;` | XOR swap. |

---

## 🐘 `BigInteger` for Huge Numbers
> [!tip] When numbers exceed `Long.MAX_VALUE`, `BigInteger` is your only option. It's slower but essential.

| `BigInteger` Method | Purpose | Example |
| :--- | :--- | :--- |
| `BigInteger.valueOf(long val)` | Create from `long` | `BigInteger b = BigInteger.valueOf(100);` |
| `new BigInteger(String val)` | Create from `String` | `BigInteger b = new BigInteger("123...");` |
| `.add(other)`, `.subtract(o)`, `.multiply(o)`, `.divide(o)` | Basic arithmetic | `b1.add(b2)` |
| `.modPow(exp, mod)` | Modular exponentiation | `a.modPow(b, m)` |
| `.modInverse(mod)` | Modular inverse | `a.modInverse(m)` |
| `.isProbablePrime(certainty)` | Primality test | `b.isProbablePrime(10)` |
| `.gcd(other)` | Greatest Common Divisor | `a.gcd(b)` |

---

> [!success] 🚀 Most Used in CP
> - `Math.abs()`, `Math.max()`, `Math.min()`
> - `Math.pow()`, `Math.sqrt()`
> - `Math.ceil()`, `Math.floor()`
> - `Integer.bitCount()`
> - Your custom `gcd(a,b)`
> - Your custom `lcm(a,b)`
> - Your custom `modPow(a,b,mod)`