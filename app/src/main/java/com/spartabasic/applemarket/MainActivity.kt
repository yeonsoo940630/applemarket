package com.spartabasic.applemarket

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.spartabasic.applemarket.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val adapter = MyAdapter(dummyData)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val name: String = dummyData[position].name
                Toast.makeText(this@MainActivity," $name 선택!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onBackPressed() {
//        val dialog = AlertDialog.Builder(this).setTitle("종료").setMessage("정말 종료하시겠습니까?")
//
//        dialog.setPositiveButton("확인") { d, _ -> d.dismiss()
//            super.onBackPressed() }
//        dialog.setNegativeButton("취소") { d, _ -> d.dismiss()
//            }
//        dialog.show()
//
//        Log.d("MainActivity", "===onBackPressed ===")



        val builder = AlertDialog.Builder(this)
        builder.setTitle("종료")
        builder.setMessage("정말 종료하시겠습니까?")

        val listener = DialogInterface.OnClickListener { dialog, p0 ->
            if(p0 == DialogInterface.BUTTON_POSITIVE){
                dialog.dismiss()

                super.onBackPressed()

            }
        }
        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", null)
        builder.show()

    }




}