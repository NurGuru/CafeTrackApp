package ru.nurguru.domain

class OrdersUseCase(private val repository: OrdersRepository) {

    fun getSumOfAllOrders(): Int = repository.getOrders().sumOf { it.sum }

    fun getAverageOrderSum():Int =getSumOfAllOrders()/ getOrdersCount()

    private fun getOrdersCount():Int=repository.getOrders().size
}

//<?xml version="1.0" encoding="utf-8"?>
//<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
//xmlns:app="http://schemas.android.com/apk/res-auto"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//android:background="@color/myGreyBlue">
//
//<androidx.recyclerview.widget.RecyclerView
//android:layout_width="0dp"
//android:layout_height="0dp"
//app:layout_constraintBottom_toBottomOf="parent"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toTopOf="parent" />
//
//<com.google.android.material.floatingactionbutton.FloatingActionButton
//android:id="@+id/btn_addProduct"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginTop="16dp"
//android:layout_marginEnd="16dp"
//android:backgroundTint="@color/myGrey"
//android:clickable="true"
//android:focusable="true"
//android:onClick="onClickNew"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintTop_toTopOf="parent"
//app:srcCompat="@drawable/ic_add" />
//</androidx.constraintlayout.widget.ConstraintLayout>