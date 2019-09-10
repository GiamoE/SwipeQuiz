package com.example.swipequiz.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swipequiz.R
import com.example.swipequiz.activities.dataClasses.Question
import kotlinx.android.synthetic.main.question_item.view.*

class QuestionAdapter(private val questions: List<Question>) :
RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    lateinit var context: Context

    // in the layoutinflater you can select which .xml to use (item_place in this case)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.question_item, parent, false)
        )
    }

    // get the size of the array
    override fun getItemCount(): Int {
        return questions.size
    }

    // bind the item in the array with the given position / int
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    // this is where you actually fill the layout
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question : Question) {
            itemView.tvQuestion.text = question.question
        }
    }

}