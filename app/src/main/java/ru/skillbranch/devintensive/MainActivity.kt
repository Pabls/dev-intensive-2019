package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.devintensive.models.Bender
import ru.skillbranch.devintensive.models.Bender.Question

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_STATUS = "EXTRA_STATUS"
        const val EXTRA_QUESTION = "EXTRA_QUESTION"
    }

    private lateinit var benderImage: ImageView
    private lateinit var textTxt: TextView
    private lateinit var messageEt: EditText
    private lateinit var sendBtn: ImageView

    private lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initBender(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(EXTRA_STATUS, benderObj.status.name)
        outState?.putString(EXTRA_QUESTION, benderObj.question.name)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send)
            if (isAnswerValid())
                sendAnswer()
            else makeErrorMessage()
    }

    private fun initView() {
        benderImage = findViewById(R.id.iv_bender)
        textTxt = findViewById(R.id.tv_text)
        messageEt = findViewById(R.id.et_message)
        sendBtn = findViewById(R.id.iv_send)
        sendBtn.setOnClickListener(this)
        makeSendOnActionDone(messageEt)
    }

    private fun initBender(savedInstanceState: Bundle?) {
        val status = savedInstanceState?.getString(EXTRA_STATUS) ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString(EXTRA_QUESTION) ?: Bender.Question.NAME.name

        benderObj = Bender(Bender.Status.valueOf(status), Question.valueOf(question))

        val (red, green, blue) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(red, green, blue), PorterDuff.Mode.MULTIPLY)

        setMessage(benderObj.askQuestion())
    }

    private fun makeSendOnActionDone(editText: EditText) {
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) sendBtn.performClick()
            false
        }
    }

    private fun makeErrorMessage() {
        val errorMessage = when (benderObj.question) {
            Question.NAME -> getString(R.string.question_error_name)
            Question.PROFESSION -> getString(R.string.question_error_profession)
            Question.MATERIAL -> getString(R.string.question_error_material)
            Question.BDAY -> getString(R.string.question_error_bday)
            Question.SERIAL -> getString(R.string.question_error_serial)
            else -> getString(R.string.question_end)
        }
        setMessage("$errorMessage\n${benderObj.question.question}")
        clearEditText()
    }

    private fun isAnswerValid(): Boolean {
        return benderObj.question.validate(messageEt.text.toString())
    }

    private fun sendAnswer() {
        val (phase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
        clearEditText()
        val (red, green, blue) = color
        benderImage.setColorFilter(Color.rgb(red, green, blue), PorterDuff.Mode.MULTIPLY)
        setMessage(phase)
    }

    private fun setMessage(text: String) {
        textTxt.text = text
    }

    private fun clearEditText() {
        messageEt.setText(getString(R.string.common_empty))
    }
}
