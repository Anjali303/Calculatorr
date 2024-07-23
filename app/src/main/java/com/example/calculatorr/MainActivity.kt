package com.example.calculatorr

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class MainActivity : ComponentActivity() {

    private lateinit var editText: EditText
    private var currentNumber: String = ""
    private var operator: String? = null
    private var firstOperand: Double = 0.0
    private var secondOperand: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                val number = (it as Button).text.toString()
                currentNumber += number
                editText.setText(currentNumber)
            }
        }

        findViewById<Button>(R.id.btnDecimal).setOnClickListener {
            if (!currentNumber.contains(".")) {
                currentNumber += "."
                editText.setText(currentNumber)
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            currentNumber = ""
            operator = null
            editText.setText("")
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            if (currentNumber.isNotEmpty()) {
                currentNumber = currentNumber.dropLast(1)
                editText.setText(currentNumber)
            }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("/") }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            if (operator != null && currentNumber.isNotEmpty()) {
                secondOperand = currentNumber.toDouble()
                val result = when (operator) {
                    "+" -> firstOperand + secondOperand
                    "-" -> firstOperand - secondOperand
                    "*" -> firstOperand * secondOperand
                    "/" -> firstOperand / secondOperand
                    else -> 0.0
                }
                editText.setText(result.toString())
                currentNumber = result.toString()
                operator = null
            }
        }
    }

    private fun setOperator(op: String) {
        if (currentNumber.isNotEmpty()) {
            firstOperand = currentNumber.toDouble()
            operator = op
            currentNumber = ""
        }
    }
}
