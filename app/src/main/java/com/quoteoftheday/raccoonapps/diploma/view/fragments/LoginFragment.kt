package com.quoteoftheday.raccoonapps.diploma.view.fragments

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.quoteoftheday.raccoonapps.diploma.R
import com.quoteoftheday.raccoonapps.diploma.managers.UserManager
import com.quoteoftheday.raccoonapps.diploma.mode.pojo.User
import com.quoteoftheday.raccoonapps.diploma.view.listener.OnLoginListener
import kotlinx.android.synthetic.main.fragment_login.*;

class LoginFragment : Fragment() {

    lateinit var listener: OnLoginListener
    private var user: User? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        user = UserManager.getUserManager().getUser("expert")
        password.setText("expert")

        loginButton?.setOnClickListener{
            if (user != null) {
                if (user?.password == null) {
                    if (password.text.toString() == passwordConfirm.text.toString()) {
                        user?.password = password.text.toString()
                        listener.login(user!!)
                    } else {
                        Toast.makeText(activity, "Ошибка, Пароль не совпадает" + password.text, Toast.LENGTH_LONG).show()
                    }
                } else {
                    if (password.text.toString() == user?.password) {
                        listener.login(user!!)
                    } else {
                        Toast.makeText(activity, "Ошибка, Неверный пароль", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        this.login.addTextChangedListener(textWatcher)
    }

    val textWatcher: TextWatcher = object : TextWatcher {
        var height = 0

        override fun afterTextChanged(s: Editable?) {
            if(UserManager.getUserManager().loginUsers.contains(s.toString())) {
                isCheckUser.visibility = View.VISIBLE
                password.setText("")
                passwordConfirm.setText("")

                val headAnim = ObjectAnimator.ofFloat(isCheckUser, "scale", 0f, 1f)
                headAnim.duration = 500
                headAnim.start()

                user = UserManager.getUserManager().getUser(s.toString())
                if(user?.password == null) {
                    passwordConfirm.visibility = View.VISIBLE
                    forgotPassword.visibility = View.GONE
                    height = 230
                } else {
                    passwordConfirm.visibility = View.GONE
                    forgotPassword.visibility = View.VISIBLE
                    height = 200
                }

                setScale(0, height)
            } else {
                user = null
                setScale(height, 0)
                if (height != 0) {
                    height = 0
                    val headAnim = ObjectAnimator.ofFloat(isCheckUser, "scale", 1f, 0f)
                    headAnim.duration = 500
                    headAnim.start()
                }
            }
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

    fun setScale(start: Int, end: Int) {
        val anim = ValueAnimator.ofInt(start, end)
        anim.addUpdateListener {
            val layoutParams: ViewGroup.LayoutParams = passwordContainer.layoutParams
            layoutParams.height = it.animatedValue as Int
            passwordContainer.layoutParams = layoutParams
        }
        anim.duration = 500
        anim.start()
    }
}