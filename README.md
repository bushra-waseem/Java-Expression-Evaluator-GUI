# Expression Evaluator (Infix to Postfix) 🧮

A professional Java desktop application that evaluates mathematical expressions. It utilizes the **Stack** data structure to convert Infix notation into Postfix (Reverse Polish Notation) and computes the final result.

---

## 📸 Interface Showcase

### 1. Complex Expression Handling
The application correctly handles parentheses and multiple operators, showing the step-by-step conversion.
![Main Interface 01](Screenshots/01%20main%20interface.png)

### 2. Multi-digit & Operator Precedence
Demonstrating the system's ability to prioritize operations like multiplication and division over addition.
![Main Interface 02](Screenshots/02%20main%20interface.png)

---

## 🧠 Core Logic & Algorithm
This project is a practical implementation of two major Stack algorithms:

1. **Shunting-Yard Algorithm:** To convert standard expressions (Infix) into a machine-readable format (Postfix).
2. **Postfix Evaluation:** Using a second stack to calculate the result from the converted expression.


### Example Workflow:
* **User Input:** `100 / (2 * 5) + 15`
* **Internal Postfix:** `100 2 5 * / 15 +`
* **Computed Result:** `25.0`

---

```mermaid
graph TD
    A[Input Expression] --> B{Is it an Operand?}
    B -- Yes --> C[Add to Output/Postfix]
    B -- No --> D{Is it an Operator?}
    D -- "(" --> E[Push to Stack]
    D -- ")" --> F["Pop till '('"]
    D -- "+, -, *, /" --> G{Check Precedence}
    G -- Higher --> H[Push to Stack]
    G -- Lower/Equal --> I[Pop Stack to Output & Push New]
    C --> J[End of Expression?]
    F --> J
    H --> J
    I --> J
    J -- Yes --> K[Pop Remaining Stack to Output]
    K --> L[Final Postfix Result]
```

## ✨ Features
* **Modern Dark UI:** Clean aesthetic for a professional look.
* **Error Validation:** Detects and reports invalid mathematical expressions.
* **Multi-digit Support:** Efficiently parses numbers with multiple digits and decimals.
* **Real-time Feedback:** Shows both the postfix string and the result instantly.

## 🛠️ Technical Stack
* **Language:** Java
* **UI Framework:** Java Swing / AWT (GUI)
* **Data Structure:** Stack (Last-In-First-Out logic)

### ⚙️ How to Setup & Run

1. **Clone the Repo:**
   ```bash
 git clone https://github.com/bushra-waseem/Java-Expression-Evaluator-GUI.git
 Import the project into IntelliJ IDEA, Eclipse, or NetBeans.
 Run the main Java file to launch the GUI window.
```

### 🤝 Connect with Me
* **LinkedIn:** [Bushra Waseem](APNA_LINKEDIN_URL_YAHAN_DALAIN)
