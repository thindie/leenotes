package com.example.thindie.leenotes.data.mapper

import com.example.thindie.leenotes.data.database.entities.CostDbModel
import com.example.thindie.leenotes.domain.entities.Cost

fun Cost.toCostDbModel(): CostDbModel{
    return CostDbModel(id, price, isBought)
}