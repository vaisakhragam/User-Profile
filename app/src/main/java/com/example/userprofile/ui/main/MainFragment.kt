package com.example.userprofile.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.userprofile.R
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }



        button_next.setOnClickListener {

            if (edittext_first_name.text.toString()
                            .isNotEmpty() && edittext_last_name.text.toString()
                            .isNotEmpty() && edittext_birthday.text.toString().isNotEmpty()
            ) {

                viewModel.firstname.value = edittext_first_name.text.toString()
                viewModel.lastname.value = edittext_last_name.text.toString()

                activity?.run {

                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, UserFragment.newInstance())
                            .addToBackStack("UserFragment")
                            .commit()
                }

            }
        }

        viewModel.firstname.observe(viewLifecycleOwner, androidx.lifecycle.Observer {


            edittext_first_name.setText(it)
        })

        viewModel.lastname.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            edittext_last_name.setText(it)
        })
        viewModel.birthdate.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            edittext_birthday.setText(it)
        })


        edittext_birthday.setOnClickListener {

            viewModel.showDatePickerDialog(context!!)

        }

    }


}