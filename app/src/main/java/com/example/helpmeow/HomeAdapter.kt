package com.example.helpmeow

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeAdapter (val context: Context, val userlist: List<CatDataPostItem>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var catName: TextView
        var catBreed: TextView
        var catDescription: TextView
        var catGender: TextView
        var catUploader: TextView
        var catLocation: TextView
        var catPhoto: ImageView
        var catRole: TextView
        var catAction: ImageView


        init {
            catName = itemView.findViewById(R.id.catName)
            catBreed = itemView.findViewById(R.id.catBreed)
            catDescription = itemView.findViewById(R.id.catDescription)
            catGender = itemView.findViewById(R.id.catGender)
            catUploader = itemView.findViewById(R.id.userName)
            catLocation = itemView.findViewById(R.id.catLocation)
            catPhoto = itemView.findViewById(R.id.catPhoto)
            catRole = itemView.findViewById(R.id.userRole)
            catAction = itemView.findViewById(R.id.catAction)

        }

        fun openGmail() {
            val emailAddress = "recipient@example.com" // Replace with the recipient's email address
            val subject = "HelpMeow Adoption Request" // Replace with the desired subject
            val message = "-This is a message from HelpMeow app-" // Replace with the desired message content

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("mailto:$emailAddress?subject=${Uri.encode(subject)}&body=${Uri.encode(message)}")

            itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.cardview_home, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catData = userlist[position]
        holder.catName.text = catData.name
        holder.catBreed.text = catData.breed
        holder.catDescription.text = catData.description
        holder.catGender.text = catData.gender
        holder.catUploader.text = catData.upload_by_username
        holder.catLocation.text = catData.location
        Glide.with(context).load(catData.photo).into(holder.catPhoto)
        holder.catRole.text = catData.role

        holder.catAction.setOnClickListener {
            holder.openGmail()
        }
    }
}