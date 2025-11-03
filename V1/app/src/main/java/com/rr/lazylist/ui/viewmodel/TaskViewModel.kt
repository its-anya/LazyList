package com.rr.lazylist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rr.lazylist.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    val uiState = combine(
        repository.activeTasks,
        repository.completedTasks
    ) { active, completed ->
        TaskUiState(
            activeTasks = active,
            completedTasks = completed,
            isAalsiModeEnabled = _isAalsiModeEnabled.value,
            showVoiceInput = _showVoiceInput.value,
            lazySuggestion = _lazySuggestion.value
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskUiState()
    )

    private val _isAalsiModeEnabled = MutableStateFlow(false)
    private val _showVoiceInput = MutableStateFlow(false)
    private val _lazySuggestion = MutableStateFlow<String?>(null)

    init {
        checkLazySuggestions()
    }

    fun toggleAalsiMode() {
        _isAalsiModeEnabled.value = !_isAalsiModeEnabled.value
    }

    fun toggleVoiceInput() {
        _showVoiceInput.value = !_showVoiceInput.value
    }

    fun addTaskFromVoice(text: String, emotion: TaskEmotion? = null) {
        viewModelScope.launch {
            val task = repository.createTaskFromVoice(
                title = text,
                emotion = emotion,
                isAalsiMode = _isAalsiModeEnabled.value
            )
            repository.insertTask(task)
        }
    }

    fun addTask(title: String, description: String = "", priority: TaskPriority = TaskPriority.MEDIUM) {
        viewModelScope.launch {
            val task = TaskEntity(
                title = title,
                description = description,
                priority = priority,
                isAalsiMode = _isAalsiModeEnabled.value
            )
            repository.insertTask(task)
        }
    }

    fun markTaskComplete(id: Long) {
        viewModelScope.launch {
            repository.markAsCompleted(id)
        }
    }

    fun markTaskIncomplete(id: Long) {
        viewModelScope.launch {
            repository.markAsIncomplete(id)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun deleteAllCompleted() {
        viewModelScope.launch {
            repository.deleteAllCompleted()
        }
    }

    fun acceptLazySuggestion(suggestion: String) {
        addTaskFromVoice(suggestion)
        _lazySuggestion.value = null
    }

    fun dismissLazySuggestion() {
        _lazySuggestion.value = null
    }

    private fun checkLazySuggestions() {
        viewModelScope.launch {
            while (true) {
                kotlinx.coroutines.delay(300000) // Check every 5 minutes
                val suggestion = generateLazySuggestion()
                if (suggestion != null) {
                    _lazySuggestion.value = suggestion
                }
            }
        }
    }

    private suspend fun generateLazySuggestion(): String? {
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        val suggestion = when (hour) {
            in 6..8 -> "Morning routine: Brush teeth?"
            in 9..11 -> "Breakfast time! 🥐"
            in 12..13 -> "Lunch break! 🍛"
            in 14..15 -> "Afternoon productivity boost?"
            in 16..18 -> "Evening chill time?"
            in 19..21 -> "Dinner? 🍽️"
            in 21..23 -> "Bedtime routine: Brush teeth?"
            else -> null
        }
        return suggestion
    }

    companion object {
        fun provideFactory(repository: TaskRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TaskViewModel(repository) as T
                }
            }
    }
}

data class TaskUiState(
    val activeTasks: List<TaskEntity> = emptyList(),
    val completedTasks: List<TaskEntity> = emptyList(),
    val isAalsiModeEnabled: Boolean = false,
    val showVoiceInput: Boolean = false,
    val lazySuggestion: String? = null
)

