# 🔮 SolSort v2.2

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-Stable-success?style=for-the-badge)
![UI](https://img.shields.io/badge/UI-Dynamic_CLI-purple?style=for-the-badge)
![Grade](https://img.shields.io/badge/Target_Grade-100%2F100-blue?style=for-the-badge)

**SolSort** is an advanced, menu-driven Algorithm Suite designed to visualize the performance and logic of fundamental Searching and Sorting algorithms. 

Now featuring **Version 2.2** with true terminal clearing, detailed statistical analysis, and robust memory management.

---

## 📋 Table of Contents
- [✨ New Features](#-new-features-v22)
- [⚙️ Algorithms Implemented](#-algorithms-implemented)
- [🚀 Installation & Usage](#-installation--usage)
- [🖥️ Interface Preview](#-interface-preview)
- [📂 Project Structure](#-project-structure)

---

## ✨ New Features (v2.2)

### 🖥️ "True Clear" Dynamic UI
Unlike standard Java console apps that just scroll text, SolSort uses **OS-Native Commands** to physically clear the terminal history:
- **Windows:** Invokes `cmd /c cls`
- **Mac/Linux:** Invokes ANSI Escape Codes
- *Result:* A clean, dashboard-like experience for every menu transition.

### 📊 Advanced Data Visualization
Go beyond simple lists. The new **Option 9** provides a deep dive into your data:
- **Grid View:** Formats large arrays into a readable 10-column grid.
- **Live Stats:** Instantly calculates **Min**, **Max**, and **Average**.

### 🛡️ "Safe" Binary Search
The program acts as a safety net. If you attempt a **Binary Search** on unsorted data:
1. The system detects the unsorted state.
2. It warns the user.
3. It **automatically sorts** the array (using QuickSort) before searching.

### 💾 Memory Management
- **Persistence:** The array stays in memory allowing multiple operations (e.g., Search, then Sort, then Search again).
- **Reset:** Use **Option 10** to wipe the memory and start fresh without restarting the application.

---

## ⚙️ Algorithms Implemented

All algorithms adhere to strict method signatures for academic compliance.

### Searching
| Algorithm | Time Complexity | Description |
| :--- | :--- | :--- |
| **Linear Search** | $O(N)$ | Iterates sequentially. Safe for unsorted data. |
| **Binary Search** | $O(\log N)$ | Divide and conquer. **Requires Sorted Data.** |

### Sorting
| Algorithm | Time Complexity | Description |
| :--- | :--- | :--- |
| **Bubble Sort** | $O(N^2)$ | Swaps adjacent elements repeatedly. |
| **Selection Sort** | $O(N^2)$ | Selects the minimum and moves it to the front. |
| **Quick Sort** | $O(N \log N)$ | Recursive partitioning around a pivot. |
| **Heap Sort** | $O(N \log N)$ | Sorts using a Binary Heap tree structure. |

---

## 🚀 Installation & Usage

### Prerequisites
- Java JDK 8 or higher.
- A standard Terminal (Command Prompt, PowerShell, Bash, or VS Code Terminal).

### Steps
1. **Clone the repository**
   ```bash
   git clone https://github.com/chiMsyt/SolSort.git
   cd SolSort
   ```
2. **Compile the source**
   ```bash
   javac SolSort.java
   ```
3. **Run the application**
   ```bash
   java SolSort
   ```

---

## 🖥️ Interface Preview

When running the program, you will interact with a clean, color-coded menu:

```text
   _____       _  _____            _   
  / ____|     | |/ ____|          | |  
 | (___   ___ | | (___   ___  _ __| |_ 
  \___ \ / _ \| |\___ \ / _ \| '__| __|
  ____) | (_) | |____) | (_) | |  | |_ 
 |_____/ \___/|_|_____/ \___/|_|   \__|
       v2.2 | Algorithms Suite

 [ STATUS: MEMORY LOADED ]
 Size: 50 integers
 Status: Unsorted
------------------------------------------------
1. Input New Array (Manual)
2. Generate Random Array
...
9. View Full Array Details (Stats + Grid View)
10. Clear Memory / Reset
11. Exit
>> Select option: _
```

---

## 📂 Project Structure

```bash
SolSort/
├── .gitignore          # Ignores compiled binaries/logs
├── README.md           # Documentation
└── SolSort.java        # Main Application Source Code
```

---

### 👨‍💻 Author
**Tim**

*Data Science and Analytics Student*
