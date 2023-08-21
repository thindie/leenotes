package com.example.thindie.leenotes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.leenotes.domain.CostManager
import com.example.thindie.leenotes.domain.NoteManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val costManager: CostManager,
    private val noteManager: NoteManager,
) : ViewModel() {

    private val _id = MutableStateFlow(0L)
    val idState = _id.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = 0L
    )

    private val _summary: MutableStateFlow<ExpandableMenuState> = MutableStateFlow(
        ExpandableMenuState()
    )
    val summarySumCostNotes = _summary.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = ExpandableMenuState()
    )

    fun onClickConcreteNote(id: Long) {
        _id.value = id
    }

    fun onExpandMenu() {
        Log.d("SERVICE_TAG_VALUE", _summary.value.toString())
        viewModelScope.launch {
            val notesSum =   noteManager.getCurrentNotesCost()
            val costsSum =  costManager.getCostSum()
            _summary.value = ExpandableMenuState(sumNotes = notesSum, sumCosts = costsSum)

        }
    }

    data class ExpandableMenuState(
        val sumNotes: String = "",
        val sumCosts: String = "",
    )


}