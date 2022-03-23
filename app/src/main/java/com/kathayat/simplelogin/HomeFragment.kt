package com.kathayat.simplelogin

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kathayat.simplelogin.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        fun getAllData(){
            Api.retrofitService.getAllData().enqueue(object: Callback<List<PackageManager.Property>> {

                override fun onResponse(
                    call: Call<List<PackageManager.Property>>,
                    response: Response<List<PackageManager.Property>>
                ) {
                    if(response.isSuccessful){
                        recyclerView = binding.recyclerView.apply{
                            myAdapter = RecyclerAdapter()
                            layoutManager = manager
                            adapter = myAdapter
                        }
                    }
                }

                override fun onFailure(call: Call<List<PackageManager.Property>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }


        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }
}