<div align="center">
  <h1>🛡️ Playfair Cipher Implementation</h1>
  <p><b>A Java-based Cryptographic Tool for Symmetric Encryption</b></p>

  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" alt="Java">
  <img src="https://img.shields.io/badge/Security-Cryptography-blue?style=for-the-badge" alt="Cryptography">
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" alt="License">
</div>

<hr />

## 📖 Overview
The **Playfair Cipher** is a manual symmetric encryption technique and was the first literal digram substitution cipher. This repository contains a robust Java implementation that handles:
* Dynamic 5x5 Matrix Generation based on a custom key.
* Automatic handling of the **'J' to 'I'** substitution.
* Digram-based encryption rules (Rectangle, Same Row, Same Column).
* Automatic padding with 'X' for repeating characters or odd-length strings.



<hr />

## ⚙️ How the Algorithm Works
This implementation follows the classic three-rule logic for transforming plaintext pairs into ciphertext:

<table>
  <tr>
    <th>Rule</th>
    <th>Logic</th>
  </tr>
  <tr>
    <td><b>Same Row</b></td>
    <td>Each letter is replaced by the letter to its immediate right (wrapping to the left if necessary).</td>
  </tr>
  <tr>
    <td><b>Same Column</b></td>
    <td>Each letter is replaced by the letter immediately beneath it (wrapping to the top if necessary).</td>
  </tr>
  <tr>
    <td><b>Rectangle</b></td>
    <td>Letters are replaced by the ones in the same row but at the corner of the rectangle defined by the pair.</td>
  </tr>
</table>

<hr />

## 🚀 Getting Started

### Prerequisites
* **Java Development Kit (JDK)** 8 or higher.

### Usage
1.  **Clone the repo:**
    ```bash
    git clone [https://github.com/YOUR_USERNAME/Playfair-Cipher.git](https://github.com/YOUR_USERNAME/Playfair-Cipher.git)
    ```
2.  **Compile the code:**
    ```bash
    javac Playfair.java
    ```
3.  **Run the application:**
    ```bash
    java Playfair
    ```

<hr />

## 🛠️ Code Features
<ul>
  <li><code>matrixgen(String key)</code>: Sanity-checks the key and populates the 5x5 grid with unique characters.</li>
  <li><code>pfencryption(String txt)</code>: Manages the preprocessing of strings (padding 'X') and coordinates the digram shift.</li>
  <li><code>matsearch(char ch)</code>: Efficiently locates the coordinates (Row, Col) of a character within the matrix.</li>
</ul>

<hr />

<div align="center">
  <p><i>Developed for Educational Purposes in Cybersecurity and Cryptography.</i></p>
</div>
