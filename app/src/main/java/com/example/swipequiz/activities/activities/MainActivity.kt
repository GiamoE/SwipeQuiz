package com.example.swipequiz.activities.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swipequiz.R
import com.example.swipequiz.activities.adapters.QuestionAdapter
import com.example.swipequiz.activities.dataClasses.Question
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class MainActivity : AppCompatActivity() {

    // create an ArrayList of the different Data items. Create a local adapter.
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        // attach the TouchHelper to my receyclerview
        rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuestions)

        // here I fill my questions array with the size of my array and the matching answers
        for (i in Question.QUESTIONS_TEXT.indices) {
            questions.add(Question(Question.QUESTIONS_TEXT[i], Question.QUESTION_ANSWERS[i]))
        }
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item. the if statements are there for both directions
            // normally a swipe action removes an item by adding the adapterpos to notifyitemchanged it stays.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.RIGHT) {
                    checkQuestionTrue(viewHolder.adapterPosition)
                }
                if (direction == ItemTouchHelper.LEFT) {
                    checkQuestionFalse(viewHolder.adapterPosition)
                }
                questionAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }
        }
        return ItemTouchHelper(callback)
    }

    // These methods check if the swipe action is correct or not.
    // We check the question at the adapterPosition with it's answer, since it's a boolean it's T or F.
    private fun checkQuestionTrue(position: Int) {
        if (questions[position].answer == TRUE) {
            Snackbar.make(tvSnackbar, "Correct the answer was: Correct", Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(tvSnackbar, "Incorrect the answer was: False", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun checkQuestionFalse(position: Int) {
        if (questions[position].answer == FALSE) {
            Snackbar.make(tvSnackbar, "Correct the answer was: False", Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(tvSnackbar, "Incorrect the answer was: False", Snackbar.LENGTH_SHORT).show()
        }
    }
}
