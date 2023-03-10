package uz.akbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import uz.akbar.mode.Test

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var tests = arrayListOf<Test>()
    var qwe = ArrayList<Int>()
    var count = 0
    var c: Boolean = false




    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qwe.add(-1)
        qwe.add(-1)
        qwe.add(-1)
        qwe.add(-1)
        tests.add(Test("6 + 6 / 6", "6", "7", "10", "15", "7"))
        tests.add(Test("8 * 2 - 9", "12", "7", "100", "24", "7"))
        tests.add(Test("10 + 9 - 12", "14", "7", "17", "31", "7"))
        tests.add(Test("7 + 7 - 7 * 7 / 7", "16", "7", "21", "4", "7"))

        createNumber(tests.size)
        createTest(index)

        next.setOnClickListener {
            val checkedRadioButtonId = answers.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
            when (answers.checkedRadioButtonId) {
                -1 -> {
                    Toast.makeText(this, "Tanlanmagan", Toast.LENGTH_LONG).show()
                }
                else -> {
                    if (tests[index].correct_answer == selectedRadioButton.text && !c && index<tests.size){
                        if(index==tests.size-1){
                            c = true
                        }
                        count ++
                    }
                }
            }

            if (answers.checkedRadioButtonId != null) {
                setJavob(index, answers.checkedRadioButtonId)
            }

            if (index < tests.size - 1) {
                index++
            }
            createTest(index)
            if (index == tests.size - 1){
                finish.visibility = View.VISIBLE
                next.visibility = View.GONE
                previous.visibility = View.GONE
            }

        }
        previous.setOnClickListener {
            val checkedRadioButtonId = answers.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
            if (answers.checkedRadioButtonId != null) {
                setJavob(index, answers.checkedRadioButtonId)
            }
            if (index!=0){
                --index
            }
            createTest(index)

        }

        finish.setOnClickListener {
            val checkedRadioButtonId = answers.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
            if (tests[index].correct_answer == selectedRadioButton.text && !c && index<tests.size){
                if(index==tests.size-1){
                    c = true
                }
                count ++
            }
            result.visibility = View.VISIBLE
            result.text = count.toString()



        }

    }


    fun setJavob(ind: Int, q: Int) {
        qwe[ind] = q
    }

    fun createTest(n: Int) {
        var test = tests[n]
        answers.check(-1)
        question.text = test.question
        answer_1.text = test.answer1
        answer_2.text = test.answer2
        answer_3.text = test.answer3
        answer_4.text = test.answer4
        if (qwe[n] != -1) {
            answers.check(qwe[n])
        }
        createNumber(4)


    }
    fun createNumber(n: Int) {
        questions_number.removeAllViews()
        for (i in 0 until n) {
            var btn = Button(this)
            btn.id = i
            btn.text = "${i + 1}"
            btn.tag = "$i"
            if (qwe[i] != -1) {
                btn.setBackgroundResource(R.drawable.btn_bg)
                btn.setTextColor(Color.WHITE)
            }
            btn.setOnClickListener(this)

            questions_number.addView(btn)
        }
    }


    override fun onClick(p0: View?) {
        var btn = findViewById<Button>(p0!!.id)
        if (answers.checkedRadioButtonId != -1) setJavob(index, answers.checkedRadioButtonId)
        index = btn.tag.toString().toInt()
        createTest(index)
        if (index != tests.size - 1){
            next.visibility = View.VISIBLE
            finish.visibility = View.GONE
            previous.visibility = View.VISIBLE
        }else if (index == tests.size - 1) {
            next.visibility = View.GONE
            finish.visibility = View.VISIBLE
        }
    }


}

