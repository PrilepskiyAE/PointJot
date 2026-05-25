package com.prilepskiy.domain

import com.prilepskiy.data.database.entity.CategoryEntity
import com.prilepskiy.data.database.entity.NoteEntity
import com.prilepskiy.domain.model.CategoryModel
import com.prilepskiy.domain.model.NoteModel


//----Category----
val categoryModel = CategoryModel(
    categoryId = 1,
    categoryName = "Test Category"
)
val expectedEntity = CategoryEntity(
    categoryId = categoryModel.categoryId,
    categoryName = categoryModel.categoryName
)

val mockCategoryEntities = listOf(
    CategoryEntity(categoryId = 1, categoryName = "Category 1"),
    CategoryEntity(categoryId = 2, categoryName = "Category 2"),
    CategoryEntity(categoryId = 3, categoryName = "Category 3")
)

val expectedCategoryModels = listOf(
    CategoryModel(categoryId = 1, categoryName = "Category 1", isActive = true),
    CategoryModel(categoryId = 2, categoryName = "Category 2", isActive = false),
    CategoryModel(categoryId = 3, categoryName = "Category 3", isActive = false)
)

//-----note----
val noteModel = NoteModel(
    noteId = 1,
    pointId = 1,
    uri = "test uri",
    note= "Note 1",
    date = 0
)

val noteEntity = NoteEntity(
    noteId = 1,
    pointId = 1,
    uri = "test uri",
    note = "Note 1",
    date = 0
)

val mockNoteEntities = listOf(
    NoteEntity(
        noteId = 1,
        pointId = 1,
        uri = "test uri",
        note = "Note 1",
        date = 0
    ),
    NoteEntity(
        noteId = 2,
        pointId = 2,
        uri = "test uri",
        note = "Note 2",
        date = 0
    ),
    NoteEntity(
        noteId = 3,
        pointId = 3,
        uri = "test uri",
        note = "Note 3",
        date = 0
    )
)

val expectedNoteModels = listOf(
    NoteModel(
        noteId = 1,
        pointId = 1,
        uri = "test uri",
        note= "Note 1",
        date = 0
    ),
    NoteModel(
        noteId = 2,
        pointId = 2,
        uri = "test uri",
        note= "Note 2",
        date = 0
    ),
    NoteModel(
        noteId = 3,
        pointId = 3,
        uri = "test uri",
        note= "Note 3",
        date = 0
    )
)