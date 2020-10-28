package com.example.userprofile.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.userprofile.R
import kotlinx.android.synthetic.main.fragment_user_detail.*


class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }





        viewModel.firstname.observe(viewLifecycleOwner, androidx.lifecycle.Observer {


            tv_first_name.text = "First name:\"${it.toUpperCase()}\""
        })

        viewModel.lastname.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            println("lastname=$it")
            tv_last_name.text = "Last name:\"${it.toUpperCase()}\""
        })

        viewModel.birthdayFormat.observe(viewLifecycleOwner, Observer {


            tv_birthday.text = "Age:$it"

        })

        button_Back.setOnClickListener {
            activity?.run {


                with(supportFragmentManager)
                {
                    if (backStackEntryCount > 1) {
                        popBackStackImmediate()
                        beginTransaction().commit()
                    } else {


                        beginTransaction()
                                .replace(R.id.container, MainFragment.newInstance())
                                .addToBackStack("MainFragment")
                                .commit()

                    }
                }
            }

        }


    }


}